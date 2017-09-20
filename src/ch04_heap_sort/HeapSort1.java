package ch04_heap_sort;

import ch03_advance_sort.s01.MergeSortOp2;
import ch03_advance_sort.s02.QuickSortOp3;
import ch03_advance_sort.s02.QuickSortOp4;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 利用基本实现的最大堆进行排序。
 * 排序方法：构建一个足够大的最大堆，将数组元素依次压入，再按堆顺序依次取出。空间复杂度O(n)
 * 由于MaxHeapBasic实现中shiftUp、shiftDown中swap操作很多，所以效率不高
 * @author Lee
 *
 */
public class HeapSort1 implements ISorter {
	public static void main(String[] args) {
		int n = 1000000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new QuickSortOp3(), new QuickSortOp4(), new MergeSortOp2(), new HeapSort1()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率显著提高
		int right = 50;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}

	@Override
	public int[] sort(int[] arr) {
		MaxHeapBasic heap = new MaxHeapBasic(arr.length + 1);
		for (int i = 0; i < arr.length; i++) {
			heap.insert(arr[i]);
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] = heap.popMax();
		}
		return arr;
	}

}
