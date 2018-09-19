package guava.collection;

import java.util.Arrays;
import java.util.List;
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
public class LambdaTest {
    public static void main(String[] args){
        List list = Arrays.asList(1,2,3,5,6);
        list.forEach((t)-> {System.out.println(t);});
    }
}
