package fr.treeptik.annuaire.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.annuaire.dao.interf.GenericDAO;
import fr.treeptik.annuaire.exceptions.DAOException;
import fr.treeptik.annuaire.utils.ContextPersistanceUtils;

public class GenericJPADAO<T, Pk> implements GenericDAO<T, Pk> {

	protected EntityManager em = ContextPersistanceUtils.getEntityManager();

	public Class<T> type;

	public GenericJPADAO(Class<T> type) {
		this.type = type;
	}

	@Override
	public T add(T entite) throws DAOException{
		try {
			em.persist(entite);
		} catch (PersistenceException e) {
			throw new DAOException("Err dans persist" + type.getSimpleName() + " " + e.getMessage(), e);
		}
		return entite;
	}

	@Override
	public void update(T entite) throws DAOException  {
		try {
			em.merge(entite);
		} catch (PersistenceException e) {
			throw new DAOException("Err dans update" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteById(Pk id) throws DAOException  {

	}

	@Override
	public void delete(T entite) throws DAOException  {
		try {
			em.remove(entite);
		} catch (PersistenceException e) {
			throw new DAOException("Err dans delete" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}

	@Override
	public T findById(Pk id) throws DAOException {
		try {
			return em.find(type, id);
		} catch (PersistenceException e) {
			throw new DAOException("Err dans findById" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws DAOException{
		try {
			Query query = em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Err dans findAll" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectByAttributes(Map<String, Object> map) throws DAOException{
		try {
			String requete = "SELECT o FROM " + type.getSimpleName() + " o WHERE";
			for (Entry<String, Object> entry : map.entrySet()) {       //idem possible sur set des clés : Set<String> cle = map.keySet();
				requete += " o."+entry.getKey() + " = '" + entry.getValue() + "' AND" ;
			}
			Query query = em.createQuery(requete.substring(0, requete.lastIndexOf(" AND")) , type);
//			//foreach pour setter les param
//			for (String cle : map.keySet()) {
//				query.setParameter(cle, map.get(cle));
//			}
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Err dans genericSelectByAttributes" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}
	
	//Correction
	public List<T> selectByParameters(Map<String, Object> map) throws DAOException {
		try {
			StringBuilder builder = new StringBuilder("Select e FROM ");
			builder.append(type.getSimpleName()).append(" e ");
			String query;
			TypedQuery<T> queryJPA;
			if (map != null && map.size()>0) {
				builder.append(" WHERE ");
				Set<String> keySet = map.keySet();
				for (String field : keySet) {
					builder.append(field).append(" = :" + field + " AND");
				} 
				
				query = builder.substring(0, builder.lastIndexOf(" AND"));
				
				queryJPA = em.createQuery(query, type);

				for (String field : keySet) {
					queryJPA.setParameter(field, map.get(field));
				} 
			}
			else {
				queryJPA = em.createQuery(builder.toString(), type);
			}
			
			return queryJPA.getResultList();
			
		} catch (PersistenceException e) {
			throw new DAOException("Err dans selectByParameters" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}
	//Correction avec iterateur i
	public List<T> selectByParameters2(Map<String, Object> map) throws DAOException {
		try {
			StringBuilder builder = new StringBuilder("Select e FROM ");
			builder.append(type.getSimpleName()).append(" e ");
			String query;
			TypedQuery<T> queryJPA;
			int i = 1;
			if (map != null && map.size()>0) {
				builder.append(" WHERE ");
				Set<String> keySet = map.keySet();
				for (String field : keySet) {
					builder.append(field).append(" = ?" + i++ + " AND");
				} 
				
				query = builder.substring(0, builder.lastIndexOf(" AND"));
				
				queryJPA = em.createQuery(query, type);

				int j=1;
				for (String field : keySet) {
					queryJPA.setParameter(j++, map.get(field));
				} 
			}
			else {
				queryJPA = em.createQuery(builder.toString(), type);
			}
			
			return queryJPA.getResultList();
			
		} catch (PersistenceException e) {
			throw new DAOException("Err dans selectByParameters" + type.getSimpleName() + " " + e.getMessage(), e);
		}
	}
	



}
