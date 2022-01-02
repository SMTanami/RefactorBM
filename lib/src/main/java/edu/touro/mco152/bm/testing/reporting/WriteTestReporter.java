package edu.touro.mco152.bm.testing.reporting;

import edu.touro.mco152.bm.observe.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * This class is currently not in use but can be implemented later if necessary.
 * An Observer that reports on WriteTests, alarming the project manager if the report does not meat company standards.
 */
public class WriteTestReporter extends TestReporter implements Observer
{

    @Override
    public void update()
    {
        //TODO Implement when necessary
    }

    @Override
    public void update(Object o)
    {
        //TODO Implement when necessary
    }

    @Override
    protected boolean assertBenchmarkResults(DiskRun run)
    {
        return false; //TODO Implement when necessary
    }
}

