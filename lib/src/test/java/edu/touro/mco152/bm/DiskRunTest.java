package edu.touro.mco152.bm;

import java.util.List;
import java.util.ArrayList;
import edu.touro.mco152.bm.persist.DiskRun;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

public class DiskRunTest {
    /**
     * testFindAll is designed to be crosschecked with the database file
     * and will also test the performance metric with accessing the DB
     * NOTE: the test case is empty as per our messages on slack regarding
     * the code not running correctly.
     */
    @Test
    @Timeout(150)
    public void testFindAll() {
        List<DiskRun> disks = DiskRun.findAll();
        List<DiskRun> expected = new ArrayList<>();

        assertEquals(expected, disks);
    }
}
