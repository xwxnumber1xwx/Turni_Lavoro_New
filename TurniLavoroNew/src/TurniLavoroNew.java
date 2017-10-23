import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.io.*;

class TurniLavoroNew {
	public static void saveFile () {
	Properties proprieties = new Properties();
	OutputStream os = null;
	try {
		os = new FileOutputStream("C:/Users/Daniele/options.proprieties") ;
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String TAG_DOM = "TAG_DOM";
	proprieties.setProperty(TAG_DOM, "Libero");
	proprieties.setProperty("TAG_MAR", "2:30-10:36");
	proprieties.setProperty("TAG_MER", "2:30-10:36");
	proprieties.setProperty("TAG_GIO", "2:30-10:36");
	proprieties.setProperty("TAG_VEN", "2:00-10:06");
	proprieties.setProperty("TAG_SAB", "Libero");
	proprieties.setProperty("NACHT_DOM", "16:00-00:06");
	proprieties.setProperty("NACHT_LUN", "16:30-00:36");
	proprieties.setProperty("NACHT_MAR", "16:30-00:36");
	proprieties.setProperty("NACHT_MER", "16:30-00:36");
	proprieties.setProperty("NACHT_GIO", "16:30-00:36");
	proprieties.setProperty("NACHT_VEN_1", "15:00-23:06");
	proprieties.setProperty("NACHT_VEN_2", "18:00-02:06");
	proprieties.setProperty("NACHT_SAB", "Libero");
	try {
		proprieties.store(os, "NON_TOCCARE");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				// Creo 5 dipendenti come prova su un array di dipendenti
				saveFile();
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>(6);
				Stampa stampa = new Stampa();
				for (int i = 0; i < 4; i++) {
					Dipendente dipendente  = new Dipendente();
					dipendente.setNome("DipendenteArray n: " + i);
					dipendente.setLivello(4);
					dipendente.setLineaLavoro(1);
					dipendente.malattia = false; // solo una prova, da cancellare
					dipendenteArrayList.add (dipendente);
					int OrarioLavorativo = 1; // 1 per TAG, 2 per NACHT
					int giornoLibero = 0;
						OrarioLavorativo = new Random () .nextInt(2) + 1;
							if (OrarioLavorativo == 2) { 
									giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
							}			stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, OrarioLavorativo, dipendente, dipendente.malattia));
					System.out.println("\n");
					
				}
				
				
				
			
				//Inserire inserimento da Tastiera su Database
				//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
				
				
					
				//Inserire lettura da Database
				//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
				//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
				
				}

}