package fr.treeptik.annuaire.dao.interf;

import java.util.List;
import java.util.Map;

public interface GenericDAO<T, Pk> {

	T add(T entite);
	
	void update(T entite);

	void deleteById(Pk key);

	void delete(T entite);
	
	T findById(Pk key);
	
	List<T> findAll();
	
	List<T> selectByAttributes(Map<String, Object> map);
	
//	List<T> genricSelectWithJointure(Map<String, Object> map);

}
