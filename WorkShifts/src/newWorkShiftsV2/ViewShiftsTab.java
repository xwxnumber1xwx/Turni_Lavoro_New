package newWorkShiftsV2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
	Button buttonBackToMain, buttonAddNewWorkingLine, buttonAddLineTimes, buttonDeleteNewWorkingLine, buttonNextWeek,
			buttonPrevWeek;
	Scene scene;
	WorkingLine workingLine;
	TableView<Week> tableShiftsWeekShifts;
	ArrayList<WeekShifts> weekShifts;
	TextField addLineTextField, activeWeek;
	ObservableList<Week> oneDayShiftList = FXCollections.observableArrayList();
	ObservableList<String> employeeObList = FXCollections.observableArrayList();
	int index = 0, weektoElaborate = 0;
	AllEmployee allEmployee;
	String idWeek;
	ContextMenu contextMenu; // right click menu
	ShiftsEmployeeAssociation shiftsEmployeeAssociation;
	AllShiftsEmployeeAssociation allShiftsEmployeeAssociation;
	String choosedName;
	TableColumn<Week, String> idEmployeeTC, nameSurnameID, idWeekTC, workingLineTC, Sunday, Monday, Tuesday, Wednesday,
			Thursday, Friday, Saturday;
	Label labelWeektoElaborate;
	AllWorkDepartment allWorkDepartment;
	LocalDate date;
	MenuBar menuBar;
	Menu fileMenu;
	MenuItem newMenuBar, openFileMenuBar, saveMenuBar, saveAsMenuBar, exitMenuBar;

	public void ViewWorkingLine() {

		window = new Stage();
		date = LocalDate.of(LocalDate.now().getYear(), 1, 1);
		date = date.with((DayOfWeek.SUNDAY));
		// Buttons delcaring
		buttons();

		// Labels
		labelWeektoElaborate = new Label("Week");

		// FileMenu on Top
		menuBarDeclaring();

		// contextMenu declaring (right click Menu on shifts)
		rightClickMenuOnShifts();

		// line TextField
		textFieldsDeclaring();

		idWeek = "";
		// Load from File WorkDepartment or create a new Object
		loadDepartmentDatabase();

		// Load from File AllShiftsEmployeeAssociation or create a new Object
		loadAllShiftsEmployeeAssociation();

		// create Tree
		rootTreeItemDeclaring();

		declaringRootWorkDepartment();

		// One Day Shift Table

		allEmployee = importAllEmployee(allEmployee);
		getSurnameEmployee();
		int tableWidth = 50;
		tableColumnDeclaring(tableWidth);

		// Table shifts
		tableViewDeclaring(tableWidth);

		setAllShiftOnTable();

		// Layout Gridpane
		GridPane gridShifts = gridPanePositionSetting();

		gridShifts.setPadding(new Insets(10, 10, 10, 10));
		gridShifts.setVgap(8);
		gridShifts.setHgap(10);
		gridShifts.getChildren().addAll(menuBar, rootWorkDepartment, activeWeek, addLineTextField, buttonAddLineTimes,
				labelWeektoElaborate, buttonNextWeek, buttonPrevWeek, buttonDeleteNewWorkingLine,
				buttonAddNewWorkingLine, tableShiftsWeekShifts, buttonBackToMain);
		gridShifts.setAlignment(Pos.CENTER);
		scene = new Scene(gridShifts, 1980, 1024);

		window.setScene(scene);
		window.setTitle("Working Line");
		window.show();
	}

	private void menuBarDeclaring() {
		fileMenu = new Menu("File");
		newMenuBar = new MenuItem("New...");
		newMenuBar.setOnAction(e -> {
			if (ConfirmBox.Confirm("SAVE", "Do you want Save before Exit?") == true) {
				saveToFile();
			}
			allWorkDepartment = new AllWorkDepartment();
			for (int x = 0; x < 53; x++) {
				allWorkDepartment.addOneWorkDepartment(new WorkDepartment());
			}
			workDepartment = allWorkDepartment.getWorkDepartmentArray().get(0);
			tableShiftsWeekShifts.getItems().clear();
			root.getChildren().clear();

		});

		openFileMenuBar = new MenuItem("Open File...");
		openFileMenuBar.setOnAction(e -> AlertBox.Display("ERROR", "This function come in the next update"));

		exitMenuBar = new MenuItem("Exit");
		exitMenuBar.setOnAction(e -> {
			if (ConfirmBox.Confirm("Exit?", "Do you want Save before Exit?") == true){
				saveToFile();
			}
			window.close();
		});
		saveMenuBar = new MenuItem("Save");
		saveMenuBar.setOnAction(e -> {
			saveToFile();
			AlertBox.Display("Saved", "File has been saved");
		});

		saveAsMenuBar = new MenuItem("Save File as...");
		saveAsMenuBar.setOnAction(e -> AlertBox.Display("ERROR", "This function come in the next update"));

		fileMenu.getItems().add(newMenuBar);
		fileMenu.getItems().add(openFileMenuBar);
		fileMenu.getItems().add(saveMenuBar);
		fileMenu.getItems().add(saveAsMenuBar);
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(exitMenuBar);

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
	}

	private void saveToFile() {
		IOFileV2.exportJson("databaseV2", "department_database", toJson.AllWorkDepartmentToJson(allWorkDepartment));
		IOFileV2.exportJson("databaseV2", "shifts_association",
				toJson.GenericObjectToJson(allShiftsEmployeeAssociation));
	}

	private void rootTreeItemDeclaring() {
		root = new TreeItem<String>("All Shifts");
		root.setExpanded(true);
		addItemsToArrayLineTree();
	}

	@SuppressWarnings("unchecked")
	private void tableViewDeclaring(int tableWidth) {
		tableShiftsWeekShifts = new TableView<Week>();
		tableShiftsWeekShifts.setEditable(true);
		tableShiftsWeekShifts.setMinWidth(tableWidth * 7);
		tableShiftsWeekShifts.setContextMenu(contextMenu);
		tableShiftsWeekShifts.getColumns().addAll(nameSurnameID, idEmployeeTC, workingLineTC, idWeekTC, Sunday, Monday,
				Tuesday, Wednesday, Thursday, Friday, Saturday);
	}

	private void tableColumnDeclaring(int tableWidth) {
		nameSurnameID = new TableColumn<>("employee");
		nameSurnameID.setMinWidth(tableWidth * 3);
		nameSurnameID.setCellValueFactory(new PropertyValueFactory<Week, String>("nameSurnameID"));
		nameSurnameID.setCellFactory(ComboBoxTableCell.forTableColumn(employeeObList));
		nameSurnameID.setOnEditCommit((TableColumn.CellEditEvent<Week, String> e) -> {
			choosedName = e.getNewValue();
			int indexCBOX = e.getTablePosition().getRow();
			tableShiftsWeekShifts.getItems().get(indexCBOX).setNameSurnameID(choosedName);
			setShiftEmployeeAssociation(indexCBOX);
			nameSurnameID.setCellValueFactory(new PropertyValueFactory<Week, String>("nameSurnameID"));
			tableShiftsWeekShifts.refresh();
		});
		idEmployeeTC = new TableColumn<>("ID employee");
		// idEmployeeTC.setVisible(false);
		idEmployeeTC.setMinWidth(tableWidth);
		idEmployeeTC.setCellValueFactory(new PropertyValueFactory<Week, String>("idEmployee"));
		idWeekTC = new TableColumn<>("ID Week");
		// idWeekTC.setVisible(false);
		idWeekTC.setMinWidth(tableWidth * 3);
		idWeekTC.setCellValueFactory(new PropertyValueFactory<Week, String>("idWeek"));
		workingLineTC = new TableColumn<>("Line");
		workingLineTC.setMinWidth(tableWidth);
		workingLineTC.setCellValueFactory(new PropertyValueFactory<Week, String>("lineName"));
		Sunday = new TableColumn<>();
		Sunday.setMinWidth(tableWidth);
		Sunday.setCellValueFactory(new PropertyValueFactory<Week, String>("sun"));
		Monday = new TableColumn<>();
		Monday.setMinWidth(tableWidth);
		Monday.setCellValueFactory(new PropertyValueFactory<Week, String>("mon"));
		Tuesday = new TableColumn<>();
		Tuesday.setMinWidth(tableWidth);
		Tuesday.setCellValueFactory(new PropertyValueFactory<Week, String>("tue"));
		Wednesday = new TableColumn<>();
		Wednesday.setMinWidth(tableWidth);
		Wednesday.setCellValueFactory(new PropertyValueFactory<Week, String>("wed"));
		Thursday = new TableColumn<>();
		Thursday.setMinWidth(tableWidth);
		Thursday.setCellValueFactory(new PropertyValueFactory<Week, String>("thu"));
		Friday = new TableColumn<>();
		Friday.setMinWidth(tableWidth);
		Friday.setCellValueFactory(new PropertyValueFactory<Week, String>("fri"));
		Saturday = new TableColumn<>();
		Saturday.setMinWidth(tableWidth);
		Saturday.setCellValueFactory(new PropertyValueFactory<Week, String>("sat"));
		addDateToDayOfWeekColumn();
	}

	private void setShiftEmployeeAssociation(int indexCBOX) {
		shiftsEmployeeAssociation = new ShiftsEmployeeAssociation();
		choosedName = choosedName.substring(choosedName.indexOf("("));
		choosedName = choosedName.substring(1, choosedName.indexOf(")", 1));
		shiftsEmployeeAssociation.setEmployee(choosedName);
		shiftsEmployeeAssociation.setIdWeek(tableShiftsWeekShifts.getItems().get(indexCBOX).getIdWeek());
		allShiftsEmployeeAssociation.addOneShiftsEmployeeAssociation(shiftsEmployeeAssociation);
	}

	private void declaringRootWorkDepartment() {
		rootWorkDepartment = new TreeView<>(root);
		rootWorkDepartment.setShowRoot(true);
		rootWorkDepartment.setPrefWidth(50);
		rootWorkDepartment.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue != null)
				index = workDepartment.getIndexLineFromName(newValue.getValue());
			if (workDepartment.getWorkingLines().size() != 0) {
				showSelected(index);
			}
			tableShiftsWeekShifts.refresh();
		});
	}

	private void loadAllShiftsEmployeeAssociation() {
		if (IOFileV2.initFile("databaseV2", "shifts_association") == false) {
			allShiftsEmployeeAssociation = toJson
					.JsonToAllShiftsEmployeeAssociation(ioFile.ImportJson("databaseV2", "shifts_association"));
		} else {
			allShiftsEmployeeAssociation = new AllShiftsEmployeeAssociation();
		}
	}

	private void loadDepartmentDatabase() {
		if (IOFileV2.initFile("databaseV2", "department_database") == false) {
			allWorkDepartment = toJson.JsonToAllWorkDepartment(ioFile.ImportJson("databaseV2", "department_database"));
			workDepartment = allWorkDepartment.getWorkDepartmentArray().get(weektoElaborate);
			takeLastIdWeek();
		} else {
			allWorkDepartment = new AllWorkDepartment();
			for (int x = 0; x < 53; x++) {
				allWorkDepartment.addOneWorkDepartment(new WorkDepartment());
			}
			workDepartment = allWorkDepartment.getWorkDepartmentArray().get(0);
		}
	}

	private void textFieldsDeclaring() {
		addLineTextField = new TextField();
		addLineTextField.setPromptText("Write the name");
		addLineTextField.setMinWidth(100);
		activeWeek = new TextField();
		String w = String.valueOf(1 + weektoElaborate);
		activeWeek.setText("WeeK: " + w);
		activeWeek.setMinWidth(50);
		activeWeek.setEditable(false);
	}

	private void rightClickMenuOnShifts() {
		contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete");
		MenuItem addShift = new MenuItem("Add Shifts");
		MenuItem copyShift = new MenuItem("Copy All Shift");
		contextMenu.getItems().addAll(addShift, delete, copyShift);
		addShift.setOnAction(e -> {
			idWeek = LocalDateTime.now().toString();
			workDepartment = AddShiftTime.addTimes(workDepartment, idWeek);
			tableShiftsWeekShifts.refresh();
		});
		delete.setOnAction(e -> {
			if (ConfirmBox.Confirm("Delete?", "Do you want to delete the selected Shifts?"))
				deleteSelectedShiftButton();

		});

		// Copy all the Shifts to Another week without Employee
		copyShift.setOnAction(e -> {
			int wk = CopyShifts.choseWeekToCopy();
			if (ConfirmBox.Confirm("Copy?", "Do you want to copy Shifts from week: " + (1 + weektoElaborate)
					+ " to week: " + (1 + wk) + "?") == true) {
				idWeek = LocalDateTime.now().toString();
				WorkDepartment workDepartmentClone = allWorkDepartment.getWorkDepartmentArray().get(wk);
				for (int u = 0; u < workDepartment.getWorkingLines().size(); u++) {
					WorkingLine WorkingLineClone = new WorkingLine();
					WorkingLineClone.setNameLine(workDepartment.getWorkingLines().get(u).getNameLine());
					for (int t = 0; t < workDepartment.getWorkingLines().get(u).getWeekShifts().size(); t++) {
						WeekShifts weekShiftsClone = new WeekShifts();
						weekShiftsClone.setIdWeek(idWeek + u + t);
						for (int v = 0; v < workDepartment.getWorkingLines().get(u).getWeekShifts().get(t)
								.getOneDayShifts().size(); v++) {
							OneDayShift oneDayShiftClone = new OneDayShift();
							oneDayShiftClone.setDayOff(workDepartment.getWorkingLines().get(u).getWeekShifts().get(t)
									.getOneDayShifts().get(v).isDayOff());
							oneDayShiftClone.setStartWorkTime(workDepartment.getWorkingLines().get(u).getWeekShifts()
									.get(t).getOneDayShifts().get(v).getStartWorkTime());
							oneDayShiftClone.setEndWorkTime(workDepartment.getWorkingLines().get(u).getWeekShifts()
									.get(t).getOneDayShifts().get(v).getEndWorkTime());
							weekShiftsClone.addOneDayShift(oneDayShiftClone);
						}
						WorkingLineClone.addOneWeekShift(weekShiftsClone);
					}
					workDepartmentClone.addWorkingLine(WorkingLineClone);
				}

				// allWorkDepartment.setOneWorkDepartment(workDepartmentClone, wk);
				AlertBox.Display("DONE", "Shifts succesfully copied");
			}
		});
	}

	private void buttons() {

		buttonAddLineTimes = new Button();
		buttonAddLineTimes.setText("Add Start/End Working Time");
		buttonAddLineTimes.setOnAction(e -> {
			idWeek = LocalDateTime.now().toString();
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
			saveToFile();
			window.close();
		});

		buttonNextWeek = new Button("->");
		buttonNextWeek.setOnAction(e -> {
			String w = ("WeeK: " + "1");
			if (weektoElaborate < 52) {
				weektoElaborate++;
				w = "WeeK: " + String.valueOf(1 + weektoElaborate);
			} else {
				weektoElaborate = 0;
			}
			activeWeek.setText(w);
			workDepartment = allWorkDepartment.getWorkDepartmentArray().get(weektoElaborate);
			root.getChildren().clear();
			addItemsToArrayLineTree();
			setAllShiftOnTable();
			addDateToDayOfWeekColumn();
		});
		buttonPrevWeek = new Button("<-");
		buttonPrevWeek.setOnAction(e -> {
			String w = ("WeeK: " + "52");
			if (weektoElaborate > 0) {
				weektoElaborate--;
				w = "WeeK: " + String.valueOf(1 + weektoElaborate);
			} else {
				weektoElaborate = 52;
			}
			activeWeek.setText(w);
			workDepartment = allWorkDepartment.getWorkDepartmentArray().get(weektoElaborate);
			root.getChildren().clear();
			addItemsToArrayLineTree();
			setAllShiftOnTable();
			addDateToDayOfWeekColumn();

		});

	}

	private void addDateToDayOfWeekColumn() {
		Sunday.setText(DayOfWeek.SUNDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.SUNDAY));
		date = date.plusDays(1);
		Monday.setText(DayOfWeek.MONDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.MONDAY));
		date = date.plusDays(1);
		Tuesday.setText(DayOfWeek.TUESDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.TUESDAY));
		date = date.plusDays(1);
		Wednesday.setText(DayOfWeek.WEDNESDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.WEDNESDAY));
		date = date.plusDays(1);
		Thursday.setText(DayOfWeek.THURSDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.THURSDAY));
		date = date.plusDays(1);
		Friday.setText(DayOfWeek.FRIDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.FRIDAY));
		date = date.plusDays(1);
		Saturday.setText(DayOfWeek.SATURDAY + "\n" + date.plusWeeks(weektoElaborate).with(DayOfWeek.SATURDAY));
		date = date.minusDays(6);
	}

	private void addItemsToArrayLineTree() {
		for (int x = 0; x < workDepartment.getWorkingLines().size(); x++) {
			workingLine = workDepartment.getWorkingLines().get(x);
			arrayLineTree.add(lineTree(workingLine.getNameLine(), root));
		}
	}

	private void takeLastIdWeek() {
		idWeek = LocalDateTime.now().toString();
	}

	private GridPane gridPanePositionSetting() {
		GridPane gridShifts = new GridPane();
		GridPane.setConstraints(menuBar, 10, 10);
		GridPane.setConstraints(buttonBackToMain, 3, 2);
		GridPane.setConstraints(rootWorkDepartment, 0, 0);
		GridPane.setConstraints(buttonAddNewWorkingLine, 0, 3);
		GridPane.setConstraints(activeWeek, 2, 1);
		GridPane.setConstraints(tableShiftsWeekShifts, 1, 0);
		GridPane.setConstraints(labelWeektoElaborate, 1, 1);
		GridPane.setConstraints(buttonNextWeek, 2, 2);
		GridPane.setConstraints(buttonPrevWeek, 1, 2);
		GridPane.setConstraints(addLineTextField, 0, 2);
		GridPane.setConstraints(buttonDeleteNewWorkingLine, 0, 4);
		GridPane.setConstraints(buttonAddLineTimes, 3, 0);

		return gridShifts;
	}

	private AllEmployee importAllEmployee(AllEmployee allEmployee) {
		if (IOFileV2.initFile("databaseV2", "database") == false)
			allEmployee = toJson.JsonToAllEmployee(ioFile.ImportJson("databaseV2", "database"));
		return allEmployee;
	}

	private void deleteSelectedShiftButton() {
		ObservableList<Week> shiftsSelected, allShiftsTable;
		allShiftsTable = tableShiftsWeekShifts.getItems();
		shiftsSelected = tableShiftsWeekShifts.getSelectionModel().getSelectedItems();
		workingLine.deleteWeekShift(shiftsSelected.get(0).getIdWeek());
		allShiftsEmployeeAssociation.idWeekAlredyExist(shiftsSelected.get(0).getIdWeek());
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
		allWeekShifts.addAll(weekShifts.getOneDayShifts());
		return allWeekShifts;

	}

	public void showSelected(int index) {

		// View only the selected line
		if (index != -1) {
			setSelectedShiftOnTable(index);
			// View All Shifts on Table
		} else {
			index = 0;
			setAllShiftOnTable();
		}
	}

	private void setSelectedShiftOnTable(int index) {
		tableShiftsWeekShifts.getItems().clear();
		workingLine = workDepartment.getWorkingLines().get(index);
		weekShifts = workingLine.getWeekShifts();
		for (int shiftsProLine = 0; shiftsProLine < workingLine.getWeekShifts().size(); shiftsProLine++) {
			tableShiftsWeekShifts.setItems(getShowedWeek(weekShifts.get(shiftsProLine), workingLine));
		}
	}

	private void setAllShiftOnTable() {
		tableShiftsWeekShifts.getItems().clear();
		for (int x = 0; x < workDepartment.getWorkingLines().size(); x++) {
			workingLine = workDepartment.getWorkingLines().get(x);
			for (int y = 0; y < workingLine.getWeekShifts().size(); y++) {
				weekShifts = workingLine.getWeekShifts();
				tableShiftsWeekShifts.setItems(getShowedWeek(weekShifts.get(y), workingLine));
			}
		}
	}

	public void addLineButton() {
		if (addLineTextField.getText().isEmpty() || addLineTextField.getText() == "") {
			AlertBox.Display("ERROR", "Please write a name");
		} else {
			arrayLineTree.add(lineTree(addLineTextField.getText(), root));
			WorkingLine workLine = new WorkingLine();
			workLine.setNameLine(addLineTextField.getText());
			workDepartment.addWorkingLine(workLine);
			AlertBox.Display("Done", "Working Line succesfully added");
			addLineTextField.clear();
		}
	}

	public void deleteLineButton() {
		boolean yesno = workDepartment.deleteWorkingLine(addLineTextField.getText());
		if (yesno == true) {
			IOFileV2.exportJson("databaseV2", "department_database", toJson.AllWorkDepartmentToJson(allWorkDepartment));
			AlertBox.Display(addLineTextField.getText(), "Deleted");
			addLineTextField.clear();
			window.close();
			ViewWorkingLine();
		} else
			AlertBox.Display("ERROR", addLineTextField.getText() + " non found into the database");
	}

	public ObservableList<Week> getShowedWeek(WeekShifts weekShifts, WorkingLine workingLine) {
		Week week = new Week();
		// chec if idWeek is alredy associated with an Employee
		int index = allShiftsEmployeeAssociation.idWeekAlredyExist(weekShifts.getIdWeek());
		if (index != -1) {
			shiftsEmployeeAssociation = allShiftsEmployeeAssociation.getShiftsEmployeeAssociation().get(index);
			week.setIdEmployeeTC(shiftsEmployeeAssociation.getEmployee());
			Employee employeeTemp = allEmployee
					.findEmployeeFromID(Long.parseLong(shiftsEmployeeAssociation.getEmployee()));
			week.setNameSurnameID(employeeTemp.getSurname() + " " + employeeTemp.getName() + " ("
					+ employeeTemp.getEmployeeID() + ")");
		}
		week.setWeek(workingLine.getNameLine(), weekShifts.getIdWeek(),
				weekShifts.getOneDayShifts().get(0).getStartEnd(), weekShifts.getOneDayShifts().get(1).getStartEnd(),
				weekShifts.getOneDayShifts().get(2).getStartEnd(), weekShifts.getOneDayShifts().get(3).getStartEnd(),
				weekShifts.getOneDayShifts().get(4).getStartEnd(), weekShifts.getOneDayShifts().get(5).getStartEnd(),
				weekShifts.getOneDayShifts().get(6).getStartEnd());
		oneDayShiftList.add(week);
		return oneDayShiftList;

	}

	public ObservableList<String> getSurnameEmployee() {
		employeeObList.add("");
		for (int x = 0; x < allEmployee.getAllEmployee().size(); x++) {
			employeeObList.add(allEmployee.getAllEmployee().get(x).getSurname() + " "
					+ allEmployee.getAllEmployee().get(x).getName() + " ("
					+ allEmployee.getAllEmployee().get(x).getEmployeeID() + ")");
		}
		return employeeObList;
	}

	// Class for view the shifts on Table plus Employee and other Informations
	public static class Week {
		String nameSurnameID = "";
		String idEmployee = "";
		String lineName = "";
		String idWeek = "";
		String sun = "";
		String mon = "";
		String tue = "";
		String wed = "";
		String thu = "";
		String fri = "";
		String sat = "";

		public void setWeek(String lineName, String idWeek, String sun, String mon, String tue, String wed, String thu,
				String fri, String sat) {
			this.lineName = lineName;
			this.idWeek = idWeek;
			this.sun = sun;
			this.mon = mon;
			this.tue = tue;
			this.wed = wed;
			this.thu = thu;
			this.fri = fri;
			this.sat = sat;
		}

		public void setNameSurnameID(String nameSurnameID) {
			this.nameSurnameID = nameSurnameID;
			if (nameSurnameID.isEmpty() == false) {
				String id = nameSurnameID;
				id = id.substring(id.indexOf("("));
				id = id.substring(1, id.indexOf(")", 1));
				setIdEmployeeTC(id);
			}

		}

		public void setIdEmployeeTC(String idEmployee) {
			this.idEmployee = idEmployee;
		}

		public String getNameSurnameID() {
			return this.nameSurnameID;
		}

		public String getIdEmployee() {
			return idEmployee;
		}

		public String getLineName() {
			return this.lineName;
		}

		public String getIdWeek() {
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
