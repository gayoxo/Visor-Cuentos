package Vista.Visor;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;

import Model.Modelo111218;
import Model.Pesonaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

public class UnirEntidades extends JFrame {
	
	
private JPanel Selected;
private JPanel Actual;
private UnirEntidades Yo;
private ActionListener AL;

public UnirEntidades() {
	setTitle("Merge Nominals");
	setSize(657,451);
	Yo=this;
	
	JSplitPane splitPane = new JSplitPane();
	splitPane.setResizeWeight(0.5);
	getContentPane().add(splitPane, BorderLayout.CENTER);
	
	JScrollPane panel = new JScrollPane();
	splitPane.setRightComponent(panel);
	panel.setPreferredSize(new Dimension(100,100));
	panel.setSize(new Dimension(100,100));
	
	Selected = new JPanel();
	panel.setViewportView(Selected);
	Selected.setLayout(new BoxLayout(Selected, BoxLayout.Y_AXIS));
	
	JScrollPane panel_1 = new JScrollPane();
	splitPane.setLeftComponent(panel_1);
	panel_1.setPreferredSize(new Dimension(300,100));
	panel_1.setSize(new Dimension(300,100));
	
	Actual = new JPanel();
	panel_1.setViewportView(Actual);
	Actual.setLayout(new BoxLayout(Actual, BoxLayout.Y_AXIS));
	
	JMenuBar menuBar_1 = new JMenuBar();
	setJMenuBar(menuBar_1);
	
	JMenu actions = new JMenu("Actions");
	menuBar_1.add(actions);
	
	JMenuItem mergeSelected = new JMenuItem("Merge Selected");
	actions.add(mergeSelected);
	
	JMenuItem Remove = new JMenuItem("Remove Selected");
	actions.add(Remove);
	
	JMenuItem next = new JMenuItem("Next");
	actions.add(next);
	
	Remove.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (Selected.getComponentCount()>1)
			{
			String[] enter=new String[Selected.getComponentCount()];
			for (int i = 0; i < Selected.getComponentCount(); i++) {
				JButtonPropio J=(JButtonPropio) Selected.getComponent(i);
				enter[i]=J.getText();
			} 
			


				Modelo111218.getInstance().Remove(enter); 
				refresca();
			
			
			}
		}
	});
	
	mergeSelected.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (Selected.getComponentCount()>1)
			{
			String[] enter=new String[Selected.getComponentCount()];
			for (int i = 0; i < Selected.getComponentCount(); i++) {
				JButtonPropio J=(JButtonPropio) Selected.getComponent(i);
				enter[i]=J.getText();
			} 
			
			// Con JCombobox
			String seleccion = (String) JOptionPane.showInputDialog(
			   Yo,
			   "Selecciona Destino",
			   "Selector de opciones",
			   JOptionPane.QUESTION_MESSAGE,
			   null,  // null para icono defecto
			   enter, 
			   enter[0]);

			if (seleccion!=null){
				Modelo111218.getInstance().MergeNominal(enter,seleccion); 
				refresca();
			}
			
			}
		}
	});

	
	next.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			PanelSeleccionAutomaticoManual PSAM=new PanelSeleccionAutomaticoManual();
			PSAM.setVisible(true);
			
		}
	});
	AL=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			((JButtonPropio)arg0.getSource()).swap();
			
		}
	};

//	JButtonPropio J2B=new JButtonPropio(new Pesonaje("Hola"), Actual, Selected);
//	J2B.setActual(Actual);
//	J2B.addActionListener(AL);

	refresca();
		
	
}


	public void refresca() {
		Selected.removeAll();
		Actual.removeAll();
		Actual.updateUI();
		Selected.updateUI();
		ArrayList<Pesonaje> ListaPersonajes =Modelo111218.getInstance().getNominalesTexto();
		for (Pesonaje pesonaje : ListaPersonajes) {
			JButtonPropio JB=new JButtonPropio(pesonaje,Actual,Selected);
			JB.setActual(Actual);
			JB.addActionListener(AL);
			
		}
		
	}
}
