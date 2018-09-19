package guava.collection;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/21
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class MapsTest {
    public static void main(String[] args) {

        Map map = Maps.uniqueIndex(Iterators.forArray("12","123","1234","12345"), new Function<String, Integer>(){
            @Nullable
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });
        System.out.println(map);


    }
}
