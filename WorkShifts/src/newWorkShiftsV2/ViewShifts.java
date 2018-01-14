package newWorkShiftsV2;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	Button buttonBackToMain, buttonAddNewWorkingLine, buttonDeleteNewWorkingLine;
	Scene scene;
	WorkingLine workingLine;
	TableView<OneDayShift> tableShiftsWeekShifts;
	WeekShifts weekShifts;
	TextField lineField;

	@SuppressWarnings("unchecked")
	public void ViewWorkingLine() {
		// Buttons
		buttonAddNewWorkingLine = new Button();
		buttonAddNewWorkingLine.setOnAction(e -> {
			addLineButton();
		});
		buttonAddNewWorkingLine.setText("Add new working Line");

		buttonDeleteNewWorkingLine = new Button();
		buttonDeleteNewWorkingLine.setOnAction(e -> {
			deleteLineButton();
		});
		buttonDeleteNewWorkingLine.setText("Delete working Line");

		buttonBackToMain = new Button();
		buttonBackToMain.setOnAction(e -> {
			IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
			window.close();
		});
		buttonBackToMain.setText("Back to Main");

		// line TextField
		lineField = new TextField();
		lineField.setPromptText("name");
		lineField.setMinWidth(100);

		if (IOFileV2.initFile("databaseV2", "department_database") == false) {
			workDepartment = toJson.JsonToWorkDepartment(ioFile.ImportJson("databaseV2", "department_database"));
		} else {
			window.close();
			AlertBox.Display("ERROR!", "Need to add New Working Department");
			workDepartment = new WorkDepartment();
			addWorkOganization addWorkOganization = new addWorkOganization();
			addWorkOganization.addEmployeeForm(workDepartment);
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
		/*
		 * TableColumn<Employee, Long> idEmployee = new TableColumn<>("EmployeeID");
		 * idEmployee.setMinWidth(50); idEmployee.setCellValueFactory(new
		 * PropertyValueFactory<>("employeeID"));
		 * 
		 * // Name TableColumn<Employee, String> name = new TableColumn<>("Name");
		 * name.setMinWidth(100); name.setCellValueFactory(new
		 * PropertyValueFactory<>("name"));
		 * 
		 * 
		 * TableColumn<Employee, String> surname = new TableColumn<>("Surname");
		 * surname.setMinWidth(100); surname.setCellValueFactory(new
		 * PropertyValueFactory<>("surname"));
		 */
		// One Day Shift
		int tableWidth = 70;
		TableColumn<OneDayShift, LocalDateTime> Sunday = new TableColumn<>("Sunday");
		Sunday.setMinWidth(tableWidth);
		Sunday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Monday = new TableColumn<>("Monday");
		Monday.setMinWidth(tableWidth);
		Monday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Tuesday = new TableColumn<>("Tuesday");
		Tuesday.setMinWidth(tableWidth);
		Tuesday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Wednesday = new TableColumn<>("Wednesday");
		Wednesday.setMinWidth(tableWidth);
		Wednesday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Thursday = new TableColumn<>("Thursday");
		Thursday.setMinWidth(tableWidth);
		Thursday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Friday = new TableColumn<>("Friday");
		Friday.setMinWidth(tableWidth);
		Friday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		TableColumn<OneDayShift, LocalDateTime> Saturday = new TableColumn<>("Saturday");
		Saturday.setMinWidth(tableWidth);
		Saturday.setCellValueFactory(new PropertyValueFactory<OneDayShift, LocalDateTime>("startWorkTime"));

		// Table shifts
		tableShiftsWeekShifts = new TableView<OneDayShift>();
		if (workDepartment.getWorkingLines().size() != 0) {
			weekShifts = workingLine.getShift().get(0); // Just an example
			tableShiftsWeekShifts.setItems(getAllOneDayWeekShifts(weekShifts));
		}
		tableShiftsWeekShifts.setMinWidth(tableWidth * 7);
		tableShiftsWeekShifts.getColumns().addAll(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday);

		GridPane gridShifts = new GridPane();
		GridPane.setConstraints(buttonBackToMain, 2, 2);
		GridPane.setConstraints(rootWorkDepartment, 0, 0);
		GridPane.setConstraints(buttonAddNewWorkingLine, 0, 3);
		GridPane.setConstraints(tableShiftsWeekShifts, 1, 0);
		GridPane.setConstraints(lineField, 0, 2);
		GridPane.setConstraints(buttonDeleteNewWorkingLine, 0, 4);

		gridShifts.setPadding(new Insets(10, 10, 10, 10));
		gridShifts.setVgap(8);
		gridShifts.setHgap(10);
		gridShifts.getChildren().addAll(rootWorkDepartment, lineField, buttonDeleteNewWorkingLine,
				buttonAddNewWorkingLine, tableShiftsWeekShifts, buttonBackToMain);
		gridShifts.setAlignment(Pos.CENTER);
		scene = new Scene(gridShifts, 1024, 720);

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

	public ObservableList<OneDayShift> getAllOneDayWeekShifts(WeekShifts weekShifts) {

		ObservableList<OneDayShift> allWeekShifts = FXCollections.observableArrayList();
		allWeekShifts.addAll(weekShifts.getWeekShifts());
		return allWeekShifts;

	}

	public void addLineButton() {
		arrayLineTree.add(lineTree(lineField.getText(), root));
		WorkingLine workLine = new WorkingLine();
		workLine.setNameLine(lineField.getText());
		workDepartment.addWorkingLine(workLine);
		AlertBox.Display("Done", "Working Line succesfully added");
		lineField.clear();
	}

	// ERROR
	public void deleteLineButton() {

		workDepartment.deleteWorkingLine(lineField.getText());
		IOFileV2.exportJson("databaseV2", "department_database", toJson.WorkDepartmentToJson(workDepartment));
		AlertBox.Display(lineField.getText(), "Deleted");
		lineField.clear();
		window.close();
		ViewWorkingLine();
		/*
		 * rootWorkDepartment.getSelectionModel().clearSelection();
		 * rootWorkDepartment.getSelectionModel().selectedItemProperty().addListener((v,
		 * oldValue, newValue) -> { if (newValue != null) {
		 * root.getParent().getChildren().remove(newValue); // ERROR!!!
		 * workDepartment.deleteWorkingLine(newValue.getValue());
		 * IOFileV2.exportJson("databaseV2", "department_database",
		 * toJson.WorkDepartmentToJson(workDepartment));
		 * AlertBox.Display(newValue.getValue(), "Deleted"); } });
		 * rootWorkDepartment.getSelectionModel().clearSelection();
		 */
	}

}
