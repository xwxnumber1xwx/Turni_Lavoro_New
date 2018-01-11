package newWorkShiftsV2;

public class AddEmployee {

	public static Employee addEmployeeFromForm(long employeeCode, String Surname, String name) {
		Employee employee = new Employee();
		employee.setEmployeeID(employeeCode);
		employee.setName(name);
		employee.setSurname(Surname);
		AlertBox.Display("Done", "Employee has been added!");
		return employee;

	}
}
