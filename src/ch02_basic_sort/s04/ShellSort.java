package ch02_basic_sort.s04;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 希尔排序
 * 也叫 缩小增量排序，是直接插入排序的优化版本
 * 实现思想： 按序列选取gap间隙，对每个间隙将数组划分为小数组，对小数组进行插入排序
 * gap序列最后必须为1，即对整个数组执行插入排序，从而保证最后排序正确性
 * @author Lee
 *
 */
public class ShellSort implements ISorter {
	public static void main(String[] args) throws Exception {
		int n = 1000000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new ShellSort(), new ShellSortOp1()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

//		int[] arr1 = { 23, 25, 12, 42, 35, 2, 3, 34, 5, 2, 6432, 2465, 5, 3, 2, 79, 9, 65, 0, 4, 32 };
//		int[] arr2 = Arrays.copyOf(arr1, arr1.length);
//		System.out.println(Arrays.toString(arr1));
//		MethodExeTimerUtil.getMethodExecuteTime(new ShellSort(), "sort", new Class<?>[] { int[].class }, arr1);
//		// new ShellSort().sort(arr1);
//		DataSourceUtil.isSorted(arr1);
//		MethodExeTimerUtil.getMethodExecuteTime(new ShellSortOp1(), "sort", new Class<?>[] { int[].class }, arr2);
//		// new ShellSortOp1().sort(arr2);
//		DataSourceUtil.isSorted(arr2);
	}

	@Override
	public int[] sort(int[] arr) {
//		int counter = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int k = 0; k < gap; k++) {
				for (int i = k + gap; i < arr.length; i += gap) {
					int e = arr[i], j;
					for (j = i; j >= gap && e < arr[j - gap]; j -= gap) {
						arr[j] = arr[j - gap];
//						counter++;
					}
					arr[j] = e;
//					System.out.println(Arrays.toString(arr) + " gap=" + gap + " i=" + i);
				}
			}
		}
//		System.out.println(counter);
		return arr;
	}

}
