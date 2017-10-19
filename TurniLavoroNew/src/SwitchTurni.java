import java.util.ArrayList;

public class SwitchTurni {
	public static ArrayList<String> generaTurni (int giornoLibero, int MattSera, Dipendente dipendente, boolean malattia) {
		/*Inserire:
			- Calcolare la malattia per pochi giorni
			- Calcolare le OreFestivita, OreDomeniche
			- Calcolare e confrontare OreNotturne, OreFestivita, OreDomeniche con quelle degli altri Dipendenti e dare
			precedenza o meno
			- 
		*/
		Turni sett = new Turni();
		ArrayList <String> turniLavoratoreArrayList = new ArrayList<String>(6);
		int x = 0;
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
		do {
			if (x != giornoLibero) {
				if (malattia == false) {
				turniLavoratoreArrayList.add(x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera]);
				}
				else {
					turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank");
					dipendente.setGiorniMalattia(1);
				}
				if (x == 0 & MattSera == 2) { //Domenica NACHT
					dipendente.setOreDomenica(sett.oreNotturne[x][MattSera-1]); //Calcolo oreDomenica
				//	turniLavoratore[x] = sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]; //Venerdì però mi riscrive sopra con i prossimi cicli quando x = 5
				} 
				else {
					if (x == 5 & MattSera == 2 & giornoLibero == 0) {
						if (malattia == false) {
						turniLavoratoreArrayList.add (5, sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						}
						else {
							turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank");
							dipendente.setGiorniMalattia(1);
						}
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera]); //Calcolo oreNotturne VENERDI alle 18
					}
					else {
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera-1]); //Calcolo oreNotturne venerdi alle 15
					}
				}
			}
			else {
				if (malattia == false) {
					turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " libero"); // ERRORE perchè l'arrayList non ha ancora la posizione del giornoLibero
					}
					else {
						turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " krank");
			}
			}
			x++;
		}
		while (x < 6);

		return turniLavoratoreArrayList;
	}
}

