package JSONReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.ObjLongConsumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONReader {
    public static void main(String[] args) throws IOException, ParseException {
        String FILE_PATH = "./src/main/java/JSONReader/customers.json";
        JSONObject object = getJson(FILE_PATH);
        System.out.println(object);
    }

    private static JSONObject getJson(String FILE_PATH) throws IOException, ParseException {
        JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(FILE_PATH));
        
        Long id = (long) jo.get("id");
        String name = (String) jo.get("name");
        Long age = (long) jo.get("age");

        System.out.println(id.intValue());
        System.out.println(name);
        System.out.println(age.intValue());
        JSONArray accountsArray = (JSONArray) jo.get("accounts");
        for (Object obj : accountsArray) {
            Map map = (Map) obj;
            long accountId = (long) map.get("accountId");
            System.out.println(accountId);
        }


        return jo;
    }
}
