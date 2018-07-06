import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.text.JTextComponent;

/**
 * Listener per la gestione dell'inserimento del paziente
 * @author Marco
 *
 */
public class InsertListener implements ActionListener {
	private JFrame frm;
	private JPanel nome, cognome, codiceSanitario, luogoNascita;
	private  JSpinner spin;
	
	public InsertListener(JFrame frm,JPanel nome,JPanel cognome, JPanel codiceSanitario,JPanel luogoNascita,JSpinner spin){
		this.frm=frm;
		this.nome=nome;
		this.cognome=cognome;
		this.codiceSanitario=codiceSanitario;
		this.luogoNascita=luogoNascita;
		this.spin=spin;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String name,surname, codSan, lNascita;
		Component[] comp=nome.getComponents();
		name=((JTextComponent) comp[1]).getText();
		comp=codiceSanitario.getComponents();
		if(!((JFormattedTextField) comp[1]).isEditValid()) {
			JOptionPane.showMessageDialog(null, "Codice sanitario non valido", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
		}
		codSan=((JTextComponent) comp[1]).getText();
		codSan.toUpperCase();
		comp=cognome.getComponents();
		surname=((JTextComponent) comp[1]).getText();
		comp=luogoNascita.getComponents();
		lNascita=((JTextComponent) comp[1]).getText();
		if(name.length()==0 || surname.length()==0 || lNascita.length()==0 || codSan.length()==0) {
			JOptionPane.showMessageDialog(null, "Campo vuoto", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
		}
		//@test
		File f=new File(Start.databasePath, codSan);
		if(f.exists()) {
			try (	BufferedReader dati= new BufferedReader(new FileReader(Start.loginFile))) {
				String[] v=dati.readLine().split(";");
				if(v[0]!=name){
					JOptionPane.showMessageDialog(null, "Paziente già registrato. Nome non corrisponde", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
				}
				else if(v[1]!=surname){
					JOptionPane.showMessageDialog(null, "Paziente già registrato. Cognome non corrisponde", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
				}
				else if(v[3]!=lNascita){
					JOptionPane.showMessageDialog(null, "Paziente già registrato. Luogo di nascita non corrisponde", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
				}
				else if(v[4]!=(((Date)(spin.getValue())).toString())){
					JOptionPane.showMessageDialog(null, "Paziente già registrato. Data di nascita non corrisponde", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Paziente già registrato. Aggiornamento avvenuto con successo", "Registrazione paziente", JOptionPane.INFORMATION_MESSAGE); 
		}else {
			JOptionPane.showMessageDialog(null, "Paziente  registrato con successo", "Registrazione paziente", JOptionPane.INFORMATION_MESSAGE); 
		}
		Paziente p=new Paziente(name, surname, codSan, lNascita, (Date)spin.getValue());
		Archivio.getArchivio().addArc(p);
		frm.dispose();
	}

}
