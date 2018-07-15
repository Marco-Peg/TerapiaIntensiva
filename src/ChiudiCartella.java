import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChiudiCartella implements ActionListener {
	private JFrame frm;
	private JComboBox<String> pazList;
	
	public ChiudiCartella(JComboBox<String> pazList, JFrame frm){
		this.pazList = pazList;
		this.frm=frm;
	}
	
	public void actionPerformed(ActionEvent e) {

		Paziente p = Archivio.getArchivio().getPazFromIndex(pazList.getSelectedIndex());
		
		String id = p.getID();
		int L = Archivio.getArchivio().getArray().length;
		String[] temp = new String [L-1];
		int i = 0;
		
		try(BufferedReader read = new BufferedReader(new FileReader(new File(Start.defaultPath,"archivio")))) {
			String v=read.readLine();
			while(v!=null) {
				if(v.length()>2) {
					String[] s=v.split(";");
					if (0 != id.compareToIgnoreCase(s[1])){
						temp[i++] = v;
					}					
				}
				v=read.readLine();
			}
		} catch (FileNotFoundException ei) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		} catch (IOException ei) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
}
		i = 0;
				
		try(FileWriter out=new FileWriter(new File(Archivio.archivioPath))) {
			for (String a: temp) 
				out.write(a + "\n");
		} catch (IOException ei) {
			System.out.println(ei);
} 
		frm.dispose();
		Archivio.getArchivio().delArc(p);
		}

	}


