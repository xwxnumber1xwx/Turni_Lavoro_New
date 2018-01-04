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

public class DayOffForm {
	
	public static void SetDayOff() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Set DayOff");
		window.setMinWidth(250);
		IOFile save = new IOFile();
		final ArrayList <Employee> employeeArrayList = save.ImportObjectFromFile("database");
		DatePicker checkInDatePicker = new DatePicker();
		checkInDatePicker.setValue(LocalDate.now().plusWeeks(1));
		checkInDatePicker.setShowWeekNumbers(true);
				
		ChoiceBox <String> choiceEmployee = new ChoiceBox<>();
		Employee employee;
		Employee employeeTemp = null;
		for (int y = 0; y < employeeArrayList.size(); y++) {
			employee = employeeArrayList.get(y);
				choiceEmployee.getItems().add(employee.getSurname() + " " + employee.getName());
			}
		
		
		Label label = new Label("Which employee need a Day off?");
		Button buttonExit = new Button("Ok");
		buttonExit.setOnAction(e -> {
			final Employee employeeTemp2 = SelectedEmployee(employeeArrayList, choiceEmployee);
			DayOff.AddDayOffromForm(employeeTemp2, checkInDatePicker.getValue());
			AlertBox.Display("Done", "Day Off Added");
			window.close();
		});
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, choiceEmployee, checkInDatePicker, buttonExit);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
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
				System.out.print("FUNZIONA");
				 return employee;
			}
		}
		return null;
	}
}
