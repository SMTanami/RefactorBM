package edu.touro.mco152.bm.testing.reporting;
import edu.touro.mco152.bm.observe.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * An Observer that reports on ReadTests, alarming the project manager if the report does not meat company standards.
 */
public class ReadTestReporter extends TestReporter implements Observer
{
    @Override
    public void update()
    {

    }

    /**
     * Asserts the DiskRun is up to standards via {@link #assertBenchmarkResults(DiskRun)}. If the DiskRun shows unimpressive
     * results, the method will update the product manager via slack.
     * @param o DiskRun from the DiskReadTest
     */
    @Override
    public void update(Object o)
    {
        if (o instanceof DiskRun)
        {
            if (assertBenchmarkResults((DiskRun)o))
                slackManager.postMsg2OurChannel("Test Report - read benchmark completed with iteration max time " +
                        decimalFormatter.format(calculateIterationMaxTimePercent((DiskRun)o)) + "% greater than benchmarks average iteration time" +
                        " when only a 3% difference is allowed.");
        }
    }

    /**
     * Asserts 'rules' to be followed by the DiskRun.
     * @param run DiskRun for the method to enforce rules over
     * @return true if the DiskRun shows unimpressive results
     */
    @Override
    protected boolean assertBenchmarkResults(DiskRun run)
    {
        return calculateIterationMaxTimePercent(run) > 3;
    }

    /**
     * @param run DiskRun that contains the relevant information
     * @return the percentage difference between the max iteration time vs. the average iteration time
     */
    private double calculateIterationMaxTimePercent(DiskRun run)
    {
        return 100 - ((run.getRunAvg() / run.getRunMax()) * 100);
    }
}

