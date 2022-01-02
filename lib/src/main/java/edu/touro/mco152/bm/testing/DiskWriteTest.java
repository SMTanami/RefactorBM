package edu.touro.mco152.bm.testing;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.observe.Observable;
import edu.touro.mco152.bm.observe.Observer;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.touro.mco152.bm.App.*;
import static edu.touro.mco152.bm.DiskMark.MarkType.WRITE;

/**
 * This class represents a Test for Disks. Upon instantiation, one can offer arguments to the constructor for specified
 * run parameters, or leave them blank for default test parameters.
 * <p>
 * This is a concrete DiskTest class. It's job is to perform Write Tests for Disks
 */
public class DiskWriteTest extends DiskTest implements Observable
{
    private final Set<Observer> observers = new HashSet<>();

    private int wUnitsComplete;
    private int wUnitsTotal;
    private int startFileNum;
    private int blockSize;
    private float percentComplete;

    private byte[] blockArr;
    private final DiskMark wMark = new DiskMark(WRITE);
    private final DiskRun run = new DiskRun(DiskRun.IOMode.WRITE, sequence);

    /**
     * Returns a DiskWriteTest instance with overridden test parameters.
     * @param numOfMarks number of 'marks' - the duration of the test
     * @param numOfBlocks number of disk blocks to be used
     * @param blockSizeKb size of disk blocks to be used
     * @param sequence sequence of IO operations
     */
    public DiskWriteTest(int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence)
    {
        super(numOfMarks, numOfBlocks, blockSizeKb, sequence);
    }

    /**
     * Returns a DiskReadInstance with default test parameters.
     */
    public DiskWriteTest() { super(); }

    /**
     * This method begins a Write Test for a Disk and presents data accumulated overtime onto the given Benchmark UI.
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

        if (!App.multiFile) {
            testFile = new File(dataDir.getAbsolutePath() + File.separator + "testdata.jdm");
        }

        for (int m = startFileNum; m < startFileNum + numOfMarks && !bUI.isAborted(); m++) {

            if (App.multiFile) {
                testFile = new File(dataDir.getAbsolutePath()
                        + File.separator + "testdata" + m + ".jdm");
            }

            wMark.setMarkNum(m);
            long startTime = System.nanoTime();
            long totalBytesWrittenInMark = 0;

            String mode = "rw";
            if (App.writeSyncEnable) {
                mode = "rwd";
            }

            try {
                try (RandomAccessFile rAccFile = new RandomAccessFile(testFile, mode)) {
                    for (int b = 0; b < numOfBlocks; b++) {
                        if (this.sequence == DiskRun.BlockSequence.RANDOM) {
                            int rLoc = Util.randInt(0, numOfBlocks - 1);
                            rAccFile.seek((long) rLoc * blockSize);
                        } else {
                            rAccFile.seek((long) b * blockSize);
                        }
                        rAccFile.write(blockArr, 0, blockSize);
                        totalBytesWrittenInMark += blockSize;
                        wUnitsComplete++;
                        percentComplete = (float) wUnitsComplete / (float) wUnitsTotal * 100f;
                        bUI.setProgression((int) percentComplete);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            long endTime = System.nanoTime();
            long elapsedTimeNs = endTime - startTime;
            double sec = (double) elapsedTimeNs / (double) 1000000000;
            double mbWritten = (double) totalBytesWrittenInMark / (double) MEGABYTE;
            wMark.setBwMbSec(mbWritten / sec);
            msg("m:" + m + " write IO is " + wMark.getBwMbSecAsString() + " MB/s     "
                    + "(" + Util.displayString(mbWritten) + "MB written in "
                    + Util.displayString(sec) + " sec)");
            App.updateMetrics(wMark);
            bUI.stageData(wMark);
            trackRunStats(run);
        }
        notifyObservers();
    }

    /**
     * Stores statistics of the given DiskRun.
     * @param run DiskRun to continuously update
     */
    private void trackRunStats(DiskRun run)
    {
        run.setRunMax(wMark.getCumMax());
        run.setRunMin(wMark.getCumMin());
        run.setRunAvg(wMark.getCumAvg());
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
        wUnitsComplete = 0;

        wUnitsTotal = numOfBlocks * numOfMarks;

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
        msg("Running readTest " + false);
        msg("num files: " + numOfMarks + ", num blks: " + numOfBlocks
                + ", blk size (kb): " + blockSizeKb + ", blockSequence: " + blockSequence);
    }

    /**
     * Initializes local variables to aid {@code performTest()} in storing and using data
     */
    public DiskRun getRun()
    {
        return run;
    }

    /**
     * @param observer observer to register to the observable
     */
    @Override
    public void registerObserver(Observer observer)
    {
        observers.add(observer);
    }

    /**
     * @param observer observer to unregister from the observable
     */
    @Override
    public void unregisterObserver(Observer observer)
    {
        observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update(run);
        }
    }
}
