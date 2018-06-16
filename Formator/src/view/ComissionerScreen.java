package view;

import controller.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.table.RequestsTable;
import view.table.StudentsTable;
import view.utility.Factory;
import view.utility.ReqInfoPane;
import view.utility.ReviewScreen;
import view.utility.UserInfoPane;
import view.utility.UserScreen;

public class ComissionerScreen extends Stage {

	VBox geralPane = new VBox(20);
	HBox tablePane = new HBox(20);
	HBox buttonPane = new HBox(20);
	ReqInfoPane rInfoPane = new ReqInfoPane();
	UserInfoPane uInfoPane = new UserInfoPane(LoginScreen.user);
	
	StudentsTable studTable = new StudentsTable();
	RequestsTable reqTable = new RequestsTable();
	
	Button reviewBut = new Button("Review");
	Button manBut = new Button("Manage Users");
	Button exitBut = new Button("Exit");
	
	
	public ComissionerScreen() {
		this.setTitle("Formator");
		this.setResizable(false);
		
		Control.loadStudData(StudentsTable.getData());
		setActions();
		rInfoPane.loadTotal(RequestsTable.getData());

		reqTable.setPrefWidth(500);
		studTable.setPrefWidth(150);
		
		studTable.getSelectionModel().select(0);
		
		tablePane.getChildren().addAll(studTable,reqTable);
		tablePane.setAlignment(Pos.CENTER);
		
		buttonPane.getChildren().addAll(reviewBut,manBut,exitBut);
		buttonPane.setAlignment(Pos.CENTER);
		
		geralPane.setPadding(new Insets(20));
		geralPane.setAlignment(Pos.CENTER);
		geralPane.getChildren().addAll(uInfoPane,tablePane,rInfoPane,buttonPane);
		
		geralPane.setPrefSize(800, 600);
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
		this.getIcons().add(new Image(this.getClass().getResource("ufpr.jpg").toExternalForm()));
	}
	
	private void setActions() {
		studTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			Control.loadReqData(newSelection.getStudent(),RequestsTable.getData());
			rInfoPane.loadTotal(RequestsTable.getData());
		});
		manBut.setOnAction(e -> {
			new UserScreen(this).showAndWait();
		});
		exitBut.setOnAction(e-> {
			LoginScreen screen = new LoginScreen();
			this.close();
			screen.show();
		});
		reviewBut.setOnAction(e -> {
			if (reqTable.getSelectionModel().getSelectedItem() == null) {
				Factory.showWarning("Please select a record!");
	        	return;
			}
			
			String student = studTable.getSelectionModel().getSelectedItem().getStudent();
			new ReviewScreen(this,student,
					reqTable.getSelectionModel().getSelectedIndex()).showAndWait();
			Control.loadReqData(student, RequestsTable.getData());
		});
		
	}
}
