package tests;

import org.junit.Test;
<<<<<<< HEAD
=======
import beans.*;
import java.sql.Date;
>>>>>>> master

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
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 1;
		assertEquals(expected, te.getIdTest());
>>>>>>> master
	}

	@Test
	public void testSetIdTest() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 2;
		te.setIdTest(expected);
		assertEquals(expected, te.getIdTest());
>>>>>>> master
	}

	@Test
	public void testGetDate() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Date expected = sqlDate;
		assertEquals(expected,te.getDate());
>>>>>>> master
	}

	@Test
	public void testSetDate() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		java.util.Date utilDate = new java.util.Date();
		Date expected = new java.sql.Date(utilDate.getTime());
		te.setDate(expected);
		assertEquals(expected,te.getDate());
>>>>>>> master
	}

	@Test
	public void testGetHeure() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "15:49:00";
		assertEquals(expected,te.getHeure());
>>>>>>> master
	}

	@Test
	public void testSetHeure() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "15:59:00";
		te.setHeure(expected);
		assertEquals(expected,te.getHeure());
>>>>>>> master
	}

	@Test
	public void testGetStatut() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "Passed";
		assertEquals(expected,te.getStatut());
>>>>>>> master
	}

	@Test
	public void testSetStatut() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "Failed";
		te.setStatut(expected);
		assertEquals(expected,te.getStatut());
>>>>>>> master
	}

	@Test
	public void testGetProjet() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Projet expected = p;
		assertEquals(expected,te.getProjet());
>>>>>>> master
	}

	@Test
	public void testSetProjet() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Projet expected = new Projet(2, "Projet de test unitaires");
		te.setProjet(expected);
		assertEquals(expected,te.getProjet());
>>>>>>> master
	}

	@Test
	public void testGetCampagne() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Campagne expected = c;
		assertEquals(expected,te.getCampagne());
>>>>>>> master
	}

	@Test
	public void testSetCampagne() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Campagne expected = new Campagne(2, "Campagne de test unitaire", p);
		te.setCampagne(expected);
		assertEquals(expected,te.getCampagne());
>>>>>>> master
	}

	@Test
	public void testGetTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Testeur expected = t;
		assertEquals(expected,te.getTesteur());
>>>>>>> master
	}

	@Test
	public void testSetTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Testeur expected = new Testeur(1, "Maryan");
		te.setTesteur(expected);
		assertEquals(expected,te.getTesteur());
>>>>>>> master
	}

}
