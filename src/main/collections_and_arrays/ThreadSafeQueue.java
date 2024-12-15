package src.main.collections_and_arrays;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 ArrayBlockingQueue는 다중 스레드 환경에서 안전하고 효율적인 큐 작업을 제공하는 고정 크기 큐 구현체다.

 주요 특징 및 동작 원리:
 1. 내부적으로 ReentrantLock을 사용한 동기화:
 - 생산자와 소비자 간의 동기화를 ReentrantLock을 통해 구현.
 - 생산자-소비자 패턴에서 데이터의 일관성과 스레드 안전성을 보장.
 - 항목 추가(offer)와 제거(poll) 작업이 직렬화되어 동시 접근을 안전하게 처리.

 2. 고정 크기 버퍼:
 - 큐의 크기가 고정되어 있어, 메모리 관리가 용이.
 - 큐가 가득 차거나 비어 있을 때, 대기(blocking) 메커니즘을 통해 작업을 조율.
 - 생산자는 큐가 가득 차면 대기하며, 소비자는 큐가 비어 있으면 대기.

 3. 대기(blocking) 메커니즘:
 - put()과 take() 메서드는 큐가 가득 차거나 비어 있을 때 대기(blocking)하여 작업 흐름을 제어.
 - 특정 조건이 충족되면 생산자와 소비자는 자동으로 작업을 재개.
 - 생산자-소비자 패턴에 적합하며, 작업 대기열(queue)을 안전하게 처리.

 4. 성능 특성:
 - 고정된 크기의 버퍼를 사용하여 메모리 효율성을 유지하며, 동기화를 통해 스레드 간 충돌을 방지.
 - 대기 메커니즘을 통해 작업 손실이나 데이터 경합 없이 안전하게 처리.
 - 큐의 크기를 미리 설정하여 메모리 오버헤드를 최소화.

 5. 활용 사례:
 - 생산자-소비자 패턴의 구현.
 - 고정 크기의 작업 대기열 관리.
 - 제한된 자원(예: 연결 풀, 작업 스레드)을 관리하는 시스템.

 주의사항:
 - 큐의 크기가 고정되어 있으므로, 과도한 작업이 발생할 경우 큐가 가득 차는 상황이 빈번해질 수 있음.
 - 대기 메커니즘으로 인해 생산자 또는 소비자가 과도하게 대기하지 않도록 큐 크기와 작업 흐름을 조율하는 것이 중요.
 */

public class ThreadSafeQueue {

    private static final Logger logger = Logger.getLogger(ThreadSafeQueue.class.getName());

    // ArrayBlockingQueue는 고정 크기(10000)의 스레드 안전한 큐.
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10000);

    // AtomicInteger를 사용해 고유한 항목 번호를 생성하며, 동시성 환경에서 안전하게 증가 가능.
    private static final AtomicInteger itemNr = new AtomicInteger();

    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();

    private static final ExecutorService producerService = Executors.newFixedThreadPool(3);
    private static final ExecutorService consumerService = Executors.newFixedThreadPool(3);

    private static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                int item = itemNr.incrementAndGet(); // 원자적으로 항목 번호 증가.
                try {
                    queue.put(item); // 큐가 가득 찼을 경우 대기.
                    logger.info(() -> "Produced: " + item + " by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Integer item = queue.take(); // 큐가 비었을 경우 대기.
                    logger.info(() -> "Consumed: " + item + " by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 로그 출력 형식을 간단히 설정.
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        // 3개의 생산자와 소비자 스레드 실행.
        for (int i = 0; i < 3; i++) {
            producerService.execute(producer);
            consumerService.execute(consumer);
        }

        // 프로그램 1초 동안 실행 후 종료.
        Thread.sleep( 1000);

        producerService.shutdownNow();
        consumerService.shutdownNow();

        logger.info("Application has terminated successfully.");
    }
}
