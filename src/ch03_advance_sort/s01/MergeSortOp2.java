package ch03_advance_sort.s01;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 优化2 更改递归到底的判断条件，对小数组采用直接插入排序
 * 
 * @author Lee
 *
 */
public class MergeSortOp2 implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new SelectionSort(), new InsertionSort(), new InsertionSortOp1(), new MergeSort(),
				new MergeSortOp1(), new MergeSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSort(arr, 0, arr.length);
		return arr;
	}

	// 对arr数组中[l,r)区间进行归并排序
	private void mergeSort(int[] arr, int l, int r) {
		// if (l >= r - 1)
		// return;
		// 更改递归到底的判断条件
		if ((r - l) < 15) {
			InsertionSortOp1.sort(arr, l, r);
			return;
		}

		int mid = (l + r) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid, r);
		// 若当前数组的中间值前数据和中间值数据有序，表明经过上面的递归调用后，当前arr[l,r)范围内都有序，无需再次归并
		if (arr[mid - 1] > arr[mid])
			MergeSort.merge(arr, l, mid, r);
	}

}
