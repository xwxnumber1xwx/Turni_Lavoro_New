
public class Dipendente {
	//  azubi, nuovoArrivato, lifeFirma devo metterli una una classe estesa?
	// Oppure devo mettere Dipendente come superclasse e poi Mitarbeirer, azubi, nuovoArrivato, lifeFirma?
	long codiceLavoratore = 0;
	String nome;
	int livello = 0;
	int lineaLavoro = 0;
	boolean linieLeiter = false;
	boolean soloMattina = false;
	private double OreNotturne = 0;
	private double OreFestivita = 0;
	private double OreDomenica = 0;
	boolean azubi = false;
	boolean nuovoArrivato = false;
	boolean lifeFirma = false;
	boolean licenziato = false;
	boolean malattia = false;
	
	public String getNome() {
		return nome;
	}
	public void setNome (String nome) {
		this.nome = nome;
	}
	public int getLivello () {
		return livello;
	}
	public void setLivello (int livello) {
		this.livello = livello;
	}
	public int getLineaLavoro () {
		return lineaLavoro;
	}
	public void setLineaLavoro (int i) {
		this.lineaLavoro = i;
	}
	public void setOreNotturne(double OreNotturne) {
		this.OreNotturne += OreNotturne;
	}
	public double getOreNotturne() {
		return OreNotturne;
	}
	public void setOreFestivita(double OreFestivita) {
		this.OreFestivita += OreFestivita;
	}
	public double getOreFestivita() {
		return OreFestivita;
	}
	public void setOreDomenica(double OreDomenica) {
		this.OreDomenica += OreDomenica;
	}
	public double getOreDomenica() {
		return OreDomenica;
	}
}


