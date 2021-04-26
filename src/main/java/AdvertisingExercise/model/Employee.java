package AdvertisingExercise.model;

import java.io.Serializable;

public class Employee implements Serializable{
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return name + "_" + salary;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
}