package Model;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Pesonaje {

	protected String Name;
	protected String Acotado;
	protected ImageIcon ImagenAsociada;
	protected String FindName;
	protected ArrayList<String> Sinonimos;
	
	public Pesonaje(String NameIn, String acotado) {
		Name=NameIn;
		Acotado=acotado;
		ImagenAsociada=null;
		FindName=null;
		Sinonimos=Thesaurus.getSinonimos(acotado);
	}
	
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}

	public ImageIcon getImagenAsociada() {
		return ImagenAsociada;
	}
	
	public void setImagenAsociada(ImageIcon imagenAsociada) {
		ImagenAsociada = imagenAsociada;
	}
	
	public String getFindName() {
		if (FindName==null) return Name;
		return FindName;
	}
	
	public void setFindName(String findName) {
		FindName = findName;
	}
	
	public boolean EstaEn(String Nuevo)
	{
	if (getName().toUpperCase().equals(Nuevo.toUpperCase()))
		return true;
	if (getAcotado().toUpperCase().equals(Nuevo.toUpperCase()))
		return true;
	for (String S : Sinonimos) {
		if (S.toUpperCase().equals(Nuevo.toUpperCase()))
			{
			FindName=getFindName()+"+"+Nuevo;
			return true; 
			}
	}
	return false;
	}
	
	public ArrayList<String> getSinonimos() {
		return Sinonimos;
	}
	
	public String getAcotado() {
		return Acotado;
	}
	
	public String getFindNameReal() {
		return FindName;
	}
}
