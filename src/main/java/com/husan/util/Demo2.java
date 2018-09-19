package com.husan.util;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/15
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Demo2 {

    private int age;

    private String a1;

    private String name;

    public Demo2(int age, String a1){
        this.age = age;
        this.a1 = a1;
        this.name = "name"+age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }
}
