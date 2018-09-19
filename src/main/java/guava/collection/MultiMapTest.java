package guava.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/19
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class MultiMapTest {
    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        String key = "1";
        multimap.put(key, "1");
        multimap.put(key, "2");
        multimap.put(key, "3");
        String key2 = "2";
        multimap.put(key2, "4");
        multimap.put(key2, "3");
        System.out.println(multimap.size());
        System.out.println(multimap.keySet());
        System.out.println(multimap.asMap());
        System.out.println(multimap.containsValue("4"));
        System.out.println(multimap.values());
        System.out.println(multimap.entries());
    }
}
