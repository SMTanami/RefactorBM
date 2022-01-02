package edu.touro.mco152.bm;

import edu.touro.mco152.bm.command.DiskReadCommand;
import edu.touro.mco152.bm.command.DiskWriteCommand;
import edu.touro.mco152.bm.ui.Gui;

import javax.swing.*;

/**
 * This class is a client with which one would use to run read and write tests for disks. The logic contained in this class
 * works in tandem with App.java to know what to instantiate.
 * <p>
 * This class now acts as the client in regards to the Command pattern. It will instantiate relevant commands
 * based on App.java requirements.
 */
public class DiskTester extends HardwareTester
{
    private final DiskInvoker invoker = new DiskInvoker();

    /**
     * This method creates the proper commands necessary to work with {@code App.java}. Once done, the method relies on
     * the invoker to invoke those commands and execute them.
     * @param bUI any implementation of the BenchmarkUI interface that would like to work with this instance of
     *            HardwareTester
     * @return true if the Test was successfully ran
     */
    @Override
    public Boolean initiateTest(BenchmarkUI bUI)
    {
        enqueueProperCommands(bUI);
        invoker.invokeCommands();

        Gui.updateLegend();

        if (App.autoReset) {
            App.resetTestData();
            Gui.resetTestData();
        }

        if (App.readTest && App.writeTest && !bUI.isAborted()) {
            JOptionPane.showMessageDialog(Gui.mainFrame,
                    """
                            For valid READ measurements please clear the disk cache by
                            using the included RAMMap.exe or flushmem.exe utilities.
                            Removable drives can be disconnected and reconnected.
                            For system drives use the WRITE and READ operations\s
                            independently by doing a cold reboot after the WRITE""",
                    "Clear Disk Cache Now", JOptionPane.PLAIN_MESSAGE);
        }

        App.nextMarkNumber += App.numOfMarks;

        return true;
    }

    /**
     * Enqueues commands based on the user's selection while the main application is running.
     * @param bUI any implementation of the BenchmarkUI interface that would like to work with this instance of
     *            HardwareTester
     */
    private void enqueueProperCommands(BenchmarkUI bUI)
    {
        if (App.readTest)
        {
            DiskReadCommand readCommand = new DiskReadCommand(bUI, App.numOfMarks, App.numOfBlocks, App.blockSizeKb, App.blockSequence);
            invoker.enqueueCommand(readCommand);
        }

        if (App.writeTest)
        {
            DiskWriteCommand writeCommand = new DiskWriteCommand(bUI, App.numOfMarks, App.numOfBlocks, App.blockSizeKb, App.blockSequence);
            invoker.enqueueCommand(writeCommand);
        }
    }
}
