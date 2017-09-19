package ch03_advance_sort.s02;

import java.util.Arrays;

import ch02_basic_sort.s04.ShellSortOp1;
import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * jdk自带快速排序
 * 在jdk7以上实现为Dual-Pivot QuickSort，jdk6及以下为3-Ways QuickSort(?)
 * @author 60238
 *
 */
public class JdkDualPivotQuickSort implements ISorter{

	public static void main(String[] args) {
		int n = 5000000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new JdkDualPivotQuickSort(), new QuickSortOp3(), new QuickSortOp4(), new MergeSortOp2(), new ShellSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率显著提高
		int right = 50;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}
	
	@Override
	public int[] sort(int[] a) {
		Arrays.sort(a);
		return a;
	}

}
