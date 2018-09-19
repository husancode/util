package guava;

import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/13
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ObjectsTest {
    public static void main(String[] args) {
        String[] list = new String[]{"30*12","7*12","7*1","30*1"};
        Arrays.sort(list,  Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));
    }
}
