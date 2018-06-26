import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class LoginListener implements ActionListener{
		private JPanel id, password;
		
		public LoginListener(JPanel id, JPanel password) {
			this.id=id;
			this.password=password;
		}
		public void actionPerformed(ActionEvent e) {
			String user=((JTextField)(id.getComponent(1))).getText() ;
			user+=';';
			String pass=((JTextField)(password.getComponent(1))).getText() ;
			pass+=';';
			BufferedReader file;
			try {
				file= new BufferedReader(new FileReader("files/LoginData"));
				String s=file.readLine();
				while( s!= null ){
					if(user.equals(s.substring(0, user.length()))){
						if(pass.equals(s.substring(user.length(), user.length()+pass.length()))){
							System.out.println("Accesso consentito"); return;
						}
						else{
							JOptionPane.showMessageDialog(null, "Accesso non consentito"); return;
						}
					}
					s=file.readLine();
				}
				JOptionPane.showMessageDialog(null, "Utente non esistente");
			} catch( FileNotFoundException e2){
				System.out.println(e2);
			} catch ( IOException e1) {
			}	
		}
		
}
