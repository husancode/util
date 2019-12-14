package com.husan.util;

import Test.EnumSerializer;
import Test.PackageState;
import Test.Person;
import Test.PersonSerializer;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     使用Gson的一些常用操作，
 *     也可以使用FastJson
 *     gson使用例子：
 *     可以new GsonBuilder().registerTypeAdapter(Type type, Object typeAdapter);注册自定义的序列化和反序列化接口
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/15
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class JsonUtil {

    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 忽略某些字段的序列化
     * @param obj
     * @param skipFields
     * @return
     */
    public static String toJson(Object obj, final List<String> skipFields){

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            public boolean shouldSkipField(FieldAttributes f) {
                return skipFields.contains(f.getName());
            }

            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        return gson.toJson(obj);
    }


    /**
     *
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> objClass) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, objClass);
    }

    /**
     * 支持带泛型的类型的反序列化：
     * 例如List<Demo1>，如果使用普通的fromJson的话，
     * 会被序列化为LinkedTreeMap
     * @param jsonStr
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonByType(String jsonStr, TypeToken<T> type){
        return new Gson().fromJson(jsonStr, type.getType());
    }

    public static void main(String[] args) {
        String ss = null;
        Demo1 demo2 = fromJson(ss, Demo1.class);
        Demo1 demo1 = new Demo1("husan", "xiaoshan", new Demo2(17, "a1"));
        Demo1 d2 = new Demo1("husi", "binjiang", new Demo2(18, "b1"));
        List<Demo1> list = Arrays.asList(demo1, d2);
        String jsonStr = toJson(list, Arrays.asList("name"));

        List<Demo1> list2 = fromJsonByType(jsonStr, new TypeToken<List<Demo1>>() {
        });
        System.out.println(list2);

        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(PackageState.class,
                new EnumSerializer());
        gsonBuilder.registerTypeAdapter(Person.class, new PersonSerializer());
        Gson gson = gsonBuilder.create();

    }

}
