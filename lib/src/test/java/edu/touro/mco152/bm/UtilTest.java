package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    /**
     * The second run of this test is designed to fail for the purposes of the assignment
     * @param expected
     * @param input
     */
    @ParameterizedTest
    @CsvSource({
            "12.34, 12.34",
            "42.5, 42.5"
    })
    public void testDecimalFormat(String expected, double input) {
        assertEquals(expected, Util.displayString(input));
    }

    /**
     * testRandInt() Tests the upper and lower BOUNDARIES of the randInt method in Util
     */
    @Test
    public void testRandInt() {
        int random = Util.randInt(2, 5);
        assertTrue(random >= 2 && random <= 5);
    }

    /**
     * Second test of randInt designed to get an exception with a bigger min than max
     */
    @Test
    public void testRandInt2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Util.randInt(5, 2);
        });
    }

    /**
     * testDeleteDirectory is testing for a nonexisstent directory another boundary condition
     */
    @Test
    public void testDeleteDirectoryNull() {
        assertFalse(Util.deleteDirectory(new File("fake.txt")));
    }
}
