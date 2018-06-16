package view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.table.TUserModel;

public class UsersTable extends TableView<TUserModel> {
	private TableColumn<TUserModel, String> userCol = new TableColumn<>("User");
	private TableColumn<TUserModel, String> passCol = new TableColumn<>("Password");
	private TableColumn<TUserModel, String> roleCol = new TableColumn<>("Role");
	private static final ObservableList<TUserModel> data = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	public UsersTable() {
		data.clear();
		this.getColumns().addAll(userCol,passCol,roleCol);
		this.setItems(data);
		
		userCol.setCellValueFactory(new PropertyValueFactory<TUserModel,String>("user"));
		passCol.setCellValueFactory(new PropertyValueFactory<TUserModel,String>("pass"));
		roleCol.setCellValueFactory(new PropertyValueFactory<TUserModel,String>("role"));
		
		userCol.prefWidthProperty().bind(this.widthProperty().multiply(0.33));
		passCol.prefWidthProperty().bind(this.widthProperty().multiply(0.33));
		roleCol.prefWidthProperty().bind(this.widthProperty().multiply(0.33));
	}

	public static ObservableList<TUserModel> getData() {
		return data;
	}
}
