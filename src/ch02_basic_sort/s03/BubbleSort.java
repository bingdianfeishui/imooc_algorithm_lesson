package ch02_basic_sort.s03;

import util.DataSourceUtil;
import util.ISorter;

/**
 * 冒泡排序严格定义实现
 * @author Lee
 *
 */
public class BubbleSort implements ISorter {

	@Override
	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			//每次都从头开始判断并交换，到上一次最大数所在位置之前结束
			//内层循环每次完毕，都会把当前未排序数组中的最大数放到未排序队列的最后
			for (int j = 1; j < arr.length - i + 1; j++) {
				if (arr[j] < arr[j - 1])
					DataSourceUtil.swap(arr, j, j - 1);
			}
		}
		return arr;
	}

}
