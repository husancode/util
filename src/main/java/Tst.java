import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/28
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Tst {

    public static void main(String[] args) throws InterruptedException {
        Socket socket = new Socket();
        byte[] byttt = new byte[1000];
        System.out.println(byttt.length);
        TimeUnit.SECONDS.sleep(1000);
    }
}
