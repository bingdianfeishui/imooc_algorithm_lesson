package ch04_heap_sort;

import ch03_advance_sort.s01.MergeSortOp2;
import ch03_advance_sort.s02.JdkDualPivotQuickSort;
import ch03_advance_sort.s02.QuickSortOp3;
import ch03_advance_sort.s02.QuickSortOp4;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 运用最大堆的原理在源数组上直接进行排序。 无需额外数组空间，空间复杂度O(1) 注意：索引从0开始
 * 
 * @author Lee
 *
 */
public class HeapSort4 implements ISorter {
	public static void main(String[] args) {
		int n = 1000000;
		int swapTimes = (int) (n * 0.0001);
		ISorter[] sorters = { new JdkDualPivotQuickSort(), new QuickSortOp3(), new QuickSortOp4(), new MergeSortOp2(),
				new HeapSort1(), new HeapSort2(), new HeapSort3(), new HeapSort4() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率显著提高
		int right = 50;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}

	@Override
	public int[] sort(int[] arr) {
		int n = arr.length;
		// 数组原地堆排序，索引从0开始！！！
		for (int i = (n - 1) / 2; i >= 0; i--) {
			shiftDown(arr, n, i);
		}

		// 每次循环将数组头部最大元素与最后元素交换
		for (int i = n - 1; i > 0; i--) {
			DataSourceUtil.swap(arr, 0, i);
			// 然后在除最后n-1-i个元素后剩下的堆中将首部元素进行下移
			shiftDown(arr, i, 0);
		}
		return arr;
	}

	private void shiftDown(int[] a, int n, int k) {
		int temp = a[k], j;
		while ((j = 2 * k + 1) < n) {
			if (j + 1 < n && a[j + 1] > a[j])
				j += 1;
			if (a[j] > temp) {
				a[k] = a[j];
				k = j;
			} else
				break;
		}
		a[k] = temp;
	}

}
