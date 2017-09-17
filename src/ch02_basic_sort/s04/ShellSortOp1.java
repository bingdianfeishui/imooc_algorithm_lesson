package ch02_basic_sort.s04;

import util.ISorter;

/**
 * ϣ������ ����򻯰�
 * �����������������Ժã���һ��ѭ�����������������ܿ��ܸ����Ϊ���ִ�в����������
 * ����ÿ����϶���ֵ�С���鲻��ÿ�ζ�������С����ִ�в������򣬶��Ǹ��齻�桢���������Ľ�������
 * ������֤ÿ������������Ƕ�������������Ч�ʱ��
 * ͨ��counter��������֪������ʵ����λ�Ĵ���һ��
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
