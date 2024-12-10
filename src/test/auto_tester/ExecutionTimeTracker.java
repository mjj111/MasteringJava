package src.test.auto_tester;

public class ExecutionTimeTracker {

    public interface CodeBlock {
        void execute() throws Exception;
    }

    public void trackExecutionTime(CodeBlock block) {
        long startTime = System.nanoTime();

        try {
            block.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error during execution", e);
        } finally {
            long endTime = System.nanoTime();
            System.out.println("Time taken: " + (endTime - startTime) / 1000000 + "ms");
        }
    }
}
