package ch03_advance_sort.s01;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 优化2 B mergeSortB和mergeB右边界改为闭区间
 * @author Lee
 *
 */
public class MergeSortOp2B implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new SelectionSort(), new InsertionSort(), new InsertionSortOp1(), new MergeSort(),
				new MergeSortOp1(), new MergeSortOp2B() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSortB(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr数组中[l,r]区间进行归并排序
	private void mergeSortB(int[] arr, int l, int r) {
		// if (l >= r - 1)
		// return;
		// 更改递归到底的判断条件
		if ((r - l) < 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}

		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		// 若当前数组的中间值前数据和中间值数据有序，表明经过上面的递归调用后，当前arr[l,r]范围内都有序，无需再次归并
		if (arr[mid] > arr[mid + 1])
			MergeSortB.mergeB(arr, l, mid, r);
	}

}
