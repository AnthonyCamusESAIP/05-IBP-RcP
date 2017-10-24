package tests;

import org.junit.Test;

import junit.framework.TestCase;
import beans.*;

public class TEST_Projet extends TestCase{

	Projet p = new Projet(1, "Projet de test");
	@Test
	public void testGetIdProjet() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 1;
		assertEquals(expected, p.getIdProjet());
>>>>>>> master
	}

	@Test
	public void testSetIdProjet() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 1;
		p.setIdProjet(expected);
		assertEquals(expected, p.getIdProjet());
>>>>>>> master
	}

	@Test
	public void testGetLabel() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected ="Projet de test";
		assertEquals(expected, p.getLabel());
>>>>>>> master
	}

	@Test
	public void testSetLabel() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected ="Projet de test unitaire";
		p.setLabel(expected);
		assertEquals(expected, p.getLabel());
>>>>>>> master
	}

}
