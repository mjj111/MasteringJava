package src.main.collections_and_arrays;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 CopyOnWriteArraySet은 다중 스레드 환경에서 스레드 안전성을 제공하는 Set 구현체다.

 주요 특징 및 동작 원리:
 1. CopyOnWriteArrayList 기반의 동작:
 - 내부적으로 CopyOnWriteArrayList를 사용하여 데이터 관리.
 - Set의 특성인 중복 방지와 CopyOnWriteArrayList의 스레드 안전성을 결합.
 - 쓰기 작업(추가, 삭제) 시 기존 배열의 복사본을 생성하여 데이터 변경을 수행.

 2. 읽기 작업의 안정성:
 - 쓰기 작업 중에도 기존 배열은 변경되지 않으므로, 읽기 작업은 항상 안전하게 수행 가능.
 - ConcurrentModificationException과 같은 문제를 방지하며, 다중 스레드 환경에서 데이터 읽기의 일관성을 보장.

 3. 쓰기 작업의 성능 비용:
 - 쓰기 작업마다 배열 복사가 이루어지므로, 쓰기 작업이 빈번한 환경에서는 성능 저하 발생 가능.
 - 읽기 작업이 많은 환경에서 효율적이며, 쓰기 작업이 드문 환경에서 적합.

 4. Set 특성 유지:
 - 중복 항목을 허용하지 않는 Set의 특성을 유지.
 - 데이터 추가 시 기존 데이터와 중복 여부를 검사하여 고유한 값만 저장.

 5. 활용 사례:
 - 이벤트 리스너 관리와 같이 데이터 변경이 드물고 읽기 작업이 많은 환경.
 - 다중 스레드에서 중복 없는 데이터 관리가 필요한 경우.
 - 읽기 작업 위주의 설정값 저장소, 캐시 등.

 주의사항:
 - 쓰기 작업이 빈번하거나 데이터 크기가 큰 경우, 배열 복사로 인해 메모리 사용량 증가 및 성능 저하 가능.
 - 읽기 작업 위주의 사용 환경에서 가장 효율적.
 */
public class ThreadSafeSet {

    private static final Logger logger = Logger.getLogger(ThreadSafeSet.class.getName());

    // CopyOnWriteArraySet은 스레드 안전한 Set으로, 동시성 작업에서 안정적.
    private static final Set<Integer> set = new CopyOnWriteArraySet<>();

    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    private static final ExecutorService producerService = Executors.newSingleThreadExecutor();
    private static final ExecutorService consumerService = Executors.newSingleThreadExecutor();

    private static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                int item = (int) (Math.random() * 1000); // 무작위 숫자 생성.
                set.add(item); // Set에 항목 추가.
                logger.info(() -> "Produced: " + item + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // 생산 간 짧은 대기.
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
                Iterator<Integer> iterator = set.iterator(); // 안전한 반복자 생성.
                while (iterator.hasNext()) {
                    Integer item = iterator.next();
                    logger.info(() -> "Consumed: " + item + " by " + Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(500); // 소비 간 대기.
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

        // 생산자와 소비자 스레드 실행.
        producerService.execute(producer);
        consumerService.execute(consumer);

        // 10초 후 프로그램 종료.
        Thread.sleep(10000);

        producerService.shutdownNow();
        consumerService.shutdownNow();

        logger.info("Application has terminated successfully.");
    }
}
