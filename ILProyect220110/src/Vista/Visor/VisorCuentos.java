package Vista.Visor;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JPanel;
import javax.swing.JButton;

import Model.Modelo111218;
import Model.Parrafo;
import Model.Pesonaje;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VisorCuentos extends JFrame {
	
	private int ActualLine=0;
	private JTextArea Texto;
	private JButton btnNewButton;
	private JButton button;
	private ArrayList<Parrafo> Parrafos;
	private JPanel panel_1;
	private VisorCuentos Visor;
	//private JLabel label;
	
	public VisorCuentos() {
		Visor=this;
		Parrafos=Modelo111218.getInstance().getTextoLineado();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setTitle("Visor");
		int xSize = 700;  
		int ySize =614;
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize = ((int) tk.getScreenSize().getWidth());  
		ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ActualLine--;
			refrescamelotodo();
			btnNewButton.setEnabled(true);
			if (ActualLine==0) button.setEnabled(false);
			}
		});
		panel.add(button);
		button.setEnabled(false);
		
		btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualLine++;
				refrescamelotodo();
				button.setEnabled(true);
				if (ActualLine==Parrafos.size()-1) btnNewButton.setEnabled(false);
			}
		});
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		
//		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
//		
//		
//		JScrollPane scrollPane_3 = new JScrollPane();
//		panel_1.add(scrollPane_3);
//		
//		Texto = new JTextArea();
//		scrollPane_3.setViewportView(Texto);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		panel_1.add(scrollPane);
//		
//		P1 = new JLabel("");
//		scrollPane.setViewportView(P1);
//		
//		JScrollPane scrollPane_1 = new JScrollPane();
//		panel_1.add(scrollPane_1);
//		
//		P2 = new JLabel("");
//		scrollPane_1.setViewportView(P2);
//		
//		JScrollPane scrollPane_2 = new JScrollPane();
//		panel_1.add(scrollPane_2);
//		
//		P3 = new JLabel("");
//		scrollPane_2.setViewportView(P3);
		
//		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
//		
//		JPanel scrollPane = new JPanel();
//		panel_1.add(scrollPane);		
//		scrollPane.setLayout(new BorderLayout(0, 0));
//		
//		JLabel label = new JLabel("");
//		label.addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent arg0) {
//				JLabel label22 = (JLabel)arg0.getSource();
//				ImageIcon II = null;
//				try {
//					II = new ImageIcon(new URL("http://images5.cuantocabron.com/ccs/2011/12/CC_844490_mirada_fija_si_lo_has_dicho_lo_has_dicho.jpg"));
//				} catch (MalformedURLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				Icon icono = new ImageIcon(II.getImage().getScaledInstance(label22.getWidth(), label22.getHeight(), Image.SCALE_DEFAULT));
//				
//				label22.setIcon(icono);
//			}
//		});
//		scrollPane.add(label, BorderLayout.CENTER);
//		ImageIcon II = null;
//		try {
//			II = new ImageIcon(new URL("http://images5.cuantocabron.com/ccs/2011/12/CC_844490_mirada_fija_si_lo_has_dicho_lo_has_dicho.jpg"));
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		Icon icono = new ImageIcon(II.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
//		
//		label.setIcon(icono);	
//		label.repaint();
//		
//		repaint();
		
		refrescamelotodo();
		
		
		
		
	}

	protected void refrescamelotodo() {
		Parrafo P=Parrafos.get(ActualLine);
		ArrayList<Pesonaje> salida = P.getPersonajesParrafo();
		salida=clear(salida);
		panel_1.removeAll();
		int Tot=salida.size()+1;	
		Double Div=((double)Tot)/((double)2);
		int Columnas=Div.intValue();
		int Filas=(int)(Math.round(Div));
		panel_1.setLayout(new GridLayout(Filas, Columnas, 0, 0));
		
//		JScrollPane scrollPane_3 = new JScrollPane();
//		panel_1.add(scrollPane_3);
		
		Texto = new JTextArea();
//		scrollPane_3.setViewportView(Texto);
//		Texto.setAutoscrolls(true);
		Texto.setText(P.getTextoParrafo());
		Texto.setLineWrap(true);
		
		//Texto.setFont(new Font("Alibi", Font.PLAIN, 33));
		Texto.setFont(new Font("Candy Round BTN Cond", Font.PLAIN, 33));
		
		JScrollPane scrollPane_3 = new JScrollPane(Texto,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		panel_1.add(scrollPane_3);
		
		for (Pesonaje pesonaje : salida) {
			
			JPanel scrollPane = new JPanel();
			scrollPane.setLayout(new BorderLayout(0, 0));
			
			//JScrollPane scrollPane = new JScrollPane();
			
			
			panel_1.add(scrollPane);		
			JLabel P1 = new JLabel("");
			
			scrollPane.add(P1,BorderLayout.CENTER);
			
			//scrollPane.setViewportView(P1);
			
			
			ImageIcon II=pesonaje.getImagenAsociada();
			//P1.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
			//P1.setSize("100%", "100%");
			P1.setIcon(II);	
			
			P1.repaint();
			
			P1.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					JLabel label22 = (JLabel)arg0.getSource();
					Icon II = label22.getIcon();
					Image III=((ImageIcon) II).getImage();
//					float hf=((float)III.getHeight(null)/((float)III.getWidth(null)));
//					float hw=((float)III.getWidth(null))/((float)III.getHeight(null));
//					Icon icono=null;
//					if (hf>hw)
//					{
//					float aux=	((float)III.getHeight(null))/ ((float)label22.getHeight());
//					
//					icono = new ImageIcon(III.getScaledInstance(Math.round((((float)III.getWidth(null))/aux)-1), label22.getHeight(), Image.SCALE_DEFAULT));
//					}
//					else 
//					{
//						float aux=	((float)III.getWidth(null))/((float) label22.getWidth());
//						
//						icono = new ImageIcon(III.getScaledInstance(label22.getWidth(),Math.round((((float)III.getHeight(null))/aux)-1), Image.SCALE_DEFAULT));
//					}
					ImageIcon icono = new ImageIcon(III.getScaledInstance(label22.getWidth(),label22.getHeight(), Image.SCALE_DEFAULT));
					label22.setIcon(icono);
				}
			});
		}
		
		Visor.actualizarPantalla();
		
		
//		if (salida.size()>0)
//		{
//			ImageIcon II=salida.get(0).getIcono();
//			P1.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
//			P1.setIcon(II);	
//			P1.repaint();
//			if (salida.size()>1)
//			{
//				II=salida.get(1).getIcono();
//				P2.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
//				P2.setIcon(II);	
//				P2.repaint();
//				if (salida.size()>2)
//				{
//					II=salida.get(2).getIcono();
//					P3.setBounds(145, 193, II.getIconWidth(), II.getIconHeight());
//					P3.setIcon(II);	
//				}else P3.setIcon(null);
//			}else {
//				P2.setIcon(null);
//				P3.setIcon(null);
//			}
//		}else {
//			P1.setIcon(null);
//			 P2.setIcon(null);
//			 P3.setIcon(null);
//		}
//		Texto.setText(C.getText());
		
		
		
		//repaint();
		
	}

	private ArrayList<Pesonaje> clear(ArrayList<Pesonaje> Entrada) {
		ArrayList<Pesonaje> Salida=new ArrayList<Pesonaje>();
		for (Pesonaje pesonaje : Entrada) {
			if (pesonaje.getImagenAsociada()!=null)
				Salida.add(pesonaje);
		}
		return Salida;
	}

//	private ArrayList<SubjectMasImagen> clear(ContextoLinea contextoLinea) {
//		ArrayList<SubjectMasImagen> SMI=contextoLinea.getListaPersonajes();
//		ArrayList<SubjectMasImagen> SMIC=new ArrayList<SubjectMasImagen>();
//		for (SubjectMasImagen subjectMasImagen : SMI) {
//			if (!(estaSMIC(subjectMasImagen,SMIC))) SMIC.add(subjectMasImagen);
//		}
//		return SMIC;
//	}
//
//	private boolean estaSMIC(SubjectMasImagen subjectMasImagen,
//			ArrayList<SubjectMasImagen> sMIC) {
//		for (SubjectMasImagen subjectMasImagen2 : sMIC) {
//			if (subjectMasImagen.getSujeto().getName().toUpperCase().equals(subjectMasImagen2.getSujeto().getName().toUpperCase()))
//				return true;
//		}
//		return false;
//	}
	
	public void actualizarPantalla(){ 
//		SwingUtilities.updateComponentTreeUI(this); 
//		this.validateTree(); 
		} 

}
