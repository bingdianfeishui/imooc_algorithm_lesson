package ch03_advance_sort.s02;

import java.util.Random;

import ch02_basic_sort.s02.InsertionSortOp1;
import ch03_advance_sort.s01.MergeSortOp2;
import util.DataSourceUtil;
import util.ISorter;
import util.MethodExeTimerUtil;

/**
 * 快速排序 优化3
 * 优化策略:采用新的partition算法，从两头开始检索，将等于pivot的元素较均匀的分配到两段子数组中，从而提升子递归时的分数组的切割均匀性，大幅提升在大量重复数据下的排序效率
 * 注意：将RunConfig中的VM添加如下参数 -Xss10240k，即将线程栈空间设置为10MB，否则对较大数据量和近乎有序数组排序时容易栈溢出
 * 
 * @author Lee
 *
 */
public class QuickSortOp3 implements ISorter {
	private static Random rand = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		int n = 500000;
		int swapTimes = (int) (n *0.0001);
		ISorter[] sorters = { new QuickSort(), new QuickSortOp1(), new QuickSortOp2(),new QuickSortOp3(), new MergeSortOp2() };
		MethodExeTimerUtil.batchSortIntMethodsExecution(sorters, n, swapTimes);

		// 对有大量重复数据的数组排序效率提高较大
		int right = 50;
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
		// 优化1：对size<=16的数组分段进行插入排序
		if (r - l <= 15) {
			InsertionSortOp1.sort(arr, l, r + 1);
			return;
		}
		int p = partition2(arr, l, r);
		quickSort(arr, l, p - 1);
		quickSort(arr, p + 1, r);
	}

	/**
	 * 优化3：新的partion算法，从两头分别检索，已达到等于pivot的元素在分割后尽量均分的目的
	 * 对arr[l,r]进行分割，key = arr[l],最后使得arr[l...j]<=key<=arr[j+1...r]
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	private int partition2(int[] arr, int l, int r) {
		// 优化2：每次随机选取[l,r]范围内的某个元素与arr[l]对调，用作pivot，避免有序数组时总选取到开始的较小值作为分割枢轴
		DataSourceUtil.swap(arr, l, rand.nextInt(r - l + 1) + l);

		// arr[l+1...i) <= pivot; arr(j...r] >= pivot
		int pivot = arr[l], i = l + 1, j = r;
		while (true) {
			while (i <= r && arr[i] < pivot)
				i++;
			while (j >= l + 1 && arr[j] > pivot)
				j--;
			
			//整个循环完成时，i-1 = j，即arr[l...j]<=pivot<=arr[j+1...r]
			if (i > j)
				break;

			DataSourceUtil.swap(arr, i++, j--);
			//每次循环到此处，可保证arr[l...i)<=pivot<=arr[j+1...r]
		}

		//此步不可省略。将arr[l]放到原本在[l...r]区间内应该在的位置(对此单个元素排好序，每次递归都对一个元素排好序，最后即对全部数组排好序)。
		DataSourceUtil.swap(arr, l, j); 
		return j;
	}

}
