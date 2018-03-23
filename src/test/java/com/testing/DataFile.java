package com.testing;

public class DataFile {
	
	Xls_Reader d = new Xls_Reader("C:\\QA Training\\Home_Workspace\\fullExercises\\Basic_TestNG_Framework\\Data\\NikulTest.xlsx");
	
	public String validEmail = d.getCellData("Data1", "UserName", 2);
	public String invalidEmail = d.getCellData("Data1", "UserName", 3);
	public String invalidPassword = d.getCellData("Data1", "Password", 2);
	public String emailError = d.getCellData("Data1", "Email Error", 2);
	public String passwordError = d.getCellData("Data1", "Password Error", 2);

}
