package newWorkShiftsV2;

public class ShiftsEmployeeAssociation {
	
	private String idEmployee;
	private String idWeek = "";
	/**
	 * @return the employee
	 */
	public String getEmployee() {
		return idEmployee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}
	/**
	 * @return the idWeek
	 */
	public String getIdWeek() {
		return idWeek;
	}
	/**
	 * @param idWeek the idWeek to set
	 */
	public void setIdWeek(String idWeek) {
		this.idWeek = idWeek;
	}

}
