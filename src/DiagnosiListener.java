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

	/**
	 * Listener del bottone per inserimento somministrazione
	 *
	 */
	public class DiagnosiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			String[] paz = Archivio.getArchivio().getArray();
			
			JFrame frm = new JFrame("Diagnosi");
			frm.setSize(400,400);
			frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frm.setVisible(true);
			
	        JButton salva= new JButton("Salva Dati");
			
			JComboBox pazList = new JComboBox(paz);
			
			JLabel label = new JLabel("DIAGNOSI PAZIENTE:");

			JTextArea area = new JTextArea();
	        area.setColumns(20);
	        area.setLineWrap(true);
	        area.setRows(5);
	        area.setWrapStyleWord(true);
			
	        JScrollPane scroll = new JScrollPane(area);

	        GroupLayout layout = new GroupLayout(frm.getContentPane());
	        frm.getContentPane().setLayout(layout);
	        
	        //Create a parallel group for the horizontal axis
	        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        //Create a sequential and a parallel groups
	        SequentialGroup h1 = layout.createSequentialGroup();
	        ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
	        //Add a scroll panel and a label to the parallel group h2
	        h2.addComponent(pazList, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
	        h2.addComponent(scroll, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
	        h2.addComponent(label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
	        h2.addComponent(salva, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
	        
	        //Add a container gap to the sequential group h1
	        h1.addContainerGap();
	        // Add the group h2 to the group h1
	        h1.addGroup(h2);
	        h1.addContainerGap();
	        //Add the group h1 to hGroup
	        hGroup.addGroup(Alignment.TRAILING,h1);
	        //Create the horizontal group
	        layout.setHorizontalGroup(hGroup);
	        
	        
	        //Create a parallel group for the vertical axis
	        ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        //Create a sequential group
	        SequentialGroup v1 = layout.createSequentialGroup();
	        //Add a container gap to the sequential group v1
			v1.addContainerGap();
	        //Add a label to the sequential group v1
			v1.addComponent(label);
	        v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
	        v1.addComponent(pazList);
	        v1.addContainerGap();
	        //Add scroll panel to the sequential group v1
	        v1.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
	        v1.addContainerGap();
	        v1.addComponent(salva, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE);
	        v1.addContainerGap();
	        //Add the group v1 to vGroup
	        vGroup.addGroup(v1);
	        //Create the vertical group
			layout.setVerticalGroup(vGroup);
	        frm.pack();
		
			/*try(FileReader file = new FileReader("Somministrazioni.txt")){
		        BufferedReader reader =  new BufferedReader(file);
		        area.read(reader, null);
			}catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
	        salva.addActionListener(new SalvaDiagnosi(area, frm, pazList));
			

			
		}

	}
	

