package ch04_heap_sort;

import ch03_advance_sort.s01.MergeSortOp2;
import ch03_advance_sort.s02.JdkDualPivotQuickSort;
import ch03_advance_sort.s02.QuickSortOp3;
import ch03_advance_sort.s02.QuickSortOp4;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 最大堆进行排序优化。 
 * 排序方法：直接通过数组构建一个堆，然后将各元素整理到正确位置，再推出。空间复杂度O(n)
 * 结论：n个元素逐个插入到一个空堆中，算法复杂度为O(nlogn), 而heapify的过程，算法复杂度为O(n)
 * @author Lee
 *
 */
public class HeapSort3 implements ISorter {
	public static void main(String[] args) {
		int n = 1000000;
		int swapTimes = (int) (n * 0.0001);
		ISorter[] sorters = { new JdkDualPivotQuickSort(), new QuickSortOp3(), new QuickSortOp4(), new MergeSortOp2(),
				new HeapSort1(), new HeapSort2(), new HeapSort3() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率显著提高
		int right = 50;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}

	@Override
	public int[] sort(int[] arr) {
		MaxHeapBasic heap = new MaxHeapOp2(arr);
		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] = heap.popMax();
		}
		return arr;
	}

}
