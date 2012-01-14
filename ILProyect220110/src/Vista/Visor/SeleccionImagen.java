package Vista.Visor;

import java.util.ArrayList;

import javax.swing.JFrame;

import Model.Modelo111218;
import Model.Pesonaje;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionImagen extends JFrame {
	
	enum Seleccion {Manual,Automatica};
	

	private static JButton btnNewButton;
	

	public SeleccionImagen(Seleccion S) {

		getContentPane().setLayout(new BorderLayout(0, 0));
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setTitle("Seleccion Imagen");
		int xSize = 700;  
		int ySize =700;
//		Toolkit tk = Toolkit.getDefaultToolkit();  
//		xSize = ((int) tk.getScreenSize().getWidth());  
//		ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		ArrayList<Pesonaje> AS = Modelo111218.getInstance().getNominalesTexto();
		for (Pesonaje ss : AS) {
			if (S==Seleccion.Manual){
			PanelImagenSelectManual PIM=new PanelImagenSelectManual(ss);
			tabbedPane.addTab(ss.getName(), null, PIM, null);
			}
			else
			if (S==Seleccion.Automatica)
			{ 
				PanelImagenSelectAutomatic PIM=new PanelImagenSelectAutomatic(ss);
				tabbedPane.addTab(ss.getName(), null, PIM, null);
			}

		}
		
		
		btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisorCuentos VS=new VisorCuentos();
				VS.setVisible(true);
			}
		});
		btnNewButton.setEnabled(true);
		getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		
	}

		
	
	
}
