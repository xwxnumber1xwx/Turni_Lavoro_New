package newWorkShiftsV2;

import java.util.ArrayList;

public class AllWorkDepartment {
	
	private ArrayList <WorkDepartment> workDepartmentArray = new ArrayList<WorkDepartment>(52);
	
	
	/**
	 * @return the workDepartmentArray
	 */
	public ArrayList <WorkDepartment> getWorkDepartmentArray() {
		return workDepartmentArray;
	}

	/**
	 * @param workDepartmentArray the workDepartmentArray to set
	 */
	public void setWorkDepartmentArray(ArrayList <WorkDepartment> workDepartmentArray) {
		this.workDepartmentArray = workDepartmentArray;
	}
	
	public void setOneWorkDepartment (WorkDepartment workDepartment, int week) {
		this.workDepartmentArray.set(week, workDepartment);
	}
	
	public void addOneWorkDepartment (WorkDepartment workDepartment) {
		this.workDepartmentArray.add(workDepartment);
	}

}
