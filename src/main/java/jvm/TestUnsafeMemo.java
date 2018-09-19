package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafeMemo {
	private static Unsafe unsafe;
	private static int count = 0;
	static{
		try {
			Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");

			singleoneInstanceField.setAccessible(true);

			unsafe = (Unsafe)singleoneInstanceField.get(null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}



	// -XX:MaxDirectMemorySize=40M
	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			long pointer = unsafe.allocateMemory(10 * 1024 * 1024);
			System.out.println(++count);
			// 如果不释放内存,运行一段时间会报错java.lang.OutOfMemoryError
			unsafe.freeMemory(pointer);
		}
	}

}
