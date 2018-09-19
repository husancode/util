package jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/5
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class ATest {
    public static void test() {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
    public static void main(String[] args) {

        test();
        System.out.println(Launcher.getLauncher().getClassLoader());
        System.out.println(Launcher.getLauncher().getClassLoader().getParent());

    }
}
