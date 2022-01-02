package edu.touro.mco152.bm.observe;

/**
 * An concrete observer is a class that needs to observe a subject in order to know when to perform a task. Classes that
 * require this behavior should implement this interface.
 */
public interface Observer
{
    /**
     * Lets this object know that its observable has completed it's task and executes subsequent logic.
     */
    void update();

    /**
     * Lets this object know that its observable has completed it's task and executes subsequent logic.
     */
    void update(Object o);
}
