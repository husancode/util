package guava.order;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/17
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class OrderingTest {
    public static void main(String[] args) {
       String s = "(502212647)咖咖云录像";
        int lastIndex = s.lastIndexOf("云录像");
        s = s.substring(0, lastIndex);
        System.out.println(s);
    }
}
