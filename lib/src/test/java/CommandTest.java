import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.DiskInvoker;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.command.Command;
import edu.touro.mco152.bm.command.DiskReadCommand;
import edu.touro.mco152.bm.command.DiskWriteCommand;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.*;

/**
 * This test class is used to test command pattern implementation. In other words, this test class ensures
 * whether or not one can run tests on disks after command pattern implementation and without relying on App.java.
 */
public class CommandTest implements BenchmarkUI
{
    private final int numOfMarks = 25;
    private final int numOfBlocks = 128;
    private final int blockSizeKb = 2048;
    private final DiskRun.BlockSequence blockSequence = DiskRun.BlockSequence.SEQUENTIAL;
    private final DiskInvoker invoker = new DiskInvoker();

    private int progress;
    Set<Integer> progressReports = new HashSet<>();
    List<DiskMark> markList = new ArrayList<>();

    //-----Util Methods for Tests-----
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
     * This method employs a HashSet to ensure that, from a specified test: more than one progress report is being submitted,
     * that the reports are not the same, and that they are all valid.
     * <p>
     * If the test passes, we can at least be sure that the test is getting some things done.
     */
    @AfterEach
    public void incrementalProgressDuringCommandTest()
    {
        //Assert
        for (int progressReport : progressReports)
            if (progressReport < 0 || progressReport > 100)
                Assert.fail("Progress Report value is invalid: " + progressReport);

        Assert.isTrue(progressReports.size() > 1, "All progress reports return the same value, the" +
                "benchmark is not running properly");
    }

    /**
     * This method ensures that all data that is contained within each DiskMark used in the benchmark is valid. All double
     * and integer values must be greater than zero to show actual benchmarking taking place.
     */
    @AfterEach
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


    //------Tests-------
    /**
     * This test ensures that a read command is able to be instantiated with customizable test parameters and executes
     * successfully; it's test parameters are field variables in this test class and they are used for the command's customization.
     * <p>
     * It ensures that the progress of the command is '100%' upon completion, while {@link #incrementalProgressDuringCommandTest()}
     * ensures that things were running smoothly throughout execution and {@link #diskMarkTest()} ensures that all data
     * generated throughout the test proves it's validity.
     */
    @Test
    public void readCommandTest()
    {
        //Arrange
        Command readCommand = new DiskReadCommand(this, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);
        invoker.enqueueCommand(readCommand);

        //Act
        invoker.invokeCommands();

        //Assert
        Assert.isTrue(progress == 100, "Progress was not 100% after command's execution. Test failed.");
    }

    /**
     * This test ensures that a write command is able to be instantiated with customizable test parameters and executes
     * successfully; it's test parameters are field variables in this test class and they are used for the command's customization.
     * <p>
     * It ensures that the progress of the command is '100%' upon completion, while {@link #incrementalProgressDuringCommandTest()}
     * ensures that things were running smoothly throughout execution and {@link #diskMarkTest()} ensures that all data
     * generated throughout the test proves it's validity.
     */
    @Test
    public void writeCommandTest()
    {
        //Arrange
        Command writeCommand = new DiskWriteCommand(this, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);
        invoker.enqueueCommand(writeCommand);

        //Act
        invoker.invokeCommands();

        //Assert
        Assert.isTrue(progress == 100, "Progress was not 100% after commands execution. Test failed.");
    }


    //------Overrides-------
    /**
     * This method receives a value from the command while it is executing it's task, checks the values, and stores them in a
     * {@code Set<Integer> progressReports} for later testing.
     * @param percentComplete an integer value representation of the commands progress
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


    //------Unused Overrides---------
    @Override
    public void beginWork()
    {

    }

    @Override
    public void render(List<DiskMark> markList)
    {

    }

    @Override
    public void complete()
    {

    }

    @Override
    public void abort(boolean isAborted)
    {

    }

    @Override
    public void antePropertyChangeListener(PropertyChangeListener propertyChangeListener)
    {

    }
}

