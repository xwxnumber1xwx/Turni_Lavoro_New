package newWorkShiftsV2;

import java.util.ArrayList;

public class WorkDepartment {
	private String nameOfDepartment = "";
	private ArrayList<WorkingLine> workingLines = new ArrayList<WorkingLine>();

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
	public ArrayList<WorkingLine> getWorkingLines() {
		return workingLines;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(ArrayList<WorkingLine> department) {
		this.workingLines = department;
	}
	
	public void addWorkingLine(WorkingLine workingLine) {
		workingLines.add(workingLine);
	}
	
	public WorkingLine getWorkingLineFromName(String nameOfWorkingLine) {
		int x = 0;
		WorkingLine Wkline = this.workingLines.get(0);
		int size = this.workingLines.size() -1 ;
		for (x = 0; x < size;  x++); {
			if (this.workingLines.get(x).getNameLine().compareTo(nameOfWorkingLine) == 0) {
				Wkline = this.workingLines.get(x);
			}
		}
		return Wkline;
	}
	
	public void deleteWorkingLine(String nameOfWorkingLine) {
		int x = 0;
		int size = workingLines.size() -1 ;
		for (x = 0; x < size;  x++); {
			if (workingLines.get(x).getNameLine().compareTo(nameOfWorkingLine) == 0) {
					workingLines.remove(x);
			} else {
				AlertBox.Display("ERROR!", "Nothings to delete");
			}
		}
	}

}
