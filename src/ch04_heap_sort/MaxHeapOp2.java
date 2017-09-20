package ch04_heap_sort;

/**
 * 最大堆 经典实现： 1. 索引从1开始 2. 移位操作采用swap 
 * 优化2：直接从数组构建堆
 * 
 * @author Lee
 *
 */
public class MaxHeapOp2 extends MaxHeapOp1 {

	public MaxHeapOp2() {
		super();
	}

	public MaxHeapOp2(int capacity) {
		super(capacity);
	}

	public MaxHeapOp2(int[] a) {
		data = new int[a.length + 1];
		capacity = a.length;
		size = a.length;
		for (int i = 0; i < a.length; i++) {
			data[i + 1] = a[i];
		}
		//heapify过程。初始为i=size/2，是因为：叶子结点都可以看做元素为1的最大堆，所以只需处理所有的非叶子结点即可。而完全二叉树的最后一个叶子结点就是size/2.
		for (int i = size / 2; i >= 1; i--) {
			shiftDown(i);
		}
	}
}
