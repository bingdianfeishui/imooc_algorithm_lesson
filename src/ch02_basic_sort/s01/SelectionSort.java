package ch02_basic_sort.s01;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 选择排序 O(n^2)
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
	 * 选择排序
	 * 时间复杂度O(n^2)
	 * 实现思想：两层循环，内层循环每次选出剩下的序列中最小值的索引，然后与当前元素对调
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
