package ObjectWriting;

import java.io.Serializable;

public final class Student implements Serializable {
	private int id;
	private String fullName;
	private String year;

	Student(int id, String fullName, String year) {
		this.id = id;
		this.fullName = fullName;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getYear() {
		return year;
	}
}
