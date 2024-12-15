package src.main.collections_and_arrays;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
ConcurrentLinkedDeque는 다중 스레드 환경에서도 높은 성능과 스레드 안전성을 제공하는 비차단(non-blocking) 양방향 큐(Deque) 구현체다.

 주요 특징 및 동작 원리:
 1. 비차단(lock-free) 알고리즘:
  - 내부적으로 Compare-And-Swap(CAS) 연산을 사용하여 동시성을 유지.
  - 락(lock)을 사용하지 않고도 다중 스레드 환경에서 삽입과 제거 작업을 안전하게 처리.
  - 특정 스레드가 작업을 완료하지 못하더라도 다른 스레드가 중단 없이 진행 가능.

 2. 동시성 및 성능:
  - 데이터 삽입과 제거 작업은 독립적으로 처리되므로, 스레드 간 충돌을 최소화.
  - 다중 스레드가 동시에 작업할 때에도 데이터 손실이나 race condition 발생 없이 안정적으로 처리.
  - 다중 스레드 환경에서 처리량이 높은 작업에 적합하며, 고성능 동시성 작업을 요구하는 애플리케이션에서 활용 가능.

 3. 활용 사례:
  - 작업 큐(Work Queue) 구현.
  - 다중 스레드 환경에서 작업 순서를 보장하거나 병렬 작업을 분배하는 시스템.
  - 순차적인 데이터 처리가 필요하지만 락을 피하고자 하는 환경.

 주의사항:
  - 메모리 소비가 ArrayDeque보다 상대적으로 높을 수 있음.
  - lock-free 구조이므로 작업 실패나 중단이 발생할 경우, 작업 재시도를 통해 일관성을 유지해야 함.
    이러한 특징들 덕분에, ConcurrentLinkedDeque는 다중 스레드 환경에서 안정성과 성능을 모두 제공하며,
    특히 데이터 삽입 및 제거 작업이 빈번히 발생하는 환경에서 적합하다.
 */
public class ThreadSafeDeque {

    private static final Logger logger = Logger.getLogger(ThreadSafeDeque.class.getName());

    // ConcurrentLinkedDeque는 lock-free 비차단 알고리즘을 기반으로 동작하여 동시성 보장
    private static final Queue<Integer> deque = new ConcurrentLinkedDeque<>();

    public static void main(String[] args) throws InterruptedException {

        // 로그 포맷 간소화
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        logger.info("Enqueue ...");

        // 단일 스레드를 사용하여 1부터 10까지의 숫자를 deque에 삽입
        for (int i = 0; i < 10; i++) {
            int item = i + 1;
            logger.info(() -> "Produced: " + item
                    + " by " + Thread.currentThread().getName());

            deque.offer(item); // deque에 항목 추가
        }

        logger.info("Dequeue ...");

        // 5개의 스레드를 사용하여 deque에서 항목을 소비
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000); // 항목 소비 전 대기
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                Integer item = deque.poll(); // deque의 맨 앞 항목 가져오고 제거
                if (item != null) {
                    logger.info(() -> "Consumed: " + item
                            + " by " + Thread.currentThread().getName());
                }
            });
        }

        executor.shutdown(); // 스레드 풀 종료 요청
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS); // 모든 스레드 종료 대기

        logger.info("All the threads have ended successfully");
    }
}
