package newWorkShiftsV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeForm {
	Button buttonBackToMain, buttonAddEmployee, viewAllEmployee;
	Stage window = new Stage();
	Scene scene;
	ViewAllEmployeeTab  viewAllEmployeeTab = new ViewAllEmployeeTab();;

	public void add() {
		
		

		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> window.close());
		buttonBackToMain.setText("Back to Main");
		
		viewAllEmployee = new Button();
		viewAllEmployee.setOnAction(e -> viewAllEmployeeTab.ViewAllEmployee());
		viewAllEmployee.setText("View All Employee");

		buttonAddEmployee = new Button();
		buttonAddEmployee.setText("Add Employee");
		buttonAddEmployee.setOnAction(e -> {
			AddEmployeeForm newEmployeeForm = new AddEmployeeForm();
			newEmployeeForm.addEmployeeForm();
		});
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(buttonAddEmployee, viewAllEmployee, buttonBackToMain);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox, 400, 300);

		window.setScene(scene);
		window.setTitle("New...");
		window.show();

	}
}
