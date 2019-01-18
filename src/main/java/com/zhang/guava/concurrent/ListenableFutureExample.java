package com.zhang.guava.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * {@link ListenableFuture}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-15 14:59
 * @since 1.0
 */
public class ListenableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);
       /* Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        Object result = future.get();
        System.out.println(result);*/

        // Guava
   /*     ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<Integer> future = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 100;
        });

        //future.addListener(() -> System.out.println("I am finished"), executor);
        Futures.addCallback(future, new MyCallBack(), executor);
        System.out.println("=============");*/

        // Java 8
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }, executor).whenComplete((v, t) -> System.out.println("I am finished and the result is " + v));


    }

    static class MyCallBack implements FutureCallback<Integer> {
        @Override
        public void onSuccess(@Nullable Integer result) {
            System.out.println("I am finished and the result is " + result);
        }

        @Override
        public void onFailure(Throwable t) {
            t.printStackTrace();
        }
    }
}
