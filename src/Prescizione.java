import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Prescizione implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton jb=new JButton("Inserisci prescrione");
		jb.addActionListener(new PrescrizioneListener());
	}

}
