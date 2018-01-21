package newWorkShiftsV2;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	Button buttonExit, buttonNew, buttonShifts;
	Stage window;
	Scene sceneMain, sceneAddEmployee;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		Label labelMain = new Label("Work Shifts");

		buttonShifts = new Button();
		buttonShifts.setText("Show Shifts");
		buttonShifts.setOnAction(e->{
			ViewShiftsTab shifts = new ViewShiftsTab();
			shifts.ViewWorkingLine();
		});
		
		buttonNew = new Button();
		buttonNew.setText("Employee...");
		
		buttonNew.setOnAction(e-> {
		EmployeeForm addForm = new EmployeeForm();	
		addForm.add();
		});

		buttonExit = new Button();
		buttonExit.setOnAction(e -> {
			e.consume();
			closeProgram();
		});
		buttonExit.setText("Exit");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});

		// Layout 1 (layoutMainWindow)
		VBox layoutMainWindow = new VBox(20);
		layoutMainWindow.getChildren().addAll(labelMain, buttonShifts, buttonNew, buttonExit);
		layoutMainWindow.setAlignment(Pos.CENTER);
		sceneMain = new Scene(layoutMainWindow, 400, 300);

		window.setScene(sceneMain);
		window.setTitle("Shifts");
		window.show();

	}

	public void closeProgram() {
		Boolean answer = ConfirmBox.Confirm("Exit", "Sure you want to exit?");
		if (answer == true) {
			window.close();
		}

	}
}