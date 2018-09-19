package reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/4/18
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ReflectTest {

    public static void main(String[] args) throws IllegalAccessException {
        FieldTest fieldTest = new FieldTest();
        Field field = FieldUtils.getDeclaredField(fieldTest.getClass(), "finalString", true);
        System.out.println(field.get(fieldTest));
        field.set(fieldTest, "123123");
        System.out.println(field.get(fieldTest));
    }

}
