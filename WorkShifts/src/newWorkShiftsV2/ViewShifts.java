package newWorkShiftsV2;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewShifts {
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	WorkDepartment workDepartment;
	Stage window = new Stage();
	ArrayList<TreeItem<String>> arrayLineTree = new ArrayList<TreeItem<String>>();
	TreeItem<String> root;
	TreeView<String> rootWorkDepartment;
	Button buttonBackToMain, buttonAddNewWorkingLine;
	Scene scene;
	WorkingLine workingLine;

	public void ViewWorkingLine() {

		buttonAddNewWorkingLine = new Button();
		buttonAddNewWorkingLine.setOnAction(e -> {
			addWorkOganization addWorkOganization = new addWorkOganization();
			addWorkOganization.addEmployeeForm(workDepartment);
			window.close();
			});
		buttonAddNewWorkingLine.setText("Add new working Line");
		
		
		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> window.close());
		buttonBackToMain.setText("Back to Main");

		if (IOFileV2.initFile("databaseV2", "department_database") == false) {
			workDepartment = toJson.JsonToWorkDepartment(ioFile.ImportJson("databaseV2", "department_database"));
		} else {
			window.close();
			AlertBox.Display("ERROR!", "Need to add New Working Department");
			workDepartment = new WorkDepartment();
			addWorkOganization addWorkOganization = new addWorkOganization();
			addWorkOganization.addEmployeeForm(workDepartment);
		}

		rootWorkDepartment = new TreeView<>();

		root = new TreeItem<String>();
		root.setExpanded(true);

		for (int x = 0; x < workDepartment.getWorkingLines().size(); x++) {
			workingLine = workDepartment.getWorkingLines().get(x);
			arrayLineTree.add(lineTree(workingLine.getNameLine(), root));
		}
		
		// create Tree
		rootWorkDepartment = new TreeView<>(root);
		rootWorkDepartment.setShowRoot(false);

		GridPane gridShifts = new GridPane();
		GridPane.setConstraints(buttonBackToMain, 0, 1);
		GridPane.setConstraints(rootWorkDepartment, 0, 0);
		GridPane.setConstraints(buttonAddNewWorkingLine, 0, 2);
		gridShifts.setPadding(new Insets(10, 10, 10, 10));
		gridShifts.setVgap(8);
		gridShifts.setHgap(10);
		gridShifts.getChildren().addAll(rootWorkDepartment, buttonAddNewWorkingLine, buttonBackToMain);
		gridShifts.setAlignment(Pos.CENTER);
		scene = new Scene(gridShifts, 800, 600);
		

		window.setScene(scene);
		window.setTitle("Working Line");
		window.show();
	}

	private TreeItem<String> lineTree(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}

}
