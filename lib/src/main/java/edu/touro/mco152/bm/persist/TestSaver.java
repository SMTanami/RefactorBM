package edu.touro.mco152.bm.persist;

import edu.touro.mco152.bm.observe.Observer;
import jakarta.persistence.EntityManager;

/**
 * An Observer implementation that, when notified, saves tests (benchmarks) into the system's database
 */
public class TestSaver implements Observer
{
    private final EntityManager em = EM.getEntityManager();

    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {

    }

    /**
     * {@inheritDoc} The logic in this context is uploading the benchmarks DiskRun to the system's database.
     */
    @Override
    public void update(Object o)
    {
        if (o instanceof DiskRun)
        {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }
    }
}
