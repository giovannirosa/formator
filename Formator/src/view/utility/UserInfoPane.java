package view.utility;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.User;

public class UserInfoPane extends HBox {

	Label logLabel = new Label("Logged User:");
	Label roleLabel = new Label("Role Assigned:");
	
	public UserInfoPane(User user) {
		this.setSpacing(20);
		this.getChildren().addAll(logLabel,roleLabel);
		this.setAlignment(Pos.CENTER);
		
		logLabel.setText("Logged User: "+user.getName());
		roleLabel.setText("Role Assigned: "+user.getRole());
	}
}
