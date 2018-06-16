package model;

public class Commissioner extends User {

	private static final long serialVersionUID = -2002812992305856494L;

	public Commissioner(String name, String pass) {
		setName(name);
		setPass(pass);
		setRole(Role.Comissioner);
	}
}
