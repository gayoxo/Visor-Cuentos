package Vista.Visor;

import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;
import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Pesonaje;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelImagenSelectAutomatic extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel Imagen;
	private PanelImagenSelectAutomatic Yo;
	private Pesonaje Person;
	
	public PanelImagenSelectAutomatic(Pesonaje ss) {
		setLayout(new BorderLayout(0, 0));
		
		Yo=this;
		Person=ss;
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton ShowOpt = new JButton("Open Google Image");
		ShowOpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GoogleImageFinder GF=new GoogleImageFinder(Yo);
				GF.setVisible(true);
				
			}
		});
		ShowOpt.setBounds(145, 103, 189, 23);
		panel.add(ShowOpt);
		
		JLabel Sujeto = new JLabel(Person.getName());
		Sujeto.setBounds(80, 59, 323, 14);
		panel.add(Sujeto);
		
		Imagen = new JLabel("");
		if (Person.getImagenAsociada()!=null)
			setImagen(Person.getImagenAsociada());;
		Imagen.setBounds(145, 170, 225, 224);
		
		panel.add(Imagen);
	}
	
	public void setImagen(ImageIcon imagen) {
		Person.setImagenAsociada(imagen);
		ImageIcon II=imagen;
		
		Imagen.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
		Imagen.setIcon(II);
		
		repaint();
		
//		Imagen.addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent arg0) {
//				JLabel label22 = (JLabel)arg0.getSource();
//				Icon II = label22.getIcon();
//				Icon icono = new ImageIcon(((ImageIcon) II).getImage().getScaledInstance(label22.getPreferredSize().width, label22.getPreferredSize().height, Image.SCALE_FAST));
//				
//				label22.setIcon(icono);
//			}
//		});
	}

	public Pesonaje getPerson() {
		return Person;
	}
}
