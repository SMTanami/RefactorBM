import edu.touro.mco152.bm.persist.EM;
import jakarta.persistence.EntityManager;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;

public class EMTest
{
    /**
     * This method tests for the (P)erformance in Right-BICEP and (T)ime in CORRECT. It utilizes my own Timer class to
     * test how much time it takes to instantiate an EntityManager, gauging whether it takes too long and is therefore
     * costly/expensive as well as happens in a timely manner as it should.
     * @throws InterruptedException If the Timer used within the test is interrupted.
     */
    @Test
    public void getEMTest() throws InterruptedException
    {
        //Arrange
        Timer timer = new Timer();
        long timeTakenInNanoseconds;
        double timeTakenInSeconds = 0;

        //Act
        timer.start();
        EntityManager em = EM.getEntityManager();
        timeTakenInNanoseconds = timer.stop();

        timeTakenInSeconds = (double) timeTakenInNanoseconds / 1_000_000_000;

        //Assert
        Assert.isTrue(em != null && timeTakenInSeconds < 2, "Instantiating an EntityManager took more than the " +
                "allotted 2 seconds, method is too slow.\n Time Taken: " + timeTakenInSeconds);
    }
}
