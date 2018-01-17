package OldVersion;

import java.io.IOException;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetWeekForm {

	public static void SetWeek() {
		Stage window = new Stage();
		boolean weekAlredyExist = false;
		LocalDate date = LocalDate.now();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Week to Elaborate");
		window.setMinWidth(250);
		
		ChoiceBox <Integer> choiceWeek = new ChoiceBox<>();
		IOFile save = new IOFile();
		int var = 1;
		String directory = "shift_" + date.getYear();
		for (int y = 1; y < 53; y++) {
			weekAlredyExist = save.checkWeek(y, directory);
			if (weekAlredyExist == false) {
				choiceWeek.setValue(y);
				var = y;
				y = 53;
			}
		
		}
		
		for (int x = var; x < 53; x++) {
			choiceWeek.getItems().add(x);
		}
		
		
		
		Label label = new Label("Which week do you want to elaborate?");
		Button buttonExit = new Button("Ok");
		buttonExit.setOnAction(e -> {
		try {
			ElaborateShift.Elaborate(choiceWeek.getValue());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		window.close();
		});
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, choiceWeek, buttonExit);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}
}
