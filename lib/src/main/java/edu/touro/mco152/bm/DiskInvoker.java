package edu.touro.mco152.bm;

import edu.touro.mco152.bm.command.Command;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * This class is an invoker for DiskTests. Commands can be enqueued by this class and those commands can be executed by
 * using this class's {@link DiskInvoker#invokeCommands()} method. How those commands are invoked, tracked, handled, undone,
 * etc. is this class's responsibility alone.
 */
public class DiskInvoker
{
    private final Queue<Command> commandsToExecute = new ArrayDeque<>();
    private Command currentCommand;

    /**
     * Invokes all commands that were previously enqueued.
     */
    public void invokeCommands()
    {
        for (Command c : commandsToExecute)
        {
            currentCommand = c;
            commandsToExecute.poll();
            c.execute();
        }
    }

    /**
     * @param c Concrete Command to enqueue for later execution
     * @return true if the command was enqueued successfully
     */
    public boolean enqueueCommand(Command c)
    {
        return commandsToExecute.offer(c);
    }
}

