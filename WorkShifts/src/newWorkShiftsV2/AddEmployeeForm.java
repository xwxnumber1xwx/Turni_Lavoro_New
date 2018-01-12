package newWorkShiftsV2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddEmployeeForm {

	Button buttonBackToMain, buttonInsertEmployee;
	Stage window = new Stage();
	Scene sceneAddEmployee;
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	AllEmployee allEmployee;
	// AllEmployee allEmployee = new AllEmployee();

	public AllEmployee addEmployeeForm() {

		if (IOFileV2.initFile("databaseV2", "database") == false) {
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
		} else
			allEmployee = new AllEmployee();

		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> {
			IOFileV2.exportJson("databaseV2", "database", toJson.AllEmployeeToJson(allEmployee));
			window.close();
		});
		buttonBackToMain.setText("Back to Main");
		GridPane.setConstraints(buttonBackToMain, 0, 15);

		Label labelEmployeeCode = new Label("Employee´s ID:");
		GridPane.setConstraints(labelEmployeeCode, 0, 0);
		TextField employeeCode = new TextField();
		employeeCode.setPromptText("ID");
		GridPane.setConstraints(employeeCode, 1, 0);

		// Surname
		Label labelSurname = new Label("Surname:");
		GridPane.setConstraints(labelSurname, 0, 1);
		TextField surnameInput = new TextField();
		surnameInput.setPromptText("Surname");
		GridPane.setConstraints(surnameInput, 1, 1);

		// Name
		Label labelName = new Label("Name:");
		GridPane.setConstraints(labelName, 0, 2);
		TextField nameInput = new TextField();
		nameInput.setPromptText("Name");
		GridPane.setConstraints(nameInput, 1, 2);

		buttonInsertEmployee = new Button();
		buttonInsertEmployee.setOnAction(e -> {
			employeeInput(employeeCode, surnameInput, nameInput);
			// add employee´s conditions

		});
		buttonInsertEmployee.setText("Insert Employee");
		GridPane.setConstraints(buttonInsertEmployee, 1, 7);

		// Layout (layoutAddEmployee)
		GridPane gridAddEmployee = new GridPane();
		gridAddEmployee.setPadding(new Insets(10, 10, 10, 10));
		gridAddEmployee.setVgap(8);
		gridAddEmployee.setHgap(10);

		gridAddEmployee.getChildren().addAll(labelEmployeeCode, employeeCode, labelSurname, surnameInput, labelName,
				nameInput, buttonInsertEmployee, buttonBackToMain);
		gridAddEmployee.setAlignment(Pos.CENTER);
		sceneAddEmployee = new Scene(gridAddEmployee, 600, 400);

		window.setScene(sceneAddEmployee);
		window.setTitle("Add Employee");
		window.show();
		return allEmployee;
	}

	public void employeeInput(TextField employeeID, TextField Surname, TextField Name) {
		long employeeIDLong;
		try {
			Long.parseLong(employeeID.getText());
		} catch (Exception e) {
			AlertBox.Display("Error!", "Non-numeric character exist");
			window.close();
		}

		employeeIDLong = Long.parseLong(employeeID.getText());

		Employee employee = AddEmployee.addEmployeeFromForm(employeeIDLong, Surname.getText(), Name.getText());
		allEmployee.setOneEmployee(employee);
	}

}
