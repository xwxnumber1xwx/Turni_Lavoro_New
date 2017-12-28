
import java.time.LocalDate;
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
	
	public static ArrayList<Dipendente> OrdinePerLinieLeiter(ArrayList<Dipendente> dipendenti, int NumeroLinieLeiter, int NumeroDipendentiPerLinee1TAG, int NumeroDipendentiPerLinee1NACHT, int NumeroDipendentiPerLinee2NACHT, LocalDate date, IOFile save) {
	//ERRORE
		
		ArrayList <Dipendente> ArrayLeiter = new ArrayList<Dipendente>();
		int[] DoVe = new int[5];
		int o = 0;
			for (int h = 1; h < 3; h++) { //NACHT
				int NumeroLinieLeiterL1 = NumeroLinieLeiter;
				o=0;
				int size = dipendenti.size();
				for (int x = 0; x < size; x++) {
					if (o > dipendenti.size()) {
						o = dipendenti.size();
					}
					
					//LinieLeiter NACHT
					Dipendente dip1 = dipendenti.get(o);
					if (NumeroLinieLeiterL1 != 0 && dip1.getTagNacht() == 2) {
						if (dip1.getlinieLeiter() == h || dip1.getlinieLeiter() == 3) {
							DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
							dip1.setLineaTurnoLeiter(h);
							dip1.setLineaTurno(h);
							ArrayLeiter.add(dip1);
							dipendenti.remove(o);
							NumeroLinieLeiterL1--;
						} else {
							o++;
						}
					} else {
						o++;
					}
				}
				NumeroLinieLeiterL1 = NumeroLinieLeiter;
				o=0;
				size = dipendenti.size();
				for (int x = 0; x < size; x++) {
					if (o > dipendenti.size()) {
						o = dipendenti.size();
					}
					
					//LinieLeiter TAG
					Dipendente dip1 = dipendenti.get(o);
					if (NumeroLinieLeiterL1 != 0 && dip1.getTagNacht() == 1) {
					if (dip1.getlinieLeiter() == h || dip1.getlinieLeiter() == 3) {
						DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
						dip1.setLineaTurnoLeiter(h);
						dip1.setLineaTurno(h);
						ArrayLeiter.add(dip1);
						dipendenti.remove(o);
						NumeroLinieLeiterL1--;
					} else {
						o++;
					}
				} else {
					o++;
				}
			}
			}
			for (int h = 1; h < 3; h++) {
				o=0;
				int size = dipendenti.size();
				for (int x = 0; x < size; x++) {
					if (o > dipendenti.size()) {
						o = dipendenti.size();
					}
					
					// Linie NACHT
					Dipendente dip1 = dipendenti.get(o);
					if (dip1.getTagNacht() == 2) {
					if (dip1.getLineaLavoro() == h || dip1.getLineaLavoro() == 3) {
						if (NumeroDipendentiPerLinee1NACHT != 0) {
							DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
							dip1.setLineaTurnoLeiter(0);
							dip1.setLineaTurno(1);
							ArrayLeiter.add(dip1);
							dipendenti.remove(o);
							NumeroDipendentiPerLinee1NACHT--;
						} else if (NumeroDipendentiPerLinee2NACHT != 0) {
							DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
							dip1.setLineaTurnoLeiter(0);
							dip1.setLineaTurno(2);
							ArrayLeiter.add(dip1);
							dipendenti.remove(o);
							NumeroDipendentiPerLinee2NACHT--;
						} else {
							o++;
						}
					} else {
						o++;
					}
				} else {
					o++;
				}
				}
				o=0;
				size = dipendenti.size();
				for (int x = 0; x < size; x++) {
					if (o > dipendenti.size()) {
						o = dipendenti.size();
					}
					
					//Linia TAG
					Dipendente dip1 = dipendenti.get(o);
					if (dip1.getTagNacht() == 1) {
					if (dip1.getLineaLavoro() == h || dip1.getLineaLavoro() == 3) {
						if (h == 1 && NumeroDipendentiPerLinee1TAG != 0) {
							dip1.setLineaTurnoLeiter(0);
							dip1.setLineaTurno(1);
							DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
							ArrayLeiter.add(dip1);
							dipendenti.remove(o);
							NumeroDipendentiPerLinee1TAG--;
						} else {
							
							dip1.setLineaTurnoLeiter(0);
							dip1.setLineaTurno(2);
							DoVe = FreeDay.setFreeDay(dip1, save, date, DoVe);
							ArrayLeiter.add(dip1);
							dipendenti.remove(o);
						}
					}
					} else {
						o++;
					}
				}
			}
			int size = dipendenti.size();
			for (int x = 0; x < size; x++) {
				Dipendente dip2 = dipendenti.get(x);
				dip2.setLineaTurnoLeiter(0);
				dip2.setLineaTurno(2);
				dip2.setTagNacht(1); // Dato extra La mattina perche ha superato il numero massimo di persone di sera
				DoVe = FreeDay.setFreeDay(dip2, save, date, DoVe);
				ArrayLeiter.add(dip2);

			}
			System.out.println("ArrayList dipendenti prima del Clear: ");
			dipendenti.forEach(System.out::println);
			dipendenti.clear();
			
			// ORDINAMENTO ARRAY
			dipendenti = SortArraytoDipendenti(ArrayLeiter, dipendenti);
			
			for (int b = 0; b < DoVe.length ; b++) {
				System.out.println();
				System.out.println("Giorni Liberi aggiunti X: " + b + ": "+ DoVe[b]);
			}
			
		return dipendenti;
	}
	
	public static ArrayList<Dipendente> SortArraytoDipendenti (ArrayList<Dipendente> ArrayLeiter, ArrayList<Dipendente> dipendenti) {

		ArrayList <Dipendente> L1Tag = new ArrayList<Dipendente>();
		ArrayList <Dipendente> L1Nacht = new ArrayList<Dipendente>();
		ArrayList <Dipendente> L2Tag = new ArrayList<Dipendente>();
		ArrayList <Dipendente> L2Nacht = new ArrayList<Dipendente>();

		for (int x = 0; x < ArrayLeiter.size(); x++) {
			Dipendente dip = ArrayLeiter.get(x);
			if (dip.getLineaTurnoLeiter() == 1) {
				if(dip.getTagNacht() == 1) {
					L1Tag.add(0, dip);
				} else {
					L1Nacht.add(0, dip);
				}
			} else if (dip.getLineaTurno() == 1) {
				if(dip.getTagNacht() == 1) {
					L1Tag.add(dip);
				} else {
					L1Nacht.add(dip);
				}
			} else if (dip.getLineaTurnoLeiter() == 2) {
				if(dip.getTagNacht() == 1) {
					L2Tag.add(0, dip);
				} else {
					L2Nacht.add(0, dip);
				}
			} else if (dip.getLineaTurno() == 2) {
				if(dip.getTagNacht() == 1) {
					L2Tag.add(dip);
				} else {
					L2Nacht.add(dip);
				}
			}
		}
		dipendenti.addAll(L1Tag);
		dipendenti.addAll(L2Tag);
		dipendenti.addAll(L1Nacht);
		dipendenti.addAll(L2Nacht);
		
		for (int x = 0; x < dipendenti.size(); x++) {
			Dipendente temp1 = dipendenti.get(x);
			System.out.print(temp1.getCognome() + " Linie Leiter: " + temp1.getLineaTurnoLeiter() +  " Linie: "  + temp1.getLineaTurno());
			if (temp1.getTagNacht() == 1) {
				System.out.println(" TAG");
			} else  {
				System.out.println(" NACHT");
			}
		}
		
		return dipendenti;
	}
	
}