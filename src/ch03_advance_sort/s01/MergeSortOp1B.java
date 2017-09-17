package ch03_advance_sort.s01;

import util.DataSourceUtil;
import util.ISorter;

/**
 * �鲢���� �Ż�1 B ��鲢���� �Ż�1�����𣺽�mergeSortB mergeB�ұ߽��Ϊ������
 * ���Ա������ȿ������������
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

	// ��arr������[l,r]������й鲢����
	private void mergeSortB(int[] arr, int l, int r) {
		if (l >= r)
			return;
		int mid = (l + r) / 2;
		mergeSortB(arr, l, mid);
		mergeSortB(arr, mid + 1, r);
		// ����ǰ������м�ֵǰ���ݺ��м�ֵ�������򣬱�����������ĵݹ���ú󣬵�ǰarr[l,r]��Χ�ڶ����������ٴι鲢
		if (arr[mid] > arr[mid + 1])
			MergeSortB.mergeB(arr, l, mid, r);
	}

}
