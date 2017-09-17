package ch03_advance_sort.s01;

import ch02_basic_sort.s02.InsertionSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * �鲢���� �Ż�1
 * ������鲢��ɺ��жϵ�ǰ�����������Ƿ���Ҫ�鲢�����ٹ鲢����
 * ���ڽ���������������򣬽�����ô��Ż�
 * 
 * @author Lee
 *
 */
public class MergeSortOp1 implements ISorter {
	public static void main(String[] args) {
		int n = 50000;
//		int swapTimes = 0;	//������ȫ��������飬�����Ƿ��Ż����鲢���򶼱Ȳ���������
		int swapTimes = n/100;
		ISorter[] sorters = { new InsertionSortOp1(), new MergeSort(), new MergeSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSort(arr, 0, arr.length);
		return arr;
	}

	// ��arr������[l,r)������й鲢����
	private void mergeSort(int[] arr, int l, int r) {
		if (l >= r - 1)
			return;
		int mid = (l + r) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid, r);
		//����ǰ������м�ֵǰ���ݺ��м�ֵ�������򣬱�����������ĵݹ���ú󣬵�ǰarr[l,r)��Χ�ڶ����������ٴι鲢
		if (arr[mid - 1] > arr[mid])
			MergeSort.merge(arr, l, mid, r);
	}



}
