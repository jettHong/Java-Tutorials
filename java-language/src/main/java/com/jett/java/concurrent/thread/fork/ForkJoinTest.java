package com.jett.java.concurrent.thread.fork;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join线程池可以执行一种特殊的任务，把大任务拆分成多个小任务并行执行。
 * 要求JDK >= 1.7
 * <p>
 * 步骤：
 * 1、SumTask extends RecursiveTask
 * 2、ForkJoinPool.commonPool().invoke(sumTask);
 * 3、实现类自行拆分任务，invokeAll(sub1, sub2)
 * REF：https://www.liaoxuefeng.com/wiki/1252599548343744/1306581226487842
 * @author jett
 */

public class ForkJoinTest {
    public static void main(String[] args) {
        long[] array = new long[102400];
        long expectedSum = 0;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000); // 限定最大数值，避免long承接不了溢出
            expectedSum += array[i];
        }
        
        ForkJoinTask<Long> sumTask = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(sumTask);
        long endTime = System.currentTimeMillis();
        System.out.println("Expected  sum:" + expectedSum);
        System.out.println("Fork/Join sum:" + result + " in " + (endTime - startTime));
        
    }
}

/**
 * 计算任务
 */
class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 1024;
    long[] array;
    int start;
    int end;
    
    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 一、不需要再次分解
            //return Arrays.stream(array).sum(); // 传进来的array是最原始并没有分割过，所以不能简单的sum
            long result = 0;
            for (int i = start; i < end; i++) {
                result += array[i];
            }
            return result;
        }
        // 二、任务太大，分解任务
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask sub1 = new SumTask(array, start, middle);
        SumTask sub2 = new SumTask(array, middle, end);
        // 并行执行
        invokeAll(sub1, sub2);
        // 使用 join() 等待都执行完毕
        Long result1 = sub1.join();
        Long result2 = sub2.join();
        System.out.println(String.format("result1 = %d,  result2 = %d", result1, result2));
        return result1 + result2;
    }
}
