import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import javax.swing.JOptionPane;

public class Paziente {
	private String nome, cognome, codiceSanitario;
	private Date dataNascita;
	private String luogoNascita;
	private Monitor monitor;
	private Path path;
	private Date ricovero;
	
	/**
	 * Costruttore di inizializzazione
	 * @param nome
	 * @param cognome
	 * @param codiceSanitario
	 * @param luogoNascita
	 * @param dataNascita
	 */
	public Paziente(String nome, String cognome, String codiceSanitario, String luogoNascita, Date dataNascita) {
		monitor=new Monitor(this, subject);
		
	}
	
	/**
	 * Costruttore di recupero
	 */
	public Paziente(){
		
		
	}
	
	/**
	 * Restituisce il nome del paziente, nel caso non sia salvato lo recupera da database
	 * @return nome paziente
	 */
	public String getNome(){
		if(nome == null){
			try {
				BufferedReader read=new BufferedReader(new FileReader(new File(path.toString()+"DatiAnagrafici")));
				String r=read.readLine();
				while(r!= null){
					if(r.substring(0, 4)=="nome")
						return r.substring(5);
				}
				JOptionPane.showMessageDialog(null, "No name was found\n");
			} catch (IOException e) {
			}
		}
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
	public Path getPath(){
		return path;
	}
}
