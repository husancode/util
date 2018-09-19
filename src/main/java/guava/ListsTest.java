package guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.Map;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/14
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ListsTest {
    public static void main(String[] args) {
        List<Map< String, Object>> maps = Lists.newArrayList();
        List< String > langs = Lists.newArrayList("中文","English","日本語",null);
        String lang = Joiner.on("|").useForNull("Unkown").join(langs);
        System.out.println(lang);

    }
}
