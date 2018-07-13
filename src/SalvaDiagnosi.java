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
	private JComboBox<String> pazList;
	
	public SalvaDiagnosi(JTextArea area, JFrame frame, JComboBox<String> pazList){
		this.area = area;
		this.frame = frame;
		this.pazList = pazList;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String testo = area.getText();
		Paziente p = Archivio.getArchivio().getPazFromIndex(pazList.getSelectedIndex());
		
		p.setDiagnosi(testo);
		frame.dispose();
		
	}

}
