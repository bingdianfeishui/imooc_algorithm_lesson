package ch03_advance_sort.s01;

import util.DataSourceUtil;
import util.ISorter;

/**
 * 归并排序 优化1 B 与归并排序 优化1的区别：将mergeSortB mergeB右边界改为闭区间
 * 测试表明：比开区间的运行慢
 * @author Lee
 *
 */
public class MergeSortOp1B implements ISorter {
	public static void main(String[] args) {
		int n = 13;
		int[] arr = DataSourceUtil.generateRandomIntArr(n, n);
		new MergeSortOp1B().sort(arr);
	}

	@Override
	public int[] sort(int[] arr) {
		mergeSortB(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr数组中[l,r]区间进行归并排序
	private void mergeSortB(int[] arr, int l, int r) {
		if (l >= r)
			return;
		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		// 若当前数组的中间值前数据和中间值数据有序，表明经过上面的递归调用后，当前arr[l,r]范围内都有序，无需再次归并
		if (arr[mid] > arr[mid + 1])
			MergeSortB.mergeB(arr, l, mid, r);
	}

}
