import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		path=new File(Start.databasePath, codiceSanitario);
		if(!path.exists()) {
			path.mkdir(); //creo directory
			//creo e riempo file dati anagrafici
			try(FileWriter out=new FileWriter(new File(path, "dati_anagrafici"),true)) {
				out.write(nome+";"+cognome+";"+codiceSanitario+";"+luogoNascita+";"+dataNascita.toString());
			} catch (IOException e) {
				System.out.println(e);
			} 
		}//creo directory di cura attuale
		path=new File(path, LocalDate.now().toString());
		path.mkdir();
		//aggiorna file archivio
		try(FileWriter out=new FileWriter(new File(Archivio.archivioPath),true)) {
			out.write(path.toString()+";"+codiceSanitario+"\n");
		} catch (IOException e) {
			System.out.println(e);
		} 
		monitor=new Monitor(this, new ConcreteSubject());
	}
	
	/**
	 * Costruttore di recupero
	 */
	public Paziente(File path,String codiceSanitario){
		this.codiceSanitario=codiceSanitario;
		this.path=path;
		monitor=new Monitor(this, new ConcreteSubject());
	}
	
	private void load() {
		try(BufferedReader read=new BufferedReader(new FileReader(new File(Start.databasePath, codiceSanitario+"/dati_anagrafici")))) {
			String[] v=read.readLine().split(";");
			nome=v[0];
			cognome=v[1];
			codiceSanitario=v[2];
			luogoNascita=v[3];
			DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
			dataNascita=  df.parse(v[4]);  
		} catch (IOException e) { e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
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
		if(cognome == null){
			load();
		}
		return cognome;
	}
	public String getLuogoNascita(){
		if(luogoNascita == null){
			load();
		}
		return luogoNascita;
	}
	public Date getDataNascita(){
		if(dataNascita == null){
			load();
		}
		return dataNascita;
	}
	public Date getDataRicovero(){
		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		Date data=null;
		String[] v=path.toString().split("/");
		try {
			data=  df.parse(v[v.length-1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	public String getID(){
		if(codiceSanitario == null){
			load();
		}
		return codiceSanitario;
	}
	public File getPath(){
		return path;
	}
	public Monitor getMonitor() {
		return monitor;
	}
}
