package view;

import java.util.ArrayList;
import controller.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.table.TRequestModel;
import view.table.RequestsTable;
import view.utility.AddReqScreen;
import view.utility.Factory;
import view.utility.ReqInfoPane;

public class StudentScreen extends Stage {

	VBox geralPane = new VBox(20);
	HBox buttonPane = new HBox(20);
	ReqInfoPane infoPane = new ReqInfoPane();
	
	RequestsTable reqTable = new RequestsTable();
	
	Button openBut = new Button("Open Receipt");
	Button commentBut = new Button("Open Comment");
	Button addBut = new Button("Add");
	Button deleteBut = new Button("Delete");
	Button submitBut = new Button("Submit");
	Button exitBut = new Button("Exit");
	
	public StudentScreen() {
		this.setTitle("Formator");
		this.setResizable(false);
		
		Control.loadReqData(LoginScreen.user.getName(),RequestsTable.getData());
		setActions();
		
		buttonPane.getChildren().addAll(openBut,commentBut,addBut,deleteBut,submitBut,exitBut);
		buttonPane.setAlignment(Pos.CENTER);
		
		infoPane.loadTotal(RequestsTable.getData());
		
		geralPane.setPadding(new Insets(20));
		geralPane.setAlignment(Pos.CENTER);
		geralPane.getChildren().addAll(reqTable,infoPane,buttonPane);
		
		geralPane.setPrefSize(800, 600);
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
		this.getIcons().add(new Image(this.getClass().getResource("ufpr.jpg").toExternalForm()));
	}
	
	private void setActions() {
		openBut.setOnAction(e -> {
			Control.openAction(reqTable.getSelectionModel().getSelectedItem());
		});
		addBut.setOnAction(e -> {
			new AddReqScreen(this).showAndWait();
			infoPane.loadTotal(RequestsTable.getData());
		});
		deleteBut.setOnAction(e -> {
			TRequestModel model = reqTable.getSelectionModel().getSelectedItem();
			if (model == null) {
				Factory.showWarning("Please select a record!");
	        	return;
			}
			Control.deleteFile(model.getFile());
			RequestsTable.getData().remove(model);
			Control.reqMap.put(LoginScreen.user.getName(), new ArrayList<TRequestModel>(RequestsTable.getData()));
			Control.subMap.put(LoginScreen.user.getName(), new ArrayList<TRequestModel>(RequestsTable.getData()));
			Control.saveReqData();
			Control.saveSubData();
			infoPane.loadTotal(RequestsTable.getData());
		});
		submitBut.setOnAction(e -> {
			if (infoPane.getTot() < 480) {
				Factory.showWarning("You need to add 480h in total to Submit!");
				return;
			}
			if (Factory.showConfirmation("Are you sure to submit for reviewing?") == ButtonType.YES) {
				Control.subMap.put(LoginScreen.user.getName(), new ArrayList<TRequestModel>(RequestsTable.getData()));
				Control.saveSubData();
				Factory.showWarning("Activities submited to Comission!");
			}
		});
		exitBut.setOnAction(e-> {
			LoginScreen screen = new LoginScreen();
			this.close();
			screen.show();
		});
		commentBut.setOnAction(e -> {
			TRequestModel model = reqTable.getSelectionModel().getSelectedItem();
			if (model == null) {
				Factory.showWarning("Please select a record!");
	        	return;
			}
			if (model.getComment().equals("")) {
				Factory.showWarning("No comment!");
	        	return;
			}
			Factory.showInfo(reqTable.getSelectionModel().getSelectedItem().getComment());
		});
	}
}
