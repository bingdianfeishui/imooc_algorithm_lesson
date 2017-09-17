package ch02_basic_sort.s03;

import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * ð�������Ż�2 ��¼���һ�ν�����λ��i���´�ѭ��ֻѭ������λ��֮ǰ
 * ���һ�ν�����λ�ú���������Ѿ��ź���������ѭ���ж�
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
