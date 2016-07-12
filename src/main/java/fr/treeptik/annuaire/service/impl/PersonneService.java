package fr.treeptik.annuaire.service.impl;

import java.util.List;

import fr.treeptik.annuaire.dao.interf.PersonneDAO;
import fr.treeptik.annuaire.model.pojo.Personne;

public class PersonneService {
	
	public PersonneDAO dao;

	public List<Personne> findByName(String name){
		return dao.getPersonneByFirstLastName(name, "");
	}
	
	public PersonneDAO getDao() {
		return dao;
	}

	public void setDao(PersonneDAO dao) {
		this.dao = dao;
	}
	
	

}
