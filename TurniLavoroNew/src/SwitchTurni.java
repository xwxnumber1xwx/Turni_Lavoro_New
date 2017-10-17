

public class SwitchTurni {

	public static String[] generaTurni (int giornoLibero, int MattSera, Dipendente dipendente) {
		/*Inserire:
			- Calcolare la malattia
			- Calcolare le OreFestivita, OreDomeniche
			- Calcolare e confrontare OreNotturne, OreFestivita, OreDomeniche con quelle degli altri Dipendenti e dare
			precedenza o meno
			- 
		*/
		Turni sett= new Turni();
		String turniLavoratore [];
		turniLavoratore= new String[6];
		int x = 0;
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
		turniLavoratore[giornoLibero] = sett.settimana[giornoLibero][0] + " libero";
		do {
			if (x != giornoLibero) {
				turniLavoratore[x] = sett.settimana[x][0] + " " + sett.settimana[x][MattSera];
				if (x == 0 & MattSera == 2) {
					dipendente.setOreDomenica(sett.oreNotturne[x][MattSera-1]); //Calcolo oreDomenica
					turniLavoratore[x] = sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]; //Venerdì SBAGLIATA POSIZIONE!!!
				} 
				else {
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera-1]); //Calcolo oreNotturne
				}
			}
			x++;
		}
		while (x < 6);
		return turniLavoratore;
	}
}

