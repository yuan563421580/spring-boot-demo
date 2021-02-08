package com.yuansb.demo.multi.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 实际接 Example01_01_CountDownLatch 的需求继续优化
 * 使用 CompletableFuture 方式实现替换 CountDownLatch
 *
 * 继续优化：当任务过多的时候，把每一个 task 都列出来不太现实，可以考虑通过循环来添加任务。
 *      实现：Example01_03_CompletableFuture
 */
public class Example01_02_CompletableFuture {

    public static void main(String[] args){
        Example01_02_CompletableFuture example = new Example01_02_CompletableFuture();
        CompletableFuture<Map> task1 = CompletableFuture.supplyAsync(() -> example.dealFile(0));
        CompletableFuture<Map> task2 = CompletableFuture.supplyAsync(() -> example.dealFile(1));
        CompletableFuture<Map> task3 = CompletableFuture.supplyAsync(() -> example.dealFile(2));
        CompletableFuture<Map> task4 = CompletableFuture.supplyAsync(() -> example.dealFile(3));
        CompletableFuture<Map> task5 = CompletableFuture.supplyAsync(() -> example.dealFile(4));
        CompletableFuture<Map> task6 = CompletableFuture.supplyAsync(() -> example.dealFile(5));
        CompletableFuture<Void> futureR =
                CompletableFuture.allOf(task1, task2, task3, task4, task5, task6);
        try {
            futureR.join();
        } catch (Exception e) {
        }
        Map map1 = task1.join();
        Map map2 = task2.join();
        Map map3 = task3.join();
        Map map4 = task4.join();
        Map map5 = task5.join();
        Map map6 = task6.join();
        System.out.println("map1 : " + map1.toString());
        System.out.println("map2 : " + map2.toString());
        System.out.println("map3 : " + map3.toString());
        System.out.println("map4 : " + map4.toString());
        System.out.println("map5 : " + map5.toString());
        System.out.println("map6 : " + map6.toString());
        System.out.println("all done ...");
    }
    
    
    private Map dealFile(int index){
        System.out.println("开始处理第 " + index + " 个文件");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成处理第 " + index + " 个文件");
        
        Map map = new HashMap();
        map.put("file" + index, "file success");
        return map;
    }
    
}
