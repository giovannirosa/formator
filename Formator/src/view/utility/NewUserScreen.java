package view.utility;

import controller.Control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.User.Role;
import model.*;

public class NewUserScreen extends Stage{
	VBox geralPane = new VBox(20);
	GridPane inputPane = new GridPane();
	HBox buttonPane = new HBox(20);
	
	Label userLabel = new Label("User:");
	Label passwordLabel = new Label("Password:");
	Label typeLabel = new Label("Role:");
	
	TextField userField = new TextField();
	PasswordField passwordField = new PasswordField();
	ComboBox<Role> typeBox = new ComboBox<>();
	
	Button confirmBut = new Button("Confirm");
	Button cancelBut = new Button("Cancel");
	
	public NewUserScreen(Window owner) {
		this.setTitle("New User");
		this.initStyle(StageStyle.UTILITY);
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
				 
		setActions();
	
		ObservableList<Role> statOptions = FXCollections.observableArrayList(
				Role.Student,
				Role.Comissioner);
		typeBox.setItems(statOptions);
		typeBox.getSelectionModel().select(0);
		
		inputPane.setVgap(20);
		inputPane.setHgap(20);
		inputPane.add(userLabel, 0, 0);
		inputPane.add(userField, 1, 0);
		inputPane.add(passwordLabel, 0, 1);
		inputPane.add(passwordField, 1, 1);
		inputPane.add(typeLabel, 0, 2);
		inputPane.add(typeBox, 1, 2);
		
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(confirmBut,cancelBut);
		
		geralPane.setAlignment(Pos.CENTER);
		geralPane.setPadding(new Insets(20));
		geralPane.getChildren().addAll(inputPane,buttonPane);
		geralPane.setMinWidth(300);
		
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
	}
	
	private void setActions() {
		confirmBut.setOnAction(e -> {			
			if (userField.getText().isEmpty()) {
				Factory.showError("Please enter a user!");
				return;
			}
			if (passwordField.getText().isEmpty()) {
				Factory.showError("Please enter a password!");
				return;
			}
			
			if (typeBox.getSelectionModel().getSelectedItem().equals(Role.Comissioner)){
				Commissioner c = new Commissioner(userField.getText(), passwordField.getText());
				Control.userMap.put(c.getName(),c);
			} else {
				Student c = new Student(userField.getText(), passwordField.getText());
				Control.userMap.put(c.getName(),c);
			}
			
			Control.saveUserData();
			this.close();
		});
		
		cancelBut.setOnAction(e -> this.close());
	}
		
}
