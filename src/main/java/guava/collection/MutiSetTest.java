package guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

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
public class MutiSetTest {
    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        System.out.println(multiset.size());
        multiset.add("1");
        multiset.add("1");
        multiset.add("2");
        multiset.add("3");
        multiset.add("3", 2);
        System.out.println(multiset.elementSet());
    }
}
