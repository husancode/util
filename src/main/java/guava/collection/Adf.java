package guava.collection;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/21
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Adf{

    public static void hello(FunctionTest test){
        System.out.println(test.doSomething());
    }

    public static void main(String[] args) {
        hello(()->{
            System.out.println("12323");
            return "3";
        });
    }
}
