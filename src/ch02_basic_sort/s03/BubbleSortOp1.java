package ch02_basic_sort.s03;

import ch02_basic_sort.s01.SelectionSort;
import ch02_basic_sort.s02.InsertionSortOp1;
import ch02_basic_sort.s02.InsertionSort;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * ð�������Ż�1 ��ĳ��ѭ��û�н��н�����˵����������ɣ���ǰ����
 * 
 * @author 60238
 *
 */
public class BubbleSortOp1 implements ISorter {

	public static void main(String[] args) throws Exception {
		ISorter[] sorters = { new BubbleSort(), new BubbleSortOp1(), new SelectionSort(), new InsertionSort(),
				new InsertionSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, 20000, 200);
	}

	@Override
	public int[] sort(int[] arr) {
		boolean flag = true;
		while(flag){
			flag = false;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] < arr[i - 1]) {
					DataSourceUtil.swap(arr, i, i - 1);
					flag = true;
				}
			}
		}
		return arr;
	}

}
