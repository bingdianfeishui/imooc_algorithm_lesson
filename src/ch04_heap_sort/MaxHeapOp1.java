package ch04_heap_sort;

import java.util.Random;

/**
 * 最大堆 经典实现： 1. 索引从1开始 2. 移位操作采用swap
 * 优化1：shiftUp和shiftDown时，先移位，最后交换。类插入排序Op1的优化
 * @author Lee
 *
 */
public class MaxHeapOp1 extends MaxHeapBasic{

	public MaxHeapOp1() {
		super();
	}

	public MaxHeapOp1(int capacity) {
		super(capacity);
	}

	/**
	 * 将第k(从1开始)个元素向上移位到正确位置。优化1：移动替代swap
	 * 
	 * @param k
	 */
	protected void shiftUp(int k) {
		int temp = data[k];
		while (k > 1 && temp > data[k / 2]) {
			// DataSourceUtil.swap(items, k, k / 2);
			data[k] = data[k / 2];
			k /= 2;
		}
		data[k] = temp;
	}

	/**
	 * 将最大堆位置k的元素向下移动到正确位置 。 优化1：先移动最后交换一次
	 * 
	 * @param k
	 */
	protected void shiftDown(int k) {
		int temp = data[k], j;
		while ((j = 2 * k) <= size) { // 有左孩子，说明肯定有孩子
			if (j + 1 <= size && data[j + 1] > data[j]) // 找到左右孩子中最大的所在的索引
				j = j + 1;
			if (data[j] > temp) {// 若最大的孩子比父节点还要大，则交换父节点与最大孩子节点
				// DataSourceUtil.swap(items, j, k);
				data[k] = data[j];
				k = j;
			} else
				break; // 若父节点已经是最大的，则说明已经移动到正确的位置。因为原堆就是最大堆，只有一个元素不满足规则，只需移动这一个元素到正确位置即可。
		}
		data[k] = temp;
	}

	public static void main(String[] args) {
		MaxHeapOp1 maxHeap = new MaxHeapOp1(15);
		Random rand = new Random();
		for (int i = 0; i < 13; i++) {
			int n = rand.nextInt(100);
			System.out.println(n);
			maxHeap.insert(n);
		}
		maxHeap.printArray();
		maxHeap.printSequenced();
		System.out.println(MaxHeapOp1.verify(maxHeap));
		for (int i = 0; i < 13; i++) {
			System.out.println(maxHeap.popMax());
		}
	}
}
