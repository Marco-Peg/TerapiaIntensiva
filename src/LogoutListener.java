import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener per il logout
 * @author Marco
 *
 */
public class LogoutListener implements ActionListener {
	
	public LogoutListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Start.logged=false;
		//chiudo tutte finestre attualmente visibili
		for(Window w:Window.getWindows()) {
			if(!w.getBackground().equals(Color.RED))
			w.dispose();
		}
		new Login();
	}

}
