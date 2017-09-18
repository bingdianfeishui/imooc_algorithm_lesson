package ch02_basic_sort;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import ch02_basic_sort.s04.ShellSort;
import ch02_basic_sort.s04.ShellSortOp1;
import util.ISorter;
import util.MethodExeTimerUtil;

public class BasicSortTest {
	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		// 冒泡排序太慢，不带他玩儿
//		ISorter[] sorters = { new BubbleSort(), new BubbleSortOp1(), new BubbleSortOp2(), new SelectionSort(),
//				new InsertionSort(), new InsertionSortOp1(), new ShellSort(), new ShellSortOp1() };
		ISorter[] sorters = { new SelectionSort(), new InsertionSort(), new InsertionSortOp1(), new ShellSort(),
				new ShellSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}
}
