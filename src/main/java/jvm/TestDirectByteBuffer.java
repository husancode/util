package jvm;

import java.nio.ByteBuffer;
 
public class TestDirectByteBuffer
{
	private static int count = 0;
	// -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=40M
	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
			System.out.println(++count);
		}
	}
}
