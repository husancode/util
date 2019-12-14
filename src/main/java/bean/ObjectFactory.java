package bean;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.husan.reflect.FieldCache;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @Auther: husan
 * @Date: 2019/12/4 14:29
 * @Description:
 */
public class ObjectFactory {

	/**
	 * 生产一个对象，属性值根据类型随机生产
	 * @param dataType
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T build(Class<T> dataType) throws IllegalAccessException, InstantiationException {
		List<Field> fieldList = FieldCache.getFields(dataType);
		T data = dataType.newInstance();
		for(Field field : fieldList){
			field.set(data, randomValue(field.getType()));
		}
		return data;
	}

	public static Object randomValue(Class<?> fieldType){
		if(fieldType == String.class){
			return RandomUtil.randomString(8);
		}else if(fieldType == Integer.class){
			return RandomUtil.randomInt(1, 7);
		}else if(fieldType == Date.class){
			Date date = new Date();
			return RandomUtil.randomDate(date, DateField.MINUTE, -1234, 1234);
		}else if(fieldType == Long.class){
			return RandomUtil.randomLong(1,12344);
		}else if(fieldType == Double.class){
			return RandomUtil.randomDouble();
		}else if(fieldType == Byte.class){
			return ((Integer)RandomUtil.randomInt()).byteValue();
		}
		return null;
	}
}
