package ch02_basic_sort.s02;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 优化后的插入排序，采用O(1)的空间换时间。
 * 思路：将i位置的元素拷贝一份e，然后将i之前的j-1索引处的元素依次与e比较，比e大的则后移一位（赋值给j位置）。 最后的j位置即为e应该插入的位置
 * 
 * @author Lee
 *
 */
public class InsertionSortOp1 implements ISorter {
	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSort(), new InsertionSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int e = arr[i], j; // 暂存一份e，因为可能会覆盖arr[i]。j为e应该插入的位置
			for (j = i; j > 0 && e < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
		return arr;
	}

	/**
	 * 对arr数组中[l, r)索引范围进行排序
	 * @param arr
	 * @param l inclusion
	 * @param r exclusion
	 * @return
	 */
	public static int[] sort(int[] arr, int l, int r) {
//		if (l < 0)
//			l = 0;
//		if (r > arr.length)
//			r = arr.length;
//		if (l - r >= 0)
//			return arr;

		for (int i = l + 1; i < r; i++) {
			int e = arr[i], j; // 暂存一份e，因为可能会覆盖arr[i]。j为e应该插入的位置
			for (j = i; j > l && e < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
		return arr;
	}
}
