package view.utility;

import controller.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import model.table.TUserModel;
import view.table.UsersTable;

public class UserScreen extends Stage {

	VBox geralPane = new VBox(20);
	GridPane inputPane = new GridPane();
	HBox buttonPane = new HBox(20);
	
	UsersTable usTable = new UsersTable();
	
	Button addBut = new Button("Add");
	Button delBut = new Button("Delete");
	Button closeBut = new Button("Close");
	
	public UserScreen(Window owner) {
		this.setTitle("Users");
		this.setResizable(false);
		this.initStyle(StageStyle.UTILITY);
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		
		Control.loadUserData(UsersTable.getData());
		
		setActions();
		
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(addBut,delBut,closeBut);
		
		geralPane.setAlignment(Pos.CENTER);
		geralPane.setPadding(new Insets(20));
		geralPane.getChildren().addAll(usTable,buttonPane);
		
		Scene scene = new Scene(geralPane);
		
		this.setScene(scene);
	}
	
	private void setActions() {
		addBut.setOnAction(e -> {
			new NewUserScreen(this).showAndWait();
			Control.loadUserData(UsersTable.getData());
		});
		delBut.setOnAction(e -> {
			TUserModel model = usTable.getSelectionModel().getSelectedItem();
			if (model == null) {
				Factory.showWarning("Please select a user!");
	        	return;
			}
			String user = model.getUser();
			if ((Factory.showConfirmation("Are you sure to delete "+user+" permanently?"
					+ "\nAll of its records will be deleted too!")) == ButtonType.YES) {
				Control.userMap.remove(user);
				Control.reqMap.remove(user);
				Control.subMap.remove(user);
				Control.saveAllData();
			}
			Control.loadUserData(UsersTable.getData());
		});
		closeBut.setOnAction(e -> this.close());
	}
}
