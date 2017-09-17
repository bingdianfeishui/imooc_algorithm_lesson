package ch03_advance_sort.s01;

import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 自底向上的实现方式 Bottom Up
 * 
 * @author Lee
 *
 */
public class MergeSortBU implements ISorter {
	public static void main(String[] args) {
		int n = 42123;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSortOp1(), new MergeSort(), new MergeSortB(),
				new MergeSortOp1(), new MergeSortOp1B(),new MergeSortOp2(), new MergeSortOp2B(),new MergeSortBU() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSort(arr, 0, arr.length);
		return arr;
	}

	// 对arr数组中[l,r)区间进行归并排序
	private void mergeSort(int[] arr, int l, int r) {
		if (l >= r - 1)
			return;

		for (int sz = 1; sz < arr.length; sz += sz) {
			for (int i = 0; i + sz <= arr.length; i += sz + sz) {
				MergeSort.merge(arr, i, i + sz, Math.min(arr.length, i + sz + sz));
				// merge(arr, i, i+sz, Math.min(arr.length, i + sz + sz));
			}
		}
	}

}
