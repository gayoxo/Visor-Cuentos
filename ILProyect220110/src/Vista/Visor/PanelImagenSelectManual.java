package Vista.Visor;

import java.awt.Component;
import java.awt.Panel;
import java.awt.BorderLayout;

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

public class PanelImagenSelectManual extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel Imagen;
	private JTextField textField;
	private PanelImagenSelectManual Yo;
	private Pesonaje Person;
	
	public PanelImagenSelectManual(Pesonaje ss) {
		setLayout(new BorderLayout(0, 0));
		Person=ss;
		Yo=this;
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(67, 84, 336, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton OpenDialog = new JButton("Open Image");
		OpenDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("XMLOpen");
				chooser.setMultiSelectionEnabled(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Imagen", "jpg" ,"gif");
				    chooser.setFileFilter(filter);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int sel = chooser.showOpenDialog(Yo);
				if (sel == JFileChooser.APPROVE_OPTION){
					Person.setImagenAsociada(new ImageIcon(chooser.getSelectedFile().getPath()));
					textField.setText(chooser.getSelectedFile().getPath());
					ImageIcon II=new ImageIcon(chooser.getSelectedFile().getPath());
					
					Imagen.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
					Imagen.setIcon(II);
					
					repaint();
				}else{
				return;
				}
			}
		});
		OpenDialog.setBounds(428, 83, 118, 23);
		panel.add(OpenDialog);
		
		JLabel Sujeto = new JLabel(ss.getName());
		Sujeto.setBounds(80, 59, 323, 14);
		panel.add(Sujeto);
		
		Imagen = new JLabel("");
		Imagen.setBounds(145, 193, 225, 224);
		panel.add(Imagen);
	}

}
