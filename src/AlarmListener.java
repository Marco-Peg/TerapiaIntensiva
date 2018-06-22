import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlarmListener implements ActionListener{
	private JPanel text;
	
	public AlarmListener(JPanel text) {
		this.text=text;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String value=((JTextField)(text.getComponent(0))).getText() ;
		if(value == null){
			return;
		}
		//salva azioni effettuate
	}
	
}
