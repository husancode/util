package Test;

import java.util.ArrayList;

import java.util.List;

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
public class Ad {
    private int a1;
    private String a2;

    public Ad(int a1, String a2){
        this.a1 = a1;
        this.a2 = a2;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public static void main(String[] args) {
        List<Ad> list = new ArrayList<>();
        list.add(new Ad(1,"sdf"));
        list.add(new Ad(3,"sdfsdfa"));

    }
}
