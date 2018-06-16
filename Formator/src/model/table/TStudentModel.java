package model.table;

import javafx.beans.property.SimpleStringProperty;

public class TStudentModel {

	private SimpleStringProperty student;

	public TStudentModel(String name) {
		student = new SimpleStringProperty(name);
	}
	
	public String getStudent() {
		return student.get();
	}

	public void setStudent(String s) {
		student.set(s);
	}
}
