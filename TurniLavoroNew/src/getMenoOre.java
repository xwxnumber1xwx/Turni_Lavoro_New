import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class getMenoOre {
	public ArrayList<Dipendente> OrdinePerNotti(ArrayList<Dipendente> dipendenti){
		
		Collections.sort(dipendenti, new Comparator<Dipendente>() {
			@Override
			public int compare(Dipendente dipendenti2, Dipendente dipendenti1)
			{
				return dipendenti1.getOreNotturne().compareTo(dipendenti2.getOreNotturne());
			}
		}
		return dipendenti;
	}
}
