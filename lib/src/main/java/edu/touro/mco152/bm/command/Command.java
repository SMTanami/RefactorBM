package edu.touro.mco152.bm.command;

/**
 * Represents a Command object who's job is to execute a task
 */
public interface Command
{
    /**
     * The logic for a Concrete Command to execute
     */
    void execute();
}
