package ch03_advance_sort.s01;

import java.util.Arrays;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 归并排序 
 * 与普通版的唯一区别：将mergeSort和merge右边界改为闭区间
 * @author Lee
 *
 */
public class MergeSortB implements ISorter {
	public static void main(String[] args) {
		int n = 11;
		int swapTimes = n / 100;
		ISorter[] sorters = {new MergeSortB() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSortB(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr数组中[l,r]区间进行归并排序
	private void mergeSortB(int[] arr, int l, int r) {
		if (l >= r)
			return;
		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		mergeB(arr, l, mid, r);
	}

	// 对arr数组中[l,r]区间进行归并
	static void mergeB(int[] arr, int l, int mid, int r) {
		int[] aux = Arrays.copyOfRange(arr, l, r + 1);
		int i = l, j = mid + 1;
		for (int k = l; k <= r; k++) {
			if (i > mid)
				arr[k] = aux[j++ - l];
			else if (j > r)
				arr[k] = aux[i++ - l];
			else if (aux[i - l] < aux[j - l])
				arr[k] = aux[i++ - l];
			else
				arr[k] = aux[j++ - l];

		}
	}

}
