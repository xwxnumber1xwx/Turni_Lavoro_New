package newWorkShiftsV2;

import java.util.ArrayList;

public class AllConditionsEmployee {
	String name = "AllConditions";
	private ArrayList<ConditionEmployee> conditionEmployee = new ArrayList<ConditionEmployee>();

	/**
	 * @return the conditionEmployee
	 */
	public ArrayList<ConditionEmployee> getConditionEmployee() {
		return conditionEmployee;
	}

	/**
	 * @param conditionEmployee
	 *            the conditionEmployee to set
	 */
	public void setConditionEmployee(ArrayList<ConditionEmployee> conditionEmployee) {
		this.conditionEmployee = conditionEmployee;
	}

	public void addOneConditionEmployee(ConditionEmployee conditionEmployee) {
		this.conditionEmployee.add(conditionEmployee);
	}
	
	public int getIndexFromID (long id) {
		if (this.conditionEmployee.size() > 0) {
			for (int x = 0; x < this.conditionEmployee.size(); x++) {
				if (this.conditionEmployee.get(x).getIdEmployee() == id) {
					return x;
				}
			}
		}
		return 0;
	}
}
