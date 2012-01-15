package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ImageIconEspecial extends ImageIcon {

	private ArrayList<String> Avariables;
	private int actual=0;
	private ImageIcon Yo; 
	
	public ImageIconEspecial(URL url, ArrayList<String> images,int actualin) {
		super(url);
		Yo=this;
		actual=actualin;
		Avariables=images;
		
		while((getIconWidth()<=0 || getIconHeight()<=0)&&(actual!=Avariables.size()-1))
		{
			try {
				actual++;
				System.out.println(Avariables.get(actual));
				Yo.setImage(new ImageIconEspecial(new URL(Avariables.get(actual)), Avariables,actual).getImage());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<String> getAvariables() {
		return Avariables;
	}
}
