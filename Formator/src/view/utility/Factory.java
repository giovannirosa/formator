package view.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Factory {
	
	public static void showWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING,message);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR,message);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	public static void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION,message);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	public static ButtonType showConfirmation(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION,message, ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		alert.showAndWait();
		
		return alert.getResult();
	}
}
