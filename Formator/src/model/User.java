package model;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 4297348674137163267L;

	public enum Role {
		Comissioner, Student;
	}
	
	private String name;
	private String pass;
	private Role role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void changePass(String oldPass, String newPass) {
		if (!pass.equals(oldPass)) {
			System.out.println("The old password is not correct!");
			return;
		}
		setPass(newPass);
	}
}
