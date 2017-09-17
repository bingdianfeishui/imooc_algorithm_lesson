package ch03_advance_sort.s01;

import java.util.Arrays;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * �鲢���� 
 * ����ͨ���Ψһ���𣺽�mergeSort��merge�ұ߽��Ϊ������
 * ���Ա������ȿ������������
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

	// ��arr������[l,r]������й鲢����
	private void mergeSortB(int[] arr, int l, int r) {
		if (l >= r)
			return;
		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		mergeB(arr, l, mid, r);
	}

	// ��arr������[l,r]������й鲢
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
