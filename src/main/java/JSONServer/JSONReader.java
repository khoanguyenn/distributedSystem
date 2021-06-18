package JSONServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
    public static String object2JsonString(Book book) {
        JSONObject object = new JSONObject();

        object.put("title", book.getTitle());
        object.put("publisher", book.getPublisher());

        JSONObject authorObject = new JSONObject();
        authorObject.put("name", book.getAuthor().getName());
        authorObject.put("age", book.getAuthor().getAge());

        object.put("author", authorObject);

        return object.toJSONString();
    }

    public static Book jsonString2Object(String JSONString) throws ParseException {
        JSONObject object = (JSONObject) new JSONParser().parse(JSONString);

        String title = (String) object.get("title");
        String publisher = (String) object.get("publisher");
        JSONObject authorObject = (JSONObject) object.get("author");

        String authorName = (String) authorObject.get("name");
        int authorAge = ((Long) authorObject.get("age")).intValue();

        Author author = new Author(authorName, authorAge);
        
        return new Book(title, publisher, author);
    }
}
