package view;

import controller.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import view.utility.Factory;

public class LoginScreen extends Stage {

	VBox geralPane = new VBox(20);
	GridPane gridPane = new GridPane();
	HBox buttonPane = new HBox(20);
	
	Label nameLabel = new Label("Name:");
	Label passLabel = new Label("Password:");
	TextField nameField = new TextField();
	PasswordField passField = new PasswordField();
	
	ImageView imgView = new ImageView();
	
	Button loginBut = new Button("Login");
	Button closeBut = new Button("Close");
	
	public static User user;
	
	public LoginScreen() {
		this.setTitle("Formator");
		this.setResizable(false);
		
		Image img = new Image("/view/ufpr.jpg", 500, 500, true, true);
		imgView.setImage(img);
		
		setActions();
		Control.loadAllData();
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		gridPane.add(nameLabel, 1, 1);
		gridPane.add(nameField, 2, 1);
		gridPane.add(passLabel, 1, 2);
		gridPane.add(passField, 2, 2);
		
		buttonPane.getChildren().addAll(loginBut,closeBut);
		buttonPane.setAlignment(Pos.CENTER);
		
		geralPane.setAlignment(Pos.CENTER);
		geralPane.setPadding(new Insets(20));
		geralPane.getChildren().addAll(imgView,gridPane,buttonPane);
		
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
		this.getIcons().add(new Image(this.getClass().getResource("ufpr.jpg").toExternalForm()));
	}
	
	private void setActions() {
		loginBut.setOnAction(e -> {
			User u = Control.userMap.get(nameField.getText());
			if (u == null) {
				Factory.showError("No user with this name!");
				System.out.println("No user with this name!");
				return;
			}
			
			if (u.getPass().equals(passField.getText())) {
				user = u;
				System.out.println("Success!");
				this.close();
				switch(user.getRole()) {
				case Comissioner:
					ComissionerScreen screen = new ComissionerScreen();
					screen.show();
					break;
				case Student:
					StudentScreen main = new StudentScreen();
					main.show();
					break;
				}
			} else {
				Factory.showError("The password do not match!");
				System.out.println("The password do not match!");
			}
		});
		
		closeBut.setOnAction(e -> System.exit(0));
	}
}
