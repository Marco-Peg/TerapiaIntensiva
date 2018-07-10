import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class SalvaDiagnosi implements ActionListener{
	
	private JTextArea area;
	private JFrame frame;
	private JComboBox pazList;
	
	public SalvaSomministrazioni(JTextArea area, JFrame frame, JComboBox pazList){
		this.area = area;
		this.frame = frame;
		this.pazList = pazList;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String testo = area.getText();
		Paziente p = Archivio.getArchivio().getPazFromIndex(pazList.getSelectedIndex());
		File txt = new File(p.getPath(), "Diagnosi");
		
		try(FileWriter fileW = new FileWriter(txt)){
			fileW.write(testo);
		}catch (IOException f) {
		// TODO Auto-generated catch block
			f.printStackTrace();
		}
		frame.dispose();
		
	}

}
