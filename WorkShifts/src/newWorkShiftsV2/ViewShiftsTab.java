package newWorkShiftsV2;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewShiftsTab {
	IOFileV2 ioFile = new IOFileV2();
	ToJson toJson = new ToJson();
	WorkDepartment workDepartment;
	Stage window;
	ArrayList<TreeItem<String>> arrayLineTree = new ArrayList<TreeItem<String>>();
	TreeItem<String> root;
	TreeView<String> rootWorkDepartment;
	Button buttonBackToMain, buttonAddNewWorkingLine, buttonAddLineTimes, buttonDeleteNewWorkingLine,
			buttonDeleteWeekShift;
	Scene scene;
	WorkingLine workingLine;
	TableView<WeekShifts> tableShiftsWeekShifts;
	ArrayList<WeekShifts> weekShifts;
	TextField lineField;
	ObservableList<WeekShifts> oneDayShiftList = FXCollections.observableArrayList();
	int index = 0;
	AllEmployee allEmployee;

	@SuppressWarnings("unchecked")
	public void ViewWorkingLine() {
		// Buttons
		window = new Stage();

		buttonDeleteWeekShift = new Button();
		buttonDeleteWeekShift.setText("Delete shift");
		buttonDeleteWeekShift.setOnAction(e -> {
			if (ConfirmBox.Confirm("SURE?", "Do you want to delete this shift?")) {
				deleteButton();
			}

		});

		buttonAddLineTimes = new Button();
		buttonAddLineTimes.setText("Add Start/End Working Time");
		buttonAddLineTimes.setOnAction(e -> {
			//rootWorkDepartment.getSelectionModel().clearSelection();
			workDepartment = AddShiftTime.addTimes(workDepartment);
			tableShiftsWeekShifts.refresh();
		});

		buttonAddNewWorkingLine = new Button();
		buttonAddNewWorkingLine.setText("Add new working Line");
		buttonAddNewWorkingLine.setOnAction(e -> {
			addLineButton();
		});

		buttonDeleteNewWorkingLine = new Button();
		buttonDeleteNewWorkingLine.setText("Delete working Line");
		buttonDeleteNewWorkingLine.setOnAction(e -> {
			if (ConfirmBox.Confirm("SURE?", "Do You want to delete this shift?"))
				deleteLineButton();
		});

		buttonBackToMain = new Button();
		buttonBackToMain.setText("Back to Main");
		buttonBackToMain.setOnAction(e -> {
			IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
			window.close();
		});

		// line TextField
		lineField = new TextField();
		lineField.setPromptText("Write the name");
		lineField.setMinWidth(100);

		if (IOFileV2.initFile("databaseV2", "department_database") == false) {
			workDepartment = toJson.JsonToWorkDepartment(ioFile.ImportJson("databaseV2", "department_database"));
		} else {
			workDepartment = new WorkDepartment();
		}

		// create Tree
		rootWorkDepartment = new TreeView<>();
		root = new TreeItem<String>();
		root.setExpanded(true);
		for (int x = 0; x < workDepartment.getWorkingLines().size(); x++) {
			workingLine = workDepartment.getWorkingLines().get(x);
			arrayLineTree.add(lineTree(workingLine.getNameLine(), root));
		}

		rootWorkDepartment = new TreeView<>(root);
		rootWorkDepartment.setShowRoot(false);
		rootWorkDepartment.setPrefWidth(50);
		rootWorkDepartment.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue != null)
				index = workDepartment.getIndexLineFromName(newValue.getValue());
			if (workDepartment.getWorkingLines().size() != 0)
				showSelected(index);
			tableShiftsWeekShifts.refresh();
		});


		// One Day Shift Table
		int tableWidth = 50;
		

		ChoiceBox<String> choiceEmployee = new ChoiceBox<>();
		if (IOFileV2.initFile("databaseV2", "database") == false) {
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
		for (int x = 0; x < allEmployee.getAllEmployee().size(); x++)
			choiceEmployee.getItems().add(allEmployee.getAllEmployee().get(x).getSurname() + " " + allEmployee.getAllEmployee().get(x).getName());
		}
		
		choiceEmployee.setMinWidth(100);
		
		TableColumn<WeekShifts, String> Sunday = new TableColumn<>("Sunday");
		Sunday.setMinWidth(tableWidth);
		Sunday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Sunday"));
		TableColumn<WeekShifts, String> Monday = new TableColumn<>("Monday");
		Monday.setMinWidth(tableWidth);
		Monday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Monday"));
		TableColumn<WeekShifts, String> Tuesday = new TableColumn<>("Tuesday");
		Tuesday.setMinWidth(tableWidth);
		Tuesday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Tuesday"));
		TableColumn<WeekShifts, String> Wednesday = new TableColumn<>("Wednesday");
		Wednesday.setMinWidth(tableWidth);
		Wednesday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Wednesday"));
		TableColumn<WeekShifts, String> Thursday = new TableColumn<>("Thursday");
		Thursday.setMinWidth(tableWidth);
		Thursday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Thursday"));
		TableColumn<WeekShifts, String> Friday = new TableColumn<>("Friday");
		Friday.setMinWidth(tableWidth);
		Friday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Friday"));
		TableColumn<WeekShifts, String> Saturday = new TableColumn<>("Saturday");
		Saturday.setMinWidth(tableWidth);
		Saturday.setCellValueFactory(new PropertyValueFactory<WeekShifts, String>("Saturday"));

		// Table shifts
		tableShiftsWeekShifts = new TableView<WeekShifts>();
		tableShiftsWeekShifts.setMinWidth(tableWidth * 7);
		tableShiftsWeekShifts.getColumns().addAll(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday);

		// Layout Gridpane
		GridPane gridShifts = new GridPane();
		GridPane.setConstraints(buttonBackToMain, 2, 2);
		GridPane.setConstraints(rootWorkDepartment, 0, 0);
		GridPane.setConstraints(buttonAddNewWorkingLine, 0, 3);
		GridPane.setConstraints(tableShiftsWeekShifts, 1, 0);
		GridPane.setConstraints(choiceEmployee, 2, 0);
		GridPane.setConstraints(lineField, 0, 2);
		GridPane.setConstraints(buttonDeleteNewWorkingLine, 0, 4);
		GridPane.setConstraints(buttonAddLineTimes, 1, 1);
		GridPane.setConstraints(buttonDeleteWeekShift, 1, 3);

		gridShifts.setPadding(new Insets(10, 10, 10, 10));
		gridShifts.setVgap(8);
		gridShifts.setHgap(10);
		gridShifts.getChildren().addAll(rootWorkDepartment, lineField, buttonAddLineTimes, buttonDeleteNewWorkingLine,
				buttonAddNewWorkingLine, choiceEmployee, tableShiftsWeekShifts, buttonDeleteWeekShift, buttonBackToMain);
		gridShifts.setAlignment(Pos.CENTER);
		scene = new Scene(gridShifts, 1024, 720);

		window.setScene(scene);
		window.setTitle("Working Line");
		window.show();
	}

	private void deleteButton() {
		ObservableList<WeekShifts> shiftsSelected, allShiftsTable;
		allShiftsTable = tableShiftsWeekShifts.getItems();
		shiftsSelected = tableShiftsWeekShifts.getSelectionModel().getSelectedItems();
		workingLine.getShift().removeAll(shiftsSelected);
		allShiftsTable.removeAll(shiftsSelected);

	}

	private TreeItem<String> lineTree(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}

	public ObservableList<OneDayShift> getAllOneDayWeekShifts(WeekShifts weekShifts) {

		ObservableList<OneDayShift> allWeekShifts = FXCollections.observableArrayList();
		allWeekShifts.addAll(weekShifts.getWeekShifts());
		return allWeekShifts;

	}

	public void showSelected(int index) {
		for (int x = 0; x < tableShiftsWeekShifts.getItems().size(); x++)
			tableShiftsWeekShifts.getItems().clear();
		workingLine = workDepartment.getWorkingLines().get(index);
		for (int shiftsProLine = 0; shiftsProLine < workingLine.getShift().size(); shiftsProLine++) {
			weekShifts = workingLine.getShift();
			tableShiftsWeekShifts.setItems(getWeek(weekShifts.get(shiftsProLine)));

		}
	}

	public void addLineButton() {
		if (lineField.getText().isEmpty() || lineField.getText() == "") {
			AlertBox.Display("ERROR", "Please write a name");
		} else {
			arrayLineTree.add(lineTree(lineField.getText(), root));
			WorkingLine workLine = new WorkingLine();
			workLine.setNameLine(lineField.getText());
			workDepartment.addWorkingLine(workLine);
			AlertBox.Display("Done", "Working Line succesfully added");
			lineField.clear();
		}
	}

	public void deleteLineButton() {
		boolean yesno = workDepartment.deleteWorkingLine(lineField.getText());
		if (yesno == true) {
			IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
			AlertBox.Display(lineField.getText(), "Deleted");
			lineField.clear();
			window.close();
			ViewWorkingLine();
		} else
			AlertBox.Display("ERROR", lineField.getText() + " non found into the database");
	}

	public ObservableList<WeekShifts> getWeek(WeekShifts weekShifts) {
		oneDayShiftList.add(weekShifts);
		return oneDayShiftList;

	}

}
