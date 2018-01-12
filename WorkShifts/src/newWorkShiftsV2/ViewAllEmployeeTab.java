package newWorkShiftsV2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAllEmployeeTab {
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	AllEmployee allEmployee;
	Stage window = new Stage();;
	TableView<Employee> table;
	Button buttonBackToMain, addEmployee, deleteEmployee, saveButton;
	Scene scene;
	TextField idField, surnamenField, nameField;

	@SuppressWarnings("unchecked")
	public void ViewAllEmployee() {

		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> window.close());
		buttonBackToMain.setText("Back to Main");

		if (IOFileV2.initFile("databaseV2", "database") == false) {
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
		} else
			allEmployee = new AllEmployee();

		// ID
		TableColumn<Employee, Long> idEmployee = new TableColumn<>("EmployeeID");
		idEmployee.setMinWidth(50);
		idEmployee.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

		// Name
		TableColumn<Employee, String> name = new TableColumn<>("Name");
		name.setMinWidth(100);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Surname
		TableColumn<Employee, String> surname = new TableColumn<>("Surname");
		surname.setMinWidth(100);
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));

		// Id TextField
		idField = new TextField();
		idField.setPromptText("ID");
		idField.setMinWidth(100);

		// surname TextField
		surnamenField = new TextField();
		surnamenField.setPromptText("Surmane");
		surnamenField.setMinWidth(100);

		// name TextField
		nameField = new TextField();
		nameField.setPromptText("name");
		nameField.setMinWidth(100);
		
		// Buttons
		addEmployee = new Button("add");
		addEmployee.setOnAction(e-> {
			addButton();
		});
		deleteEmployee = new Button("delete");
		deleteEmployee.setOnAction(e-> {
			deleteButton();
		});
		
		saveButton = new Button("save");
		saveButton.setOnAction(e-> {
			IOFileV2.exportJson("databaseV2", "database", toJson.AllEmployeeToJson(allEmployee));
			AlertBox.Display("SAVED", "saved into the database");
		});
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(idField, surnamenField, nameField, addEmployee, deleteEmployee, saveButton);
		
		
		table = new TableView<Employee>();
		table.setItems(getAllEmployee(allEmployee));

		table.getColumns().addAll(idEmployee, surname, name);

		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(table, hbox, buttonBackToMain);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox, 800, 600);

		window.setScene(scene);
		window.setTitle("All Employee");
		window.show();
	}

	public ObservableList<Employee> getAllEmployee(AllEmployee allEmpoloyee) {

		ObservableList<Employee> AllEmployeObsList = FXCollections.observableArrayList();
		AllEmployeObsList.addAll(allEmpoloyee.getAllEmployee());
		return AllEmployeObsList;

	}

	public void addButton() {
		long employeeIDLong;
		try {
			Long.parseLong(idField.getText());
		} catch (Exception ex) {
			AlertBox.Display("Error!", "Non-numeric character exist");
			window.close();
		}

		employeeIDLong = Long.parseLong(idField.getText());
		Employee employee = AddEmployee.addEmployeeFromForm(employeeIDLong, surnamenField.getText(),
				nameField.getText());
		table.getItems().add(employee);
		allEmployee.setOneEmployee(employee);
		idField.clear();
		surnamenField.clear();
		nameField.clear();
	}

	public void deleteButton() {
		ObservableList<Employee> employeeSelected, allEmployeeTable;
		allEmployeeTable = table.getItems();
		employeeSelected = table.getSelectionModel().getSelectedItems();
		//employeeSelected.forEach(allEmployeeTable::remove);
		allEmployeeTable.removeAll(employeeSelected);
		allEmployee.getAllEmployee().removeAll(employeeSelected);
	}

}
