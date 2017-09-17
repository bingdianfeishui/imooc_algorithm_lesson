package ch02_basic_sort.s03;

import util.DataSourceUtil;
import util.ISorter;

/**
 * ð�������ϸ���ʵ��
 * @author Lee
 *
 */
public class BubbleSort implements ISorter {

	@Override
	public int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			//ÿ�ζ���ͷ��ʼ�жϲ�����������һ�����������λ��֮ǰ����
			//�ڲ�ѭ��ÿ����ϣ�����ѵ�ǰδ���������е�������ŵ�δ������е����
			for (int j = 1; j < arr.length - i + 1; j++) {
				if (arr[j] < arr[j - 1])
					DataSourceUtil.swap(arr, j, j - 1);
			}
		}
		return arr;
	}

}
