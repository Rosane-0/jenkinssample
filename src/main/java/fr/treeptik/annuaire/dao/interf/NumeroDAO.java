package fr.treeptik.annuaire.dao.interf;

import java.util.List;

import fr.treeptik.annuaire.model.pojo.Numero;
import fr.treeptik.annuaire.model.pojo.Personne;

public interface NumeroDAO extends GenericDAO<Numero, Integer>{
	
	Numero findByTypeTel(String type, String numTel);
	Numero UpdateByTypeNum(Numero numero, String type, String numTel);
	List<Personne> getPersonneByNumero(Numero numeroTel);

}
