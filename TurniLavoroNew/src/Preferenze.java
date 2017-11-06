import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Preferenze {
	
	public static void InitFile () {
		Properties proprieties = new Properties();
		OutputStream os = null;
		try {
			os = new FileOutputStream("options.proprieties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		proprieties.setProperty("TAG_Y_N", "Y");
		proprieties.setProperty("NACHT_Y_N", "Y");
		proprieties.setProperty("TAG_MIN_MITARBEITER", "3");
		proprieties.setProperty("NACHT_MIN_MITARBEITER", "6");
		proprieties.setProperty("NUM_MITARBEITER_LINEE1_NACHT", "2");
		proprieties.setProperty("ZUSCHLAG_NACHT", "50");
		proprieties.setProperty("ZUSCHLAG_FEIERTAG", "150");
		proprieties.setProperty("ZUSCHLAG_SONNTAG", "75");
		proprieties.setProperty("TAG_INIZIO_H", "2");
		proprieties.setProperty("TAG_INIZIO_M", "30");
		proprieties.setProperty("TAG_FINE_H", "10");
		proprieties.setProperty("TAG_FINE_M", "36");
		proprieties.setProperty("NACHT_INIZIO_H", "16");
		proprieties.setProperty("NACHT_INIZIO_M", "30");
		proprieties.setProperty("NACHT_FINE_H", "00");
		proprieties.setProperty("NACHT_FINE_M", "36");
		proprieties.setProperty("ZUSCHLAG_TAG_INIZIO_H", "00");
		proprieties.setProperty("ZUSCHLAG_TAG_INIZIO_M", "00");
		proprieties.setProperty("ZUSCHLAG_TAG_FINE_H", "04");
		proprieties.setProperty("ZUSCHLAG_TAG_FINE_M", "00");
		proprieties.setProperty("ZUSCHLAG_NACHT_INIZIO_H", "21");
		proprieties.setProperty("ZUSCHLAG_NACHT_INIZIO_M", "00");
		proprieties.setProperty("ZUSCHLAG_NACHT_FINE_H", "00");
		proprieties.setProperty("ZUSCHLAG_NACHT_FINE_M", "00");
		try {
			proprieties.store(os, "NON_TOCCARE"); //Salva il file con tutte le informazioni di sopra. Questo metodo va per ultimo.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public static String getOnePreference(String scelta) {
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
		String dettagli = proprieties.getProperty(scelta);
		return dettagli;
	}
}
