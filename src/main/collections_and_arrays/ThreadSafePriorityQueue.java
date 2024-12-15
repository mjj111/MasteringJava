package src.main.collections_and_arrays;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 PriorityBlockingQueue를 사용하여 다중 스레드 환경에서 안전한 우선순위 큐를 제공한다.

 주요 특징 및 동작 원리:
 1. 동기화된 데이터 처리:
 - 내부적으로 동기화된 메서드를 제공하여, 항목 추가와 제거 작업을 안전하게 처리.
 - 스레드 간 데이터 충돌을 방지하며, 다중 스레드가 동시에 작업할 수 있도록 설계.

 2. 우선순위 정렬:
 - 항목은 우선순위에 따라 자동으로 정렬되며, 기본적으로 Comparable 인터페이스를 구현한 객체의 자연 순서를 사용.
 - 사용자 정의 Comparator를 설정하여 커스텀 정렬 기준을 지정 가능.

 3. 대기(blocking) 기능:
 - 비어 있는 큐에서 항목을 가져오려고 할 경우, 항목이 추가될 때까지 대기.
 - 다중 스레드 환경에서 생산자-소비자 패턴을 구현하기에 적합.

 4. 성능 특성:
 - 락(lock)을 사용하지 않는 대신 내부적으로 효율적인 동기화 메커니즘을 통해 높은 성능 제공.
 - 데이터 삽입과 제거 작업 모두 O(log n)의 시간 복잡도를 가짐.

 5. 활용 사례:
 - 우선순위 기반 작업 스케줄링.
 - 실시간 이벤트 처리 및 병렬 작업 분배.
 - 생산자-소비자 패턴 구현.

 주의사항:
 - 우선순위 큐의 특성상, 동일한 우선순위를 가진 항목들의 순서는 보장되지 않을 수 있음.
 - 대기 메커니즘을 과도하게 사용할 경우, 작업 처리 속도가 저하될 수 있음.
 */
public class ThreadSafePriorityQueue {

    private static final Logger logger = Logger.getLogger(ThreadSafePriorityQueue.class.getName());

    // PriorityBlockingQueue를 사용하여 스레드 안전한 우선순위 큐 구현.
    private static final PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    private static final ExecutorService producerService = Executors.newFixedThreadPool(2);
    private static final ExecutorService consumerService = Executors.newFixedThreadPool(2);

    private static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) { // 20개의 항목만 생성
                int item = (int) (Math.random() * 1000);
                queue.offer(item); // 큐에 항목 추가
                logger.info(() -> "Produced: " + item + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // 생산 간 짧은 대기
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
                    Integer item = queue.take(); // 큐에서 항목을 가져오고 제거 (대기 가능)
                    logger.info(() -> "Consumed: " + item + " by " + Thread.currentThread().getName());
                    Thread.sleep(500); // 소비 간 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // 인터럽트 발생 시 반복 종료
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        // 생산자와 소비자 스레드 실행
        producerService.execute(producer);
        consumerService.execute(consumer);

        producerService.shutdown();
        if (producerService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("All the producer threads have ended successfully");
        } else {
            logger.warning("Some producer threads did not finish in time");
        }

        consumerService.shutdownNow();
        if (consumerService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("All the consumer threads have ended successfully");
        } else {
            logger.warning("Some consumer threads did not finish in time");
        }

        logger.info("Application has terminated successfully.");
    }
}
