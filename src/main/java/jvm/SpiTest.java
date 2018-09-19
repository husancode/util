package jvm;

import spi.SpiInterface;
import sun.security.provider.ConfigFile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/6
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class SpiTest {

    public static void test() throws MalformedURLException {
        File file = new File("D:\\workspace\\redisplay\\target\\classes\\");
        URL[] urls = new URL[]{file.toURI().toURL()};
        MyUrlClassLoader loader = new MyUrlClassLoader(urls);
        Thread.currentThread().setContextClassLoader(loader);
        ServiceLoader<SpiInterface> serviceLoader = ServiceLoader.load(SpiInterface.class);
        Iterator<SpiInterface> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            SpiInterface  spiInterface = iterator.next();
            spiInterface.say();
            System.out.println(spiInterface.getClass());
            System.out.println(spiInterface.getClass().getClassLoader());
            System.out.println(SpiInterface.class.getClassLoader());
        }
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        while(true){
            test();
            TimeUnit.SECONDS.sleep(5);
        }
    }

}
