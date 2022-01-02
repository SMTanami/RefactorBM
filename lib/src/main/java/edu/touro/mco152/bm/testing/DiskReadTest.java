package edu.touro.mco152.bm.testing;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.touro.mco152.bm.App.*;
import static edu.touro.mco152.bm.DiskMark.MarkType.READ;

/**
 * This class represents a Test for Disks. Upon instantiation, one can offer arguments to the constructor for specified
 * run parameters, or leave them blank for default test parameters.
 * <p>
 * This is a concrete DiskTest class. It's job is to perform Read Tests for Disks
 */
public class DiskReadTest extends DiskTest
{
    private int rUnitsComplete;
    private int rUnitsTotal;
    private int startFileNum;
    private int blockSize;
    private float percentComplete;

    private byte[] blockArr;
    private final DiskMark rMark = new DiskMark(READ);
    private final DiskRun run = new DiskRun(DiskRun.IOMode.READ, sequence);

    /**
     * Returns a DiskReadTest instance with overridden test parameters.
     * @param numOfMarks number of 'marks' - the duration of the test
     * @param numOfBlocks number of disk blocks to be used
     * @param blockSizeKb size of disk blocks to be used
     * @param sequence sequence of IO operations
     */
    public DiskReadTest(int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence)
    {
        super(numOfMarks, numOfBlocks, blockSizeKb, sequence);
    }

    /**
     * Returns a DiskReadInstance with default test parameters.
     */
    public DiskReadTest() { super(); }

    /**
     * This method begins a Read Test for a Disk and presents data accumulated overtime onto the given Benchmark UI.
     * @param bUI Benchmark UI for test to run on
     */
    @Override
    public void performTest(BenchmarkUI bUI)
    {
        announceStartToConsole();
        initializeVariables();

        setupDiskRunParams(run);

        msg("disk info: (" + run.getDiskInfo() + ")");

        Gui.chartPanel.getChart().getTitle().setVisible(true);
        Gui.chartPanel.getChart().getTitle().setText(run.getDiskInfo());

        for (int m = startFileNum; m < startFileNum + numOfMarks && !bUI.isAborted(); m++) {

            if (App.multiFile) {
                testFile = new File(dataDir.getAbsolutePath()
                        + File.separator + "testdata" + m + ".jdm");
            }

            rMark.setMarkNum(m);
            long startTime = System.nanoTime();
            long totalBytesReadInMark = 0;

            try {
                try (RandomAccessFile rAccFile = new RandomAccessFile(testFile, "r")) {
                    for (int b = 0; b < numOfBlocks; b++) {
                        if (this.sequence == DiskRun.BlockSequence.RANDOM) {
                            int rLoc = Util.randInt(0, numOfBlocks - 1);
                            rAccFile.seek((long) rLoc * blockSize);
                        } else {
                            rAccFile.seek((long) b * blockSize);
                        }
                        rAccFile.readFully(blockArr, 0, blockSize);
                        totalBytesReadInMark += blockSize;
                        rUnitsComplete++;
                        percentComplete = (float) rUnitsComplete / (float) rUnitsTotal * 100f;
                        bUI.setProgression((int) percentComplete);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            long endTime = System.nanoTime();
            long elapsedTimeNs = endTime - startTime;
            double sec = (double) elapsedTimeNs / (double) 1000000000;
            double mbRead = (double) totalBytesReadInMark / (double) MEGABYTE;
            rMark.setBwMbSec(mbRead / sec);
            msg("m:" + m + " READ IO is " + rMark.getBwMbSec() + " MB/s    "
                    + "(MBread " + mbRead + " in " + sec + " sec)");

            App.updateMetrics(rMark);
            bUI.stageData(rMark);
            trackRunStats(run);
        }
    }

    /**
     * Stores statistics of the given DiskRun.
     * @param run DiskRun to continuously update
     */
    private void trackRunStats(DiskRun run)
    {
        run.setRunMax(rMark.getCumMax());
        run.setRunMin(rMark.getCumMin());
        run.setRunAvg(rMark.getCumAvg());
        run.setEndTime(new Date());
    }

    /**
     * Setup method for a DiskRun that handles establishing it's internal variables
     * @param run DiskRun to use throughout the test to track run statistics with
     */
    private void setupDiskRunParams(DiskRun run)
    {
        run.setNumMarks(numOfMarks);
        run.setNumBlocks(numOfBlocks);
        run.setBlockSize(blockSizeKb);
        run.setTxSize(App.targetTxSizeKb());
        run.setDiskInfo(Util.getDiskInfo(dataDir));
    }

    /**
     * Initializes local variables to aid {@code performTest()} in storing and using data
     */
    private void initializeVariables()
    {
        rUnitsComplete = 0;

        rUnitsTotal = numOfBlocks * numOfMarks;

        blockSize = blockSizeKb * KILOBYTE;
        blockArr = new byte [blockSize];

        for (int b = 0; b < blockArr.length; b++) {
            if (b % 2 == 0) {
                blockArr[b]=(byte)0xFF;
            }
        }

        startFileNum = App.nextMarkNumber;
    }

    /**
     * Sends a message to the console relaying the state of the run parameters to the user
     */
    private void announceStartToConsole()
    {
        msg("Running readTest " + true);
        msg("num files: " + numOfMarks + ", num blks: " + numOfBlocks
                + ", blk size (kb): " + blockSizeKb + ", blockSequence: " + blockSequence);
    }

    /**
     * @return This test's DiskRun object.
     */
    public DiskRun getRun()
    {
        return run;
    }
}

