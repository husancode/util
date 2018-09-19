package btrace;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/19
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ErrorDebug implements Runnable{

    public static boolean compare(String str1, String str2){
        if(str1 == null){
            throw new NullPointerException();
        }
        boolean result =  str1.equals(str2);

        return result;

    }

    @Override
    public void run() {
        boolean result = false;
        //try {
            result = ErrorDebug.compare("122", "1q23123");
        //} catch (Exception e) {
           // e.printStackTrace();
        //}
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException {
        ErrorDebug errorDebug = new ErrorDebug();
        while(true){
            new Thread(errorDebug).start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
