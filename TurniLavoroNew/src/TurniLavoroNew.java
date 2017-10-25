import java.util.ArrayList;
import java.util.Random;
import java.io.*;

class TurniLavoroNew {
	
// DA SISTEMARE HO CONFUSO LINEALAVORO CON TAG E NACHT
	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				// Creo 5 dipendenti come prova su un array di dipendenti
				Preferenze.InitFile();
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>(6);
				Stampa stampa = new Stampa();
				int LineaLavoro = 0;
				int LineaLavoroTag = 0;
				int LineaLavoroNacht = 0;
				String p = Preferenze.getOnePreference("TAG_MIN_MITARBEITER");
				int minMatt = Integer.parseInt(p);
				Preferenze.getOnePreference("NACHT_MIN_MITARBEITER");
				int minNacht = Integer.parseInt(p);
				//int minMatt = Integer.parseInt(Preferenze.getOnePreference("TAG_MIN_MITARBEITER")); //ERRORE MI RESTITUISCE NULL!
				//int minNacht = Integer.parseInt(Preferenze.getOnePreference("NACHT_MIN_MITARBEITER"));//ERRORE MI RESTITUISCE NULL!
				for (int i = 0; i < 5; i++) {
					Dipendente dipendente  = new Dipendente();
					dipendente.setNome("DipendenteArray n: " + i);
					dipendente.setLivello(4);
					int[] linea = {1,2}; //Da inserire in base alla capacità lavorativa;
					dipendente.setLineaLavoro(linea);
					dipendente.malattia = new Random () .nextBoolean(); // solo una prova per la gente malata, da cancellare
					dipendenteArrayList.add (dipendente);
					// int OrarioLavorativo = 1; // 1 per TAG, 2 per NACHT
					if (LineaLavoroNacht <= minNacht) {
						LineaLavoro = 2;
						LineaLavoroNacht++;
					} else {
						LineaLavoro = 1;
						LineaLavoroTag++;
					}
					
					int giornoLibero = 0;
					int OrarioLavorativo = new Random () .nextInt(2) + 1;
						if (OrarioLavorativo == 2) { 
								giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
						}			
						stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, OrarioLavorativo, dipendente, dipendente.malattia), LineaLavoro);
						System.out.println("\n");
				}
				
				
				
			
				//Inserire inserimento da Tastiera su Database
				//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
				
				
					
				//Inserire lettura da Database
				//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
				//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
				
				}

}