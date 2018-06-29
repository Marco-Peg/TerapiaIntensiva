import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Inizializzazione sistema
 * @author Marco
 *
 */
public class Start {
	 static final String  defaultPath="files/";
	 static final String databasePath="files/database/";
	 static final String loginFile="files/LoginData";
	 static final Archivio archivio=new Archivio();
	 static boolean logged=false;
	 
	 public static void main(String[] args) {
		
		/**recupero dati archivio*/
		//...
		 
		 //@test
		 archivio.addArc(new Paziente("nome", "cognome", "codiceSanitario", "luogoNascita", new Date()));
		 archivio.addArc(new Paziente("nome", "cognome", "codiceSanitario", "luogoNascita", new Date()));

		/**Schermata di login iniziale*/
		Login log=new Login();
			
	}
	
	 
	 
}
