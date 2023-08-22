package org.test.test00_99;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * CompletableFuture allOf实践
 */
public class Test12 {
    public static List<String> times = new ArrayList<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 要使用自定义线程池，或者CompletableFuture最后加join或者get阻塞主线程，
        // 不然主线程不等CompletableFuture子线程完成就结束了，
        // ForkJoinPool是创建的是守护线程
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("completableFuture-test-");
                    return thread;
                });

        CompletableFuture.allOf(Stream.iterate(0, i -> i + 1)
                        .limit(10)
                        .map(i -> CompletableFuture.runAsync(() -> {
                            try {
                                Thread.sleep(new Random().nextInt(1000));
                            } catch (InterruptedException e) {
                                System.out.println("123");
                                throw new RuntimeException(e);
                            }
                            Thread.currentThread().setName(Thread.currentThread().getName() + i);
                            System.out.println(Thread.currentThread().getName());
                            times.add(String.valueOf(i));
                        }, executor))
                        .toArray(CompletableFuture[]::new))
                .thenRun(() -> System.out.println(times));
        executor.shutdown();
    }
}
