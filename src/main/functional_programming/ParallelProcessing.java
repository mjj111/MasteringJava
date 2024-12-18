package src.main.functional_programming;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ParallelProcessing {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int noOfProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("해당 머신의 프로세서 개수 :  " + noOfProcessors + " 개 입니다. \n");

        Clock clock = Clock.systemUTC();
        List<Double> numbers = generateRandomNumbers(1_000_000);

        processStream(numbers, clock, false, "Sequential sum");

        processStream(numbers, clock, true, "Parallel sum");

        processCustomThreadPool(numbers, clock, 5);
    }

    private static List<Double> generateRandomNumbers(int count) {
        Random rnd = new Random();
        List<Double> numbers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            numbers.add(rnd.nextDouble());
        }
        return numbers;
    }

    private static void processStream(List<Double> numbers, Clock clock, boolean isParallel, String label) {
        long startTime = clock.millis();
        double result = (isParallel ? numbers.parallelStream() : numbers.stream())
                .reduce(Double::sum)
                .orElse(-1d);
        displayExecutionTime(clock.millis() - startTime, label, result);
    }

    private static void processCustomThreadPool(List<Double> numbers, Clock clock, int threadCount) throws InterruptedException, ExecutionException {
        ForkJoinPool customThreadPool = new ForkJoinPool(threadCount);
        long startTime = clock.millis();
        double result = customThreadPool.submit(
                () -> numbers.parallelStream().reduce(Double::sum)
        ).get().orElse(-1d);
        displayExecutionTime(clock.millis() - startTime, "Parallel sum with custom thread pool (" + threadCount + " threads)", result);
        customThreadPool.shutdown();
    }

    private static void displayExecutionTime(long time, String label, double result) {
        System.out.println(label + ": " + result);
        System.out.println("Execution time: " + time + " ms (" +
                TimeUnit.SECONDS.convert(time, TimeUnit.MILLISECONDS) + " s)\n");
    }
}
