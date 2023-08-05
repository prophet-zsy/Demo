import com.google.gson.*;

import java.util.Map;
import java.util.Set;

public class GSONDemo {
    private class Bean {
        String str;
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
//        转json字符串
        String jsonStr1 = gson.toJson("Hello");
        System.out.println(jsonStr1);
        String jsonStr2 = gson.toJson(123);
        System.out.println(jsonStr2);
        System.out.println("==========");

//        json字符串转Bean类
        String jsonStr3 = "{str: Hello}";
        Bean bean = gson.fromJson(jsonStr3, Bean.class);  // 通过定义bean类来解析json字符串
        System.out.println(bean.str);

        JsonElement jsonElement = JsonParser.parseString(jsonStr3);  // 先解析，再看
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
//        System.out.println(entries);
        for (Map.Entry<String, JsonElement> entry: entries) {
            System.out.println(entry.getKey());
        }

//        Bean类转json字符串
        String jsonStr4 = gson.toJson(bean);
        System.out.println(jsonStr4);

    }
}
