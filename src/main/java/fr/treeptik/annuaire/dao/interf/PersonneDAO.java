package fr.treeptik.annuaire.dao.interf;

import java.util.Date;
import java.util.List;

import fr.treeptik.annuaire.model.pojo.Numero;
import fr.treeptik.annuaire.model.pojo.Personne;

public interface PersonneDAO extends GenericDAO<Personne, Integer>{
	
	Personne addNumToPersonne(Personne personne, Numero numero);
//	Personne addAdresseToPersonne(Personne personne, Adresse adresse);
	List<Personne> getPersonneByFirstLastName(String nom, String prenom);
	Personne updateNPD(Personne p, String nom, String prenom, Date dateNaissance);
	Personne getNumerosByPersonne(Personne personne);
	Personne getAdresseByPersonne(Personne personne);

}
