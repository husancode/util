package Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GsonTest6 {
 
	public static void main(String[] args) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PackageState.class,
				new EnumSerializer());
		gsonBuilder.registerTypeAdapter(Person.class, new PersonSerializer());
		Gson gson = gsonBuilder.create();
		PackageItem item = new PackageItem();
		item.setName("item_name");
		item.setSize("500M");
		item.setState(PackageState.UPDATING);// 这个 state是枚举值
		Person person = new Person("husan", 22);
		item.setPerson(person);
		String s = gson.toJson(item);
		System.out.println(s);
 
		System.out.println("--------------------------------");
 
		PackageItem retItem = gson.fromJson(s, PackageItem.class);
		System.out.println(retItem);
		System.out.println(retItem.getPerson().getAge());
	}
}
