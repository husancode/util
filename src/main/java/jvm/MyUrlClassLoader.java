package jvm;

import com.sun.webkit.network.URLs;
import sun.reflect.Reflection;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
public class MyUrlClassLoader extends URLClassLoader {

    private String name;

    public MyUrlClassLoader(URL[] urls){
        super(urls, null);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        File file = new File("D:\\workspace\\redisplay\\target\\classes\\");
        URL[] urls = new URL[]{file.toURI().toURL()};
        MyUrlClassLoader loader = new MyUrlClassLoader(urls);
        System.out.println("Thread context load:"+Thread.currentThread().getContextClassLoader());
        System.out.println("default load:" + MyUrlClassLoader.class.getClassLoader());
        Class<?> b1 = loader.loadClass("AlarmTrig", false);
        System.out.println("load class:"+b1);
        System.out.println("b1 loader:" + b1.getClassLoader());
        Class<?> b3 = b1.forName("Tst", true, b1.getClassLoader());
        System.out.println("b3 loader:" + b3.getClassLoader());
        Object object1 = b1.newInstance();
        System.out.println(b1.isInstance(object1));
        Method method = b1.getDeclaredMethod("test");
        method.invoke(object1);
        Class unicodeTest = b1.forName("UnicodeTest", true, b1.getClassLoader());
        System.out.println(unicodeTest+","+unicodeTest.getClassLoader());

    }


}
