package ch03_advance_sort.s02;

import java.util.Random;

import ch02_basic_sort.s02.InsertionSortOp1;
import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 快速排序  优化2 
 * 优化策略：pivot选取采用随机抽取法，能显著提高近乎有序数组的排序效率
 * 缺点：对有大量重复数据的数组排序效率仍较低下。原因是从单端开始检索判断，使大量相等的元素被分配在分割后的某一端数组内，造成分割极不均匀，产生冗余的分割次数，使快排的时间复杂度严重退化，最差到O(n^2)
 * 注意：将RunConfig中的VM添加如下参数 -Xss10240k，即将线程栈空间设置为10MB，否则对较大数据量和近乎有序数组排序时容易栈溢出
 * @author Lee
 *
 */
public class QuickSortOp2 implements ISorter {
	private static Random rand = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		int n = 500000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new QuickSort(), new QuickSortOp1(), new QuickSortOp2(), new MergeSortOp2()};
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);
		
		//对有大量重复数据的数组排序效率低下
		int right = 100;
		System.out.printf("\n大量重复数据数组排序： size=%d, range=[0, %d)\n", n, right);
		int[] sourceData = DataSourceUtil.generateRandomIntArr(n, right);
		MethodExeTimerUtil.batchExecuteSorters(sorters, sourceData, true);
	}

	@Override
	public int[] sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	// 对arr[l,r]区间进行快速排序
	private void quickSort(int[] arr, int l, int r) {
		//优化1：对size<=16的数组分段进行插入排序
		if (r - l <= 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}
		int p = partition(arr, l, r);
		quickSort(arr, l, p - 1);
		quickSort(arr, p + 1, r);
	}

	/**
	 * 对arr[l,r]进行分割，key = arr[l],使得arr[l...j]<key<=arr[j+1...r]
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	private int partition(int[] arr, int l, int r) {
		//优化2：每次随机选取[l,r]范围内的某个元素与arr[l]对调，用作pivot，避免有序数组时总选取到开始的较小值作为分割枢轴
		DataSourceUtil.swap(arr, l, rand.nextInt(r - l + 1) + l);
		
		int pivot = arr[l], j = l;
		for (int i = l + 1; i <= r; i++) {
			if (arr[i] < pivot)
				DataSourceUtil.swap(arr, i, ++j);
		}
		DataSourceUtil.swap(arr, l, j);
		return j;
	}

}
