package com.imooc;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ArrayList<Future<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new CustomTask());
            resultList.add(submit);
        }
        resultList.forEach(result -> {
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();


    }
//
//
//
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ArrayList<String> resultList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Future<String> submit = executorService.submit(new CustomTask());
//            String result = null;
//            try {
//                result = submit.get();
//                resultList.add(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        resultList.forEach(System.out::println);
//        executorService.shutdown();
//    }
}

class CustomTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println("线程名:" + threadName + " 开始时间:" + LocalDateTime.now());
        System.out.println("系统需要业务操作1秒");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "threadName = " + threadName + " 结束时间:" + LocalDateTime.now();
    }
}