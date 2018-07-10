import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ChiudiCartella implements ActionListener {

	private JComboBox pazList;
	
	public ChiudiCartella(JComboBox pazList){
		this.pazList = pazList;
	}
	
	public void actionPerformed(ActionEvent e) {

		Paziente p = Archivio.getArchivio().getPazFromIndex(pazList.getSelectedIndex());
		
		String id = p.getID();
		int L = Archivio.getArchivio().getArray().lenght;
		String[] temp = new String [L-1];
		int i = 0;
		
		try(BufferedReader read = new BufferedReader(new FileReader(new File(Start.defaultPath,"archivio")))) {
			String v=read.readLine();
			while(v!=null) {
				if(v.length()>2) {
					String[] s=v.split(";");
					if (s[1] != id){
						temp[i++] = v;
					}					
				}
				v=read.readLine();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
}
		i = 0
				
		try(FileWriter out=new FileWriter(new File(Archivio.archivioPath))) {
			for (String a: temp) 
				out.write(a + "\n");
		} catch (IOException e) {
			System.out.println(e);
} 
			
		}

	}

}
