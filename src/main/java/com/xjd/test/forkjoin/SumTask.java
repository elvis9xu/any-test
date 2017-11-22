package com.xjd.test.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author elvis.xu
 * @since 2017-05-11 10:58
 */
// 计算 1+2+3+...
public class SumTask extends RecursiveTask<Integer>{

	private static final int THRESHOLD = 10; // 阈值
	private int start;
	private int end;

	public SumTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if ((end - start) > THRESHOLD) { // 超出阈值范围
			int middle = (start + end) / 2;
			SumTask leftTask = new SumTask(start, middle);
			SumTask rightTask = new SumTask(middle + 1, end);

			// 提交执行
			leftTask.fork();
			rightTask.fork();

			// 等待完成
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();

			// 合并结果
			sum += leftResult + rightResult;

		} else { // 阈值范围内
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// 计算1 + 2 + 3 + ... + n
		int n = 100000000;
		SumTask sumTask = new SumTask(1, n);

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.currentTimeMillis();
		ForkJoinTask<Integer> result = forkJoinPool.submit(sumTask);
		try {
			Integer sum = result.get();
			long cost = System.currentTimeMillis() - start;
			System.out.println(cost + "ms:  " + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println(cost + "ms:  " + sum);
	}
}
