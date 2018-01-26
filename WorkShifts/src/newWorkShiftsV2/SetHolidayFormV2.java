package newWorkShiftsV2;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetHolidayFormV2 {

	static IOFileV2 ioFile = new IOFileV2();
	static AllConditionsEmployee allconditionsEmployee;
	static ToJson toJson = new ToJson();
	static AllEmployee allEmployee;
	static DatePicker holidayInDatePickerStart;
	static DatePicker holidayInDatePickerEnd;
	

	public static void SetHoliday() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Set Holiday");
		window.setMinWidth(250);

		allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));

		if (IOFileV2.initFile("databaseV2", "condition_employee") == false) {
			allconditionsEmployee = toJson.JsonToCondition(ioFile.ImportJson("databaseV2", "condition_employee"));
		} else
			allconditionsEmployee = new AllConditionsEmployee();

		holidayInDatePickerStart = new DatePicker();
		holidayInDatePickerEnd = new DatePicker();
		holidayInDatePickerStart.setValue(LocalDate.now().plusWeeks(1));
		holidayInDatePickerStart.setShowWeekNumbers(true);
		holidayInDatePickerEnd.setValue(holidayInDatePickerStart.getValue().plusDays(1));
		holidayInDatePickerEnd.setShowWeekNumbers(true);

		ChoiceBox<String> choiceEmployee = new ChoiceBox<>();
		Employee employee;
		for (int y = 0; y < allEmployee.getAllEmployee().size(); y++) {
			employee = allEmployee.getAllEmployee().get(y);
			choiceEmployee.getItems()
					.add(employee.getSurname() + " " + employee.getName() + "  (" + employee.getEmployeeID() + ")");
		}

		Label label = new Label("Which employee need Holidays");
		Label labelStart = new Label("From");
		Label labelEnd = new Label("To");

		Button buttonExit = new Button("Ok");
		buttonExit.setOnAction(e -> {
			final long employeID = SelectedEmployee(allEmployee.getAllEmployee(), choiceEmployee);
			int index = allconditionsEmployee.getIndexFromID(employeID);
			if (index == 0) {
				ConditionEmployee conditionEmployee = new ConditionEmployee();
				conditionEmployee.setIdEmployee(employeID);
				conditionEmployee.setHoliday(holidayInDatePickerStart.getValue(), holidayInDatePickerEnd.getValue());
				allconditionsEmployee.addOneConditionEmployee(conditionEmployee);
			} else 
				allconditionsEmployee.getConditionEmployee().get(index).setHoliday(holidayInDatePickerStart.getValue(), holidayInDatePickerEnd.getValue());
			
			IOFileV2.exportJson("databaseV2", "condition_employee",
					toJson.ConditionEmployeeToJson(allconditionsEmployee));
			AlertBox.Display("Done", "Holiday Added");
			window.close();
		});

		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, choiceEmployee, labelStart, holidayInDatePickerStart, labelEnd,
				holidayInDatePickerEnd, buttonExit);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.showAndWait();

	}

	public static long SelectedEmployee(ArrayList<Employee> employeeArrayList, ChoiceBox<String> choiceEmployee) {
		String valueChoiceBox = choiceEmployee.getValue();
		for (int x = 0; x < employeeArrayList.size(); x++) {
			Employee employee = employeeArrayList.get(x);
			String employeeString = employee.getSurname() + " " + employee.getName() + "  (" + employee.getEmployeeID()
					+ ")";

			if (valueChoiceBox.compareTo(employeeString) == 0) {
				return employee.getEmployeeID();
			}
		}
		return 999999999;
	}
}
