package fr.treeptik.annuaire.service.impl;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.treeptik.annuaire.dao.interf.PersonneDAO;
import fr.treeptik.annuaire.exceptions.DAOException;
import fr.treeptik.annuaire.model.pojo.Personne;

public class PersonneServiceTest {
	
	@BeforeClass
	public static void shouldDoSomething(){
		
	}
	
	@Before
	public static void shoudDoOtherThing(){
		System.out.println("do the thing !");
	}

	@Test
	public static void shouldReturn2Personnes(){
		
		PersonneService personneService = new PersonneService();
		
		/*
		 * Preparation des mocks :
		 */
		
		PersonneDAO mockDao = EasyMock.mock(PersonneDAO.class);
		
		List<Personne> resultMock = Arrays.asList(new Personne("Perso1", "Prenom1"), new Personne("Perso2", "Prenom2"));
		
		//comportements du mock
		EasyMock.expect(mockDao.getPersonneByFirstLastName("Pers", "")).andReturn(resultMock);
		EasyMock.expect(mockDao.getPersonneByFirstLastName("Toto", "")).andReturn(null);

		//fige les comportements
		EasyMock.replay(mockDao);  
		
		personneService.setDao(mockDao);
		
		List<Personne> resultList = personneService.findByName("Pers");
		Assert.assertNotNull(resultList);
		Assert.assertEquals(2, resultList.size());
		
		List<Personne> resultList2 = personneService.findByName("Toto");
		Assert.assertNotNull(resultList);
		Assert.assertEquals(2, resultList.size());
		
		EasyMock.verify(mockDao);     //fail si on teste pas Toto
		
		
	}
	
//	@Test(expected=)
//	public void shouldThrowException(){
//		throw new DAOException("Erreurrrrr !! ", cause)
//	}
	
	
	/*
	 * @before : c'est fait avant chaque test de la classe de test
	 * @beforeclass : fait avant même l'instanciation de la classe
 	 * et à la fin comment tester une exception
 	 */
	
}
