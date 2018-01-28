package newWorkShiftsV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CopyShifts {

			public static int choseWeekToCopy() {
			
			Stage window = new Stage();

			window.initModality(Modality.APPLICATION_MODAL);
			window.setMinWidth(250);
			
			Label labelWeek = new Label(" Select Week to Copy: ");
			Button buttonExit = new Button("Ok");

			ChoiceBox<Integer> week = new ChoiceBox<>();
			for (int k = 1; k < 53; k++)
				week.getItems().add(k);
			week.setValue(1);
			buttonExit.setOnAction(e -> {
				
				window.close();
				});

			VBox layout = new VBox(20);
			layout.getChildren().addAll(labelWeek, week, buttonExit);
			layout.setAlignment(Pos.CENTER);

			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
			
			return week.getValue()-1;

		}
	}
	
