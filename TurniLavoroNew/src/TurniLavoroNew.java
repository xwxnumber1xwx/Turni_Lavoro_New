import java.util.ArrayList;
import java.util.Random;
import java.io.*;

class TurniLavoroNew {
	
// DA SISTEMARE HO CONFUSO LINEALAVORO CON TAG E NACHT
	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				// Creo 9 dipendenti come prova su un array di dipendenti
				Preferenze.InitFile();
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>(9);
				Stampa stampa = new Stampa();
				int LineaLavoro = 1; // da AGGIUNGERE funzione per determinare quale line lavoro
				int Nacht = 0;
				int TagNacht = 1;
				int NumMitarbeiterLinee = 1;
				String p = Preferenze.getOnePreference("NACHT_MIN_MITARBEITER");
				int minNacht = Integer.parseInt(p);
				p = Preferenze.getOnePreference("NUM_MITARBEITER_LINEE1_NACHT");
				int NumMitarbeiterNacht = Integer.parseInt(p);
				for (int i = 0; i < 9; i++) {
					Dipendente dipendente  = new Dipendente();
					dipendente.setNome("DipendenteArray n: " + i);
					dipendente.setLivello(4);
					int[] linea = {1,2}; //Da inserire in base alla capacità lavorativa;
					dipendente.setLineaLavoro(linea);
					//dipendente.malattia = new Random () .nextBoolean(); // solo una prova per la gente malata, da cancellare
					dipendenteArrayList.add (dipendente);
					if (Nacht <= minNacht) { // IMPLEMENTARE REFERENCE A METODI
						Nacht++;
						TagNacht = 2; //NACHT
						if (NumMitarbeiterLinee < NumMitarbeiterNacht) {
							LineaLavoro = 1;
							NumMitarbeiterLinee++;
						} else {
								LineaLavoro = 2;
							}
					} else {
						TagNacht = 1; //TAG
					}
					
					int giornoLibero = 0;
						if (TagNacht == 2) { 
								giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
						}			
						stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, TagNacht, dipendente, dipendente.malattia), LineaLavoro);
						System.out.println("\n");
				}
				
				
				
			
				//Inserire inserimento da Tastiera su Database
				//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
				
				
					
				//Inserire lettura da Database
				//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
				//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
				
				}

}