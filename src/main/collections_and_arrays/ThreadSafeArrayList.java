package src.main.collections_and_arrays;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 CopyOnWriteArrayList는 다중 스레드 환경에서도 스레드 안전성을 보장하는 특수한 리스트 구현체다.

 주요 특징 및 동작 원리:
 1. 쓰기 시 배열 복사:
  - 데이터 추가, 수정, 삭제와 같은 쓰기 작업이 발생할 때 기존 배열의 복사본을 생성.
  - 이로 인해 쓰기 작업 중에도 다른 스레드는 기존 데이터를 안전하게 읽을 수 있음.

 2. 읽기 작업의 일관성 보장:
  - 쓰기 작업이 완료되기 전까지는 기존 배열이 유지되므로, 읽기 작업 중에는 항상 동일한 상태의 데이터를 보장.
  - ConcurrentModificationException과 같은 예외가 발생하지 않으며, 읽기 작업이 많은 환경에서 특히 유리.

 3. 스레드 안전성:
  - 내부적으로 배열 복사를 통해 읽기와 쓰기 작업 간의 충돌을 방지하며, 락(lock)을 사용하지 않는 대신 배열 복사를 통한 동시성 제어를 수행.(Lock-free까지는 아니다.)
  - 이는 읽기 작업은 고속으로 처리하면서도 쓰기 작업 시 추가적인 메모리 사용 및 성능 비용을 수반.

 4. 성능 특성:
  - 쓰기 작업의 비용이 높은 대신, 읽기 작업의 성능은 ArrayList와 거의 유사.
  - 쓰기 작업이 빈번한 환경에서는 성능 저하가 발생할 수 있으나, 읽기 작업이 많은 환경에서는 안정성과 효율성을 제공.

 5. 활용 사례:
  - MongoDB와 같은 NoSQL 데이터베이스에서도 CopyOnWrite 방식을 활용하여 데이터 읽기 작업의 일관성을 유지.
  - 쓰기 작업이 드물고 읽기 작업이 다수인 환경, 예를 들어 설정값 관리, 캐싱, 이벤트 리스너 저장소 등에 적합.

 주의사항:
  - 쓰기 작업 발생 시 기존 배열의 복사본을 생성하므로, 데이터 크기가 크거나 쓰기 빈도가 높은 환경에서는 메모리 사용량 증가 및 성능 저하가 발생할 수 있음.
  - 읽기 작업이 많은 환경에서 사용해야 최대 성능을 발휘할 수 있음.
 **/

public class ThreadSafeArrayList {

    private static final Logger logger = Logger.getLogger(ThreadSafeArrayList.class.getName());

    private static final List<Integer> list = new CopyOnWriteArrayList<>();

    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    private static final ExecutorService producerService = Executors.newFixedThreadPool(2);
    private static final ExecutorService consumerService = Executors.newFixedThreadPool(2);

    private static volatile boolean isRunning = true;

    private static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) { // 20개의 항목 생성
                int item = (int) (Math.random() * 1000);
                list.add(item);
                logger.info(() -> "Produced: " + item
                        + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // 생산 간 짧은 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // 인터럽트 발생 시 종료
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                for (Integer item : list) { // CopyOnWriteArrayList는 안전하게 반복 가능
                    logger.info(() -> "Consumed: " + item
                            + " by " + Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(500); // 소비 간 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // 인터럽트 발생 시 종료
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        producerService.execute(producer);
        consumerService.execute(consumer);

        producerService.shutdown(); // 생산자 스레드 풀 종료 요청
        if (producerService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("All the producer threads have ended successfully");
        } else {
            logger.warning("Some producer threads did not finish in time");
        }

        isRunning = false; // 소비자 스레드 종료 신호
        consumerService.shutdownNow(); // 소비자 스레드 종료 요청
        if (consumerService.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("All the consumer threads have ended successfully");
        } else {
            logger.warning("Some consumer threads did not finish in time");
        }

        logger.info("Application has terminated successfully.");
    }
}