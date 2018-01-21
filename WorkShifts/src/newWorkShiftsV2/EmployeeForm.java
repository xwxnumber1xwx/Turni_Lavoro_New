package newWorkShiftsV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeForm {
	Button buttonBackToMain, buttonAddEmployee, viewAllEmployee, buttonAddHolidays;
	Stage window = new Stage();
	Scene scene;
	ViewAllEmployeeTab  viewAllEmployeeTab = new ViewAllEmployeeTab();;

	public void add() {
		
		buttonAddHolidays = new Button("addHolidays");
		buttonAddHolidays.setOnAction(e-> SetHolidayFormV2.SetHoliday());

		buttonBackToMain = new Button();
		buttonBackToMain.setText("Back to Main");
		buttonBackToMain.setOnAction(e -> window.close());
		
		
		viewAllEmployee = new Button();
		viewAllEmployee.setText("View All Employee");
		viewAllEmployee.setOnAction(e -> viewAllEmployeeTab.ViewAllEmployee());
		

		buttonAddEmployee = new Button();
		buttonAddEmployee.setText("Add Employee");
		buttonAddEmployee.setOnAction(e -> {
			AddEmployeeForm newEmployeeForm = new AddEmployeeForm();
			newEmployeeForm.addEmployeeForm();
		});
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(buttonAddEmployee, viewAllEmployee, buttonAddHolidays, buttonBackToMain);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox, 400, 300);

		window.setScene(scene);
		window.setTitle("Employee...");
		window.show();

	}
}
