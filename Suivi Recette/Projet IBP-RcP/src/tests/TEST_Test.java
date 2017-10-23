package tests;

import org.junit.jupiter.api.Test;
import beans.*;
import java.sql.Date;

import junit.framework.TestCase;

public class TEST_Test extends TestCase{
	
	Projet p = new Projet(1, "Projet de test");
	Campagne c = new Campagne(1, "Campagne de test", p);
	Testeur t = new Testeur(1, "Anthony");
	java.util.Date utilDate = new java.util.Date();
	Date sqlDate = new java.sql.Date(utilDate.getTime());
	beans.Test te = new beans.Test(1,sqlDate,"15:49:00","Passed",p,c,t);
	
	@Test
	public void testGetIdTest() {
		int expected = 1;
		assertEquals(expected, te.getIdTest());
	}

	@Test
	public void testSetIdTest() {
		int expected = 2;
		te.setIdTest(expected);
		assertEquals(expected, te.getIdTest());
	}

	@Test
	public void testGetDate() {
		Date expected = sqlDate;
		assertEquals(expected,te.getDate());
	}

	@Test
	public void testSetDate() {
		java.util.Date utilDate = new java.util.Date();
		Date expected = new java.sql.Date(utilDate.getTime());
		te.setDate(expected);
		assertEquals(expected,te.getDate());
	}

	@Test
	public void testGetHeure() {
		String expected = "15:49:00";
		assertEquals(expected,te.getHeure());
	}

	@Test
	public void testSetHeure() {
		String expected = "15:59:00";
		te.setHeure(expected);
		assertEquals(expected,te.getHeure());
	}

	@Test
	public void testGetStatut() {
		String expected = "Passed";
		assertEquals(expected,te.getStatut());
	}

	@Test
	public void testSetStatut() {
		String expected = "Failed";
		te.setStatut(expected);
		assertEquals(expected,te.getStatut());
	}

	@Test
	public void testGetProjet() {
		Projet expected = p;
		assertEquals(expected,te.getProjet());
	}

	@Test
	public void testSetProjet() {
		Projet expected = new Projet(2, "Projet de test unitaires");
		te.setProjet(expected);
		assertEquals(expected,te.getProjet());
	}

	@Test
	public void testGetCampagne() {
		Campagne expected = c;
		assertEquals(expected,te.getCampagne());
	}

	@Test
	public void testSetCampagne() {
		Campagne expected = new Campagne(2, "Campagne de test unitaire", p);
		te.setCampagne(expected);
		assertEquals(expected,te.getCampagne());
	}

	@Test
	public void testGetTesteur() {
		Testeur expected = t;
		assertEquals(expected,te.getTesteur());
	}

	@Test
	public void testSetTesteur() {
		Testeur expected = new Testeur(1, "Maryan");
		te.setTesteur(expected);
		assertEquals(expected,te.getTesteur());
	}

}
