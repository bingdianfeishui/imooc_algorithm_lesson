package ch03_advance_sort.s01;

import java.util.Arrays;

import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 
 * 自顶向下，递归实现
 * 分治思想：将一个数组多次分割为小数组，对小数组排序然后再依次合并
 * 
 * @author Lee
 *
 */
public class MergeSort implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSortOp1(), new MergeSort() };
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
		int mid = (l + r) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid, r);
		merge(arr, l, mid, r);
	}

	// 对arr数组中[l,r)区间进行归并
	static void merge(int[] arr, int l, int mid, int r) {
		int[] aux = Arrays.copyOfRange(arr, l, r);
		int i = l, j = mid;
		for (int k = l; k < r; k++) {
			if (i >= mid)
				arr[k] = aux[j++ - l];
			else if (j >= r)
				arr[k] = aux[i++ - l];
			else if (aux[i - l] < aux[j - l])
				arr[k] = aux[i++ - l];
			else
				arr[k] = aux[j++ - l];
		}
	}

}
