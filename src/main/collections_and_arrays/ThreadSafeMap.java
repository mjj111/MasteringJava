package src.main.collections_and_arrays;

import java.util.Iterator;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
ConcurrentHashMap은 다중 스레드 환경에서 스레드 안전성과 높은 성능을 제공하는 맵(Map) 구현체다.

 주요 특징 및 동작 원리:
 1. 분할 잠금(bucket-level locking):
  - 내부적으로 데이터를 버킷(bucket)으로 나누고, 각 버킷에 대한 잠금을 독립적으로 관리.
  - 다중 스레드가 동시에 서로 다른 버킷에 데이터를 삽입하거나 수정할 수 있도록 하여 성능을 향상.
  - 전체 맵이 아닌 특정 버킷에만 잠금이 걸리므로, 동시성 작업이 빈번한 환경에서도 충돌을 최소화.

 2. 읽기 작업의 비차단(non-blocking):
  - 대부분의 읽기 작업은 락 없이 수행 가능.
  - 쓰기 작업 중에도 다른 스레드가 데이터를 읽을 수 있으며, 일관된 상태를 보장.
  - 쓰기 작업은 버킷 수준에서 잠금을 사용하므로, 읽기 작업과 충돌하지 않음.

 3. 동시성 제어:
  - 내부적으로 Compare-And-Swap(CAS) 연산을 활용하여 데이터 추가 및 삭제를 수행.
  - 데이터의 삽입, 수정, 삭제 작업이 락(lock) 기반으로 처리되지만, 잠금 범위가 좁아 성능 저하가 적음.
  - race condition이나 데이터 손실 없이 다중 스레드 작업을 안전하게 처리.

 4. 성능 특성:
  - HashMap보다 약간 높은 메모리 소비와 복잡한 내부 구조를 가지지만, 동시성 환경에서는 HashMap보다 훨씬 높은 성능 제공.
  - 쓰기 작업이 빈번한 환경에서도 데이터 일관성을 유지하며, 읽기 작업이 많은 환경에서 더욱 효율적.

 5. 활용 사례:
  - 다중 스레드가 동시에 읽고 쓰는 데이터 구조가 필요한 환경.
  - 캐싱, 세션 관리, 실시간 상태 정보 저장 등 동시성 처리가 필수적인 애플리케이션.
  - 대규모 병렬 작업을 처리해야 하는 데이터 분석 및 분산 시스템.

 주의사항:
  - lock-free 알고리즘과는 다르게 특정 상황에서 버킷 잠금을 사용하므로, 극한의 낮은 지연 시간이 요구되는 경우에는 적합하지 않을 수 있음.
  - 데이터 크기가 커지거나 동시 작업이 매우 많을 경우, 성능 모니터링 및 튜닝이 필요할 수 있음.
 */
public class ThreadSafeMap {

    private static final Logger logger = Logger.getLogger(ThreadSafeMap.class.getName());

    // ConcurrentMap은 스레드 안전한 키-값 저장소로, 동시성 작업에서 안정적.
    private static final ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<>();

    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    private static final ExecutorService producerService = Executors.newSingleThreadExecutor();
    private static final ExecutorService consumerService = Executors.newSingleThreadExecutor();

    private static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) { // 20개의 항목만 생성
                int item = (int) (Math.random() * 1000);
                map.put(item, item);
                logger.info(() -> "Produced: " + item
                        + " by " + Thread.currentThread().getName());
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
                Iterator<Integer> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    Integer item = iterator.next();
                    logger.info(() -> "Consumed: " + item
                            + " by " + Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(500); // 소비 간 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

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
