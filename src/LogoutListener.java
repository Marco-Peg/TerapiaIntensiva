import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Listener per il logout
 * @author Marco
 *
 */
public class LogoutListener implements ActionListener {
	private JFrame frm;
	
	public LogoutListener(JFrame frm) {
		this.frm=frm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Start.logged=false;
		frm.dispose();
		new Login();
	}

}
