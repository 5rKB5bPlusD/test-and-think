package org.test.test00_99;

import java.util.concurrent.*;

/**
 * java异步编程
 */
public class Test06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        threadPool();
        completableFuture();
    }

    //一般多线程异步
    public static void threadPool() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1000L * 10,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    int count = 1;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("test-thread-name-" + count++);
                        return thread;
                    }
                });

        Runnable runnable = () -> {
            System.out.println("hello runnable");
            System.out.println(Thread.currentThread().getName());
        };

        Callable<String> callable = () -> {
            System.out.println("hello callable");
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000L * 5);
            return "hello callable";
        };

        executor.submit(runnable);
        Future<String> future = executor.submit(callable);
        while (!future.isDone()) {
            System.out.println(future.get());
        }

        executor.shutdown();
    }

    //CompletableFuture异步
    public static void completableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1 + 2;
        }).thenApply((res) -> {
            System.out.println(Thread.currentThread().getName());
            return res * 10;
        }).thenAcceptAsync((res) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(res);
        });

        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 1 + 2;
                }).thenApply((res) -> {
                    System.out.println(Thread.currentThread().getName());
                    return res * 10;
                });
        while (!completableFuture.isDone()) {
            System.out.println("CompletableFuture is not finished yet...");
        }
        long result = completableFuture.get();//get join是阻塞的
        System.out.println(result);
    }
}
