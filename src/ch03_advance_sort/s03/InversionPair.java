package ch03_advance_sort.s03;

import java.util.Arrays;

import util.DataSourceUtil;
import util.MethodExeTimerUtil;

/**
 * 练习： 求逆序对的数量,即满足i<j且arr[i]>arr[j]的pair数量
 * 
 * @author Lee
 *
 */
public class InversionPair {

	public static void main(String[] args) throws Exception {
		int n = 6;
		int[] arr = DataSourceUtil.generateRandomIntArr(n, n);
		DataSourceUtil.printArr(arr);
		System.out.println("getCountByForce:    " + getCountByForce(arr));
		System.out.println("getCountByMerge:    " + getCountByMerge(arr));
		DataSourceUtil.printArr(arr);

		n = 10000;
		int[] data = DataSourceUtil.generateRandomIntArr(n, n);
		MethodExeTimerUtil.getMethodExecuteTime(InversionPair.class, "getCountByForce", new Class<?>[] { int[].class },
				true, true, new Object[] { data });
		MethodExeTimerUtil.getMethodExecuteTime(InversionPair.class, "getCountByMerge", new Class<?>[] { int[].class },
				true, true, new Object[] { data });

	}

	/**
	 * 暴力穷举求逆序对数量
	 * 时间复杂度O(n^2)
	 * @param arr
	 * @return
	 */
	private static int getCountByForce(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j])
					count++;
			}
		}
		return count;
	}

	/**
	 * 利用归并排序，在归并的同时进行逆序对计数再累加
	 * 预期时间复杂度可降为O(nlogn)
	 * @param arr
	 * @return
	 */
	private static int getCountByMerge(int[] arr) {
		return mergeCounter(arr, 0, arr.length - 1);
	}

	private static int mergeCounter(int[] arr, int l, int r) {
		if (l >= r)
			return 0;
		int mid = (l + r) / 2;
		int c1 = mergeCounter(arr, l, mid);
		int c2 = mergeCounter(arr, mid + 1, r);
		int c3 = merge(arr, l, mid, r);
		return c1 + c2 + c3;
	}

	/**
	 * 在归并同时求逆序对的数量并累加返回。由于子数组已经排序，所以可以方便的分块批量累加计数，而不用每个元素依次判断。见下方count += (mid - i + 1)注释
	 * @param arr
	 * @param l
	 * @param mid
	 * @param r
	 * @return
	 */
	private static int merge(int[] arr, int l, int mid, int r) {
		int count = 0;
		int[] aux = Arrays.copyOfRange(arr, l, r + 1);
		// 1,2,2, 3,5,6
		int i = l, j = mid + 1;
		for (int k = l; k <= r; k++) {
			if (i > mid)
				arr[k] = aux[j++ - l];
			else if (j > r)
				arr[k] = aux[i++ - l];
			else if (aux[i - l] > aux[j - l]) {
				//归并时计算逆序对数量。因为子数组已经排序，所以arr[i ... mid]与arr[j]都构成逆序对，所以累加数量为(mid - i + 1)
				count += (mid - i + 1);
				arr[k] = aux[j++ - l];
			} else
				arr[k] = aux[i++ - l];
		}

		return count;
	}

}
