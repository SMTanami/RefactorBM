package edu.touro.mco152.bm;

/**
 * Hardware Tester is the parent class of all sub-entities that want to be tested in BadBM. This abstraction is made
 * so that any class that extends HardwareTester can have a BenchmarkUI passed into it to allow for benchmarking.
 */
public abstract class HardwareTester
{
    /**
     * @param bUI any implementation of the BenchmarkUI interface that would like to work with this instance of
     * HardwareTester
     * @return true if the method executed successfully
     * @throws Exception if the test method throws an exception
     */
    public abstract Boolean initiateTest(BenchmarkUI bUI);
}


