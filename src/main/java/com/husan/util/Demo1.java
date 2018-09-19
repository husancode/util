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
public class Demo1 {
    private String name;

    private String address;

    private Demo2 demo2;

    public Demo1(String name, String address, Demo2 demo2){
        this.name = name;
        this.address = address;
        this.demo2 = demo2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Demo2 getDemo2() {
        return demo2;
    }

    public void setDemo2(Demo2 demo2) {
        this.demo2 = demo2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
