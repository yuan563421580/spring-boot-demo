package com.yuansb.demo.lambda;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.Random;

/**
 * 测试用例一
 *
 * 使用 @JunitPerfConfig 需要在 pom.xml 引入 junitperf
 * 直接在 StreamIntTest 左侧绿色箭头 点击即可运行
 * 运行结果 在 idea 工具 run 窗口可以查看
 * 在 target -> junitperf -> reports -> ... -> StreamIntTest.html 可以查看效率图
 * 按照测试用例一 、 用例二 、 用例三 顺序执行 ，以此类推进行观察
 */
public class StreamIntTest {

    /**
     * 【最终结论】
     *  ·对于简单的数字(list-Int)遍历，普通for循环效率的确比Stream串行流执行效率高（1.5-2.5倍）。
     *      但是Stream流可以利用并行执行的方式发挥CPU的多核优势,因此并行流计算执行效率高于for循环。
     *  ·对于list-Object类型的数据遍历，普通for循环和Stream串行流比也没有任何优势可言，更不用提Stream并行流计算。
     *
     * 虽然在不同的场景、不同的数据结构、不同的硬件环境下。Stream流与for循环性能测试结果差异较大，甚至发生逆转。但是总体上而言：
     *  ·Stream并行流计算 >> 普通for循环 ~= Stream串行流计算。
     *  ·数据容量越大，Stream流的执行效率越高。
     *  ·Stream并行流计算通常能够比较好的利用CPU的多核优势。CPU核心越多，Stream并行流计算效率越高。
     */

    public static int[] arr;

    @BeforeAll
    public static void init() {
        arr = new int[500000000];  //5亿个随机Int
        randomInt(arr);
    }

    @JunitPerfConfig( warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntFor() {
        minIntFor(arr);
    }

    @JunitPerfConfig( warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntParallelStream() {
        minIntParallelStream(arr);
    }

    @JunitPerfConfig( warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntStream() {
        minIntStream(arr);
    }

    private int minIntStream(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    private int minIntParallelStream(int[] arr) {
        return Arrays.stream(arr).parallel().min().getAsInt();
    }

    private int minIntFor(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int anArr : arr) {
            if (anArr < min) {
                min = anArr;
            }
        }
        return min;
    }

    private static void randomInt(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt();
        }
    }

}
