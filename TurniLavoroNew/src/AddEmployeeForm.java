import java.awt.Checkbox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddEmployeeForm {

	Button buttonAddDayOff, buttonBackToMain, buttonInsertEmployee;
	Stage window = new Stage();
	Scene sceneAddEmployee;
	
	public void addEmployeeForm() {
		
		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> window.close());
		buttonBackToMain.setText("Back to Main");
		GridPane.setConstraints(buttonBackToMain, 0, 15);
		
		Label labelEmployeeCode = new Label("Employee code:");
		GridPane.setConstraints(labelEmployeeCode, 0, 0);
		TextField employeeCode = new TextField("Code");
		GridPane.setConstraints(employeeCode, 1, 0);
		
		//Surname
		Label labelSurname = new Label("Surname:");
		GridPane.setConstraints(labelSurname, 0, 1);
		TextField surnameInput = new TextField("Surname");
		GridPane.setConstraints(surnameInput, 1, 1);
		
		//Name
		Label labelName = new Label("Name:");
		GridPane.setConstraints(labelName, 0, 2);
		TextField nameInput = new TextField("Name");
		GridPane.setConstraints(nameInput, 1, 2);

		//Ability
		Label labelAbility = new Label("Line Ability:");
		GridPane.setConstraints(labelAbility, 0, 3);
		CheckBox Line1CheckBox = new CheckBox("Line 1");
		CheckBox Line2CheckBox = new CheckBox("Line 2");
		CheckBox Line1LeaderCheckBox = new CheckBox("Leader Line 1");
		CheckBox Line2LeaderCheckBox = new CheckBox("Leader Line 2");
		GridPane.setConstraints(Line1LeaderCheckBox, 2, 3);
		GridPane.setConstraints(Line2LeaderCheckBox, 2, 4);
		Label labelOnlyMorning = new Label("Want work only in the Morning?");
		GridPane.setConstraints(labelOnlyMorning, 0, 5);
		CheckBox onlyMorningCheckBox = new CheckBox("Yes");
		GridPane.setConstraints(Line1CheckBox, 1, 3);
		GridPane.setConstraints(Line2CheckBox, 1, 4);
		GridPane.setConstraints(onlyMorningCheckBox, 1, 5);
		
		buttonInsertEmployee = new Button();
		buttonInsertEmployee.setOnAction(e -> {
			employeeInput(employeeCode, surnameInput, nameInput, Line1CheckBox, Line2CheckBox, Line1LeaderCheckBox, Line2LeaderCheckBox, onlyMorningCheckBox);
			//Create.createEmployee();
		});
		buttonInsertEmployee.setText("Insert Employee");
		GridPane.setConstraints(buttonInsertEmployee, 1, 7);
		
		// Layout 2 (layoutAddEmployee)
					GridPane gridAddEmployee = new GridPane();
					gridAddEmployee.setPadding(new Insets(10, 10, 10, 10));
					gridAddEmployee.setVgap(8);
					gridAddEmployee.setHgap(10);
					
					gridAddEmployee.getChildren().addAll(
						labelEmployeeCode, employeeCode, labelSurname, surnameInput,
						labelName, nameInput, labelAbility, Line1CheckBox, Line2CheckBox, 
						Line1LeaderCheckBox, Line2LeaderCheckBox,
						labelOnlyMorning, onlyMorningCheckBox,
						buttonInsertEmployee, buttonBackToMain);
					gridAddEmployee.setAlignment(Pos.CENTER);
					sceneAddEmployee = new Scene(gridAddEmployee, 600, 400);
					
					window.setScene(sceneAddEmployee);
					window.setTitle("Add Employee");
					window.show();
	}
	

	public void employeeInput(TextField employeeCode, TextField Surname, TextField Name, CheckBox line1CheckBox, CheckBox line2CheckBox, CheckBox Line1LeaderCheckBox, CheckBox Line2LeaderCheckBox, CheckBox onlyMorningCheckBox) {
		int lineAbility = 0;
		int lineLeader = 0;
		Boolean onyMorning = false;
		long employeeCodeLong;
		try {
			Long.parseLong(employeeCode.getText());
		} catch(Exception e) {
		    AlertBox.Display("Error!", "Non-numeric character exist");
		    window.close();
		}
		
		employeeCodeLong = Long.parseLong(employeeCode.getText());
		
		if (line1CheckBox.isSelected() == true && line2CheckBox.isSelected() == true) {
		lineAbility = 3;
		} else if (line1CheckBox.isSelected() == true) {
			lineAbility = 1;
		} else {
			lineAbility = 2;
		}
	
	if (Line1LeaderCheckBox.isSelected() == true && Line2LeaderCheckBox.isSelected() == true) {
		lineLeader = 3;
		} else if (Line1LeaderCheckBox.isSelected() == true) {
			lineLeader = 1;
		} else {
			lineLeader = 2;
		}
	
	
	if (onlyMorningCheckBox.isSelected() == true) {
		onyMorning = true;
	}
	
	Create.createEmployeeFromForm(employeeCodeLong, Surname.getText(), Name.getText(), lineAbility, lineLeader, onlyMorningCheckBox.isSelected());
		String str = Surname.getText() + " " + Name.getText();
		System.out.println(str);
	}
	
	
}
