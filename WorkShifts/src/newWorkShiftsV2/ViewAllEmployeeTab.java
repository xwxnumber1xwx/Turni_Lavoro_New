package newWorkShiftsV2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAllEmployeeTab {
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	AllEmployee allEmployee;
	Stage window = new Stage();;
	TableView<Employee> table;
	Button buttonBackToMain;
	Scene scene;
	
	@SuppressWarnings("unchecked")
	public void ViewAllEmployee() {
		
		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> window.close());
		buttonBackToMain.setText("Back to Main");
		
		if (IOFileV2.initFile("databaseV2", "database") == false) {
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
			} else
				allEmployee = new AllEmployee();
		
		TableColumn<Employee, Long> idEmployee = new TableColumn<>("employeeID");
		idEmployee.setMinWidth(50);
		idEmployee.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
		
		TableColumn<Employee, String> name = new TableColumn<>("name");
		name.setMinWidth(100);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Employee, String> surname = new TableColumn<>("surname");
		surname.setMinWidth(100);
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		
		
		table = new TableView<Employee>();
		table.setItems(getAllEmployee(allEmployee));
		
		table.getColumns().addAll(idEmployee, surname, name);
		
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(table, buttonBackToMain);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox, 400, 300);

		window.setScene(scene);
		window.setTitle("All Employee");
		window.show();
	}
	
	public ObservableList<Employee> getAllEmployee(AllEmployee allEmpoloyee){
		
		ObservableList<Employee> AllEmployeObsList = FXCollections.observableArrayList();
			AllEmployeObsList.addAll(allEmpoloyee.getAllEmployee());
		return AllEmployeObsList;
		
	}

}
