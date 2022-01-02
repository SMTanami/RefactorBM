package edu.touro.mco152.bm.testing.reporting;

import edu.touro.mco152.bm.SlackManager;
import edu.touro.mco152.bm.observe.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A base class for Reporter Observer classes to implement. It enforces all sub-classes to implement the
 * {@link #assertBenchmarkResults(DiskRun)} which enforces 'rules' for benchmarks.
 */
public abstract class TestReporter implements Observer
{
    protected final SlackManager slackManager = new SlackManager("GoodBM");
    protected final NumberFormat decimalFormatter = new DecimalFormat("#0.00");

    /**
     * Ensures that a DiskRun from a test (benchmark) is stable by enforcing conditions the run must pass.
     * @param run DiskRun for the method to enforce rules over
     * @return true if the DiskRun abides to all enforced rules, false otherwise
     */
    protected abstract boolean assertBenchmarkResults(DiskRun run);
}

