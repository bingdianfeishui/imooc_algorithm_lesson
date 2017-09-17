package util;

import java.util.Arrays;
import java.util.Random;

public class DataSourceUtil {
	// ���ɳ���Ϊn����ΧΪ[0, r)�������������
	public static int[] generateRandomIntArr(int n, int r) {
		return generateRandomIntArr(n, 0, r);
	}

	// ���ɳ���Ϊn����ΧΪ[l, r)�������������
	public static int[] generateRandomIntArr(int n, int l, int r) {
		int[] arr = new int[n];
		Random seed = new Random(System.currentTimeMillis());
		for (int i = 0; i < n; i++) {
			arr[i] = seed.nextInt(n) % (r - l) + l;
		}
		return arr;
	}

	// ���ɳ���Ϊn�����������[0,n)��Χ�ڵ�����
	// ԭ����[0,n)������飬Ȼ�����ѡ����������swapTimes��
	public static int[] generateAlmostSortedIntArr(int n, int swapTimes) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Random seed = new Random(System.currentTimeMillis());
		for (int i = 0; i < swapTimes; i++) {
			int pos1 = seed.nextInt(n) % n;
			int pos2 = seed.nextInt(n) % n;
			swap(arr, pos1, pos2);
		}
		return arr;
	}

	public static void swap(int[] arr, int i, int j) {
		// swap 1
		 int temp = arr[i];
		 arr[i] = arr[j];
		 arr[j] = temp;

		// swap 2
		// arr[i] = arr[i] + arr[j];
		// arr[j] = arr[i] - arr[j];
		// arr[i] = arr[i] - arr[j];

		// swap 3
		// if (arr[i] != arr[j]) {
		// arr[i] ^= arr[j];
		// arr[j] ^= arr[i];
		// arr[i] ^= arr[j];
		// }
	}

	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				System.out.printf("Not Sorted: arr[%d]=%d > arr[%d]=%d\n", i, arr[i], i + 1, arr[i + 1]);
				return false;
			}
		}
		// System.out.println("Sorted!");
		return true;
	}

	public static void printArr(int[] arr) {
		printArr(arr, 5000);
	}

	public static void printArr(int[] arr, int maxLength) {
		if (arr.length > maxLength) {
			System.out.println("Arrays.length=" + arr.length + " is too large, stop printing. ");
			return;
		}
		System.out.println(Arrays.toString(arr));
	}
}
