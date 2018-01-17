package OldVersion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Proprieties {
	
	public static boolean FileExist(String nameFile) {
		boolean yn = false;
		try {
			File file = new File(nameFile);
			boolean isCreate = file.createNewFile();
			
			if (isCreate) {
				String logText = ("File " + nameFile +  " created");
				IOFile.writeLog("log", "log", logText);
				yn = false;
				
			} else {
				String logText = ("File " + nameFile +  " alredy exist");
				IOFile.writeLog("log", "log", logText);
				yn = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return yn;
	}
	
	public static void InitFile () {
		Properties proprieties = new Properties();
		OutputStream os = null;
		try {
			os = new FileOutputStream("options.proprieties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		proprieties.setProperty("HOW_MANY_MINUTES_TO_WORK", "486");
		proprieties.setProperty("NIGHT_MINIMUM_EMPLOYEE", "8");
		proprieties.setProperty("NIGHT_LINE1_NUMBER_EMPLOYEE", "4");
		proprieties.setProperty("MORNING_LINE1_NUMBER_EMPLOYEE", "2");
		proprieties.setProperty("NIGHT_RATE", "50");
		proprieties.setProperty("HOLIDAY_RATE", "150");
		proprieties.setProperty("SUNDAY_RATE", "75");
		proprieties.setProperty("MORNING_START_H", "2");
		proprieties.setProperty("MORNING_START_M", "30");
		proprieties.setProperty("MORNING_END_H", "10");
		proprieties.setProperty("MORNING_END_M", "36");
		proprieties.setProperty("MORNING_FRIDAY_START_H", "2");
		proprieties.setProperty("MORNING_FRIDAY_START_M", "00");
		proprieties.setProperty("MORNING_FRIDAY_END_H", "10");
		proprieties.setProperty("MORNING_FRIDAY_END_M", "06");
		proprieties.setProperty("NIGHT_START_H", "16");
		proprieties.setProperty("NIGHT_START_M", "30");
		proprieties.setProperty("NIGHT_END_H", "00");
		proprieties.setProperty("NIGHT_END_M", "36");
		proprieties.setProperty("NIGHT_FRIDAY_START_H", "15");
		proprieties.setProperty("NIGHT_FRIDAY_START_M", "00");
		proprieties.setProperty("NIGHT_FRIDAY_END_H", "23");
		proprieties.setProperty("NIGHT_FRIDAY_END_M", "06");
		proprieties.setProperty("NIGHT_FRIDAY_SUNDAYOFF_START_H", "18");
		proprieties.setProperty("NIGHT_FRIDAY_SUNDAYOFF_START_M", "00");
		proprieties.setProperty("NIGHT_FRIDAY_SUNDAYOFF_END_H", "2");
		proprieties.setProperty("NIGHT_FRIDAY_SUNDAYOFF_END_M", "06");
		proprieties.setProperty("NIGHTRATE_END_H", "04");
		proprieties.setProperty("NIGHTRATE_END_M", "00");
		proprieties.setProperty("NIGHTRATE_START_H", "21");
		proprieties.setProperty("NIGHTRATE_START_M", "00");
		proprieties.setProperty("NIGHT_SUNDAY_START_H", "16");
		proprieties.setProperty("NIGHT_SUNDAY_START_M", "00");
		proprieties.setProperty("NIGHT_SUNDAY_END_H", "00");
		proprieties.setProperty("NIGHT_SUNDAY_END_M", "06");
		try {
			proprieties.store(os, "Proferennces to change");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public static String getOnePropriety(String choose) {
		Properties proprieties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream ("options.proprieties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			proprieties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String details = proprieties.getProperty(choose);
		return details;
	}
}
