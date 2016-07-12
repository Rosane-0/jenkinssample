package fr.treeptik.annuaire.dao.interf;

import javax.persistence.PersistenceException;

import fr.treeptik.annuaire.dao.impl.NumeroDAOImpl;
import fr.treeptik.annuaire.dao.impl.PersonneDAOImpl;
import fr.treeptik.annuaire.exceptions.DAOFactoryException;

public class DAOFactory {
	
	public static PersonneDAO personneDAO;
	public static NumeroDAO numeroDAO;
//	public static AdresseDAO adresseDAO;
	
	public static PersonneDAO getPersonneDAO() throws DAOFactoryException {
		
		try {
			if (personneDAO == null) {
				return new PersonneDAOImpl();
			}
		} catch (PersistenceException e) {
			throw new DAOFactoryException("Erreur dans DAOFactory getPersonneDAO " +e.getMessage() , e);
		}
		return personneDAO;
		
	}
	
	
	public static NumeroDAO getNumeroDAO() throws DAOFactoryException {
		
		try {
			if (numeroDAO == null) {
				return new NumeroDAOImpl();
			}
		} catch (PersistenceException e) {
			throw new DAOFactoryException("Erreur dans DAOFactory getNumeroDAO " +e.getMessage() , e);
		}
		return numeroDAO;
		
	}
	
//	public static AdresseDAO getAdresseDAO() throws DAOFactoryException {
//		
//		try {
//			if (adresseDAO == null) {
//				return new AdresseDAOImpl();
//			}
//		} catch (PersistenceException e) {
//			throw new DAOFactoryException("Erreur dans DAOFactory getAdresseDAO " +e.getMessage() , e);
//		}
//		return adresseDAO;
//		
//	}

	
}
