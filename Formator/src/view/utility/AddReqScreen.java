package view.utility;

import java.io.File;
import java.util.ArrayList;

import controller.Control;
import controller.Serializer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.table.TRequestModel;
import model.table.TRequestModel.Status;
import view.LoginScreen;
import view.table.RequestsTable;

public class AddReqScreen extends Stage {

	VBox geralPane = new VBox(20);
	GridPane inputPane = new GridPane();
	HBox buttonPane = new HBox(20);
	
	Label descLabel = new Label("Description:");
	Label typeLabel = new Label("Type:");
	Label hoursLabel = new Label("Hours:");
	Label receiptLabel = new Label("Receipt:");
	
	TextField descField = new TextField();
	TextField typeField = new TextField();
	TextField hoursField = new TextField();
	TextField receiptField = new TextField();
	
	File file;
	
	Button uploadBut = new Button("Upload");
	Button confirmBut = new Button("Confirm");
	Button cancelBut = new Button("Cancel");
	
	public AddReqScreen(Window owner) {
		this.setTitle("Submit");
		this.setResizable(false);
		this.initStyle(StageStyle.UTILITY);
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		
		setActions();
		receiptField.setEditable(false);
	
		inputPane.setVgap(20);
		inputPane.setHgap(20);
		inputPane.add(descLabel, 0, 0);
		inputPane.add(descField, 1, 0);
		inputPane.add(typeLabel, 0, 1);
		inputPane.add(typeField, 1, 1);
		inputPane.add(hoursLabel, 0, 2);
		inputPane.add(hoursField, 1, 2);
		inputPane.add(receiptLabel, 0, 3);
		inputPane.add(receiptField, 1, 3);
		inputPane.add(uploadBut, 2, 3);
		
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(confirmBut,cancelBut);
		
		geralPane.setAlignment(Pos.CENTER);
		geralPane.setPadding(new Insets(20));
		geralPane.getChildren().addAll(inputPane,buttonPane);
		
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
	}
	
	private void setActions() {
		uploadBut.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			file = fileChooser.showOpenDialog(this);
			if (file == null)
				return;
			if (file.getName().contains(".pdf")) {
				receiptField.setText(file.getName());
			} else {
				Factory.showWarning("Please upload PDF archives only!");
			}
		});
		
		confirmBut.setOnAction(e -> {			
			if (descField.getText().isEmpty()) {
				Factory.showError("Please enter a description!");
				return;
			}
			if (typeField.getText().isEmpty()) {
				Factory.showError("Please enter a type!");
				return;
			}
			if (hoursField.getText().isEmpty()) {
				Factory.showError("Please enter the hours!");
				return;
			}
			if (file == null) {
				Factory.showError("Please enter the file!");
				return;
			}
			
			int id;
			if (RequestsTable.getData().size() > 0) {
				id = RequestsTable.getData().get(RequestsTable.getData().size()-1).getId()+1;
			} else {
				id = 0;
			}
			
			double h = 0;
			try {
				h = Double.parseDouble(hoursField.getText());
			} catch (NumberFormatException ex) {
				Factory.showError("Please enter digit only in Hours field!");
				return;
			}
			
			File dest = new File(Serializer.CONFIG_HOME+file.getName());
//			try {
//				int i = 1;
//				while (!dest.createNewFile()) {
//					dest = new File(Serializer.CONFIG_HOME+file.getName().replace(".pdf","")+
//							"("+i+")"+".pdf");
//					i++;
//				}
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
			
			Control.copyFile(file, dest);
			
			RequestsTable.getData().add(new TRequestModel(id,descField.getText(),
					typeField.getText(),h,Status.Pending,"",dest));
			
			Control.reqMap.put(LoginScreen.user.getName(), new ArrayList<TRequestModel>(RequestsTable.getData()));
			Control.saveReqData();
			
			this.close();
		});
		
		cancelBut.setOnAction(e -> this.close());
	}
}
