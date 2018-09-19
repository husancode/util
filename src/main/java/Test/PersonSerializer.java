package Test;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @Date 2018/8/20
 */
public class PersonSerializer implements JsonSerializer<Person>,JsonDeserializer<Person> {

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String[] elementArr = json.getAsString().split("-");
        return new Person(elementArr[0], Integer.parseInt(elementArr[1]));
    }

    @Override
    public JsonElement serialize(Person src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName()+"-"+src.getAge());
    }
}
