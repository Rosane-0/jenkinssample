package fr.treeptik.annuaire.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.annuaire.dao.interf.PersonneDAO;
import fr.treeptik.annuaire.exceptions.DAOException;
import fr.treeptik.annuaire.model.pojo.Numero;
import fr.treeptik.annuaire.model.pojo.Personne;
import fr.treeptik.annuaire.utils.ContextPersistanceUtils;

public class PersonneDAOImpl extends GenericJPADAO<Personne, Integer> implements PersonneDAO {
	
	public PersonneDAOImpl() {
		super(Personne.class);
	}

	private EntityManager em = ContextPersistanceUtils.getEntityManager();

//	@Override
//	public Personne add(Personne personne) throws DAOException{
//		try {
//			em.persist(personne);
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans PersonneDAOImpl addPersonne "+ e.getMessage(), e);
//		}
//		return personne;
//	}

//	@Override
//	public void update(Personne personne) {
//		try {
//			em.merge(personne);
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans PersonneDAOImpl updatePersonne "+ e.getMessage(), e);
//		}
//	}
	
	@Override
	public Personne updateNPD(Personne p, String nom, String prenom, Date dateNaissance) throws DAOException{
		try {
			p = findById(p.getId());
			p.setNom(nom);
			p.setPrenom(prenom);
			p.setDateNaissance(dateNaissance);
			em.merge(p);
			return p;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl updateNPD "+ e.getMessage(), e);
		}
	}

	@Override
	public void deleteById(Integer id) throws DAOException{
		try {
			Query query = em.createQuery("DELETE FROM Personne p WHERE p.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl deleteById "+ e.getMessage(), e);
		}
	}

//	@Override
//	public void delete(Personne personne) throws DAOException{
//		try {
//			em.remove(personne);
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans PersonneDAOImpl deletePersonne "+ e.getMessage(), e);
//		}
//	}

	@Override
	public Personne findById(Integer id) throws DAOException{
		try {
			Personne p = em.createQuery("SELECT p FROM "+ Personne.class.getName() +" p "
					+ "LEFT JOIN FETCH p.adresse LEFT JOIN FETCH p.numerosTel "
					+ "WHERE p.id = :id ", Personne.class).setParameter("id", id).getSingleResult();
			return p;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl findById "+ e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findAll() throws DAOException {
		try {
			 TypedQuery<Personne> query = em.createQuery("SELECT DISTINCT p FROM Personne p "
			 		+ "LEFT JOIN FETCH p.adresse LEFT JOIN FETCH p.numerosTel ORDER BY p.id", Personne.class);
			 List<Personne> personnes = query.getResultList();
			return personnes;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl findAll "+ e.getMessage(), e);
		}
	}

	@Override
	public Personne addNumToPersonne(Personne personne, Numero numero) throws DAOException {
		try {
			personne = findById(personne.getId());
			Set<Numero> numeros = personne.getNumerosTel();
			numeros.add(numero);
			em.merge(personne);
			return personne;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl addNumToPersonne "+ e.getMessage(), e);
		}
		
	}

//	@Override
//	public Personne addAdresseToPersonne(Personne personne, Adresse adresse) throws DAOException {
//		try {
//			personne = findById(personne.getId());
//			personne.setAdresse(adresse);
//			em.merge(personne);
//			return personne;
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans PersonneDAOImpl addAdresseToPersonne "+ e.getMessage(), e);
//		}
//		
//	}

	@Override
	public Personne getNumerosByPersonne(Personne personne) throws DAOException {
		try {
			personne = em.createQuery("SELECT p FROM Personne p "
					+ "LEFT JOIN FETCH p.numerosTel n WHERE :personne MEMBER OF n", 
					Personne.class).setParameter("personne", personne).getSingleResult();		
			return personne;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl getNumerosByPersonne "+ e.getMessage(), e);
		}
	}

	@Override
	public Personne getAdresseByPersonne(Personne personne) throws DAOException {
		try {
			personne = em.createQuery("SELECT p FROM Personne p "
					+ "LEFT JOIN FETCH p.adresse a WHERE :personne MEMBER OF a"
					, Personne.class).setParameter("personne", personne).getSingleResult();		
			return personne;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl getAdresseByPersonne "+ e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> getPersonneByFirstLastName(String nom, String prenom) throws DAOException {
		try {
			TypedQuery<Personne> query = em.createQuery("SELECT DISTINCT p FROM Personne p "
//					+ "LEFT JOIN FETCH p.numerosTel "
					+ "WHERE p.nom = :nom"
					, Personne.class);
			query.setParameter("nom", nom);
//			query.setParameter("prenom", prenom);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans PersonneDAOImpl getPersonneByFirstLastName "+ e.getMessage(), e);
		}
	}
	
	




}
