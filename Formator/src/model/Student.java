package model;

public class Student extends User {

	private static final long serialVersionUID = -4344337568767117590L;

	public Student(String name, String pass) {
		setName(name);
		setPass(pass);
		setRole(Role.Student);
	}
	
	public Student() {
		setName("jose");
		setPass("1234");
		setRole(Role.Student);
	}
}
