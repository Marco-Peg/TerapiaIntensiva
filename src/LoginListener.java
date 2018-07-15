import java.awt.Color;
import java.awt.Window;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;


/**
 * Listener al bottone di login
 * @author Marco
 *
 */
public class LoginListener implements ActionListener{
		private JPanel id, password;
		private static final UserInitializer userCreator= new UserInitializer();
		
		/**
		 * Costruttore: salva le variabili necessarie alla gestione del login
		 * @param log  finestra di login
		 * @param id  panel con il testo dello user
		 * @param password  panel con il testo della password
		 */
		public LoginListener( JPanel id, JPanel password) {
			this.id=id;
			this.password=password;
		}
		
		/**
		 * Controlla che i campi siano corretti e permette accesso di utenti
		 */
		public void actionPerformed(ActionEvent e) {
			String user=((JTextField)(id.getComponent(1))).getText() ;
			String pass=((JTextField)(password.getComponent(1))).getText() ;
			try (BufferedReader file= new BufferedReader(new FileReader(Start.loginFile))){
				String s=file.readLine();
				while( s!= null ){
					String[] v=s.split(";");
					if(user.equals(v[0])){
						if(pass.equals(v[1])){
							//chiudo tutte finestre attualmente visibili
							for(Window w:Window.getWindows()) {
								//se ho allarmi attivi??
								if(!w.getBackground().equals(Color.RED))
									w.dispose();
							}
							userCreator.getUser(v[0], v[2]);
							Start.logged=true;
							 return;
						}
						else{
							JOptionPane.showMessageDialog(null, "Accesso non consentito", "Login", JOptionPane.WARNING_MESSAGE); return;
						}
					}
					s=file.readLine();
				}
				JOptionPane.showMessageDialog(null, "Utente non esistente", "Login", JOptionPane.WARNING_MESSAGE);
			} catch( FileNotFoundException e2){
				System.out.println(e2);
			} catch ( IOException e1) {
			}	
		}
		
}
