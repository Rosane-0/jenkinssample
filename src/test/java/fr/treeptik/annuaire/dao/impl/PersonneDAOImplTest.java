package fr.treeptik.annuaire.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.treeptik.annuaire.dao.interf.DAOFactory;
import fr.treeptik.annuaire.dao.interf.PersonneDAO;
import fr.treeptik.annuaire.model.pojo.Personne;
import fr.treeptik.annuaire.utils.ContextPersistanceUtils;

public class PersonneDAOImplTest {
	
	@Test
	public static void findByFirstLastNameShoudReturn10(){
		
		ContextPersistanceUtils.getEntityManager();
		
		PersonneDAO personneDAO = DAOFactory.getPersonneDAO();
		
		List<Personne> personnes = personneDAO.getPersonneByFirstLastName("Last", "First");
		
		Assert.assertNotNull(personnes);
		Assert.assertEquals(10, personnes.size());
		
	}
}
