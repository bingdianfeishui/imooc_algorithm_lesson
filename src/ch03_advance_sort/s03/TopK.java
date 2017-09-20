package ch03_advance_sort.s03;

import java.util.Arrays;
import java.util.Random;

import util.DataSourceUtil;

/**
 * Top K问题：含有n个元素(不相同)的乱序数组，求前k大的数或第k大的数(0<k<n)
 * 解法可参考：http://blog.csdn.net/ts173383201/article/details/7839482
 * 
 * @author Lee
 *
 */
public class TopK {
	private static Random rand = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		int n = 10000000, k = 1000000;//rand.nextInt(1000000);
		int[] a1 = DataSourceUtil.generateAlmostSortedIntArr(n, n + n);
		int[] a2 = Arrays.copyOf(a1, n);
		System.out.printf("乱序不重复数组：size=%d, range=[0,%d), k=%d\n", n, n, k);

		long start = System.nanoTime();
		int[] k1 = topK1(a1, k);
		long time1 = System.nanoTime() - start;
		System.out.printf("topK1 execution time1: %9.6fs\n", time1 / 1000000000.0);

		start = System.nanoTime();
		int[] k2 = topK2(a2, k);
		long time2 = System.nanoTime() - start;
		System.out.printf("topK2 execution time2: %9.6fs\n", time2 / 1000000000.0);
		System.out.println("time1 / time2 = " + time1 / time2);
		// System.out.println("topK1：" + Arrays.toString(k1));
		// System.out.println("topK1：" + Arrays.toString(k2));
		System.out.println("Result of topK1 == topK2: " + arraysEqualIgnoreOrder(k1, k2));
	}

	//
	private static boolean arraysEqualIgnoreOrder(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return Arrays.equals(a1, a2);
	}

	/**
	 * 求前k大 解法1：最暴力的解法,先进行完整排序，再求前k大 可采用快速排序、堆排序或java7自带的Dual-Pivot
	 * 用系统自带quickSort来进行排序，然后选出前k大
	 * 
	 * @param source
	 * @param k
	 * @return
	 */
	private static int[] topK1(int[] source, int k) {
		if (k >= source.length)
			return source;
		Arrays.sort(source);
		return Arrays.copyOfRange(source, source.length - k, source.length);
	}

	/**
	 * 求前k大 解法2：
	 * 类快速排序思想，按降序分割数组。若前半部分数据个数n<k，则对后半段数组继续分选出前(k-n)大；若前半部分数据个数n>k，则在进行细分。
	 * 由于只是筛选大小不完整排序，且每次都只处理部分数组（期望值为一半），所以效率很高 返回值为数组的前k大个元素的新数组，但没有排序！！！
	 * 
	 * @param source
	 * @param k
	 * @return
	 */
	private static int[] topK2(int[] source, int k) {
		if (k >= source.length)
			return source;
		partitionTopK(source, 0, source.length - 1, k);
		int[] a = Arrays.copyOf(source, k);
		// Arrays.sort(a);
		return a;
	}

	/**
	 * 对a[l...r]进行降序分割，若前半部分数据个数n<k，则对后半段数组继续递归选出前(k-n)大；若前半部分数据个数n>k，则在进行递归细分选出n大。
	 * 由于没有相等元素，所以采用QuickSortOp3的分割方式
	 * 
	 * @param a
	 * @param l
	 * @param r
	 * @param k
	 */
	private static void partitionTopK(int[] a, int l, int r, int k) {
		if (k == 0 || k >= r - l + 1 || l >= r)
			return;

		// 分割前随机抽取pivot元素
		DataSourceUtil.swap(a, l, rand.nextInt(r - l + 1) + l);

		// 分割a[l...r]部分，使a[l...j-1] >= pivot && a[j + 1...r] <= pivot [降序排列]
		int pivot = a[l], i = l + 1, j = r;
		while (true) {
			while (i <= r && a[i] > pivot)
				i++;
			while (j >= l + 1 && a[j] < pivot)
				j--;
			if (i > j)
				break;
			DataSourceUtil.swap(a, i++, j--);
		}
		DataSourceUtil.swap(a, l, j);

		if (j - l + 1 > k)
			partitionTopK(a, l, j - 1, k);
		else if (j - l + 1 < k)
			partitionTopK(a, j + 1, r, k - (j - l + 1));
		else
			return;
	}
}
