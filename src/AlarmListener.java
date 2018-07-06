import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
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
		String pass=password.getText() ;
		try (BufferedReader file= new BufferedReader(new FileReader(Start.loginFile))){
			String s=file.readLine();
			while( s!= null ){
				String[] v=s.split(";");
				if(user.equals(v[0])){
					if(pass.equals(v[1])){
						System.out.println(v[2]);
						if(v[2].equals("d") || v[2].equals("p")){
						//?salva azioni effettuate
							a.interrupt();
						JOptionPane.showMessageDialog(null, "Allarme gestito con successo", "Gestione allarme", JOptionPane.INFORMATION_MESSAGE);
						return;
						}
						JOptionPane.showMessageDialog(null, "Accesso non consentito. Ruolo non autorizzato", "Login", JOptionPane.WARNING_MESSAGE); return;	
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
