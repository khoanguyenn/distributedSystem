package xmlrpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import org.apache.xmlrpc.*;

public class JavaClient {
	public static void main(String[] args) {
		try {
			// XmlRpcClient
			XmlRpcClient client =  new XmlRpcClient("http://localhost:90");

			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int num1 = 0, num2 = 0;
				try {
					System.out.println("First number");
					num1 = Integer.parseInt(br.readLine());
					System.out.println("Second number");
					num2 = Integer.parseInt(br.readLine());
				} catch(NumberFormatException exception) {
					System.out.println("Please enter a number !");
				}
				
				// params
				Vector params = new Vector();
				params.addElement(num1);
				params.addElement(num2);

				// call a remote function
				Object result = null;
				System.out.println("Please enter your operant !");
				String operant = br.readLine();
				switch(operant) { 
					case "+":
						result = client.execute("JavaServer.sum", params);
						break;
					case "-":
						result = client.execute("JavaServer.sub", params);
						break;
					case "*":
						result = client.execute("JavaServer.mul", params);
						break;
					case "/":
						result = client.execute("JavaServer.div", params);
						break;
					default:
						System.out.println("Please enter the correct operant !");
				}
				// process result
				int sum = ((Integer) result).intValue();
				System.out.println("The sum is: " + sum);
			}
			
			
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
}
