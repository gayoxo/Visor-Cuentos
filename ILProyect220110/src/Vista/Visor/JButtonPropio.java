package Vista.Visor;

import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Pesonaje;

public class JButtonPropio extends JButton {

	private Pesonaje MiPersonaje;
	private JPanel Inicial;
	private JPanel Selected;
	private JPanel Actual;
	
	public JButtonPropio(Pesonaje pesonaje, JPanel Inicialin, JPanel Selectedin) {
		super(pesonaje.getName());
		MiPersonaje=pesonaje;
		Inicial=Inicialin;
		Selected=Selectedin;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void swap()
		{
		if (Actual!=null)
		{
			if (Actual==Inicial)
				{
				setActual(Selected);
				}
			else if (Actual==Selected)
			{
				setActual(Inicial);
			}
		}
		}
	public void setActual(JPanel actual) {
		if (Actual!=null) 
			{
			Actual.remove(this);
			Actual.updateUI();
			}
		Actual = actual;
		Actual.add(this);
		Actual.updateUI();
	}
	
	public Pesonaje getMiPersonaje() {
		return MiPersonaje;
	}
	
	
}
