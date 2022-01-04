package observerTesting;

import edu.touro.mco152.bm.observe.Observer;

/**
 * This object acts as a flagging observer. It contains no real logic, and is only meant to be used in unit tests.
 * The job of this object is to be registered unto an observable and flip it's own 'observed' boolean when notified / updated.
 */
public class TestObserver implements Observer
{
    private boolean observed = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {
        observed = true;
    }

    /**
     * @param o The object necessary for the implementing class to execute properly.
     */
    @Override
    public void update(Object o)
    {
        observed = true;
    }

    /**
     * @return true if this observer was successfully registered unto and notified by an observable object
     */
    public boolean isObserved()
    {
        return observed;
    }
}

