import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.DiskTester;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.*;

/**
 * DiskTesterTest is a test class that ensures DiskTester is able to be used in any {@code BenchmarkUI}
 * implementation; that it runs as it should as a standalone class.
 */
public class DiskTesterTest implements BenchmarkUI
{
    int progress;
    DiskTester diskTester = new DiskTester();
    Set<Integer> progressReports = new HashSet<>();
    List<DiskMark> markList = new ArrayList<>();

    /**
     * This method is called once before we begin testing methods and it minimally set's up app to allow DiskTester to run
     */
    @BeforeAll
    static void initApp()
    {
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();
        System.out.println(App.getConfigString());
        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference
        App.dataDir = new File(App.locationDir.getAbsolutePath()+File.separator+App.DATADIRNAME);

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }

    /**
     * Runs {@code disktester.InitiateTest(this)} before each method
     * @throws Exception if DiskTester.initiateTest() throws an exception
     */
    @BeforeEach
    @Override
    public void beginWork()
    {
        diskTester.initiateTest(this);
    }

    /**
     * This test employs a HashSet to ensure that more than one progress report is being returned from DiskTester,
     * that the reports sent from DiskTester are not the same, and that they are all valid.
     * <p>
     * If the test passes, we can at least be sure that DiskTester is getting some things done.
     */
    @Test
    public void progressReportsTest()
    {
        //Assert
        for (int progressReport : progressReports)
            if (progressReport < 0 || progressReport > 100)
                Assert.fail("Progress Report value is invalid: " + progressReport);

        Assert.isTrue(progressReports.size() > 1,"All progress reports return the same value, the" +
                "benchmark is not running properly");
    }

    /**
     * This test ensures that after DiskTester has completed its benchmark, the result of its progress is '100'. With
     * the help of {@code void progressReportsTest()}, we can be sure that the result is not a fluke and progress incremented
     * from 0 to 100 in some fashion.
     * <p>
     * If this test passes, we can be sure that the DiskTester runs successfully and is ready to be implemented in any
     * {@code BenchmarkUI} implementation.
     */
    @Test
    public void benchmarkTest()
    {
        //Assert
        Assert.isTrue(progress == 100, "Progress value was not 100 and therefore the benchmark was not" +
                " complete");
    }

    /**
     * This test ensures that all data that is contained within each DiskMark used in the benchmark is valid. All double
     * and integer values must be greater than zero to show actual benchmarking taking place.
     */
    @Test
    public void diskMarkTest()
    {
        //Act + Assert
        for (DiskMark diskMark : markList)
        {
            double bwMbSec = diskMark.getBwMbSec();
            double cumAvg = diskMark.getCumAvg();
            double cumMax = diskMark.getCumMax();
            double cumMin = diskMark.getCumMin();
            int markNum = diskMark.getMarkNum();

            Assert.isFalse(bwMbSec <= 0, "BwMbSec was less than or equal to zero: " + bwMbSec);
            Assert.isFalse(cumAvg <= 0, "Cumulative Average was less than or equal to zero: " + cumAvg);
            Assert.isFalse(cumMax <= 0, "Cumulative maximum was less than or equal to zero: " + cumMax);
            Assert.isFalse(cumMin <= 0, "Cumulative minimum was less than or equal to zero: " + cumMin);
            Assert.isFalse(markNum <= 0, "Mark number was less than or equal to zero: " + markNum);
        }
    }

    /**
     * This method receives a value from DiskTester's test initiation method, checks the values, and stores them in a
     * {@code Set<Integer> progressReports} for later testing.
     * @param percentComplete an integer value representation of DiskTester's {@code .initiateTest()} progress
     */
    @Override
    public void setProgression(int percentComplete)
    {
        if (percentComplete < 0 || percentComplete > 100)
            throw new IllegalArgumentException("Percentage complete cannot be less than 0 or greater than 100");
        if (percentComplete == progress)
            return;

        progress = percentComplete;
        progressReports.add(progress);
    }

    /**
     * This method simply takes in diskMarks from the running benchmark and stores it for evaluation in unit testing.
     * @param diskMark diskMark that contains data meant to be staged (stored for testing)
     */
    @Override
    public void stageData(DiskMark diskMark)
    {
        markList.add(diskMark);
    }

    /**
     * Method always returns false as we never want to abort DiskTester's test when testing it.
     * @return {@code false}
     */
    @Override
    public boolean isAborted()
    {
        return false;
    }

    //---------------------Unused Methods------------------------------

    @Override
    public void render(List<DiskMark> markList)
    {}

    @Override
    public void complete()
    {

    }

    @Override
    public void abort(boolean b)
    {

    }

    @Override
    public void antePropertyChangeListener(PropertyChangeListener propertyChangeListener)
    {

    }
}

