package json3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Writing {
	public static void main(String[] args) throws FileNotFoundException {

		// JSONObject
		JSONObject jo = new JSONObject();

		// putting data to JSONObject
		jo.put("id", 123);
		jo.put("name", "Smith");
		jo.put("age", 24);

		// first map in accounts
		LinkedHashMap map1 = new LinkedHashMap();
		map1.put("id", 15747);
		map1.put("balance", 201);

		// second map in accounts
		LinkedHashMap map2 = new LinkedHashMap();
		map2.put("id", 21467);
		map2.put("balance", 645);
		
		List accounts = new ArrayList();
		accounts.add(map1);
		accounts.add(map2);		
		
		jo.put("accounts", accounts);	
		System.out.println(jo);
		
		

		// writing JSON to file
//		PrintWriter pw = new PrintWriter("customer22.json");
//		pw.write(jsonString);
//
//		pw.flush();
//		pw.close();
	}
}
