package fr.umfds.TP2_Courriel;

public class EnvoiInvalide extends Exception {
	private static final long serialVersionUID = 1L;

	public EnvoiInvalide(String string) {
        super(string);
    }    
}