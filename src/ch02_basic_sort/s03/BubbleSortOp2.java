package ch02_basic_sort.s03;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 冒泡排序优化2 记录最后一次交换的位置i，下次循环只循环到此位置之前
 * 最后一次交换的位置后面的序列已经排好序，无需再循环判断
 * @author 60238
 *
 */
public class BubbleSortOp2 implements ISorter {

	public static void main(String[] args) throws Exception {
		ISorter[] sorters = { new BubbleSort(), new BubbleSortOp1(), new BubbleSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, 50000, 500);
	}

	@Override
	public int[] sort(int[] arr) {
		int flag = arr.length, k;
		while (flag > 0) {
			k = flag;
			flag = 0;
			for (int i = 1; i < k; i++) {
				if (arr[i] < arr[i - 1]) {
					DataSourceUtil.swap(arr, i, i - 1);
					flag = i;
				}
			}
		}
		return arr;
	}

}
