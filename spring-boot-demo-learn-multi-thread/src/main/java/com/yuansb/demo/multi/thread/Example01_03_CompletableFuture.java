package com.yuansb.demo.multi.thread;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 优化 Example01_02_CompletableFuture 的实现逻辑
 */
public class Example01_03_CompletableFuture {

    public static void main(String[] args) {
        Example01_03_CompletableFuture example = new Example01_03_CompletableFuture();

        // 模拟构造文件信息
        List<Integer> filesIndex = example.structFilesInfo();

        // 异步处理所有文件
        List<CompletableFuture<Map>> fileFutures = filesIndex.stream()
                .map(index -> CompletableFuture.supplyAsync(() -> example.dealFile(index)))
                .collect(Collectors.toList());
        // 解释成普通的for循环语句
        /*List<CompletableFuture<Map>> fileFutures = new ArrayList<>();
        for (int i = 0; i < filesIndex.size(); i++) {
            final int index = i;
            CompletableFuture<Map> fileFuture = CompletableFuture.supplyAsync(() -> {
                Map map = example.dealFile(index);
                return map;
            });
            fileFutures.add(fileFuture);
        }*/

        // 将他们合并起来
        //CompletableFuture[] completableFutures = fileFutures.toArray(new CompletableFuture[fileFutures.size()]);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                fileFutures.toArray(new CompletableFuture[fileFutures.size()])
        );

        fileFutures.stream()
                .map(f -> f.join())
                .forEach(System.out::println);

        System.out.println("all done success...");
    }

    private List<Integer> structFilesInfo() {
        List<Integer> filesIndex = Arrays.asList(0, 1, 2, 3, 4, 5);
        return filesIndex;
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
