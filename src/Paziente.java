import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Paziente {
	private String nome, cognome, codiceSanitario;
	private Date dataNascita;
	private String luogoNascita;
	private Monitor monitor;
	private File path; //directory in cui salvo tutti i dati del ricovero attuale
	
	/**
	 * Costruttore di inizializzazione
	 * @param nome
	 * @param cognome
	 * @param codiceSanitario
	 * @param luogoNascita
	 * @param dataNascita
	 */
	public Paziente(String nome, String cognome, String codiceSanitario, String luogoNascita, Date dataNascita) {
		this.codiceSanitario=codiceSanitario;
		this.cognome=cognome;
		this.nome=nome;
		this.luogoNascita=luogoNascita;
		this.dataNascita=dataNascita;
		monitor=new Monitor(this, new ConcreteSubject());
		path=new File(Start.databasePath, codiceSanitario);
		path.mkdir(); //creo directory
		//creo e riempo file dati anagrafici
		try {
			FileWriter out=new FileWriter(new File(path, "dati_anagrafici"));
			out.write(nome+";"+cognome+";"+codiceSanitario+";"+luogoNascita+";"+dataNascita.toString());
		} catch (IOException e) {} 
		//creo directory di cura attuale
		path=new File(path, LocalDate.now().toString());
		path.mkdir();
	}
	
	/**
	 * Costruttore di recupero
	 */
	public Paziente(){
		
		
	}
	
	private void load() {
		try {
			BufferedReader read=new BufferedReader(new FileReader(new File(Start.databasePath, codiceSanitario+"/dati_anagrafici")));
			//....
			String r=read.readLine();
			while(r!= null){
				if(r.substring(0, 4)=="nome")
					return r.substring(5);
			}
			JOptionPane.showMessageDialog(null, "No name was found\n");
		} catch (IOException e) {
		}
	}
	/**
	 * Restituisce il nome del paziente, nel caso non sia salvato lo recupera da database
	 * @return nome paziente
	 */
	public String getNome(){
		if(nome == null){
			load();
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
	public File getPath(){
		return path;
	}
	public Monitor getMonitor() {
		return monitor;
	}
}
