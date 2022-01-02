package edu.touro.mco152.bm.command;

import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.testing.DiskReadTest;
import edu.touro.mco152.bm.testing.DiskTest;

/**
 * DiskReadCommand implements Command and is a Concrete Command Object. This command initiates a ReadTest for Disks by
 * instantiating a DiskReadTest and calling its {@link DiskReadTest#performTest(BenchmarkUI bUI)} method
 */
public class DiskReadCommand implements Command
{
    private final BenchmarkUI bUI;
    private final DiskReadTest readTest;

    /**
     * Returns a command with the given parameters and that is ready for execution
     * @param bUI Benchmark User Interface the test should be working in tandem with this command
     * @param numOfMarks number of 'marks'. i.e. the duration of the test
     * @param numOfBlocks number of disk blocks to be used
     * @param blockSizeKb size of disk blocks to be used
     * @param sequence sequence of IO operations
     */
    public DiskReadCommand(BenchmarkUI bUI, int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence)
    {
        this.bUI = bUI;
        readTest = new DiskReadTest(numOfMarks, numOfBlocks, blockSizeKb, sequence);
    }

    /**
     * Begins execution of this commands {@link DiskTest} object.
     */
    @Override
    public void execute()
    {
        readTest.performTest(bUI);
    }

    /**
     * @return this commands {@link DiskTest} object
     */
    public DiskReadTest getReadTest()
    {
        return readTest;
    }
}

