package ch04_heap_sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import util.DataSourceUtil;

/**
 * 最大堆 经典实现： 1. 索引从1开始 2. 移位操作采用swap
 * 
 * @author Lee
 *
 */
public class MaxHeapBasic {
	// 堆中元素个数
	protected int size;
	// 堆的容量
	protected int capacity;
	protected static int DEFAULT_CAPACITY = 10;
	// 堆元素存储数组。注意：堆索引从1开始，所以数组长度为capacity + 1
	protected int[] data = null;

	public MaxHeapBasic() {
		data = new int[DEFAULT_CAPACITY + 1];
		this.capacity = DEFAULT_CAPACITY;
		size = 0;
	}

	public MaxHeapBasic(int capacity) {
		data = new int[capacity + 1];
		this.capacity = capacity;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 检查容量，若已满，则扩容1.5倍
	 */
	protected void checkCapacity() {
		if (size >= capacity) { // 已装满
			// 扩容1.5倍
			int newCapacity = capacity + capacity / 2;
			int[] newArray = new int[newCapacity + 1];
			// transfer
			for (int i = 1; i < capacity; i++) {
				newArray[i] = data[i];
			}
			data = newArray;
			capacity = newCapacity;
		}
	}

	/**
	 * 插入一个元素n。先插入到数组末尾，然后向上移动到正确位置。
	 * 
	 * @param n
	 */
	public void insert(int n) {
		checkCapacity();
		data[++size] = n;

		// shiftUp使添加元素后的数组满足最大堆的性质
		shiftUp(size);
	}

	/**
	 * 将第k(从1开始)个元素向上移位到正确位置，每次都交换
	 * 
	 * @param k
	 */
	protected void shiftUp(int k) {
		while (k > 1 && data[k] > data[k / 2]) {
			DataSourceUtil.swap(data, k, k / 2);
			k /= 2;
		}
	}

	/**
	 * 获取堆顶最大的元素。 获取完后将最后一个元素移到堆顶，再往下移位到正确位置
	 * 
	 * @return
	 */
	public int popMax() {
		if (size <= 0)
			return Integer.MIN_VALUE;
		int ret = data[1];

		// 将最后一个元素移到堆顶,并将size--，即丢弃最后一个元素
		data[1] = data[size--];
		shiftDown(1);
		return ret;
	}

	/**
	 * 将最大堆位置k的元素向下移动到正确位置 。 采用交换的方法，若父节点比子节点小，则将父节点与子节点中最大的节点交换
	 * 
	 * @param k
	 */
	protected void shiftDown(int k) {
		int j;
		while ((j = 2 * k) <= size) { // 有左孩子，说明肯定有孩子
			if (j + 1 <= size && data[j + 1] > data[j]) // 找到左右孩子中最大的所在的索引
				j = j + 1;
			if (data[j] > data[k]) {// 若最大的孩子比父节点还要大，则交换父节点与最大孩子节点
				DataSourceUtil.swap(data, j, k);
				k = j;
			} else
				break; // 若父节点已经是最大的，则说明已经移动到正确的位置。因为原堆就是最大堆，只有一个元素不满足规则，只需移动这一个元素到正确位置即可。
		}
	}

	/**
	 * 打印数组
	 */
	public void printArray() {
		System.out.println(Arrays.toString(Arrays.copyOfRange(data, 1, size + 1)));
	}

	/**
	 * 依次打印堆中的元素。
	 * 从深度拷贝的MaxHeap对象中依次出队打印。
	 */
	public void printSequenced() {
		try {
			MaxHeapBasic copy = (MaxHeapBasic) this.clone();
			for (int i = 0; i < size; i++) {
				System.out.print(copy.popMax() + " ");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		MaxHeapBasic heap = new MaxHeapBasic();
		heap.capacity = this.capacity;
		heap.size = this.size;
		int[] newItems = new int[capacity + 1];
		for (int i = 1; i <= size; i++) {
			newItems[i] = this.data[i];
		}
		heap.data = newItems;
		return heap;
	}

	/**
	 * 校验heap是不是最大堆 完全二叉堆。
	 * 
	 * @param heap
	 * @return
	 */
	public static boolean verify(MaxHeapBasic heap) {
		return verifyImpl(heap.data, heap.size, 1);
	}

	/**
	 * 采用栈做辅助存储，从上到下、从左到右依次遍历堆，校验是否满足最大堆的性质：父节点比子节点都大。
	 * 
	 * @param a
	 * @param size
	 * @param k
	 * @return
	 */
	protected static boolean verifyImpl(int[] a, int size, int k) {
		if (k > size)
			return false;

		int li = 2 * k;
		int ri = 2 * k + 1;
		Stack<Integer> stack = new Stack<>();
		while (true) {
			if (ri <= size) {
				stack.push(ri);
				if (a[ri] > a[k])
					return false;
			}

			if (li <= size) {
				stack.push(li);
				if (a[li] > a[k])
					return false;
			}

			if (stack.empty())
				return true;

			k = stack.pop();
			li = 2 * k;
			ri = 2 * k + 1;
		}
	}

	public static void main(String[] args) {
		MaxHeapBasic maxHeap = new MaxHeapBasic(100);
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			maxHeap.insert(rand.nextInt(100));
		}
		maxHeap.printArray();
		System.out.println(MaxHeapBasic.verify(maxHeap));
		maxHeap.printSequenced();
		for (int i = 0; i < 10; i++) {
			System.out.println(maxHeap.popMax());
		}
	}
}
