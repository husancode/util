package jvm;

import com.sun.btrace.annotations.BTrace;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
public class ClassReloadTest{

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InterruptedException {
        //runClassMethod();
        while(true){
            File file = new File("F:\\workspace\\util\\target\\classes\\");
            URL[] urls = new URL[]{file.toURI().toURL()};
            MyUrlClassLoader loader = new MyUrlClassLoader(urls);
            System.out.println(loader.getParent());
            Class<?> classMethod = loader.loadClass("jvm.ClassMethod");
            System.err.println(classMethod.getClassLoader());
            TimeUnit.SECONDS.sleep(5);

        }


    }

    public static void runClassMethod(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(ClassMethod.class.getClassLoader());
                    ClassMethod.test();
                    try {
                        TimeUnit.SECONDS.sleep(7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
