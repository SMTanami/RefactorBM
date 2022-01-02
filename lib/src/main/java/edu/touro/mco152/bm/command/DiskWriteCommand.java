package edu.touro.mco152.bm.command;

import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.testing.DiskTest;
import edu.touro.mco152.bm.testing.DiskWriteTest;

/**
 * DiskWriteCommand implements Command and is a Concrete Command Object. This command initiates a WriteTest for Disks by
 * instantiating a DiskWriteTest and calling its {@link DiskWriteTest#performTest(BenchmarkUI bUI)} method
 */
public class DiskWriteCommand implements Command
{
    private final BenchmarkUI bUI;
    private final DiskWriteTest writeTest;

    /**
     * Returns a command with the given parameters and that is ready for execution
     * @param bUI Benchmark User Interface the test should be working in tandem with this command
     * @param numOfMarks number of 'marks'. i.e. the duration of the test
     * @param numOfBlocks number of disk blocks to be used
     * @param blockSizeKb size of disk blocks to be used
     * @param sequence sequence of IO operations
     */
    public DiskWriteCommand(BenchmarkUI bUI, int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence)
    {
        this.bUI = bUI;
        writeTest = new DiskWriteTest(numOfMarks, numOfBlocks, blockSizeKb, sequence);
    }

    /**
     * Begins execution of this commands {@link DiskTest} object.
     */
    @Override
    public void execute()
    {
        writeTest.performTest(bUI);
    }

    /**
     * @return this commands {@link DiskTest} object
     */
    public DiskWriteTest getWriteTest()
    {
        return writeTest;
    }
}


