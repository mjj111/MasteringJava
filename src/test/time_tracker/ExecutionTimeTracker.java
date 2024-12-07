package src.test.time_tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ExecutionTimeTracker {

    long startTime;

    @BeforeEach
    public void setUp() {
        startTime = System.nanoTime();
    }

    @AfterEach
    public void tearDown() {
        long endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) / 1000000 + "ms");
    }
}
