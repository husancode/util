
package Test;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Tst {
    public static void testFinally(){
        try{
            throw new RuntimeException("sdf");
        }finally {
            System.out.println("finaly");

        }
    }
    public static void main(String[] args) {

        testFinally();
    }
}
