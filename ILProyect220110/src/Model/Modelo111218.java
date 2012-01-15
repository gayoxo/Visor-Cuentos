package Model;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Modelo111218 {
	
	private static Modelo111218 Yo;
	private String TextoFreeLing;
	private static ArrayList<Pesonaje> NominalesTexto; 
	private ArrayList<Parrafo> TextoLineado;
	
	
	private Modelo111218() {
		TextoFreeLing="";
		NominalesTexto=new ArrayList<Pesonaje>();
		TextoLineado=new ArrayList<Parrafo>();
		
	}
	
	public static Modelo111218 getInstance()
	{
	if (Yo==null) Yo=new Modelo111218();
	return Yo;
	}

	public Pesonaje generaPersonaje(String Nuevo, String acotado) {
		
		for (Pesonaje P : NominalesTexto) {
			if (P.EstaEn(Nuevo.toUpperCase())||(P.EstaEn(acotado.toUpperCase()))) 
				{
				P.setAparicion(P.getAparicion()+1);
				return P;
				}
		}
		Pesonaje NecesitaNuevo=new Pesonaje(Nuevo,acotado);
		NominalesTexto.add(NecesitaNuevo);
		return NecesitaNuevo;
	}

	public void setTextoFreeLing(String textoFreeLing) {
		TextoFreeLing = textoFreeLing;
		NominalesTexto=new ArrayList<Pesonaje>();
		TextoLineado=new ArrayList<Parrafo>();
		GeneralParrafos();
	}

	private void GeneralParrafos() {
		int LineaActual = 1;
		String[] freelingTextSplit = TextoFreeLing.split("\n");
		StringBuffer Parrafo = new StringBuffer();
		for (String string : freelingTextSplit) {
			String[] ColumnasString = string.split(" ");
			if (!ColumnasString[0].isEmpty()) {
				Parrafo.append(string+'\n');
				if (ColumnasString[2].toUpperCase().equals("FP")) {
					TextoLineado.add(new Parrafo(LineaActual, Parrafo
							.toString()));
					Parrafo = new StringBuffer();
					LineaActual++;
				}
			}
		}
		//testeando;
		ProcesoDeLimpieza();
		System.out.println("End Load");

	}
	
	private void ProcesoDeLimpieza() {
		ArrayList<Pesonaje> Limpios=new ArrayList<Pesonaje>();
		for (Pesonaje P:NominalesTexto)
			{
			if (P.getAparicion()>2)
				{
				Limpios.add(P);
				}
			else
				{
				BorrarParrafos(P);
				}
			
			}
		NominalesTexto=Limpios;
		
	}

	private void BorrarParrafos(Pesonaje p) {
		for (Parrafo par : TextoLineado) {
			LimpiarParrafoUni(par,p);
		}
		
	}

	private void LimpiarParrafoUni(Parrafo par, Pesonaje borrar) {
		ArrayList<Pesonaje> PersonNew=new ArrayList<Pesonaje>();
		for (Pesonaje PerPos: par.getPersonajesParrafo())
			{
			if (!PerPos.getName().equals(borrar.getName()))
				PersonNew.add(PerPos);
			}
		par.setPersonajesParrafo(PersonNew);
		
	}

	public ArrayList<Pesonaje> getNominalesTexto() {
		return NominalesTexto;
	}
	
	public ArrayList<Parrafo> getTextoLineado() {
		return TextoLineado;
	}
	
	public String getTextoFreeLing() {
		return TextoFreeLing;
	}
	
	public String getTextoOriginal() {
		StringBuffer SB=new StringBuffer();
		for (Parrafo par : TextoLineado) {
			SB.append(par.getTextoParrafo()+'\n');
			
		}
		return SB.toString();
	}

	public void MergeNominal(String[] enter, String seleccion) {
		Pesonaje Final=findPersonaje(seleccion);
		StringBuffer SB=new StringBuffer();
		SB.append(Final.getFindName());
		for (String seleccionfor : enter) {
			if (!seleccionfor.equals(seleccion))
				SB.append("+"+ seleccionfor);
		}
		
		Final.setFindName(SB.toString());
		for (String booradores : enter) {
			Pesonaje PersonajeBorrar=findPersonaje(booradores);
			remplace(Final,PersonajeBorrar);
		}
		
	}

	private void remplace(Pesonaje final1, Pesonaje personajeBorrar) {
		if (final1!=personajeBorrar)
		{
			NominalesTexto.remove(personajeBorrar);
			for (Parrafo par : TextoLineado) {
				if (par.getPersonajesParrafo().contains(personajeBorrar))
					{
					par.getPersonajesParrafo().remove(personajeBorrar);
					par.getPersonajesParrafo().remove(final1);
					par.getPersonajesParrafo().add(final1);
					}
			} 
		}
		
	}

	private Pesonaje findPersonaje(String seleccion) {
		for (Pesonaje PP : NominalesTexto) {
			if (PP.getName().equals(seleccion)) return PP;
		}
		throw new Error("Personaje no encontrado");
	}

	public void Remove(String[] enter) {
		for (String Personaje : enter) {
			Pesonaje PersonajeBorrar=findPersonaje(Personaje);
			removePer(PersonajeBorrar);
		}
		
	}

	private void removePer(Pesonaje personajeBorrar) {
		NominalesTexto.remove(personajeBorrar);
		for (Parrafo par : TextoLineado) {
			par.getPersonajesParrafo().remove(personajeBorrar);
		}
		
	}

}
