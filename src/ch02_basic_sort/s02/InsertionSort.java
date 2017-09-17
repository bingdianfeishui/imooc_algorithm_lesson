package ch02_basic_sort.s02;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * ������������
 * �ص㣺��������������O(n^2)�����Ի����������������ǳ��죬��С�ɵ�O(n)
 * ��ǰԪ��������ǰһ��Ԫ�رȽϽ���(ǰ���Ԫ����֮ǰ��ѭ���Ѿ��������)
 * @author Lee
 *
 */
public class InsertionSort implements ISorter{
	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSort()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			//�ӵ�ǰ��ǰ�Һ���λ�ò��룬�ȵ�ǰԪ�ش��Ԫ�����������
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				DataSourceUtil.swap(arr, j, j - 1);
			}
		}
		return arr;
	}
}
