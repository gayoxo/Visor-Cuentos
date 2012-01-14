package Vista.Visor;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Modelo111218;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class MainVisor extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File ActualCuento;
	private MainVisor Yo;
	private JLabel TextSelectedHistory;
	private JTextArea textArea;
	private JButton btnNewButton_1;
	
	public MainVisor() {
		
		Yo=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Visor");
		int xSize = 700;  
		int ySize =700;
//		Toolkit tk = Toolkit.getDefaultToolkit();  
//		xSize = ((int) tk.getScreenSize().getWidth());  
//		ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(657,451);
		getContentPane().setLayout(null);
		
		JButton XMLSelector = new JButton("Select Freeling output Tale");
		XMLSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("XMLOpen");
				chooser.setMultiSelectionEnabled(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "TXT", "txt");
				    chooser.setFileFilter(filter);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int sel = chooser.showOpenDialog(Yo);
				if (sel == JFileChooser.APPROVE_OPTION){
					ActualCuento = chooser.getSelectedFile();
					TextSelectedHistory.setText(ActualCuento.getPath());
					LoadFileCuento();
				}else{
				return;
				}
			}
		});

		XMLSelector.setBounds(27, 76, 213, 23);
		getContentPane().add(XMLSelector);

		
		TextSelectedHistory = new JLabel("");
		TextSelectedHistory.setBounds(250, 76, 391, 23);
		getContentPane().add(TextSelectedHistory);
		
		
		JLabel lblNewLabel = new JLabel("HISTORY VISUAL MAKER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 47));
		lblNewLabel.setBounds(43, 0, 539, 65);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(196, 135, 426, 271);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnNewButton_1 = new JButton("Visualizar");
		btnNewButton_1.addActionListener(new ActionListener() {

					

					@Override
					public void actionPerformed(ActionEvent arg0) {
						//TODO Mergegrupos.
						UnirEntidades UE=new UnirEntidades();
						UE.setVisible(true);
//						PanelSeleccionAutomaticoManual PSAM=new PanelSeleccionAutomaticoManual();
//						PSAM.setVisible(true);
					}


				
			
		});
		btnNewButton_1.setBounds(27, 179, 149, 91);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
	}

	protected void LoadFileCuento() {
		FileReader fr;
		try {
			fr = new FileReader (ActualCuento);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer SB=new StringBuffer();
			String linea;
			while ((linea = br.readLine())!=null)
				{
				SB.append(linea);
				SB.append("\n");
				}
			Modelo111218.getInstance().setTextoFreeLing(SB.toString());
			textArea.setText(Modelo111218.getInstance().getTextoOriginal());
			btnNewButton_1.setVisible(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Archivo no Encontrado","Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de Entrada/Salida","Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		
	}

//	protected void LoadFile() {
//		try {  
//	        // Creamos la factoria de parseadores por defecto  
//	        XMLReader reader = XMLReaderFactory.createXMLReader();  
//	        // Añadimos nuestro manejador al reader  
//	        reader.setContentHandler(new Manejador());           
//	        // Procesamos el xml de ejemplo  
//	        reader.parse(new InputSource(new FileInputStream(ActualXML)));
//	        
//	     } catch (SAXException e) {  
//	        e.printStackTrace();  
//	     } catch (IOException e) {  
//	        e.printStackTrace();  
//	        JOptionPane.showMessageDialog(null, "El Archivo no existe", "Errror", JOptionPane.ERROR_MESSAGE, null);
//	     } 
//		
//	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//	Subject S1=new Subject("Alicia");
//	S1.getSinonimos().add("Niña");
//	S1.getSinonimos().add("Muchacha");
//	Subject S2=new Subject("Pulgarcito");
//	S2.getSinonimos().add("Pequeño");
//	S2.getSinonimos().add("Muchacho");
//	ListadeSujetos L=ListadeSujetos.getInstance();
//	L.getSujetos().add(S1);
//	L.getSujetos().add(S2);
		

	        MainVisor M=new MainVisor();
	    	M.setVisible(true);	
	    	

	
	}
}
