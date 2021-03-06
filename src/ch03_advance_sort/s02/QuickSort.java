package ch03_advance_sort.s02;

import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 快速排序 标准实现
 * 对乱序数组排序效率很高，对近乎有序（升序）的数组，效率低下（最低可退化至O(n^2)），且容易栈溢出Stackoverflow（可更改JVM参数来改善）
 * 注意：将RunConfig中的VM添加如下参数 -Xss10240k，即将线程栈空间设置为10MB，否则对较大数据量和近乎有序数组排序时容易栈溢出
 * @author Lee
 *
 */
public class QuickSort implements ISorter {
	public static void main(String[] args) {
		int n = 1000000;
		int swapTimes = n / 1000;
		ISorter[] sorters = {new QuickSort(), new MergeSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr[l,r]区间进行快速排序
	private void quickSort(int[] arr, int l, int r) {
		if (l >= r)
			return;

		int p = partition(arr, l, r);
		quickSort(arr, l, p);
		quickSort(arr, p + 1, r);
	}

	/**
	 * 对arr[l,r]进行分割，key = arr[l],使得arr[l...j]<key<=arr[j+1...r]
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	private int partition(int[] arr, int l, int r) {
		int pivot = arr[l], j = l;
		for (int i = l + 1; i <= r; i++) {
			if (arr[i] < pivot)
				DataSourceUtil.swap(arr, i, ++j);
		}
		DataSourceUtil.swap(arr, l, j);
		return j;
	}

}
