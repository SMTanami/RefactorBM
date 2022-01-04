package observerTesting;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.BenchmarkUI;
import edu.touro.mco152.bm.DiskInvoker;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.command.DiskReadCommand;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.*;

public class ObserverTest implements BenchmarkUI
{
    private final int numOfMarks = 25;
    private final int numOfBlocks = 128;
    private final int blockSizeKb = 2048;
    private final DiskRun.BlockSequence blockSequence = DiskRun.BlockSequence.SEQUENTIAL;
    private final DiskInvoker invoker = new DiskInvoker();

    private final TestObserver testObserver = new TestObserver();

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
     * This method imitates the actions of a client. It creates a command to be invoked by this class and registers
     * the class's TestObserver attribute as an observer.
     */
    private void createCommandAndRegisterTestObserver()
    {
        DiskReadCommand command = new DiskReadCommand(this, numOfMarks, numOfBlocks, blockSizeKb, blockSequence);
        command.getReadTest().registerObserver(testObserver);
        invoker.enqueueCommand(command);
    }


    //------Tests-------
    /**
     * Tests to see if the registered Observer (this class's TestObserver attribute) was notified of the benchmarks
     * completion.
     */
    @Test
    public void TestObserverFlag()
    {
        //Arrange
        createCommandAndRegisterTestObserver();

        //Act
        invoker.invokeCommands();

        //Assert
        Assert.isTrue(testObserver.isObserved(), "Observed flag in TestObserver was false and therefore never updated," +
                " test failed subsequently.");
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
    public void stageData(DiskMark diskMark)
    {

    }

    @Override
    public boolean isAborted()
    {
        return false;
    }

    @Override
    public void setProgression(int percentComplete)
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

