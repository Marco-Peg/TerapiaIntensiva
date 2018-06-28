import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class AlarmListener implements ActionListener{
	private Alarm a;
	private JTextComponent id, password, input;
	
	public AlarmListener(Alarm a, JTextComponent id, JTextComponent password, JTextComponent input) {
		this.a=a;
		this.id=id;
		this.password=password;
		this.input=input;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String user=id.getText() ;
		user+=';';
		String pass=password.getText() ;
		pass+=';';
		BufferedReader file;
		try {
			file= new BufferedReader(new FileReader("files/LoginData"));
			String s=file.readLine();
			while( s!= null ){
				if(user.equals(s.substring(0, user.length()))){
					if(pass.equals(s.substring(user.length(), user.length()+pass.length()))){
						//???????
						//?salva azioni effettuate
						JOptionPane.showMessageDialog(null, "Allarme gestito con successo", "Gestione allarme", JOptionPane.INFORMATION_MESSAGE);
						a.interrupt();
						return;
					}
					else{
						JOptionPane.showMessageDialog(null, "Accesso non consentito", "Login", JOptionPane.WARNING_MESSAGE); return;
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
