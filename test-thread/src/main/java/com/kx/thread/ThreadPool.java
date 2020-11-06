package com.kx.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //当前线程数
        threadPool.setCorePoolSize(20);
        // 最大线程数
        threadPool.setMaxPoolSize(120);
        //线程池所使用的缓冲队列,容量 10W
        threadPool.setQueueCapacity(100000);
        //设定 线程池拒绝策略， 线程池满，消息从 队列头删除，重新进入队列尾
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(50);
        //  线程名称前缀
        threadPool.setThreadNamePrefix("MyAsync-");
        threadPool.initialize(); // 初始化
        System.out.println("--------------------------》》》开启线程池");

        List<Integer> s = new ArrayList<>();
        try {
            for (int i = 0; i < 50000; i++) {
                s.add(i);
                if (i > 0 && i%200 == 0) {

                    List<Integer> s1 = new ArrayList<>(s);
                    threadPool.execute(() -> {
                        Thread t = Thread.currentThread();
                        try {
                            Thread.sleep(8000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(t.getName() + s1.toString());
                    });
                    s.clear();
                }
            }
            System.out.println(Thread.currentThread().getName() + s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
