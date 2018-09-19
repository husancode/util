import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/14
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Tsdf {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run() {
                beforeShutdown();
            }

        });
        TimeUnit.SECONDS.sleep(100);
    }

    public static void beforeShutdown(){
        System.out.println("before shutdown");
    }
}
