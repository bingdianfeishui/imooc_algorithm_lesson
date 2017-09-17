package ch03_advance_sort.s01;

import java.util.Arrays;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import ch02_basic_sort.s03.BubbleSort;
import ch02_basic_sort.s03.BubbleSortOp1;
import ch02_basic_sort.s03.BubbleSortOp2;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * �鲢���� �Ż�2 
 * ���ĵݹ鵽�׵��ж���������С�������ֱ�Ӳ�������
 * @author Lee
 *
 */
public class MergeSortOp2 implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = {new BubbleSort(), new BubbleSortOp1(), new BubbleSortOp2(), new SelectionSort(),
				new InsertionSort(), new InsertionSortOp1(), new MergeSort(), new MergeSortOp1(), new MergeSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSort(arr, 0, arr.length);
		return arr;
	}

	// ��arr������[l,r)������й鲢����
	private void mergeSort(int[] arr, int l, int r) {
//		if (l >= r - 1)
//			return;
		//���ĵݹ鵽�׵��ж�����
		if ((r - l) < 15){
			InsertionSortOp1.sort(arr, l, r);
			return;
		}

		int mid = (l + r) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid, r);
		// ����ǰ������м�ֵǰ���ݺ��м�ֵ�������򣬱�����������ĵݹ���ú󣬵�ǰarr[l,r)��Χ�ڶ����������ٴι鲢
		if (arr[mid - 1] > arr[mid])
			merge(arr, l, r);
	}

	// ��arr������[l,r)������й鲢
	private void merge(int[] arr, int l, int r) {
		int[] aux = Arrays.copyOfRange(arr, l, r);
		int i = l, mid = (l + r) / 2, j = mid;
		for (int k = l; k < r; k++) {
			if (i >= mid)
				arr[k] = aux[j++ - l];
			else if (j >= r)
				arr[k] = aux[i++ - l];
			else if (aux[i - l] < aux[j - l])
				arr[k] = aux[i++ - l];
			else
				arr[k] = aux[j++ - l];
		}
	}

}
