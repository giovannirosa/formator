package view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.table.TStudentModel;

public class StudentsTable extends TableView<TStudentModel> {

	private TableColumn<TStudentModel, String> studCol = new TableColumn<>("Student");
	private static final ObservableList<TStudentModel> data = FXCollections.observableArrayList();
	
	@SuppressWarnings("unchecked")
	public StudentsTable() {
		data.clear();
		this.getColumns().addAll(studCol);
		this.setItems(data);
		
		studCol.setCellValueFactory(new PropertyValueFactory<TStudentModel,String>("student"));
		studCol.prefWidthProperty().bind(this.widthProperty().multiply(1));
	}

	public static ObservableList<TStudentModel> getData() {
		return data;
	}
}
