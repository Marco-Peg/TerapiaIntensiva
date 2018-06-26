
public abstract class Personale {
	
	protected String nome, cognome, id, ruolostr;
	protected char ruolo;
	
	public String ID(){
		return id;
	}
	
	public String Nome(){
		return cognome + " " + nome;
	}
	
	public String strRuolo(){
		return ruolostr;
	}

	public char Ruolo(){
		return ruolo;
	}
	
}
