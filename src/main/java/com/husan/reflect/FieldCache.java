package com.husan.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: husan
 * @Date: 2019/12/4 14:35
 * @Description:
 */
public class FieldCache {

	private static ConcurrentHashMap<Class, List<Field>> cache = new ConcurrentHashMap<>(512);

	public static List<Field> getFields(Class c){
		List<Field> result = cache.get(c);
		if(result == null){
			result = new ArrayList<>();
			Field[] fields = c.getDeclaredFields();
			for(Field field : fields){
					field.setAccessible(true);
					result.add(field);
			}
			Field[] superFields = c.getSuperclass().getDeclaredFields();
			for(Field field : superFields){
					field.setAccessible(true);
					result.add(field);
			}
			cache.put(c, result);
		}
		return result;
	}

}
