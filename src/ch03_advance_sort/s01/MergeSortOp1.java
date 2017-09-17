package ch03_advance_sort.s01;

import java.util.Arrays;

import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 优化1
 * 子数组归并完成后，判断当前两个子数组是否需要归并，减少归并操作
 * 对于近乎有序的数组排序，建议采用此优化
 * 
 * @author Lee
 *
 */
public class MergeSortOp1 implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
//		int swapTimes = 0;	//对于完全有序的数组，无论是否优化，归并排序都比插入排序慢
		int swapTimes = n/100;
		ISorter[] sorters = { new InsertionSortOp1(), new MergeSort(), new MergeSortOp1() };
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
		//若当前数组的中间值前数据和中间值数据有序，表明经过上面的递归调用后，当前arr[l,r)范围内都有序，无需再次归并
		if (arr[mid - 1] > arr[mid])
			merge(arr, l, r);
	}

	// 对arr数组中[l,r)区间进行归并
	private void merge(int[] arr, int l, int r) {
		int[] aux = Arrays.copyOfRange(arr, l, r);
		int i = l, mid = (l + r) / 2, j = mid;
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
