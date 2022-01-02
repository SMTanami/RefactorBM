package edu.touro.mco152.bm;

import edu.touro.mco152.bm.ui.Gui;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

import static edu.touro.mco152.bm.App.dataDir;

public class GUIBenchmarkUI extends SwingWorker<Boolean, DiskMark> implements BenchmarkUI
{
    HardwareTester hardwareTester;

    /**
     * Instantiates a GUIBenchmarkUI
     * @param hardwareTester Any class that extends the HardwareTester class (piece of hardware that would like to be tested)
     */
    public GUIBenchmarkUI(HardwareTester hardwareTester)
    {
        this.hardwareTester = hardwareTester;
    }

    /**
     * This overrides Swing's doInBackground method. The hardware in this class will begin its test here and will be run
     * by Swing.
     * @return true if the hardware test passed successfully
     * @throws Exception if the hardware test thrown an exception
     */
    @Override
    protected Boolean doInBackground() throws Exception
    {
        return hardwareTester.initiateTest(this);
    }

    /**
     * This method schedules this GUIBenchmarkUI for execution on a separate thread managed completely by Swing (IoC).
     */
    @Override
    public void beginWork()
    {
        execute();
    }

    @Override
    public void abort(boolean aborted)
    {
        cancel(aborted);
    }

    /**
     * Adds a PropertyChangeListener to the listener list. The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called as many times as it is added.
     * If listener is null, no exception is thrown and no action is taken.
     * @param pcl the propertyChangeListener to be added
     */
    @Override
    public void antePropertyChangeListener(PropertyChangeListener pcl)
    {
        addPropertyChangeListener(pcl);
    }

    /**
     * This method is not in use in this instance. What would be contained in this method is called by Swing in it's
     * own thread.
     * @param markList List<DiskMark> to be rendered by the UI instance
     */
    @Override
    public void render(List<DiskMark> markList)
    {

    }

    /**
     * This method is not in use in this instance. What would be contained in this method is called by Swing in it's
     * own thread.
     */
    @Override
    public void complete()
    {

    }

    /**
     * This method calls Swing's {@code .publish()} method. It 'reports' chunks of data to be rendered or processed by
     * the UI later on. Swing then processes the data passed in on its own.
     * @param diskMark A chunk of data that is meant to be presented on the UI for the user
     */
    @Override
    public void stageData(DiskMark diskMark)
    {
        publish(diskMark);
    }

    /**
     * @return true if user presses 'Cancel' button on the UI
     */
    @Override
    public boolean isAborted()
    {
        return isCancelled();
    }

    /**
     * This method informs the UI of its level of completion.
     * @param percentComplete integer representation of how complete the hardware's test is.
     */
    @Override
    public void setProgression(int percentComplete)
    {
        setProgress(percentComplete);
    }

    /**
     * This method is an override of Swing's {@code .process() method}. When {@code report()} calls {@code publish()},
     * data is staged to be processed on the UI to represent said data to the user.
     * @param markList {@code List<DiskMark>}'s that are meant to be rendered on the UI.
     */
    @Override
    public void process(List<DiskMark> markList)
    {
        markList.stream().forEach((dm) ->
        {
            if (dm.type == DiskMark.MarkType.WRITE)
            {
                Gui.addWriteMark(dm);
            }
            else
            {
                Gui.addReadMark(dm);
            }
        });
    }

    /**
     * This is an override of Swing's done method. Lets the UI know that the hardware test is complete.
     */
    @Override
    public void done()
    {
        if (App.autoRemoveData) {
            Util.deleteDirectory(dataDir);
        }
        App.state = App.State.IDLE_STATE;
        Gui.mainFrame.adjustSensitivity();
    }
}

