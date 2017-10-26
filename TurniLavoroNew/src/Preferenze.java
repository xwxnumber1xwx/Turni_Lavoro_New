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
