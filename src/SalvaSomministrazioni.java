import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SalvaSomministrazioni implements ActionListener{
	
	private JTextArea area;
	private JFrame frame;
	
	public SalvaSomministrazioni(JTextArea area, JFrame frame){
		this.area = area;
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String testo = area.getText();
		
		try(FileWriter fileW = new FileWriter("Somministrazioni.txt", true)){
			fileW.write(testo);
		}catch (IOException f) {
		// TODO Auto-generated catch block
			f.printStackTrace();
		}
		frame.dispose();
		
	}

}
