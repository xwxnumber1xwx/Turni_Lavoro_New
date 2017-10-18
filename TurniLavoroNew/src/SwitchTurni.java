

public class SwitchTurni {

	public static String[] generaTurni (int giornoLibero, int MattSera, Dipendente dipendente, boolean malattia) {
		/*Inserire:
			- Calcolare la malattia
			- Calcolare le OreFestivita, OreDomeniche
			- Calcolare e confrontare OreNotturne, OreFestivita, OreDomeniche con quelle degli altri Dipendenti e dare
			precedenza o meno
			- 
		*/
		Turni sett = new Turni();
		String turniLavoratore [];
		turniLavoratore = new String[6];
		int x = 0;
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
		if (malattia == false) {
		turniLavoratore[giornoLibero] = sett.settimana[giornoLibero][0] + " libero";
		}
		else {
			turniLavoratore[giornoLibero] = sett.settimana[giornoLibero][0] + " krank";
		}
		do {
			if (x != giornoLibero) {
				if (malattia == false)
				turniLavoratore[x] = sett.settimana[x][0] + " " + sett.settimana[x][MattSera];
				else 
					turniLavoratore[x] = sett.settimana[giornoLibero][0] + " krank";
				if (x == 0 & MattSera == 2) { //Domenica NACHT
					dipendente.setOreDomenica(sett.oreNotturne[x][MattSera-1]); //Calcolo oreDomenica
				//	turniLavoratore[x] = sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]; //Venerdì però mi riscrive sopra con i prossimi cicli quando x = 5
				} 
				else {
					if (x == 5 & MattSera == 2 & giornoLibero == 0) {
						if (malattia == false) {
						turniLavoratore[5] = sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]; //impostata Venerdi alle 18 se domenica NACHT è libero
						}
						else {
							turniLavoratore[x] = sett.settimana[giornoLibero][0] + " krank";
						}
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera]); //Calcolo oreNotturne VENERDI alle 18
					}
					else {
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera-1]); //Calcolo oreNotturne venerdi alle 15
					}
				}
			}
			x++;
		}
		while (x < 6);
			
		return turniLavoratore;
	}
}

