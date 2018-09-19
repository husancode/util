package Test;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import Test.Hello;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

public class CacheTest {
	
	public static void main(String[] args) {

		System.out.println(Integer.MIN_VALUE+":"+Integer.MAX_VALUE);
		long date = 0;
		Date d = new Date(date);

		System.out.println(d);

	}

}
