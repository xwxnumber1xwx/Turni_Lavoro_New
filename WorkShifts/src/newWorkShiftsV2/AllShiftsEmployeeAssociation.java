package newWorkShiftsV2;

import java.util.ArrayList;

public class AllShiftsEmployeeAssociation {

	private ArrayList<ShiftsEmployeeAssociation> shiftsEmployeeAssociation = new ArrayList<ShiftsEmployeeAssociation>();

	/**
	 * @return the shiftsEmployeeAssociation
	 */
	public ArrayList<ShiftsEmployeeAssociation> getShiftsEmployeeAssociation() {
		return shiftsEmployeeAssociation;
	}

	/**
	 * @param shiftsEmployeeAssociation
	 *            the shiftsEmployeeAssociation to set
	 */
	public void setShiftsEmployeeAssociation(ArrayList<ShiftsEmployeeAssociation> shiftsEmployeeAssociation) {
		this.shiftsEmployeeAssociation = shiftsEmployeeAssociation;
	}

	public void addOneShiftsEmployeeAssociation(ShiftsEmployeeAssociation shiftsEmployeeAssociation) {
		if (this.shiftsEmployeeAssociation.size() > 0)
			idWeekAlredyExistAndDeleteIT(shiftsEmployeeAssociation.getIdWeek());
		this.shiftsEmployeeAssociation.add(shiftsEmployeeAssociation);
	}

	public boolean idWeekAlredyExistAndDeleteIT(String idWeek) {
		for (int x = 0; x < shiftsEmployeeAssociation.size(); x++) {
			if (this.shiftsEmployeeAssociation.get(x).getIdWeek().equals(idWeek)) {
				this.shiftsEmployeeAssociation.remove(x);
				return true;
			}

		}
		return false;
	}
	

	public int idWeekAlredyExist(String idWeek) {
		for (int x = 0; x < shiftsEmployeeAssociation.size(); x++) {
			if (this.shiftsEmployeeAssociation.get(x).getIdWeek().equals(idWeek)) {
				return x;
			}
		}
		return -1;
	}
}
