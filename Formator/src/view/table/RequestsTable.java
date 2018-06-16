package view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.table.TRequestModel;

public class RequestsTable extends TableView<TRequestModel> {

	private TableColumn<TRequestModel, Integer> idCol = new TableColumn<>("ID");
	private TableColumn<TRequestModel, String> descCol = new TableColumn<>("Description");
	private TableColumn<TRequestModel, String> typeCol = new TableColumn<>("Type");
	private TableColumn<TRequestModel, Double> hoursCol = new TableColumn<>("Hours");
	private TableColumn<TRequestModel, String> statusCol = new TableColumn<>("Status");
	private static final ObservableList<TRequestModel> data = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	public RequestsTable() {
		data.clear();
		this.getColumns().addAll(idCol,descCol,typeCol,hoursCol,statusCol);
		this.setItems(data);
		
		idCol.setCellValueFactory(new PropertyValueFactory<TRequestModel,Integer>("id"));
		idCol.setPrefWidth(40);
		idCol.setResizable(false);
		descCol.setCellValueFactory(new PropertyValueFactory<TRequestModel,String>("desc"));
		typeCol.setCellValueFactory(new PropertyValueFactory<TRequestModel,String>("type"));
		hoursCol.setCellValueFactory(new PropertyValueFactory<TRequestModel,Double>("hours"));
		statusCol.setCellValueFactory(new PropertyValueFactory<TRequestModel,String>("status"));
		
		idCol.prefWidthProperty().bind(this.widthProperty().multiply(0.10));
		descCol.prefWidthProperty().bind(this.widthProperty().multiply(0.30));
		typeCol.prefWidthProperty().bind(this.widthProperty().multiply(0.20));
		hoursCol.prefWidthProperty().bind(this.widthProperty().multiply(0.20));
		statusCol.prefWidthProperty().bind(this.widthProperty().multiply(0.20));
		this.requestLayout();
	}

	public static ObservableList<TRequestModel> getData() {
		return data;
	}
}
