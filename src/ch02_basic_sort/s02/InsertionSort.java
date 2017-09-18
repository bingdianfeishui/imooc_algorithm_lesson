package ch02_basic_sort.s02;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 基本插入排序
 * 特点：对乱序数组排序O(n^2)，但对基本有序的数组排序非常快，最小可到O(n)
 * 当前元素依次与前一个元素比较交换(前面的元素在之前的循环已经排序完成)
 * @author Lee
 *
 */
public class InsertionSort implements ISorter{
	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSort()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			//从当前往前找合适位置插入，比当前元素大的元素依次向后移
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				DataSourceUtil.swap(arr, j, j - 1);
			}
		}
		return arr;
	}
}
