import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Inizializzazione sistema
 *
 */
public class Start {
	 static final String  defaultPath="files/";
	 static final String databasePath="files/database/";
	 static final String loginFile="files/LoginData";
	 static boolean logged=false;
	 
	 public static void main(String[] args) {
		Archivio archivio=Archivio.getArchivio(10);
		/*recupero dati archivio*/
		try(BufferedReader read = new BufferedReader(new FileReader(new File(defaultPath,"archivio")));) {
			String v=read.readLine();
			while(v!=null) {
				if(v.length()>2) {
					Paziente p=new Paziente(new File(v));
					archivio.addArc(p);
				}
			}
			/*String[] v=read.readLine().split(";");
			for(String s:v) {
				Paziente p=new Paziente(new File(s));
				archivio.addArc(p);
			}*/
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		}
		
		 
		 //@test
		 //Archivio.getArchivio().addArc(new Paziente("nome", "cognome", "codiceSanitario", "luogoNascita", new Date()));
		 //Archivio.getArchivio().addArc(new Paziente("nome", "cognome", "codiceSanitario", "luogoNascita", new Date()));

		/*Schermata di login iniziale*/
		new Login();
			
	}
	
	 
	 
}
