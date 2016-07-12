package fr.treeptik.annuaire.exceptions;

public class SaisieException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SaisieException (String message, Throwable cause){
		super(message, cause);
	}

}
