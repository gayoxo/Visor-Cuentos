package Vista.Visor;

import java.awt.Panel;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Modelo111218;
import Vista.Visor.SeleccionImagen.Seleccion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class PanelSeleccionAutomaticoManual extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame Yo;
	
	
	
	public PanelSeleccionAutomaticoManual() {
		Yo=this;
		
		getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblSeleccionDeObtenedor = new JLabel("Seleccion de Obtenedor de Imagenes");
		lblSeleccionDeObtenedor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSeleccionDeObtenedor.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSeleccionDeObtenedor);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton Manual = new JButton("Seleccion Local Manual");
		panel_3.add(Manual);
		Manual.setAlignmentX(Component.CENTER_ALIGNMENT);
		Manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeleccionImagen SI=new SeleccionImagen(Seleccion.Manual);	
				SI.setVisible(true);
				Yo.setVisible(false);
			}
		});
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.PAGE_AXIS));
		
		JButton Automatico = new JButton("Seleccion Remota Manual");
		Automatico.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(Automatico);
		
		JButton btnNewButton = new JButton("Modo Remota Asistida");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcessoAsistido.Iniciate();
				Yo.setVisible(false);
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(btnNewButton);
		
		JButton Express = new JButton("Seleccion Remota Express");
		Express.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(Express);
		Express.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcessoExpress.Iniciate();
				Yo.setVisible(false);
			}
		});
		Automatico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeleccionImagen SI=new SeleccionImagen(Seleccion.Automatica);	
				SI.setVisible(true);
				Yo.setVisible(false);
			}
		});
		
		setSize(548,381);
	}

}
