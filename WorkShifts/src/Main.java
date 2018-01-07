import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{


		Button buttonElaborate, buttonAddEmployee, buttonAddDayOff, buttonBackToMain, buttonSetHolidays, buttonExit, buttonInsertEmployee;
		Stage window;
		Scene sceneMain, sceneAddEmployee;
		
		public static void main(String[] args) {
			launch (args);
		}
		
		@Override
		public void start(Stage primaryStage) throws Exception{
			window = primaryStage;
			
			Label labelMain = new Label("Work Shifts");
			
			buttonElaborate = new Button();
			buttonElaborate.setText("Elaborate Shifts");
			buttonElaborate.setOnAction(this);
			
			buttonAddEmployee = new Button();
			buttonAddEmployee.setText("Add Employee");
			buttonAddEmployee.setOnAction(this);
			
			buttonAddDayOff = new Button();
			buttonAddDayOff.setText("Add DayOff");
			buttonAddDayOff.setOnAction(this);
			
			buttonSetHolidays = new Button();
			buttonSetHolidays.setText("Add Holidays");
			buttonSetHolidays.setOnAction(e -> SetHolidayForm.SetHoliday());
		
			buttonExit = new Button();
			buttonExit.setOnAction(e -> {
				e.consume();
				closeProgram();
			});
			buttonExit.setText("Exit");
			window.setOnCloseRequest(e-> {
				e.consume();
				closeProgram();
			});
			
			
			
			
			// Layout 1 (layoutMainWindow)
			VBox  layoutMainWindow = new VBox(20);
			layoutMainWindow.getChildren().addAll(labelMain, buttonElaborate, buttonAddEmployee, buttonAddDayOff, buttonSetHolidays, buttonExit);
			layoutMainWindow.setAlignment(Pos.CENTER);
			sceneMain = new Scene (layoutMainWindow, 400, 300); 
			
						
			window.setScene(sceneMain);
			window.setTitle("Shifts");
			window.show();
			
		}
		@Override
		public void handle(ActionEvent event) {
			if(event.getSource()==buttonElaborate) {
				SetWeekForm.SetWeek();
			}
			if(event.getSource()==buttonAddEmployee) {
				AddEmployeeForm newEmployeeForm = new AddEmployeeForm();
				newEmployeeForm.addEmployeeForm();
			}
			if (event.getSource()==buttonAddDayOff) {
				DayOffForm.SetDayOff();
			}
						
		}
		
		public void closeProgram() {
			Boolean answer = ConfirmBox.Confirm("Exit", "Sure you want to exit?");
			if (answer == true) {
				window.close();
			}
			
		}
	}