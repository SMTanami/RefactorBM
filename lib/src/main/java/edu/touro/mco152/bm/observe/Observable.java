package edu.touro.mco152.bm.observe;

/**
 * All classes that are to be observed by concrete observers should implement this interface. This interface contracts
 * classes to contain these methods so clients can register, unregister, and notify observers for the implemented class.
 */
public interface Observable
{
    /**
     * @param observer observer to register to the observable
     */
    void registerObserver(Observer observer);

    /**
     * @param observer observer to unregister from the observable
     */
    void unregisterObserver(Observer observer);

    /**
     * Notifies registered observers of this subjects completion
     */
    void notifyObservers();
}