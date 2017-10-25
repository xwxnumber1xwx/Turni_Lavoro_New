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
		proprieties.setProperty("MITTLE_Y_N", "N");
		proprieties.setProperty("NACHT_Y_N", "Y");
		proprieties.setProperty("TAG_MIN_MITARBEITER", "3");
		proprieties.setProperty("MITTLE_MIN_MITARBEITER", "0");
		proprieties.setProperty("NACH_MIN_MITARBEITER", "2");
		proprieties.setProperty("TAG_DOM", "Libero");
		proprieties.setProperty("TAG_MAR", "2:30-10:36");
		proprieties.setProperty("TAG_MER", "2:30-10:36");
		proprieties.setProperty("TAG_GIO", "2:30-10:36");
		proprieties.setProperty("TAG_VEN", "2:00-10:06");
		proprieties.setProperty("TAG_SAB", "Libero");
		proprieties.setProperty("MITTLE_DOM", "");
		proprieties.setProperty("MITTLE_MAR", "");
		proprieties.setProperty("MITTLE_MER", "");
		proprieties.setProperty("MITTLE_GIO", "");
		proprieties.setProperty("MITLE_VEN", "");
		proprieties.setProperty("MITTLE_SAB", "");
		proprieties.setProperty("NACHT_DOM", "16:00-00:06");
		proprieties.setProperty("NACHT_LUN", "16:30-00:36");
		proprieties.setProperty("NACHT_MAR", "16:30-00:36");
		proprieties.setProperty("NACHT_MER", "16:30-00:36");
		proprieties.setProperty("NACHT_GIO", "16:30-00:36");
		proprieties.setProperty("NACHT_VEN_1", "15:00-23:06");
		proprieties.setProperty("NACHT_VEN_2", "18:00-02:06");
		proprieties.setProperty("NACHT_SAB", "Libero");
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
