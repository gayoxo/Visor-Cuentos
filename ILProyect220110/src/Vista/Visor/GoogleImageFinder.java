package Vista.Visor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import Model.Modelo111218;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GoogleImageFinder extends JFrame {
	
	private ArrayList<String> Images=new ArrayList<String>();
	private PanelImagenSelectAutomatic Padre;
	private GoogleImageFinder Yo;
	
	public GoogleImageFinder(PanelImagenSelectAutomatic padre) {
		Yo=this;
		Padre=padre;
		Images=new ArrayList<String>();
		getImagenes(Padre.getPerson().getFindName());
		Images=RecutImages();
		int Tot=Images.size();	
		Double Div=((double)Tot)/((double)2);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		
		for (String S1 : Images) {
		try {
			//JPanel panelaux = new JPanel();
			JLabel Imagen = new JLabel("");
			ImageIcon II=new ImageIcon((new URL(S1)));
			Imagen.setIcon(II);
			Imagen.setMaximumSize(new Dimension(100,100));
			Imagen.setMinimumSize(new Dimension(100,100));
			Imagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ImageIcon iIcon=(ImageIcon)(((JLabel)arg0.getSource()).getIcon());
					Padre.setImagen(iIcon);
					Yo.setVisible(false);
				}

				

				
			});
			panel_2.add(Imagen);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);

//		JLabel Imagen = new JLabel("");
//		Imagen.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				
//			}
//		});
//		ImageIcon II;
//		try {
//			II = new ImageIcon(new URL("http://www.juegosde.us/wp-content/uploads/2011/07/Space-Invaders.jpg"));
//		
//		Imagen.setIcon(II);
//		panel_2.add(Imagen);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private ArrayList<String> RecutImages() {
		ArrayList<String> Imagenes=new ArrayList<String>();
		if (Images.size()<10) return Images;
		else 
		{
			for (int i = 0; i < 10; i++) {
				Imagenes.add(Images.get(i));
			}
		}
		return Imagenes;
	}

	private void getImagenes(String name) {
		try {
			String TextoExtra="+Cuento+";
			String Busqueda="http://images.google.com.uy/images?q="+name+TextoExtra;
			System.out.println(Busqueda);
			URL google = new URL(Busqueda); 
	        HttpURLConnection connection = (HttpURLConnection) google.openConnection();
	        connection.addRequestProperty("User-Agent", "Mozilla/4.76"); 
			BufferedReader in = new BufferedReader( 
			new InputStreamReader( 
					connection.getInputStream())); 

			String inputLine; 
			StringBuffer SB=new StringBuffer();
			
				while ((inputLine = in.readLine()) != null){
					//System.out.println(inputLine);
					SB.append(inputLine);
					SB.append("\n");
				}
				
			SplitImagenes(SB.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}

	private void SplitImagenes(String string) {
		String[] strings=string.split("imgurl=");
		for (String string2 : strings) {
			evalualinea(string2);
			//System.out.println(string2);
		}
		
	}

	private void evalualinea(String string2) {
		if (string2.startsWith("http")){
			String[] aux=string2.split(".jpg");
			//System.out.println(aux[0]+".jpg");
			String[] aux2=string2.split(".JPG");
			
			String Salida="vacio";
			if (aux.length>1)
				Salida=aux[0]+".jpg";
			else 
				if (aux2.length>1)
					Salida=aux[0]+".jpg";
			
			Salida=testFormato(Salida);
			
			if (Salida!=null) Images.add(Salida);
			
			
		}
		
	}

	private String testFormato(String salida) {
		String SalidaAct=salida;
		try {
			SalidaAct=URLDecoder.decode(SalidaAct, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String[] Splitt=SalidaAct.split("%");
//		if (Splitt.length==1) 
			return SalidaAct;
	//	else return null;
	}
	
	

}
