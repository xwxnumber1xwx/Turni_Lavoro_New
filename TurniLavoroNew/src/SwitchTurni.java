
import java.time.LocalTime;
import java.util.ArrayList;


public class SwitchTurni {
	public static ArrayList<String> generaTurni (int giornoLibero, int MattSera, Dipendente dipendente, boolean malattia) {
		/*Inserire:
			- Calcolare la malattia per pochi giorni
		*/
		Turni sett = new Turni();
		ArrayList <String> turniLavoratoreArrayList = new ArrayList<String>(6);
		int x = 0;
		LocalTime inizioTAG = sett.getTagInizio();
		LocalTime fineTAG = sett.getTagFine();
		LocalTime InizioNachtZuSchlagTAG = sett.getInizioNachtZuSchlagTAG();
		LocalTime FineNachtZuSchlagTAG = sett.getFineNachtZuSchlagTAG();
		LocalTime inizioNACHT = sett.getNachtInizio();
		LocalTime fineNACHT = sett.getNachtFine();
		LocalTime InizioNachtZuSchlagNACHT = sett.getInizioNachtZuSchlagNACHT();
		LocalTime FineNachtZuSchlagNACHT = sett.getFineNachtZuSchlagNACHT();
		LocalTime InizioNachtZuSchlagVEN_Dom = sett.getInizioNachtZuSchlagVEN_Dom();
		LocalTime FineNachtZuSchlagVEN_Dom = sett.getFineNachtZuSchlagVEN_Dom();
		LocalTime InizioNachtZuSchlagVEN = sett.getInizioNachtZuSchlagVEN();
		LocalTime FineNachtZuSchlagVEN = sett.getFineNachtZuSchlagVEN();
		LocalTime domNach_inizio = sett.getdomNacht_inizio();
		LocalTime domNach_Fine = sett.getdomNach_fine();
		LocalTime inizio_NachtZuSchlag = sett.getInizioNachtZuSchlagNACHT();
		LocalTime fine_NachtZuSchlag = sett.getFineNachtZuSchlagTAG();
		
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
			
		for (x = 0; x < 7; x++) {
			if (x != giornoLibero) {
				if (malattia == true) {
					turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank"); //scrive krank per quel giorno
					dipendente.setGiorniMalattia(1); //aggiunge 1 giorno malattia al conto delle malattie del dipendente.
					}
				if (MattSera == 2) {
					if (x == 0) { // Domenica Lavorariva
						dipendente.setOreDomenicaLT(Turni.calcoloZuSchlag(domNach_inizio, domNach_Fine, inizio_NachtZuSchlag, fine_NachtZuSchlag)); //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
					}
					if (x == 5 & giornoLibero == 0) { // ven 18 perche domenica libera
						turniLavoratoreArrayList.add (x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN_Dom, FineNachtZuSchlagVEN_Dom, inizio_NachtZuSchlag, fine_NachtZuSchlag));  //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						continue;
					}
					if (x == 5) { //ven 15
						dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN, FineNachtZuSchlagVEN, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
						turniLavoratoreArrayList.add(x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera]); //Scrive Il giono "domenica, lunedi, ecc.." oppure "libero"
						continue;
					}
				}
				turniLavoratoreArrayList.add(x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera]); //Scrive Il giono "domenica, lunedi, ecc.." oppure "libero"
				if (MattSera == 1) {
				dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(inizioTAG, fineTAG, InizioNachtZuSchlagTAG, FineNachtZuSchlagTAG));
				}
				if (MattSera == 2) {
					dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(inizioNACHT, fineNACHT, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
				}
			} else {
				turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " libero");
			}
		}
				
				
		/*		
				if (malattia == false & x != 5) {
				turniLavoratoreArrayList.add(x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera]); //Scrive Il giono "domenica, lunedi, ecc.." oppure "libero"
				}
				else {
					if (malattia == true) {
					turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krankMMM"); //scrive krank per quel giorno
					dipendente.setGiorniMalattia(1); //aggiunge 1 giorno malattia al conto delle malattie del dipendente.
					}
				}
				if (x == 0 & MattSera == 2) { //Domenica NACHT
					dipendente.setOreDomenicaLT(Turni.calcoloZuSchlag(domNach_inizio, domNach_Fine, inizio_NachtZuSchlag, fine_NachtZuSchlag)); //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
				} 
				else {
					if (x == 5 & MattSera == 2 & giornoLibero == 0) { //venerdi sera con domenica libera
						if (malattia == false) {
						turniLavoratoreArrayList.add (x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN_Dom, FineNachtZuSchlagVEN_Dom, inizio_NachtZuSchlag, fine_NachtZuSchlag));  //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						}
						else {
							turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank"); //scrive krank per quel giorno
							dipendente.setGiorniMalattia(1); //aggiunge 1 giorno malattia al conto delle malattie del dipendente.
						}
					}
					else {
						if (MattSera == 2 & giornoLibero != 0) { // se ha lavorato di NACHT e la domenica ha lavorato allora imposta venerdi alle 15
					dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN, FineNachtZuSchlagVEN, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
					System.out.println("IL NACHTZUSCHLAG NACHT GUADAGNATO OGGI: " + Turni.calcoloZuSchlag(inizioNACHT, fineNACHT, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
						} else { //venerdi TAG 
							dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(inizioTAG, fineTAG, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
							System.out.println("IL NACHTZUSCHLAG TAG GUADAGNATO OGGI: " + Turni.calcoloZuSchlag(inizioTAG, fineTAG, InizioNachtZuSchlagTAG, FineNachtZuSchlagTAG));
						}
					
					}
				}
			}
			else {
				if (malattia == false) {
					turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " libero");
					}
					else {
						turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " krank");
						dipendente.setGiorniMalattia(1);
			}
			}
		}
 */
		return turniLavoratoreArrayList;
	}
}

