/**
 * Inizializzazione sistema e inizio con login
 */
public class Start {
	 static final String  defaultPath="files/"; //path contenente tutti i file
	 static final String databasePath="files/database/"; //contiene le cartelle di tutti i pazienti
	 static final String loginFile="files/LoginData"; //file con i dati di login
	 static boolean logged=false; //flag di log
	 
	 public static void main(String[] args) {
		Archivio.getArchivio(10);
		/*Schermata di login iniziale*/
		new Login();
	}
	
	 
	 
}
