package newWorkShiftsV2;


import com.google.gson.Gson;

public class ToJson {
	Gson gson = new Gson();

	public String AllEmployeeToJson(AllEmployee allEmployee) {

		String gsonString = gson.toJson(allEmployee);
		return gsonString;
	}

	public String WorkDepartmentToJson(WorkDepartment workdepartment) {

		String gsonString = gson.toJson(workdepartment);
		return gsonString;
	}

	public AllEmployee JsonToAllEmployee(String jsonString) {
		AllEmployee allEmployee = null;
		allEmployee = gson.fromJson(jsonString, AllEmployee.class);
		return allEmployee;
	}
	
	public WorkDepartment JsonToWorkDepartment(String jsonString) {
		WorkDepartment workDepartment = null;
		workDepartment = gson.fromJson(jsonString, WorkDepartment.class);
		return workDepartment;
	}

}
