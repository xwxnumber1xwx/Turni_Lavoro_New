/* 
 * Lo scopo di questo programma è generare turni di lavoro di una azienda specifica, avente due turni (mattina e sera) e con ogni turno 2 linee di lavoro (Linea 1 e Linea 2)
 * il programma deve tenere conto che tutti i lavoratori dovranno alternare i turni tra mattina e sera (tranne per scelta personale in caso il Dipendente voglia lavorare solo di mattina) in base
 * alla tariffa notturna guadagnata nel tempo, in modo che tutti i dipendenti abbiano guadagnato piu o meno le sesse ore notturne e festive in modo totalmente automatico.
 * il programma terrà conto che ogni Linea abbia bisogno di due capo-macchina/capo-Linea e dei lavoratori a sulla stessa linea. Ogni lavoratore Lavorerà su una Linea o meno in base alle proprie
 * capacità e sarà capo-macchina solo se avrà la capacità nel farlo.
 * inoltre il programma salvera su file:
 * - i turni personali di ogni Dipendente su un foglio .txt nella cartella Shift_mitarbeiter_LD. 
 * - i turni di tutti i Dipendenti diviso in più file .txt avente come nome il numero delle settimane dell'anno.
 * - i giorni liberi futuri richiesti esplecitamente dal dipendente. I files sono sotto la cartella frei_als_wunch e ogni file ha il nome del dipendente. I giorni liberi a richiesta  possono essere 
 * 			aggiunti direttamente sul file .txt. Il programma leggerà il file e ne terrà conto.
 * 
 * FUTURI AGGIORNAMENTI:
 * funzione per la gestione delle ferie
 * funzione per la gestione delle malattie
 * funzione per Lavorare una settimana di mattina o sera su richiesta
 * 
 * DA CORREGGERE: 
 * - Giorno Libero in base alla linea di lavoro e non in generale
 * - a volte chi ha la mattina riceve un giorno libero durante la settimana
 * - Metodi max 100 righe
 * 
 */
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



class TurniLavoroNew {
	

	
	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				String path = "options.proprieties";
				if (Preferenze.FileExist(path) == false) {
					Preferenze.InitFile();
				}
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>();
				int Nacht = 0;
				//int[] DoVe = new int[5];
				int NumMitarbeiterLinee = 1;
				int minNacht = Integer.parseInt(Preferenze.getOnePreference("NACHT_MIN_MITARBEITER"));
				int NumMitarbeiterNachtL1 = Integer.parseInt(Preferenze.getOnePreference("NUM_MITARBEITER_LINEE1_NACHT"));
				int NumMitarbeiterTagL1 = Integer.parseInt(Preferenze.getOnePreference("NUM_MITARBEITER_LINEE1_TAG"));
				
				//IMPUT KalenderWoche
				LocalDate date = LocalDate.of(2017, 1, 1);
				int inputWeek = 0;
				String nomeFile = "";
				int yn;
				IOFile save = new IOFile();
				Scanner scan = new Scanner (System.in);
				do { //CREAZIONE DIPENDENTE PROVVISORIA
					System.out.print("Volete Aggiungere un nuovo Dipendente? 1 = Y , 2 = N" + "\n");
					yn = scan.nextInt();
					if (yn == 1) {
						Create.createDipendente();
					}
				} while (yn == 1);
				
				//Carico i Dipendenti dal "Database"
				dipendenteArrayList = save.ImportObjectFromFile("database");
				System.out.println("volete aggiungere un giorno libero ad un dipendente? 1 si, 2 no");
				int z = scan.nextInt();
				
				if (z == 1) {
					FreeDay.addFreeDay(dipendenteArrayList, save);
					
				}
		
				//
				// chooses which week the program should process
				inputWeek = Create.setWeekToElaborate(inputWeek, save, date);
				inputWeek -= 1;
				date = date.plusWeeks(inputWeek);
				ArrayList <String> turniWeek = new ArrayList<String>();
				ArrayList <String> turnoDipendente = new ArrayList<String>();
				
				//sort the workers based on the accumulated night hours
				dipendenteArrayList = getMenoOre.OrdinePerNottiArrayList(dipendenteArrayList);

				//CHOICE WHO WORKS IN THE MORNING OR IN THE EVENING
				for (int x = 0 ; x < dipendenteArrayList.size(); x++) {
					Dipendente dipendente = dipendenteArrayList.get(x);
					
					if (inputWeek == 0) {
						dipendente.initZuSchlag();
					}

					// Morning or Evening
					if (dipendente.getSoloMattina() == true) {
						if (dipendente.getLineaLavoro() == 1 || dipendente.getLineaLavoro() == 3) {
							dipendente.setLineaTurno(1);
						} else {
							dipendente.setLineaTurno(2);
						}
						dipendente.setTagNacht(1); //TAG
					} else {
						if (dipendente.getLineaLavoro() == 1 || dipendente.getLineaLavoro() == 3) {
							if (Nacht <= minNacht) {
								if (NumMitarbeiterLinee <= NumMitarbeiterNachtL1) {
									Nacht++;
									NumMitarbeiterLinee++;
									dipendente.setLineaTurno(1);
									dipendente.setTagNacht(2); //Nacht
								} else if (dipendente.getLineaLavoro() == 2) {
										Nacht++;
										dipendente.setLineaTurno(2);
										dipendente.setTagNacht(2); //Nacht									
								}
							} else {
								dipendente.setLineaTurno(1);
								dipendente.setTagNacht(1); //TAG
							}
						} else if (dipendente.getLineaLavoro() == 2 || dipendente.getLineaLavoro() == 3) {
							if (Nacht <= minNacht) {
								Nacht++;
								dipendente.setLineaTurno(2);
								dipendente.setTagNacht(2); //Nacht
							} else {
								dipendente.setLineaTurno(2);
								dipendente.setTagNacht(1); //TAG
							}
						}
					}
						
					// Set a Free Day
					// spostato su GetMenoOre
					//DoVe = FreeDay.setFreeDay(dipendente, save, date, DoVe);
					
					}
				
					
					
				//GENERAZIONE TURNI E SALVATAGGIO	
				String directory = "turni_" + date.getYear();
				save.InitTurni(directory,  String.valueOf(inputWeek+1) + ".txt", date);
				dipendenteArrayList = getMenoOre.OrdinePerLinieLeiter(dipendenteArrayList, 2, NumMitarbeiterTagL1, NumMitarbeiterNachtL1, (minNacht-NumMitarbeiterNachtL1), date, save);
				for (int x = 0; x < dipendenteArrayList.size(); x++) {
					Dipendente dipendente = dipendenteArrayList.get(x);
					turnoDipendente = SwitchTurni.generaTurni(dipendente.getGiornoLiberoLD(), dipendente.getGiornoLibero(), dipendente.getTagNacht(), dipendente, dipendente.malattia, date.with(DayOfWeek.SUNDAY));
					turnoDipendente.add("\n");
					turniWeek.add(dipendente.getCognome() +" Linie Leiter: " + dipendente.getLineaTurnoLeiter() + " Linie: " + dipendente.getLineaTurno() +  " " + turnoDipendente);
					dipendente.setWeekShift(turnoDipendente);
					System.out.println("\n");
					nomeFile = (dipendente.getCognome() + ".txt");
					directory = "Shift_mitarbeiter_LD";
					save.ExportShiftLT(directory, nomeFile, dipendente, date);
					directory = "turni_" + date.getYear();
					save.ExportTurniDiTutti(directory, String.valueOf(inputWeek+1) + ".txt", turniWeek.get(x));
					nomeFile = (dipendente.getCognome() + ".dbs");
					save.ExportObjectToFile("database", nomeFile, dipendente);
				}
				
				turniWeek.forEach(System.out::println);
				dipendenteArrayList = getMenoOre.OrdinePerNottiArrayList(dipendenteArrayList);
				
				for(int h = 0; h < dipendenteArrayList.size(); h++) {
					Dipendente dipendenteVar = dipendenteArrayList.get(h);
					System.out.println(dipendenteVar.getCognome() + " TOT: " + dipendenteVar.getTotZuSchlag());
				}
				
				
				}
}