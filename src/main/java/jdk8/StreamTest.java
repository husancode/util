package jdk8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
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
public class StreamTest {
    public static void main(String[] args) {
        testStream1(Lists.newArrayList("1","2","3","4"));
    }

    public static void testStream1(ArrayList collection){
        long count = collection.stream().map(e -> {
            System.out.println("Saw " + e);return e;}).count();
        System.out.println(count);

    }
}
