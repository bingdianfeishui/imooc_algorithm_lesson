package ch02_basic_sort.s01;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * ѡ������ O(n^2)
 * @author Lee
 *
 */
public class SelectionSort implements ISorter{

	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new SelectionSort()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	/**
	 * ѡ������
	 * ʱ�临�Ӷ�O(n^2)
	 * ʵ��˼�룺����ѭ�����ڲ�ѭ��ÿ��ѡ��ʣ�µ���������Сֵ��������Ȼ���뵱ǰԪ�ضԵ�
	 */
	public int[] sort(int[] arr) {
		for(int i = 0; i < arr.length; i++){
			int minIndex = i;
			for(int j = i + 1; j < arr.length; j++){
				if(arr[j] < arr[minIndex])
					minIndex = j;
			}
			DataSourceUtil.swap(arr, minIndex, i);
		}
		return arr;
	}

}
