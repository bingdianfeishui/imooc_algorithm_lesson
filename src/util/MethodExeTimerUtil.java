package util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MethodExeTimerUtil {
	public static void batchSortIntMethodsExecution(ISorter[] sorters) {
		batchSortIntMethodsExecution(sorters, 500);
	}

	public static void batchSortIntMethodsExecution(ISorter[] sorters, int swapTimes) {
		batchSortIntMethodsExecution(sorters, 50000, swapTimes);
	}

	public static void batchSortIntMethodsExecution(ISorter[] sorters, int arrSize, int swapTimes) {
		int[] randomArr = DataSourceUtil.generateRandomIntArr(arrSize, arrSize);
		int[] sortedArr = DataSourceUtil.generateAlmostSortedIntArr(arrSize, swapTimes);

		System.out.println("随机数组：size=" + arrSize);
		batchExecuteSorters(sorters, randomArr);

		System.out.println("\n近乎有序数组：size=" + arrSize + ", swapTimes=" + swapTimes);
		batchExecuteSorters(sorters, sortedArr);

	}

	// 批量运行排序器并打印时间结果
	private static void batchExecuteSorters(ISorter[] sorters, int[] sourceData) {
		List<ExecutionResult> resultList = new ArrayList<>();
		for (ISorter sorter : sorters) {
			int[] tempParams = Arrays.copyOf(sourceData, sourceData.length);
			try {
				ExecutionResult exeRs = getMethodExecuteTime(sorter, "sort", new Class<?>[] { int[].class }, false,
						false, new Object[] { tempParams });
				if (!DataSourceUtil.isSorted(tempParams))
					exeRs.methodName = "[NOT SORTED]" + exeRs.methodName;
				else
					exeRs.methodName = "[SORTED]    " + exeRs.methodName;
				resultList.add(exeRs);
			} catch (Exception e) {
				System.out.printf("%s.sort(int[] arr) exectpion: %s\n", sorter.getClass().getName(), e.getMessage());
			}
		}
//		sortExecutionResult(resultList);
		calculateTimeMultiple(resultList);
		printExecutionResultList(resultList);
	}

	private static void calculateTimeMultiple(List<ExecutionResult> resultList) {
		long minNanoSec = Long.MAX_VALUE;
		for (ExecutionResult result : resultList) {
			if(result.nanoSeconds < minNanoSec)
				minNanoSec = result.nanoSeconds;
		}
		
		for (ExecutionResult result : resultList) {
			result.multiple = result.nanoSeconds * 1.0 / minNanoSec;
		}
	}

	@SuppressWarnings("unused")
	private static <T> void sortExecutionResult(List<ExecutionResult> resultList) {
		Collections.sort(resultList, new Comparator<ExecutionResult>() {
			@Override
			public int compare(ExecutionResult o1, ExecutionResult o2) {
				return (int) (o2.nanoSeconds - o1.nanoSeconds);
			}
		});
	}

	private static void printExecutionResultList(List<ExecutionResult> randomResults) {
		int maxNameLength = 0, n = 0;
		for (ExecutionResult result : randomResults) {
			n = result.methodName.length();
			if (n > maxNameLength)
				maxNameLength = n;
		}
		for (ExecutionResult result : randomResults) {
			if (result.methodName.length() < maxNameLength)
				result.methodName = fillBlank(result.methodName, maxNameLength);
			result.printExecuteTime();
		}
	}

	private static String fillBlank(String str, int maxNameLength) {
		char[] ch = new char[maxNameLength - str.length()];
		Arrays.fill(ch, ' ');
		return str += String.valueOf(ch);
	}

	public static ExecutionResult getMethodExecuteTime(Object bean, String methodName, Class<?>[] paramsTypes,
			Object... params) throws Exception {
		return getMethodExecuteTime(bean, methodName, paramsTypes, true, false, params);
	}

	public static ExecutionResult getMethodExecuteTime(Object bean, String methodName, Class<?>[] paramsTypes,
			boolean isPrintTime, boolean isPrintResult, Object[] params) throws Exception {
		Method me = null;
		if (bean instanceof Class<?>)
			me = ((Class<?>) bean).getDeclaredMethod(methodName, paramsTypes);
		else
			me = bean.getClass().getDeclaredMethod(methodName, paramsTypes);

		if (me != null) {
			boolean accessible;
			if (!(accessible = me.isAccessible()))
				me.setAccessible(true);

			if (Modifier.isStatic(me.getModifiers())) {
				if (!(bean instanceof Class<?>))
					bean = bean.getClass();
			} else {
				if (bean instanceof Class<?>)
					bean = ((Class<?>) bean).newInstance();
			}

			ExecutionResult exeRs = getMethodExecuteTimeAndReturnValue(bean, me, params);

			// 恢复访问权限
			me.setAccessible(accessible);

			if (isPrintTime) {
				exeRs.printExecuteTime();
			}
			if (isPrintResult)
				exeRs.printExecuteResult();

			return exeRs;
		}

		return new ExecutionResult(
				(bean instanceof Class<?> ? ((Class<?>) bean).getName() : bean.getClass().getName()) + "." + methodName,
				-1L, null);
	}

	private static class ExecutionResult {
		String methodName;
		long nanoSeconds;
		Object result;
		double multiple;
		
		ExecutionResult(String methodName, long time, Object result) {
			this.methodName = methodName;
			this.nanoSeconds = time;
			this.result = result;
		}

		void printExecuteResult() {
			System.out.print(methodName + " execute result:\t");
			if (result instanceof Object[]) {
				System.out.println(Arrays.toString((Object[]) result));
			} else if (result instanceof byte[]) {
				System.out.println(Arrays.toString((byte[]) result));
			} else if (result instanceof short[]) {
				System.out.println(Arrays.toString((short[]) result));
			} else if (result instanceof int[]) {
				System.out.println(Arrays.toString((int[]) result));
			} else if (result instanceof long[]) {
				System.out.println(Arrays.toString((long[]) result));
			} else if (result instanceof float[]) {
				System.out.println(Arrays.toString((float[]) result));
			} else if (result instanceof double[]) {
				System.out.println(Arrays.toString((double[]) result));
			} else if (result instanceof char[]) {
				System.out.println(Arrays.toString((char[]) result));
			} else if (result instanceof boolean[]) {
				System.out.println(Arrays.toString((boolean[]) result));
			} else {
				System.out.println(result);
			}
		}

		void printExecuteTime() {
			System.out.printf("%s:\t%10.6fs    Multiple:%9.2f\n", methodName, nanoSeconds / 1000000000d, multiple);
		}
	}

	private static ExecutionResult getMethodExecuteTimeAndReturnValue(Object bean, Method method, Object... params)
			throws Exception {
		long start = System.nanoTime();
		Object result = (Object) method.invoke(bean, params);
		long end = System.nanoTime();

		return new ExecutionResult((bean instanceof Class<?> ? ((Class<?>) bean).getName() : bean.getClass().getName())
				+ "." + method.getName(), end - start, result);
	}
}