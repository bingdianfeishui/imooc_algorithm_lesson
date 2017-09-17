package ch02_basic_sort.s02;

import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * �Ż���Ĳ������򣬲���O(1)�Ŀռ任ʱ�䡣
 * ˼·����iλ�õ�Ԫ�ؿ���һ��e��Ȼ��i֮ǰ��j-1��������Ԫ��������e�Ƚϣ���e��������һλ����ֵ��jλ�ã��� ����jλ�ü�ΪeӦ�ò����λ��
 * 
 * @author Lee
 *
 */
public class InsertionSortOp1 implements ISorter {
	public static void main(String[] args) throws Exception {
		int n = 50000;
		int swapTimes = n / 100;
		ISorter[] sorters = { new InsertionSort(), new InsertionSortOp1() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
	}

	@Override
	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int e = arr[i], j; // �ݴ�һ��e����Ϊ���ܻḲ��arr[i]��jΪeӦ�ò����λ��
			for (j = i; j > 0 && e < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
		return arr;
	}

	// ��arr������[l, r)������Χ��������
	public static int[] sort(int[] arr, int l, int r) {
//		if (l < 0)
//			l = 0;
//		if (r > arr.length)
//			r = arr.length;
//		if (l - r >= 0)
//			return arr;

		for (int i = l + 1; i < r; i++) {
			int e = arr[i], j; // �ݴ�һ��e����Ϊ���ܻḲ��arr[i]��jΪeӦ�ò����λ��
			for (j = i; j > l && e < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
		return arr;
	}
}
