package fr.treeptik.annuaire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.annuaire.dao.interf.NumeroDAO;
import fr.treeptik.annuaire.exceptions.DAOException;
import fr.treeptik.annuaire.model.pojo.Numero;
import fr.treeptik.annuaire.model.pojo.Personne;
import fr.treeptik.annuaire.utils.ContextPersistanceUtils;

public class NumeroDAOImpl extends GenericJPADAO<Numero, Integer> implements NumeroDAO {
	
	public NumeroDAOImpl() {
		super(Numero.class);
	}

	private EntityManager em = ContextPersistanceUtils.getEntityManager();

//	@Override
//	public Numero add(Numero numero) throws DAOException {
//		try {
//			em.persist(numero);
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans NumeroDAOImpl addNumero "+ e.getMessage(), e);
//		}
//		return numero;
//	}

//	@Override
//	public void update(Numero numero) throws DAOException {
//	}

	@Override
	public Numero UpdateByTypeNum(Numero numero, String type, String numTel) throws DAOException {
		try {
			numero = findById(numero.getId());
			numero.setType(type);
			numero.setTel(numTel);
			em.merge(numero);
			return numero;
		} catch (PersistenceException e) {
			throw new DAOException("rreur dans NumeroDAOImpl UpdateByTypeNum "+ e.getMessage(), e);
		}
	}

	@Override
	public void deleteById(Integer id) throws DAOException {
		try {
			Query query = em.createQuery("DELETE FROM Numero n WHERE n.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans NumeroDAOImpl deleteById "+ e.getMessage(), e);
		}
		
	}

//	@Override
//	public void delete(Numero numero) throws DAOException {
//		try {
//			em.remove(numero);
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans PersonneDAOImpl deleteNumero "+ e.getMessage(), e);
//		}
//	}

//	@Override
//	public Numero findById(Integer id) throws DAOException {
//		try {
//			Numero n = em.find(Numero.class, id);
//			return n;
//		} catch (PersistenceException e) {
//			throw new DAOException("Erreur dans NumeroDAOImpl findById "+ e.getMessage(), e);
//		}
//	}

	@Override
	public List<Numero> findAll() throws DAOException {
		try {
			 TypedQuery<Numero> query = em.createQuery("SELECT DISTINCT n FROM Numero n LEFT JOIN FETCH n.personnes", Numero.class);
			 List<Numero> numeros = query.getResultList();
			return numeros;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans NumeroDAOImpl findAll "+ e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> getPersonneByNumero(Numero numeroTel) throws DAOException {
		try {
			TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p "
					+ "LEFT JOIN FETCH p.numeros n WHERE :numero MEMBER OF n"
					, Personne.class).setParameter("numero", numeroTel);	
			List<Personne> personnes = query.getResultList();
			return personnes;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur dans NumeroDAOImpl getPersonneByNumero "+ e.getMessage(), e);
		}
	}

	@Override
	public Numero findByTypeTel(String type, String numTel) throws DAOException{
		Numero num = new Numero();
		TypedQuery<Numero> query  = em.createQuery("SELECT n FROM Numero n "
				+ "WHERE type = :type AND tel = :tel", Numero.class);
		query.setParameter("type", type);
		query.setParameter("tel", numTel);
		try {
			num = query.getSingleResult();
		} catch (NoResultException e) {
			return num;
		}
		return num;
	}


}
