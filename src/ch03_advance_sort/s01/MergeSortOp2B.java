package ch03_advance_sort.s01;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * �鲢���� �Ż�2 B mergeSortB��mergeB�ұ߽��Ϊ������
 * ���Ա������ȿ���������п�
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

	// ��arr������[l,r]������й鲢����
	private void mergeSortB(int[] arr, int l, int r) {
		// if (l >= r - 1)
		// return;
		// ���ĵݹ鵽�׵��ж�����
		if ((r - l) < 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}

		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		// ����ǰ������м�ֵǰ���ݺ��м�ֵ�������򣬱�����������ĵݹ���ú󣬵�ǰarr[l,r]��Χ�ڶ����������ٴι鲢
		if (arr[mid] > arr[mid + 1])
			MergeSortB.mergeB(arr, l, mid, r);
	}

}
