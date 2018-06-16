package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginScreen;

public class Formator extends Application {

	 public static void main(String[] args) throws Exception {
		 Serializer.createAppData();
//		 resetData();
		 launch();
	 }
	 
//	 private static void resetData() {
//		 Commissioner c = new Commissioner("andrey", "senha");
//		 Map<String,User> map = new HashMap<>();
//		 
//		 map.put("andrey", c);
//		 Serializer.serialize(Data.Login, map);
//		 
//		 Map<String,List<TRequestModel>> reqMap = new HashMap<>();
//		 Serializer.serialize(Data.Submits, reqMap);
//		 Map<String,List<TRequestModel>> reqMap2 = new HashMap<>();
//		 Serializer.serialize(Data.Requests, reqMap2);
//	 }

	@Override
	public void start(Stage stage) throws Exception {
		stage = new LoginScreen();
		stage.show();
	}
}
