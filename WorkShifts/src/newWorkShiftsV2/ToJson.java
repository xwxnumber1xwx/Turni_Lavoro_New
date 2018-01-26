package newWorkShiftsV2;


import com.google.gson.Gson;

public class ToJson {
	Gson gson = new Gson();

	public String AllEmployeeToJson(AllEmployee allEmployee) {

		String gsonString = gson.toJson(allEmployee);
		return gsonString;
	}
	
		public AllEmployee JsonToAllEmployee(String jsonString) {
		AllEmployee allEmployee = null;
		allEmployee = gson.fromJson(jsonString, AllEmployee.class);
		return allEmployee;
	}

	public String AllWorkDepartmentToJson(AllWorkDepartment allWorkdepartment) {

		String gsonString = gson.toJson(allWorkdepartment);
		return gsonString;
	}

	public AllWorkDepartment JsonToAllWorkDepartment(String jsonString) {
		AllWorkDepartment allWorkDepartment = null;
		allWorkDepartment = gson.fromJson(jsonString, AllWorkDepartment.class);
		return allWorkDepartment;
	}
	
	public String ConditionEmployeeToJson(AllConditionsEmployee allConditionsEmployee) {
		String gsonString = gson.toJson(allConditionsEmployee);
		return gsonString;
	}
	
	public AllConditionsEmployee JsonToCondition(String jsonString) {
		AllConditionsEmployee allConditionsEmployee = null;
		allConditionsEmployee = gson.fromJson(jsonString, AllConditionsEmployee.class);
		return allConditionsEmployee;
	}
	
	public String GenericObjectToJson(Object object) {
		String gsonString = gson.toJson(object);
		return gsonString;
	}
	
	public AllShiftsEmployeeAssociation JsonToAllShiftsEmployeeAssociation(String jsonString) {
		AllShiftsEmployeeAssociation allShiftsEmployeeAssociation;
		allShiftsEmployeeAssociation = gson.fromJson(jsonString, AllShiftsEmployeeAssociation.class);
		return allShiftsEmployeeAssociation;
	}


}
