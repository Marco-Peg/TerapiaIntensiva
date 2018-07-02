import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Inizializzazione sistema e inizio con login
 */
public class Start {
	 static final String  defaultPath="files/"; //path contenente tutti i file
	 static final String databasePath="files/database/"; //contiene le cartelle di tutti i pazienti
	 static final String loginFile="files/LoginData"; //file con i dati di login
	 static boolean logged=false; //flag di log
	 
	 public static void main(String[] args) {
		Archivio archivio=Archivio.getArchivio(10);
		/*recupero dati archivio*/
		/*try(BufferedReader read = new BufferedReader(new FileReader(new File(defaultPath,"archivio")));) {
			String v=read.readLine();
			while(v!=null) {
				if(v.length()>2) {
					String[] s=v.split(";");
					Paziente p=new Paziente(new File(s[0]),s[2]);
					archivio.addArc(p);
				}
				v=read.readLine();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		}*/
		 
		/*Schermata di login iniziale*/
		new Login();
	}
	
	 
	 
}
