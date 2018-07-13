import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class Chiusura implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		String[] paz = Archivio.getArchivio().getArray();
		
		JFrame frm = new JFrame("Chiusura");
		frm.setSize(400,400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setVisible(true);
		
        JButton salva= new JButton("Termina ricovero");
		
		JComboBox pazList = new JComboBox(paz);
		
		JLabel label = new JLabel("CHIUSURA CARTELLA PAZIENTE:");

        GroupLayout layout = new GroupLayout(frm.getContentPane());
        frm.getContentPane().setLayout(layout);
        
        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        SequentialGroup h1 = layout.createSequentialGroup();
        ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        h2.addComponent(pazList, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        h2.addComponent(label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        h2.addComponent(salva, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
        
        h1.addContainerGap();
        h1.addGroup(h2);
        h1.addContainerGap();
        hGroup.addGroup(Alignment.TRAILING,h1);
        layout.setHorizontalGroup(hGroup);
        
        
        ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        SequentialGroup v1 = layout.createSequentialGroup();
		v1.addContainerGap();
		v1.addComponent(label);
        v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
        v1.addComponent(pazList);
        v1.addContainerGap();
        v1.addComponent(salva, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE);
        v1.addContainerGap();
        vGroup.addGroup(v1);
		layout.setVerticalGroup(vGroup);
        frm.pack();
		
        salva.addActionListener(new ChiudiCartella(pazList, frm));
		
	}

}
