import java.awt.event.*;
import java.io.*;
import java.nio.file.attribute.UserPrincipalLookupService;

import javax.swing.*;

public class LoginListener implements ActionListener{
		private JFrame log;
		private JPanel id, password;
		private static final UserInitializer userCreator= new UserInitializer();
		
		public LoginListener(JFrame log, JPanel id, JPanel password) {
			this.log=log;
			this.id=id;
			this.password=password;
		}
		public void actionPerformed(ActionEvent e) {
			String user=((JTextField)(id.getComponent(1))).getText() ;
			String pass=((JTextField)(password.getComponent(1))).getText() ;
			BufferedReader file;
			try {
				file= new BufferedReader(new FileReader(Start.loginFile));
				String s=file.readLine();
				while( s!= null ){
					String[] v=s.split(";");
					if(user.equals(v[0])){
						if(pass.equals(v[1])){
							userCreator.getUser(v[0], v[2]);
							log.dispose();
							System.out.println("Accesso consentito"); return;
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
