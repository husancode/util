package guava;

import com.google.common.cache.CacheBuilder;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/15
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class CacheTest {
    public static void main(String[] args) {
        CacheBuilder.newBuilder().maximumSize(5000).build();
    }
}
