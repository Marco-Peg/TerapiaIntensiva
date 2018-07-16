import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Paziente {
	private String nome, cognome, codiceSanitario;
	private Date dataNascita;
	private String luogoNascita;
	private String diagnosi; //??medico deve gestire
	private Monitor monitor;
	private File path; //directory in cui salvo tutti i dati del ricovero attuale
	static private DateFormat form=  new  SimpleDateFormat();
	private int allarme=0;
	
	/**
	 * Costruttore di inizializzazione
	 * @param nome
	 * @param cognome
	 * @param codiceSanitario
	 * @param luogoNascita
	 * @param dataNascita
	 */
	public Paziente(String nome, String cognome, String codiceSanitario, String luogoNascita, Date dataNascita) {
		this.codiceSanitario=codiceSanitario.toUpperCase();
		this.cognome=cognome;
		this.nome=nome;
		this.luogoNascita=luogoNascita;
		this.dataNascita=dataNascita;
		
		path=new File(Start.databasePath, codiceSanitario.toLowerCase());
		if(!path.exists()) {
			path.mkdir(); //creo directory
			//creo e riempo file dati anagrafici
			try(FileWriter out=new FileWriter(new File(path, "dati_anagrafici"),true)) {
				out.write(nome+";"+cognome+";"+this.codiceSanitario+";"+luogoNascita+";"+form.format(dataNascita));
			} catch (IOException e) {
				System.out.println(e);
			} 
		}//creo directory di cura attuale
		path=new File(path, LocalDate.now().toString());
		path.mkdir();
		//aggiorna file archivio
		try(FileWriter out=new FileWriter(new File(Archivio.archivioPath),true)) {
			out.write(path.toString()+";"+this.codiceSanitario+"\n");
		} catch (IOException e) {
			System.out.println(e);
		} 
		monitor=new Monitor(this, new ConcreteSubject());
	}
	
	/**
	 * Costruttore di recupero
	 */
	public Paziente(File path,String codiceSanitario){
		this.codiceSanitario=codiceSanitario.toUpperCase();
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
			dataNascita= form.parse(v[4]);  
		} catch (IOException e) { e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	void setDiagnosi(String diagnosi) {
		this.diagnosi=diagnosi;
		try(FileWriter out=new FileWriter(new File(path, "Diagnosi"),true)) {
			out.write(diagnosi);
		} catch (IOException e) {
			System.out.println(e);
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
	
	/**
	 * Restituisce il valore di diagnosi. Se diagnosi non è stato già salvato allora return null
	 * @return
	 */
	public String getDiagnosi() {
		if(diagnosi==null) {
			try(BufferedReader out=new BufferedReader(new FileReader( new  File(path, "Diagnosi")))) {
				diagnosi=out.readLine();
				if(diagnosi.length()!=0) return diagnosi;
			} catch (IOException e) {
				return null;
			} 
		}
		return null;
	}
	
	public void addAllarme() {
		allarme++;
	}
	public void removeAllarme() {
		allarme--;
	}
	public int getAllarme() {
		return allarme;
	}
	
}
