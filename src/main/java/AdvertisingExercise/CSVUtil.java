package AdvertisingExercise;

import AdvertisingExercise.model.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CSVUtil {
    public static List<Employee> readData(String fileName) {
        List<Employee> employees = new ArrayList<Employee>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                StringTokenizer tokens = new StringTokenizer(data, ",");

                // get data
                String name = tokens.nextToken();
                double salary = Double.parseDouble(tokens.nextToken());

                // create an employee and add to the list
                employees.add(new Employee(name, salary));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return employees;
    }

    public static void writeData(String fileName, List<Employee> employees) {
        try {
            FileWriter writer = new FileWriter(fileName);

            // save data
            for (Employee employee : employees) {
                String name = employee.getName();
                double salary = employee.getSalary();
                writer.write(name + "," + salary + "\n");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
