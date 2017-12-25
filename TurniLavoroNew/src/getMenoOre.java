import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class getMenoOre {
	
	
	public static  ArrayList<Dipendente> OrdinePerNottiArrayList(ArrayList<Dipendente> dipendenti){
		
		Collections.sort(dipendenti, new Comparator<Dipendente>() {
			@Override
			public int compare(Dipendente dipendenti2, Dipendente dipendenti1) //mi ritorna -1,0 o 1 se dipendenti1.gerOreNotturne è minore uguale o maggiore di dipendenti2.getOreNotturne
			{
				return dipendenti2.getTotZuSchlag().compareTo(dipendenti1.getTotZuSchlag());
				
			}
		});
		return dipendenti;
	}
	
	public static ArrayList<Dipendente> OrdinePerNottiArray(ArrayList<Dipendente> dipendenti) {		
		for (int i = 0; i < dipendenti.size(); i++) {
			for (int j = 0; j < dipendenti.size() - i - 1; j++) {
				Dipendente tempDipendente1 = dipendenti.get(j);
				Dipendente tempDipendente2 = dipendenti.get(j+1);
				if (tempDipendente1.TotZuSchlag > tempDipendente2.TotZuSchlag) {
					Dipendente temp = tempDipendente1;
					dipendenti.set(j,tempDipendente2);
					dipendenti.set(j+1, temp);
			}
				
		}
	}
		for (int x = 0; x < dipendenti.size(); x++) {
			Dipendente temp1 = dipendenti.get(x);
			System.out.println(temp1.nome + " TOT: " + temp1.TotZuSchlag);
		}
		return dipendenti;
 }
	
	public static ArrayList<Dipendente> OrdinePerLinieLeiter(ArrayList<Dipendente> dipendenti, int NumeroLinieLeiter, int NumeroDipendentiPerLinee1TAG, int NumeroDipendentiPerLinee1NACHT, int NumeroDipendentiPerLinee2NACHT) {
	//ERRORE
		
		ArrayList <Dipendente> ArrayLeiter = new ArrayList<Dipendente>();
		int NumeroLinieLeiterL1 = NumeroLinieLeiter;
			for (int h = 1; h < 2; h++) { //NACHT
				for (int x = 0; x < dipendenti.size(); x++) {
					Dipendente dip1 = dipendenti.get(0);
					if (dip1.getlinieLeiter() == h || dip1.getlinieLeiter() == 3 && NumeroLinieLeiterL1 != 0 && dip1.getTagNacht() == 2) {
						dip1.setLineaTurnoLeiter(h);
						dip1.setLineaTurno(h);
						ArrayLeiter.add(dip1);
						dipendenti.remove(0);
						NumeroLinieLeiterL1--;
					}
				}
				NumeroLinieLeiterL1 = NumeroLinieLeiter;
				for (int x = 0; x < dipendenti.size(); x++) {
					Dipendente dip1 = dipendenti.get(0);
					if (dip1.getlinieLeiter() == h || dip1.getlinieLeiter() == 3 && NumeroLinieLeiterL1 != 0 && dip1.getTagNacht() == 1) {
						dip1.setLineaTurnoLeiter(h);
						dip1.setLineaTurno(h);
						ArrayLeiter.add(dip1);
						dipendenti.remove(0);
						NumeroLinieLeiterL1--;
					}
				}
				for (int x = 0; x < dipendenti.size(); x++) {
					Dipendente dip1 = dipendenti.get(0);
					if (dip1.getLineaLavoro() == h || dip1.getLineaLavoro() == 3 && dip1.getTagNacht() == 2) {
						if (NumeroDipendentiPerLinee1NACHT != 0) {
							dip1.setLineaTurno(1);
							ArrayLeiter.add(dip1);
							dipendenti.remove(0);
							NumeroDipendentiPerLinee1NACHT--;
						} else if (NumeroDipendentiPerLinee2NACHT != 0 && dip1.getLineaLavoro() == 2) {
							dip1.setLineaTurno(2);
							ArrayLeiter.add(dip1);
							dipendenti.remove(0);
							NumeroDipendentiPerLinee2NACHT--;
						}
					}
				}
				for (int x = 0; x < dipendenti.size(); x++) {
					Dipendente dip1 = dipendenti.get(0);
					if (dip1.getLineaLavoro() == h || dip1.getLineaLavoro() == 3 && dip1.getTagNacht() == 1) {
						if (h == 1 && NumeroDipendentiPerLinee1TAG != 0) {
							dip1.setLineaTurno(1);
							ArrayLeiter.add(dip1);
							dipendenti.remove(0);
							NumeroDipendentiPerLinee1TAG--;
						} else {
							dip1.setLineaTurno(2);
							ArrayLeiter.add(dip1);
							dipendenti.remove(0);
						}
					}
				}
			}
			for (int x = 0; x < dipendenti.size(); x++) {
				Dipendente dip2 = dipendenti.get(x);
				dip2.setLineaTurno(2);
				ArrayLeiter.add(dip2);
				dipendenti.remove(x);
			}
			
			for (int x = 0; x < ArrayLeiter.size(); x++) {
				Dipendente temp1 = ArrayLeiter.get(x);
				System.out.println(temp1.nome + " Linie Leiter: " + temp1.getLineaTurnoLeiter() +  " Linie: "  + temp1.getLineaTurno() + " TAG=1, NACHT=2: " + temp1.getTagNacht());
			}
		return ArrayLeiter;
	}
	
}