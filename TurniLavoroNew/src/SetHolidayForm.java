import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

public class SetHolidayForm {
	
	public static void SetHoliday() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Set Holiday");
		window.setMinWidth(250);
		IOFile save = new IOFile();
		final ArrayList <Employee> employeeArrayList = save.ImportObjectFromFile("database");
		DatePicker checkInDatePickerStart = new DatePicker();
		DatePicker checkInDatePickerEnd = new DatePicker();
		checkInDatePickerStart.setValue(LocalDate.now().plusWeeks(1));
		checkInDatePickerStart.setShowWeekNumbers(true);
		checkInDatePickerEnd.setValue(checkInDatePickerStart.getValue().plusDays(1));
		checkInDatePickerEnd.setShowWeekNumbers(true);
		
				
		ChoiceBox <String> choiceEmployee = new ChoiceBox<>();
		Employee employee;
		for (int y = 0; y < employeeArrayList.size(); y++) {
			employee = employeeArrayList.get(y);
				choiceEmployee.getItems().add(employee.getSurname() + " " + employee.getName());
			}
		
		
		Label label = new Label("Which employee need Holidays");
		Label labelStart = new Label("From");
		Label labelEnd = new Label("To");
		
		Button buttonExit = new Button("Ok");
		buttonExit.setOnAction(e -> {
			final Employee employeeTemp2 = SelectedEmployee(employeeArrayList, choiceEmployee);
			LocalDate holidaysDate = checkInDatePickerStart.getValue();
			Period dayBetween = holidaysDate.until(checkInDatePickerEnd.getValue());
			for (int x = 0; x < dayBetween.get(ChronoUnit.DAYS); x++) {
				save.dayOff(employeeTemp2, holidaysDate, "holiday");
				holidaysDate = holidaysDate.plusDays(1);
			}
			AlertBox.Display("Done", "Holiday Added");
			//log
			String logText = (employeeTemp2.getSurname() + " " + employeeTemp2.getName() + "Added a Day Off on " + checkInDatePickerStart.getValue());
			IOFile.writeLog("log", "log", logText);
			
		});
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, choiceEmployee, labelStart, checkInDatePickerStart, labelEnd, checkInDatePickerEnd, buttonExit);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.showAndWait();

	}
	
	public static Employee SelectedEmployee(ArrayList <Employee> employeeArrayList, ChoiceBox <String> choiceEmployee ) {
		for (int x = 0; x < employeeArrayList.size(); x++) {
			Employee employee = employeeArrayList.get(x);
			String valueChoiceBox = choiceEmployee.getValue();
			String employeeString = employee.getSurname() + " " + employee.getName();
			
			// ERROR!!!
			if (valueChoiceBox.compareTo(employeeString) == 0) {
				
				return employee;
			}
		}
		return null;
	}
}
