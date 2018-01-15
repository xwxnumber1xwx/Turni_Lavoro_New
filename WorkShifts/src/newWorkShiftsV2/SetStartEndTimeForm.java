package newWorkShiftsV2;

import java.time.LocalTime;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetStartEndTimeForm {
	static LocalTime time;
	public static LocalTime setTime() {
		
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		
		time = LocalTime.of(0, 0);

		Label labelHour = new Label("Hour:");
		Label labelMinutes = new Label("Minutes:");
		Button buttonExit = new Button("Ok");

		ChoiceBox<Integer> hour = new ChoiceBox<>();
		for (int k = 0; k < 24; k++)
			hour.getItems().add(k);
		hour.setValue(00);
		ChoiceBox<Integer> minute = new ChoiceBox<>();
		for (int k = 0; k < 60; k++)
			minute.getItems().add(k);
		minute.setValue(00);
		buttonExit.setOnAction(e -> {
			setTimeFunction(hour.getValue(), minute.getValue());
			window.close();
			});

		VBox layout = new VBox(20);
		layout.getChildren().addAll(labelHour, hour, labelMinutes, minute, buttonExit);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return time;

	}
	public static void setTimeFunction(int hour, int minute) {
		time = LocalTime.of(hour, minute);
	}
}
