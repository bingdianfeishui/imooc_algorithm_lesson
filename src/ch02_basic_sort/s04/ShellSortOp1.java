package ch02_basic_sort.s04;

import util.ISorter;

/**
 * 希尔排序 代码简化版
 * 对于乱序数组性能稍好，少一层循环。对有序数组性能可能更差，因为会多执行插入排序次数
 * 即对每个间隙划分的小数组不用每次都对整个小数组执行插入排序，而是各组交替、数量递增的进行排序。
 * 这样保证每次排序基本都是对有序数组排序，效率变高
 * 通过counter计数器可知，两种实现移位的次数一致
 * @author Lee
 *
 */
public class ShellSortOp1 implements ISorter {

	@Override
	public int[] sort(int[] arr) {
//		int counter = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int e = arr[i], j;
				for (j = i; j >= gap && e < arr[j - gap]; j -= gap) {
//					counter++;
					arr[j] = arr[j - gap];
				}
				arr[j] = e;
//				System.out.println(Arrays.toString(arr) + " gap=" + gap + "i=" + i);
			}
		}
//		System.out.println(counter);
		return arr;
	}

}
