package newWorkShiftsV2;

import java.time.LocalTime;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddShiftTime {
	static TextField fieldSundayStart, fieldSundayEnd, fieldMondayStart, fieldMondayEnd, fieldTuesdayStart, fieldTuesdayEnd, 
	fieldWednesdayStart, fieldWednesdayEnd, fieldThursdayStart, fieldThursdayEnd, fieldFridayStart, fieldFridayEnd, fieldSaturdayStart, fieldSaturdayEnd;
	static OneDayShift oneDayShift;
	static ToJson toJson = new ToJson();
	static ArrayList<OneDayShift> weekShifts = new ArrayList<>(6);
	public static void addTimes(WorkDepartment workDepartment) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Week to Elaborate");
		window.setMinWidth(250);
		
		ChoiceBox <String> choiceWeek = new ChoiceBox<>();
		for (int x = 0; x < workDepartment.getWorkingLines().size() ; x++)
				choiceWeek.getItems().add(workDepartment.getWorkingLines().get(x).getNameLine());
		choiceWeek.setValue(workDepartment.getWorkingLines().get(0).getNameLine());
		
		
		Label label = new Label("Where do you Want add Times?");
		Button buttonExit = new Button("Ok");
		buttonExit.setOnAction(e -> {
			oneDayShift = addOneDayShift(LocalTime.parse(fieldSundayStart.getText()), LocalTime.parse(fieldSundayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldMondayStart.getText()), LocalTime.parse(fieldMondayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldTuesdayStart.getText()), LocalTime.parse(fieldTuesdayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldWednesdayStart.getText()), LocalTime.parse(fieldWednesdayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldThursdayStart.getText()), LocalTime.parse(fieldThursdayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldFridayStart.getText()), LocalTime.parse(fieldFridayEnd.getText()));
			weekShifts.add(oneDayShift);
			oneDayShift = addOneDayShift(LocalTime.parse(fieldSaturdayStart.getText()), LocalTime.parse(fieldSaturdayEnd.getText()));
			weekShifts.add(oneDayShift);
			
			//WorkingLine workingLine = workDepartment.getWorkingLineFromName(choiceWeek.getValue());
			WeekShifts weekSh = new WeekShifts();
			weekSh.setWeekShifts(weekShifts);
			workDepartment.getWorkingLineFromName(choiceWeek.getValue()).addOneShift(weekSh);
			IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
			window.close();
			ViewShifts shifts = new ViewShifts();
			shifts.ViewWorkingLine();
			
		window.close();
		});
		Label labelSunday= new Label("Sunday");
		Label labelMonday= new Label("Monday");
		Label labelTuesday= new Label("Tuesday");
		Label labelWednesday= new Label("Wednesday");
		Label labelThursday= new Label("Thursday");
		Label labelFriday= new Label("Friday");
		Label labelSaturday= new Label("Saturday");
		
		
		//TextField
		fieldSundayStart = new TextField();
		fieldSundayStart.setPromptText("00:00");
		fieldSundayStart.setOnMousePressed(e-> fieldSundayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldSundayStart.setMaxWidth(50);
		fieldSundayEnd = new TextField();
		fieldSundayEnd.setPromptText("00:00");
		fieldSundayEnd.setOnMousePressed(e-> fieldSundayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldSundayEnd.setMaxWidth(50);
		fieldMondayStart = new TextField();
		fieldMondayStart.setPromptText("00:00");
		fieldMondayStart.setOnMousePressed(e-> fieldMondayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldMondayStart.setMaxWidth(50);
		fieldMondayEnd = new TextField();
		fieldMondayEnd.setPromptText("00:00");
		fieldMondayEnd.setOnMousePressed(e-> fieldMondayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldMondayEnd.setMaxWidth(50);
		fieldTuesdayStart = new TextField();
		fieldTuesdayStart.setPromptText("00:00");
		fieldTuesdayStart.setOnMousePressed(e-> fieldTuesdayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldTuesdayStart.setMaxWidth(50);
		fieldTuesdayEnd = new TextField();
		fieldTuesdayEnd.setPromptText("00:00");
		fieldTuesdayEnd.setOnMousePressed(e-> fieldTuesdayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldTuesdayEnd.setMaxWidth(50);
		fieldWednesdayStart = new TextField();
		fieldWednesdayStart.setPromptText("00:00");
		fieldWednesdayStart.setOnMousePressed(e-> fieldWednesdayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldWednesdayStart.setMaxWidth(50);
		fieldWednesdayEnd = new TextField();
		fieldWednesdayEnd.setPromptText("00:00");
		fieldWednesdayEnd.setOnMousePressed(e-> fieldWednesdayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldWednesdayEnd.setMaxWidth(50);
		fieldThursdayStart = new TextField();
		fieldThursdayStart.setPromptText("00:00");
		fieldThursdayStart.setOnMousePressed(e-> fieldThursdayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldThursdayStart.setMaxWidth(50);
		fieldThursdayEnd = new TextField();
		fieldThursdayEnd.setPromptText("00:00");
		fieldThursdayEnd.setOnMousePressed(e-> fieldThursdayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldThursdayEnd.setMaxWidth(50);
		fieldFridayStart = new TextField();
		fieldFridayStart.setPromptText("00:00");
		fieldFridayStart.setOnMousePressed(e-> fieldFridayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldFridayStart.setMaxWidth(50);
		fieldFridayEnd = new TextField();
		fieldFridayEnd.setPromptText("00:00");
		fieldFridayEnd.setOnMousePressed(e-> fieldFridayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldFridayEnd.setMaxWidth(50);
		fieldSaturdayStart = new TextField();
		fieldSaturdayStart.setPromptText("00:00");
		fieldSaturdayStart.setOnMousePressed(e-> fieldSaturdayStart.setText(SetStartEndTimeForm.setTime().toString()));
		fieldSaturdayStart.setMaxWidth(50);
		fieldSaturdayEnd = new TextField();
		fieldSaturdayEnd.setPromptText("00:00");
		fieldSaturdayEnd.setOnMousePressed(e-> fieldSaturdayEnd.setText(SetStartEndTimeForm.setTime().toString()));
		fieldSaturdayEnd.setMaxWidth(50);
		
		
		//Input Layout
		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(choiceWeek, 1, 0);
		GridPane.setConstraints(labelSunday, 0, 3);
		GridPane.setConstraints(fieldSundayStart, 1, 3);
		GridPane.setConstraints(fieldSundayEnd, 2, 3);
		GridPane.setConstraints(labelMonday, 0, 4);
		GridPane.setConstraints(fieldMondayStart, 1, 4);
		GridPane.setConstraints(fieldMondayEnd, 2, 4);
		GridPane.setConstraints(labelTuesday, 0, 5);
		GridPane.setConstraints(fieldTuesdayStart, 1, 5);
		GridPane.setConstraints(fieldTuesdayEnd, 2, 5);
		GridPane.setConstraints(labelWednesday, 0, 6);
		GridPane.setConstraints(fieldWednesdayStart, 1, 6);
		GridPane.setConstraints(fieldWednesdayEnd, 2, 6);
		GridPane.setConstraints(labelThursday, 0, 7);
		GridPane.setConstraints(fieldThursdayStart, 1, 7);
		GridPane.setConstraints(fieldThursdayEnd, 2, 7);
		GridPane.setConstraints(labelFriday, 0, 8);
		GridPane.setConstraints(fieldFridayStart, 1, 8);
		GridPane.setConstraints(fieldFridayEnd, 2, 8);
		GridPane.setConstraints(labelSaturday, 0, 9);
		GridPane.setConstraints(fieldSaturdayStart, 1, 9);
		GridPane.setConstraints(fieldSaturdayEnd, 2, 9);
		GridPane.setConstraints(buttonExit, 2, 11);
		
		
		GridPane gridTime = new GridPane();
		gridTime.setPadding(new Insets(1, 1, 1, 1));
		gridTime.setVgap(8);
		gridTime.setHgap(10);
		gridTime.getChildren().addAll(label, labelSunday, labelMonday, labelTuesday, labelWednesday, 
				labelThursday, labelFriday, labelSaturday, choiceWeek, buttonExit, 
				fieldSundayStart, fieldSundayEnd,
				fieldMondayStart, fieldMondayEnd,
				fieldTuesdayStart,fieldTuesdayEnd,
				fieldWednesdayStart, fieldWednesdayEnd,
				fieldThursdayStart, fieldThursdayEnd,
				fieldFridayStart, fieldFridayEnd,
				fieldSaturdayStart, fieldSaturdayEnd
				);
		gridTime.setAlignment(Pos.CENTER);
		Scene sceneTime = new Scene(gridTime, 600, 400);
		
		
		window.setScene(sceneTime);
		window.showAndWait();

	}
	
	public static OneDayShift addOneDayShift(LocalTime start, LocalTime end) {
		OneDayShift oneDayShift = new OneDayShift();
		if (start.compareTo(end) == 0) {
			oneDayShift.setDayOff(true);
			}
		else {
			oneDayShift.setStartWorkTime(start);
			oneDayShift.setEndWorkTime(end);
		}
		return oneDayShift;
				
	}
}
