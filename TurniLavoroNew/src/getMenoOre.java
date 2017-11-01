import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

public class getMenoOre {
	
	
	public static  ArrayList<Dipendente> OrdinePerNottiArrayList(ArrayList<Dipendente> dipendenti){
		
		Collections.sort(dipendenti, new Comparator<Dipendente>() {
			@Override
			public int compare(Dipendente dipendenti2, Dipendente dipendenti1) //mi ritorna -1,0 o 1 se dipendenti1.gerOreNotturne è minore uguale o maggiore di dipendenti2.getOreNotturne
			{
				return dipendenti1.getTotZuSchlag().compareTo(dipendenti2.getTotZuSchlag());
				
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
}