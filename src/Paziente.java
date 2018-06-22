import java.nio.file.Path;
import java.util.Date;

public class Paziente {
	private String nome, cognome, codiceSanitario;
	private Date dataNascita;
	private String luogoNascita;
	private Monitor monitor;
	private Path path;
	private Date ricovero;
	
	public Paziente(String nome, String cognome, String codiceSanitario, String luogoNascita, Date dataNascita) {
		monitor=new Monitor(this, subject);
		
	}
	
	public String getNome(){
		return nome;
	}
	public String getCognome(){
		return cognome;
	}
	public String getLuogoNascita(){
		return luogoNascita;
	}
	public Date getDataNascita(){
		return dataNascita;
	}
	public Date getDataRicovero(){
		return dataNascita;
	}
	public String getID(){
		return codiceSanitario;
	}
}
