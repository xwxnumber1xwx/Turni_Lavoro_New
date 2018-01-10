package newWorkShiftsV2;

import java.util.ArrayList;

public class WorkDepartment {
	private String nameOfDepartment = "";
	private ArrayList<WorkingLine> department = new ArrayList<WorkingLine>();

	/**
	 * @return the nameOfDepartment
	 */
	public String getNameOfDepartment() {
		return nameOfDepartment;
	}

	/**
	 * @param nameOfDepartment the nameOfDepartment to set
	 */
	public void setNameOfDepartment(String nameOfDepartment) {
		this.nameOfDepartment = nameOfDepartment;
	}

	/**
	 * @return the department
	 */
	public ArrayList<WorkingLine> getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(ArrayList<WorkingLine> department) {
		this.department = department;
	}
}
