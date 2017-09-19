package ch03_advance_sort.s02;

import java.util.Random;

import ch02_basic_sort.s02.InsertionSortOp1;
import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 快速排序 优化4
 * 优化策略：采用QuickSort-3-Ways方法，partition时将数组分为三部分(<,=,>)，递归时避开相等部分元素，从而避免对相同元素的重复partition
 * 对大量重复数据的数组排序效率高；对近乎有序数组排序效率稍低，但可接受
 * 注意：将RunConfig中的VM添加如下参数 -Xss10240k，即将线程栈空间设置为10MB，否则对较大数据量和近乎有序数组排序时容易栈溢出
 * 
 * @author Lee
 *
 */
public class QuickSortOp4 implements ISorter {
	private static Random rand = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		int n = 500000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new QuickSort(), new QuickSortOp1(), new QuickSortOp2(), new QuickSortOp3(), new QuickSortOp4(), new MergeSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率显著提高
		int right = 50;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}

	@Override
	public int[] sort(int[] arr) {
		quickSort3Ways(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr[l,r]区间进行快速排序
	private void quickSort3Ways(int[] arr, int l, int r) {
		// 优化1：对size<=16的数组分段进行插入排序
		if (r - l <= 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}

		// 优化2：每次随机选取[l,r]范围内的某个元素与arr[l]对调，用作pivot，避免有序数组时总选取到开始的较小值作为分割枢轴
		DataSourceUtil.swap(arr, l, rand.nextInt(r - l + 1) + l);
		
		// 优化4：采用3-ways方式分割数组，将数组分割为arr[l+1...lt]<pivot, arr[lt+1...gt-1]=pivot,
		// arr[gt, r]>pivot
		int i = l + 1, lt = l, gt = r + 1;

		int pivot = arr[l];
		// 开始分割
		while (true) {
			if (i >= gt)
				break;
			if (arr[i] < pivot)
				DataSourceUtil.swap(arr, ++lt, i++);
			else if (arr[i] > pivot)
				DataSourceUtil.swap(arr, --gt, i);
			else
				i++;
		}
		DataSourceUtil.swap(arr, l, lt);
		
		quickSort3Ways(arr, l, lt - 1);
		quickSort3Ways(arr, gt, r);
	}

}
