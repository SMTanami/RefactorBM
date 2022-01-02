package edu.touro.mco152.bm.testing;

import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This class represents a Test for Disks. Upon instantiation, one can offer arguments to the constructor for specified
 * run parameters, or leave them blank for default test parameters.
 * <p>
 * Regarding the Command Pattern, classes that extend this abstract class act as receivers.
 */
public abstract class DiskTest
{
    protected int numOfMarks = 25;
    protected int numOfBlocks = 32;
    protected int blockSizeKb = 512;
    protected DiskRun.BlockSequence sequence = DiskRun.BlockSequence.SEQUENTIAL;

    /**
     * Returns a DiskTest instance with overridden test parameters
     * @param numOfMarks number of 'marks' - the duration of the test
     * @param numOfBlocks number of disk blocks to be used
     * @param blockSizeKb size of disk blocks to be used
     * @param sequence sequence of IO operations
     */
    public DiskTest(int numOfMarks, int numOfBlocks, int blockSizeKb, DiskRun.BlockSequence sequence)
    {
        this.numOfMarks = numOfMarks;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.sequence = sequence;
    }

    /**
     * Returns a DiskTest instance with default run parameters.
     */
    public DiskTest(){}

    /**
     * Performs test-reflected logic contained within this method.
     * @param bUI Benchmark UI for test to run on
     */
    public abstract void performTest(BenchmarkUI bUI);
}

