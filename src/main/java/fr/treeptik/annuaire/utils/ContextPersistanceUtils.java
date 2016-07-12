package fr.treeptik.annuaire.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;

import fr.treeptik.annuaire.exceptions.DAOException;

public class ContextPersistanceUtils{
	
	private static EntityManager em;
	
	public static EntityManager getEntityManager() throws DAOException {
		
		try {

			if(em == null || !em.isOpen()){
				em = Persistence.createEntityManagerFactory("sample").createEntityManager();
			}

		} catch (PersistenceException | HibernateException e) {
			throw new DAOException("Erreur ContextPersistanceUtils getEntityManager " + e.getMessage(), e);
		}
		
		return em;
		
	}
	
	public static void beginTransaction() {
		em.getTransaction().begin();
	}
	
	public static void closeEntityManager() {
		em.close();
	}
	
	public static void rollbackTransaction() {
		em.getTransaction().rollback();
	}
	
	public static void commit() {
		em.getTransaction().commit();
	}
	
	public static void commitAndClose() {
		em.getTransaction().commit();
		em.close();
	}
	

}
