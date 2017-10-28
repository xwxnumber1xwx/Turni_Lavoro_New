import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class getMenoOre {
	
	
	public ArrayList<Dipendente> OrdinePerNotti(ArrayList<Dipendente> dipendenti){
		
		Collections.sort(dipendenti, new Comparator<Dipendente>() {
			@Override
			public int compare(Dipendente dipendenti2, Dipendente dipendenti1) //mi ritorna -1,0 o 1 se dipendenti1.gerOreNotturne è minore uguale o maggiore di dipendenti2.getOreNotturne
			{
				return dipendenti1.getTotZuSchlag().compareTo(dipendenti2.getTotZuSchlag());
				
			}
		});
		return dipendenti;
	}
 }
