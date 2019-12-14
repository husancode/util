package com.husan.lang;

import com.husan.reflect.FieldCache;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: husan
 * @Date: 2019/12/12 19:09
 * @Description:
 */
public class ObjectUtil {


	public static <T> void copy(T source, T target) throws IllegalAccessException {
		List<Field> fields = FieldCache.getFields(source.getClass());
		for(Field field : fields){
			field.set(target, field.get(source));
		}
	}

	/**
	 * 将对象属性为Number类型合并
	 * @param t1
	 * @param t2
	 * @param <T>
	 * @return
	 */
	public static <T> T addNumberField(T t1, T t2) {
		if(t1 == null && t2 == null){
			return null;
		}
		try {
			T result = (T)t1.getClass().newInstance();
			if(t1 != null){
				copy(t1, result);
				if(t2 != null){
					List<Field> fields = FieldCache.getFields(t1.getClass());
					for(Field field : fields){
						if(field.getType() == Integer.class || field.getType() == int.class){
							Integer n1 = (Integer)field.get(t1);
							Integer n2 = (Integer)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if (n2 != null){
								field.set(result, (n1 + n2));
							}

						}else if(field.getType() == Long.class || field.getType() == long.class){
							Long n1 = (Long)field.get(t1);
							Long n2 = (Long)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if (n2 != null){
								field.set(result, (n1 + n2));
							}
						}else if(field.getType() == Byte.class || field.getType() == byte.class){
							Byte n1 = (Byte)field.get(t1);
							Byte n2 = (Byte)field.get(t2);
							field.set(result, (n1 + n2));
						}else if(field.getType() == Double.class || field.getType() == double.class){
							Double n1 = (Double)field.get(t1);
							Double n2 = (Double)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if (n2 != null){
								field.set(result, (n1 + n2));
							}
						}else if(field.getType() == Float.class || field.getType() == float.class){
							Float n1 = (Float)field.get(t1);
							Float n2 = (Float)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if (n2 != null){
								field.set(result, (n1 + n2));
							}
						}else if(field.getType() == BigDecimal.class){
							BigDecimal n1 = (BigDecimal)field.get(t1);
							BigDecimal n2 = (BigDecimal)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if(n2 != null){
								field.set(result, n1.add(n2));
							}
						}else if(field.getType() == Short.class || field.getType() == short.class){
							Short n1 = (Short)field.get(t1);
							Short n2 = (Short)field.get(t2);
							if(n1 == null){
								field.set(result, n2);
							}else if (n2 != null){
								field.set(result, (n1 + n2));
							}
						}

					}
				}

			}else{
				copy(t2, result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e){
			e.printStackTrace();
		}
		return null;
	}

}
