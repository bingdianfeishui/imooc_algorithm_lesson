package ch03_advance_sort.s02;

import ch02_basic_sort.s02.InsertionSortOp1;
import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 快速排序  优化1 
 * 优化策略：对较短的子数组采用插入排序,效率稍有提升
 * 对乱序数组效率稍有提高，对近乎有序数组效率仍然低下且容易栈溢出
 * 注意：将RunConfig中的VM添加如下参数 -Xss10240k，即将线程栈空间设置为10MB，否则对较大数据量和近乎有序数组排序时容易栈溢出
 * @author Lee
 *
 */
public class QuickSortOp1 implements ISorter {
	public static void main(String[] args) {
		int n = 500000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new QuickSort(), new QuickSortOp1(), new MergeSortOp2()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr[l,r]区间进行快速排序
	private void quickSort(int[] arr, int l, int r) {
		//优化1：对size<=16的数组分段进行插入排序
		if (r - l <= 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}

		int p = partition(arr, l, r);
		quickSort(arr, l, p - 1);
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
