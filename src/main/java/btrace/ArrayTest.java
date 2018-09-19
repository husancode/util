package btrace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/31
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ArrayTest {



    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++));
            TimeUnit.MILLISECONDS.sleep(300);
        }
    }
}
