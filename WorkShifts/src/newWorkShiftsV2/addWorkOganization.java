package newWorkShiftsV2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class addWorkOganization {
	Button buttonInsertWorkingLine, buttonExit;
	Stage window = new Stage();
	Scene sceneAddEmployee;
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	AllEmployee allEmployee;
	ViewShiftsTab shifts = new ViewShiftsTab();

	public void addEmployeeForm(WorkDepartment workDepartment) {

		buttonExit = new Button();
		buttonExit.setOnAction(e -> {
			window.close();
			shifts.ViewWorkingLine();
			
		});
		buttonExit.setText("Exit");
		GridPane.setConstraints(buttonExit, 0, 20);

		// Name of Working Line
		Label labelWorkingLine = new Label("Working Line Name:");
		GridPane.setConstraints(labelWorkingLine, 0, 1);
		TextField workingLineInput = new TextField();
		workingLineInput.setPromptText("Working Line Name:");
		GridPane.setConstraints(workingLineInput, 1, 1);

		buttonInsertWorkingLine = new Button();
		buttonInsertWorkingLine.setOnAction(e -> {
			addWorkingLine(workDepartment, workingLineInput);
		});
		buttonInsertWorkingLine.setText("Insert Working Line");
		GridPane.setConstraints(buttonInsertWorkingLine, 1, 7);

		// Layout (layoutAddEmployee)
		GridPane gridAddEmployee = new GridPane();
		gridAddEmployee.setPadding(new Insets(10, 10, 10, 10));
		gridAddEmployee.setVgap(8);
		gridAddEmployee.setHgap(10);

		gridAddEmployee.getChildren().addAll(labelWorkingLine, workingLineInput,
				buttonInsertWorkingLine, buttonExit);
		gridAddEmployee.setAlignment(Pos.CENTER);
		sceneAddEmployee = new Scene(gridAddEmployee, 600, 400);

		window.setScene(sceneAddEmployee);
		window.setTitle("Add Employee");
		window.show();
	}

	public void addWorkingLine(WorkDepartment workDepartment, TextField workingLineInput) {
		WorkingLine workingLine = new WorkingLine();
		workingLine.setNameLine(workingLineInput.getText());
		workDepartment.setNameOfDepartment("Backerei");
		workDepartment.addWorkingLine(workingLine);
		IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
		shifts.ViewWorkingLine();
		AlertBox.Display("Done", "Working Line succesfully added");
		window.close();
	}

}
