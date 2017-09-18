package ch03_advance_sort.s01;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 自底向上的实现方式 Bottom Up
 * 非递归实现，采用循环
 * 特点：排序算法中不涉及数组寻址操作，所以可以对链表进行排序！！！
 * @author Lee
 *
 */
public class MergeSortBU implements ISorter {
	public static void main(String[] args) {
		int n = 902123;
		int swapTimes = n / 10000;
		ISorter[] sorters = {new MergeSort(), new MergeSortB(), new MergeSortOp1(),
				new MergeSortOp1B(), new MergeSortOp2(), new MergeSortOp2B(), new MergeSortBU() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSortBU(arr);
		return arr;
	}

	// 对arr数组进行归并排序，非递归实现
	private void mergeSortBU(int[] arr) {

		for (int sz = 1; sz < arr.length; sz += sz) {
			for (int i = 0; i + sz <= arr.length; i += sz + sz) {
				// 对arr[i, i+sz] 和arr[i+sz+1, i+sz+sz]进行归并
				MergeSort.merge(arr, i, i + sz, Math.min(arr.length, i + sz + sz));
			}
		}
	}

}
