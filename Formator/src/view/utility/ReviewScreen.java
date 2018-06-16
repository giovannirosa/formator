package view.utility;

import java.util.ArrayList;

import controller.Control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.table.TRequestModel;
import model.table.TRequestModel.Status;
import view.table.RequestsTable;

public class ReviewScreen extends Stage {

	VBox geralPane = new VBox(20);
	GridPane inputPane = new GridPane();
	HBox buttonPane = new HBox(20);
	
	Label descLabel = new Label("Description:");
	Label typeLabel = new Label("Type:");
	Label hoursLabel = new Label("Hours:");
	Label receiptLabel = new Label("Receipt:");
	Label statusLabel = new Label("Status:");
	Label commentLabel = new Label("Comment:");
	
	TextField descField = new TextField();
	TextField typeField = new TextField();
	TextField hoursField = new TextField();
	TextField receiptField = new TextField();
	TextArea commentsArea = new TextArea();
	ComboBox<Status> statusBox = new ComboBox<>();
	
	Button openBut = new Button("Open Receipt");
	Button confirmBut = new Button("Confirm");
	Button cancelBut = new Button("Cancel");
	
	int i;
	String student;
	
	public ReviewScreen(Window owner, String student, int i) {
		this.setTitle("Submit");
		this.setResizable(false);
		this.initStyle(StageStyle.UTILITY);
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		
		this.i = i;
		this.student = student;
		TRequestModel item = RequestsTable.getData().get(i);
		
		setActions();
		receiptField.setText(item.getFile().getName());
		receiptField.setEditable(false);
		descField.setText(item.getDesc());
		descField.setEditable(false);
		typeField.setText(item.getType());
		typeField.setEditable(false);
		hoursField.setText(Double.toString(item.getHours()));
		hoursField.setEditable(false);
		
		ObservableList<Status> statOptions = FXCollections.observableArrayList(
				Status.Pending,
				Status.Approved,
				Status.Rejected);
		statusBox.setItems(statOptions);
		statusBox.getSelectionModel().select(item.getStatusEnum());
		commentsArea.setWrapText(true);
		
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
		inputPane.add(openBut, 2, 3);
		inputPane.add(statusLabel, 0, 4);
		inputPane.add(statusBox, 1, 4);
		inputPane.add(commentLabel, 0, 5);
		inputPane.add(commentsArea, 1, 5);
		
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(confirmBut,cancelBut);
		
		geralPane.setAlignment(Pos.CENTER);
		geralPane.setPadding(new Insets(20));
		geralPane.getChildren().addAll(inputPane,buttonPane);
		
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
	}
	
	private void setActions() {
		cancelBut.setOnAction(e -> {
			this.close();
		});
		confirmBut.setOnAction(e -> {
			RequestsTable.getData().get(i).setStatusEnum(statusBox.getSelectionModel().getSelectedItem());
			RequestsTable.getData().get(i).setComment(commentsArea.getText());
			Control.reqMap.put(student,new ArrayList<>(RequestsTable.getData()));
			Control.subMap.put(student,new ArrayList<>(RequestsTable.getData()));
			Control.saveReqData();
			Control.saveSubData();
			this.close();
		});
		openBut.setOnAction(e -> {
			Control.openAction(RequestsTable.getData().get(i));
		});
	}
}
