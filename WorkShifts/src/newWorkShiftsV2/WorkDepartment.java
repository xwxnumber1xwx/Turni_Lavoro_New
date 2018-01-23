package newWorkShiftsV2;

import java.util.ArrayList;

public class WorkDepartment {
	private String nameOfDepartment = "unknow";
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
		WorkingLine Wkline = this.workingLines.get(0);
		for (int x = 0; x < this.workingLines.size();  x++) {
			if (this.workingLines.get(x).getNameLine().compareTo(nameOfWorkingLine) == 0) {
				Wkline = this.workingLines.get(x);
			}
		}
		return Wkline;
	}
	
	public int getIndexLineFromName(String nameOfWorkingLine) {
		int index = 0;
		for (int x = 0; x < this.workingLines.size();  x++) {
			if (this.workingLines.get(x).getNameLine().compareTo(nameOfWorkingLine) == 0) {
				index = x;
			}
		}
		return index;
	}
	
	
	public boolean deleteWorkingLine(String nameOfWorkingLine) {
		boolean yesNo = false;
		for (int x = 0; x < this.workingLines.size();  x++) {
			if (this.workingLines.get(x).getNameLine().compareTo(nameOfWorkingLine) == 0) {
					this.workingLines.remove(x);
					yesNo = true;
			}
		}
		return yesNo;
	}

}
