package util;

import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/5/26
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class EnvTest {
    public static void main(String[] args) {
        Map<String,String> envMap = System.getenv();
        for(Map.Entry<String, String> entry : envMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
