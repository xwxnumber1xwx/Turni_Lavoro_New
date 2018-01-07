package newWorkShiftsV2;

import java.util.ArrayList;

public class Employee{
	
	private long employeeCode = 0;
	private String name;
	private String surname;
	private int rank = 0;
	private ArrayList<Integer> workAbility = new ArrayList<Integer>();
	private ArrayList<Integer> leaderAbility = new ArrayList<Integer>();
	Statistics stats = new Statistics();
	
	public void setEmployeeCode (long employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	public long getEmployeeCode () {
		return this.employeeCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname (String surname) {
		this.surname = surname;
	}
	
	public int getRank () {
		return rank;
	}
	public void setRank (int rank) {
		this.rank = rank;
	}
	/**
	 * @return the workAbility
	 */
	public ArrayList<Integer> getWorkAbility() {
		return workAbility;
	}

	/**
	 * @param workAbility the workAbility to set
	 */
	public void setWorkAbility(ArrayList<Integer> workAbility) {
		this.workAbility = workAbility;
	}

	/**
	 * @return the leaderAbility
	 */
	public ArrayList<Integer> getLeaderAbility() {
		return leaderAbility;
	}

	/**
	 * @param leaderAbility the leaderAbility to set
	 */
	public void setLeaderAbility(ArrayList<Integer> leaderAbility) {
		this.leaderAbility = leaderAbility;
	}

	
}