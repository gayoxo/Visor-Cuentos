package Vista.Visor;

import javax.sound.midi.SysexMessage;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

import Model.Modelo111218;
import Model.Pesonaje;

public class GoogleImageFinderExpress{
	
	private ArrayList<String> Images=new ArrayList<String>();
	private ArrayList<Pesonaje> Lista;
	
	public GoogleImageFinderExpress() {
		
	}

	private void getImagenes(String name) {
		try {
			URL google = new URL("http://images.google.com.uy/images?q="+name+"+.jpg"); 
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
			connection.disconnect();
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
			String[] aux=string2.split(".JPG");
			//System.out.println(aux[0]+".jpg");
			String[] aux2=string2.split(".jpg");
			
			String Salida="vacio";
			if (aux.length>1)
				Salida=aux[0]+".JPG";
			else 
				if (aux2.length>1)
					Salida=aux2[0]+".jpg";
			
		//	Salida=testFormato(Salida);
			
			if (Salida!=null) Images.add(Salida);
			
			
		}
		
	}

	private String testFormato(String salida) {
		String SalidaAct=salida;
		try {
			SalidaAct=URLDecoder.decode(SalidaAct, "UTF-8");
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("Error: " +SalidaAct);
		}
		
//		String[] Splitt=SalidaAct.split("%");
//		if (Splitt.length==1)
			return SalidaAct;
//		else return null;
	}

		
		

	

	private void FindElementIndi(Pesonaje person) {
		Images=new ArrayList<String>();
		getImagenes(person.getFindName());
		Random R=new Random();
		String Imagen=Images.get(R.nextInt(10));
		ImageIcon II;
		try {
			
		//	Imagen=URLEncoder.encode(Imagen, "UTF-8");
			System.out.println(person.getName() + " : " + Imagen);
			II = new ImageIcon((new URL(Imagen)));
		
		person.setImagenAsociada(II);
			
		} catch (MalformedURLException e) {
			System.err.println(Imagen);
			//e.printStackTrace();
		} 
//		catch (UnsupportedEncodingException e) {
//			System.err.println(Imagen);
//			//e.printStackTrace();
//		}
		
		
	}

	public void go() {
		Lista=Modelo111218.getInstance().getNominalesTexto();
		for (Pesonaje person : Lista) {
			System.out.println("Start :" + person );
			FindElementIndi(person);
		}
		
	}
	
	
	

}
