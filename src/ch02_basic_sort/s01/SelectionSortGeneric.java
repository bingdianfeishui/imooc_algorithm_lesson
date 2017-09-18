package ch02_basic_sort.s01;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 简单泛型对象的选择排序
 * @author Lee
 *
 */
public class SelectionSortGeneric {
	public static void main(String[] args) throws Exception {
		Method me = SelectionSortGeneric.class.getMethod("selectionSort", int[].class);
		int[] arr = { 1, 3, 2, 7, 6 };
		me.invoke(SelectionSortGeneric.class, arr);
		System.out.println(Arrays.toString(arr));

		Student[] stus = { new Student("B", 90), new Student("A", 90), new Student("C", 95), new Student("D", 80) };
		// 得到泛型方法 方法1
		// Method me2 = SelectionSortGeneric.class.getMethod("selectionSort", Comparable[].class);
		// me2.invoke(SelectionSortGeneric.class, new Object[]{stus});

		// 另一种得到泛型方法的方法
		Method[] mes = SelectionSortGeneric.class.getMethods();
		for (Method m : mes) {
			Type[] types = m.getGenericParameterTypes();
			if (types.length == 1 && types[0] instanceof GenericArrayType) {
				System.out.println(m.toString() + Arrays.toString(types));
				m.invoke(SelectionSortGeneric.class, new Object[] { stus });
				break;
			}
		}
		System.out.println(Arrays.toString(stus));

		// selectionSort(stus);
	}

	public static <T extends Comparable<T>> T[] selectionSort(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j].compareTo(arr[i]) < 0) {
					T temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}

	public static int[] selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
}

class Student implements Comparable<Student> {
	String name;
	int score;

	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Student s2) {
		return score == s2.score ? name.compareTo(s2.name) : s2.score - score;
	}

	@Override
	public String toString() {
		return "\nStudent [name=" + name + ", score=" + score + "]";
	}
}