package json3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.JsonArray;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reading {
	public static void main(String[] args) 
							throws FileNotFoundException, 
							IOException, ParseException {
		// JSONObject
		FileReader fr = new FileReader("./src/main/java/json3/customer31.json");
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);

		// get id
		long id = (long) jo.get("id");
		long age = (long) jo.get("age");

		List<Account> accountList = new ArrayList<Account>();
		JSONArray accounts = (JSONArray) jo.get("accounts");
		Iterator iterator = accounts.iterator();
		while (iterator.hasNext()) {
			JSONObject accountObj = (JSONObject) iterator.next();
			Account account = new Account(
				((Long) accountObj.get("balance")).intValue(), 
				((Long) accountObj.get("id")).intValue());
			accountList.add(account);
		}

		System.out.println("ID: " + id);
		System.out.println("age: " + age);
				
//		for (Object obj : list) {
//			// cast
//			Map map = (Map) obj;
//			long accountId = (long) map.get("id");
//			long balance = (long) map.get("balance");
//			System.out.println("accountId: " + accountId +
//					" - balance: " + balance);
//		}
	}
}

