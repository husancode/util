package jvm.loader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/7/31
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Test {

    public static void testLoad(){
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println(Driver.class.getClassLoader());
        System.out.println(ServiceLoader.class.getClassLoader());
    }

    public static void main(String[] args) {
        testLoad();
    }
}
