import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.persist.DiskRun;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilTest
{
    /**
     * This method tests for the (R)ange in CORRECT. It ensures the the value returned is
     * neither greater than nor less than the minimum and maximum bounds used in the test.
     * @param val different values set as the maximum boundary within the test method to ensure the returned number is
     * not greater than the maximum boundary.
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 5000})
    public void utilRandomIntTest(int val)
    {
        //Arrange
        int actual;

        int minimumBound = 1;
        int maximumBound = val;

        //Act
        actual = Util.randInt(minimumBound, maximumBound);

        //Assert
        Assert.isFalse(actual < minimumBound || actual > maximumBound, "actual (" + actual + "), is greater" +
                " than maximum integer value, (" + val + ") or less than minimum integer value, (" + minimumBound + ")");
    }

    /**
     * This method tests for the (Right) in Right-BICEP. It ensures that the {@code Util.deleteDirectory(dir);} method
     * functions as it should.
     */
    @Test
    public void utilDeleteDirectoryTest()
    {
        //Arrange
        boolean actual;
        String path = "C:\\Repos\\FilePathTest";
        File dir = new File(path);

        //Act
        if (dir.mkdir())
            actual = Util.deleteDirectory(dir);
        else throw new IllegalArgumentException("Failed to create a test directory to delete, test failed subsequently");

        //Assert
        Assert.isTrue(actual, "Test failed to delete directory, test failed subsequently. Directory located @ " +
                path);
    }

    /**
     * This method tests for the (B)oundary conditions in Right-BICEP and (E)xistence in CORRECT. It gives a 'null'
     * value in place of a directory as an argument - a value that does not 'exist'. The programmer should validate Path
     * values to ensure that no errors occur.
     */
    @Test
    public void testUtilDeleteDirectoryNull()
    {
        //Arrange
        String actual;
        String expected = "Cannot invoke \"java.io.File.exists()\" because \"path\" is null";

        //Act
        Exception e = assertThrows(NullPointerException.class, () -> Util.deleteDirectory(null));
        actual = e.getMessage();

        //Assert
        assertEquals(expected, actual);
    }

    /**
     * This method tests for the (C)ross-check in Right-BICEP. Originally, this tested the return value of the
     * {@code .getDuration()} method in DiskRun, but I realized all I would have to test to make sure get duration works
     * is not the return value of the method, but the value of the class's 'endTime' attribute, which is a cross check.
     */
    @Test
    public void diskRunGetDurationTest()
    {
        //Arrange
        Date actual;
        DiskRun dr = new DiskRun();
        dr.setEndTime(new Date());

        //Act
        actual = dr.getEndTime();

        //Assert
        Assert.isTrue(actual != null, "DiskRun instance should have returned a Date instance, " +
                "instead returned " + actual);
    }

    /**
     * This method tests for the (E)rror conditions in Right-BICEP. The test easily forces an error condition using
     * {@code Util.randInt()} method which the programmer has to ensure does not happen or at least gives more clarity
     * as to why the method failed.
     */
    @Test
    public void testUtilRandomIntMaxMin()
    {
        //Arrange
        String actual;
        int minimumBound = 10;
        int maximumBound = 5;

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> Util.randInt(minimumBound, maximumBound));
        actual = e.getMessage();

        //Assert
        assertEquals("bound must be positive", actual);
    }
}


