package newWorkShiftsV2;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxTableCell;
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
	TableView<Week> tableShiftsWeekShifts;
	ArrayList<WeekShifts> weekShifts;
	TextField lineField;
	ObservableList<Week> oneDayShiftList = FXCollections.observableArrayList();
	ObservableList<String> employeeObList = FXCollections.observableArrayList();
	int index = 0;
	AllEmployee allEmployee;
	int idWeek;
	ChoiceBox<String> choiceEmployee;

	@SuppressWarnings("unchecked")
	public void ViewWorkingLine() {
		
		window = new Stage();
		// Buttons
		buttons();

		// line TextField
		lineField = new TextField();
		lineField.setPromptText("Write the name");
		lineField.setMinWidth(100);

		idWeek = 1;
		if (IOFileV2.initFile("databaseV2", "department_database") == false) {
			workDepartment = toJson.JsonToWorkDepartment(ioFile.ImportJson("databaseV2", "department_database"));
			takeLastIdWeek();
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

		choiceEmployee = new ChoiceBox<>();
		addEmployeesToChoiceBox();
		choiceEmployee.setMinWidth(100);
		getSurnameEmployee();
		
		TableColumn<Week, String> nameSurnameID = new TableColumn<>("employee");
		nameSurnameID.setMinWidth(tableWidth);
		//nameSurnameID.setCellValueFactory(new PropertyValueFactory<Week, String>("nameSurnameID"));
		nameSurnameID.setCellFactory(ComboBoxTableCell.forTableColumn(employeeObList));
		TableColumn<Week, String> idWeekTC = new TableColumn<>("ID Week");
		idWeekTC.setVisible(false);
		idWeekTC.setMinWidth(tableWidth);
		idWeekTC.setCellValueFactory(new PropertyValueFactory<Week, String>("idWeek"));
		TableColumn<Week, String> Sunday = new TableColumn<>("Sunday");
		Sunday.setMinWidth(tableWidth);
		Sunday.setCellValueFactory(new PropertyValueFactory<Week, String>("sun"));
		TableColumn<Week, String> Monday = new TableColumn<>("Monday");
		Monday.setMinWidth(tableWidth);
		Monday.setCellValueFactory(new PropertyValueFactory<Week, String>("mon"));
		TableColumn<Week, String> Tuesday = new TableColumn<>("Tuesday");
		Tuesday.setMinWidth(tableWidth);
		Tuesday.setCellValueFactory(new PropertyValueFactory<Week, String>("tue"));
		TableColumn<Week, String> Wednesday = new TableColumn<>("Wednesday");
		Wednesday.setMinWidth(tableWidth);
		Wednesday.setCellValueFactory(new PropertyValueFactory<Week, String>("wed"));
		TableColumn<Week, String> Thursday = new TableColumn<>("Thursday");
		Thursday.setMinWidth(tableWidth);
		Thursday.setCellValueFactory(new PropertyValueFactory<Week, String>("thu"));
		TableColumn<Week, String> Friday = new TableColumn<>("Friday");
		Friday.setMinWidth(tableWidth);
		Friday.setCellValueFactory(new PropertyValueFactory<Week, String>("fri"));
		TableColumn<Week, String> Saturday = new TableColumn<>("Saturday");
		Saturday.setMinWidth(tableWidth);
		Saturday.setCellValueFactory(new PropertyValueFactory<Week, String>("sat"));

		// Table shifts
		tableShiftsWeekShifts = new TableView<Week>();
		tableShiftsWeekShifts.setEditable(true);
		tableShiftsWeekShifts.setMinWidth(tableWidth * 7);
		tableShiftsWeekShifts.getColumns().addAll(nameSurnameID, idWeekTC, Sunday, Monday, Tuesday, Wednesday, Thursday,
				Friday, Saturday);

		// Layout Gridpane
		GridPane gridShifts = gridPanePositionSetting();

		gridShifts.setPadding(new Insets(10, 10, 10, 10));
		gridShifts.setVgap(8);
		gridShifts.setHgap(10);
		gridShifts.getChildren().addAll(rootWorkDepartment, lineField, buttonAddLineTimes, buttonDeleteNewWorkingLine,
				buttonAddNewWorkingLine, choiceEmployee, tableShiftsWeekShifts, buttonDeleteWeekShift,
				buttonBackToMain);
		gridShifts.setAlignment(Pos.CENTER);
		scene = new Scene(gridShifts, 1024, 720);

		window.setScene(scene);
		window.setTitle("Working Line");
		window.show();
	}
	
	
	
	

	private void buttons() {
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
			// rootWorkDepartment.getSelectionModel().clearSelection();
			idWeek++;
			workDepartment = AddShiftTime.addTimes(workDepartment, idWeek);
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
	}

	private void takeLastIdWeek() {
		for (int x = 0; x < workDepartment.getWorkingLines().size(); x++) {
			idWeek += workDepartment.getWorkingLines().get(x).getShift().size();
		}
	}

	private GridPane gridPanePositionSetting() {
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
		return gridShifts;
	}

	private void addEmployeesToChoiceBox() {
		if (IOFileV2.initFile("databaseV2", "database") == false) {
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
			for (int x = 0; x < allEmployee.getAllEmployee().size(); x++)
				choiceEmployee.getItems().add(allEmployee.getAllEmployee().get(x).getSurname() + " "
						+ allEmployee.getAllEmployee().get(x).getName());
		}
	}

	private void deleteButton() {
		ObservableList<Week> shiftsSelected, allShiftsTable;
		allShiftsTable = tableShiftsWeekShifts.getItems();
		shiftsSelected = tableShiftsWeekShifts.getSelectionModel().getSelectedItems();
		workingLine.deleteWeekShift(shiftsSelected.get(0).getIdWeek());
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
		weekShifts = workingLine.getShift();
		for (int shiftsProLine = 0; shiftsProLine < workingLine.getShift().size(); shiftsProLine++) {
			tableShiftsWeekShifts.setItems(getShowedWeek(weekShifts.get(shiftsProLine)));

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

	public ObservableList<Week> getShowedWeek(WeekShifts weekShifts) {
		Week week = new Week();
		week.setWeek("", weekShifts.getIdWeek(), weekShifts.getWeekShifts().get(0).getStartEnd(),
				weekShifts.getWeekShifts().get(1).getStartEnd(), weekShifts.getWeekShifts().get(2).getStartEnd(),
				weekShifts.getWeekShifts().get(3).getStartEnd(), weekShifts.getWeekShifts().get(4).getStartEnd(),
				weekShifts.getWeekShifts().get(5).getStartEnd(), weekShifts.getWeekShifts().get(6).getStartEnd());
		oneDayShiftList.add(week);
		return oneDayShiftList;

	}
	
	
	public ObservableList<String> getSurnameEmployee () {
		for (int x = 0; x < allEmployee.getAllEmployee().size(); x++) {
			employeeObList.add(allEmployee.getAllEmployee().get(x).getSurname());
		}
		return employeeObList;
	}

	public static class Week {
		String nameSurnameID = "";
		int idWeek = 0;
		String sun = "";
		String mon = "";
		String tue = "";
		String wed = "";
		String thu = "";
		String fri = "";
		String sat = "";

		public void setWeek(String nameSurnameID, int idWeek, String sun, String mon, String tue, String wed,
				String thu, String fri, String sat) {
			this.nameSurnameID = nameSurnameID;
			this.idWeek = idWeek;
			this.sun = sun;
			this.mon = mon;
			this.tue = tue;
			this.wed = wed;
			this.thu = thu;
			this.fri = fri;
			this.sat = sat;
		}

		public String getNameSurnameID() {
			return this.nameSurnameID;
		}

		public int getIdWeek() {
			return this.idWeek;
		}

		public String getSun() {
			return this.sun;
		}

		public String getMon() {
			return this.mon;
		}

		public String getTue() {
			return this.tue;
		}

		public String getWed() {
			return this.wed;
		}

		public String getThu() {
			return this.thu;
		}

		public String getFri() {
			return this.fri;
		}

		public String getSat() {
			return this.sat;
		}
	}
}
