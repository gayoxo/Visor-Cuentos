package Vista.Visor;

public class ProcessoExpress {

	public static void Iniciate() {
		GoogleImageFinderExpress GFX=new GoogleImageFinderExpress();
		GFX.go();
		VisorCuentos VS=new VisorCuentos();
		VS.setVisible(true);
		
	}
	
	

}
