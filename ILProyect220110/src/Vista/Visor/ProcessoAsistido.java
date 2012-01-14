package Vista.Visor;

import Vista.Visor.SeleccionImagen.Seleccion;

public class ProcessoAsistido {
	
	public static void Iniciate() {
		GoogleImageFinderExpress GFX=new GoogleImageFinderExpress();
		GFX.go();
		SeleccionImagen SI=new SeleccionImagen(Seleccion.Automatica);	
		SI.setVisible(true);
		
	}
	
}
