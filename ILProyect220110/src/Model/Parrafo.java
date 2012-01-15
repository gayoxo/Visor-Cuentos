package Model;

import java.util.ArrayList;

public class Parrafo {

	private int NumeroParrafo;
	private String TextoParrafo;
	private ArrayList<Pesonaje> PersonajesParrafo;
	
	public Parrafo(int NumeroParrafoIn, String TextoParrafoFreeling) {
	NumeroParrafo=NumeroParrafoIn;
	PersonajesParrafo=new ArrayList<Pesonaje>();
	genararPersonajesParrafoYTexto(TextoParrafoFreeling);
	}

	private void genararPersonajesParrafoYTexto(String TextoParrafoFreeling) {
		String[] Saltado=TextoParrafoFreeling.split("\n");
		StringBuffer SB=new StringBuffer();
		for (String string : Saltado) {
			String[] LineaFree=string.split(" ");
			if (LineaFree[2].charAt(0)==('N')){
				String Resumen=DeteccionManual(LineaFree[1]);
				Pesonaje P=Modelo111218.getInstance().generaPersonaje(LineaFree[0],Resumen);
					if (!NoEstaYa(P))
						PersonajesParrafo.add(P);
			}
			SB.append(LineaFree[0]+" ");	
		} 
		TextoParrafo=SB.toString();
		System.out.println(TextoParrafo);
		
	}

	private String DeteccionManual(String string) {
		String Nombre=string;
		if (Nombre.endsWith("ita")) 
		{
			Nombre=Nombre.substring(0, Nombre.length()-3);
			Nombre=Nombre.concat("a");
		}
		if (Nombre.endsWith("ito")) 
		{
			Nombre=Nombre.substring(0, Nombre.length()-3);
			Nombre=Nombre.concat("o");
		}
		if (Nombre.endsWith("illa")) 
		{
			Nombre=Nombre.substring(0, Nombre.length()-3);
			Nombre=Nombre.concat("a");
		}
		if (Nombre.endsWith("illo")) 
		{
			Nombre=Nombre.substring(0, Nombre.length()-3);
			Nombre=Nombre.concat("o");
		}
		return Nombre;
	}

	private boolean NoEstaYa(Pesonaje p) {
		for (Pesonaje PP : PersonajesParrafo) {
			if (PP.getName().toUpperCase().equals(p.getName().toUpperCase())) return true;
		}
		return false;
	}

	public int getNumeroParrafo() {
		return NumeroParrafo;
	}

	public void setNumeroParrafo(int numeroParrafo) {
		NumeroParrafo = numeroParrafo;
	}

	public String getTextoParrafo() {
		return TextoParrafo;
	}

	public void setTextoParrafo(String textoParrafo) {
		TextoParrafo = textoParrafo;
	}

	public ArrayList<Pesonaje> getPersonajesParrafo() {
		return PersonajesParrafo;
	}

	public void setPersonajesParrafo(ArrayList<Pesonaje> personajesParrafo) {
		PersonajesParrafo = personajesParrafo;
	}
	
	
	
}
