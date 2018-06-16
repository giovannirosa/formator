package model.table;

import javafx.beans.property.SimpleStringProperty;

public class TUserModel {

	private SimpleStringProperty user;
	private SimpleStringProperty pass;
	private SimpleStringProperty role;

	public TUserModel(String n, String p, String r) {
		user = new SimpleStringProperty(n);
		pass = new SimpleStringProperty(p);
		role = new SimpleStringProperty(r);
	}
	
	public String getUser() {
		return user.get();
	}

	public void setUser(String s) {
		user.set(s);
	}

	public String getPass() {
		return pass.get();
	}

	public void setPass(String pass) {
		this.pass.set(pass);
	}

	public String getRole() {
		return role.get();
	}

	public void setRole(String role) {
		this.role.set(role);
	}
}
