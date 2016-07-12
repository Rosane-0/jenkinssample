package fr.treeptik.annuaire.utils;

import java.util.Scanner;

import fr.treeptik.annuaire.exceptions.ServiceException;

public class ScannerUtils {

	private static Scanner scanner;

	public static Scanner getScanner() throws ServiceException {

		try {

			if(scanner == null){
				scanner = new Scanner(System.in);
			}

		} catch (Exception e) {
			scanner.nextLine();
			throw new ServiceException("Erreur ScannerUtils getScanner " + e.getMessage(), e);
		}

		return scanner;

	}

}
