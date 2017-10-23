package tests;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import beans.*;

public class TEST_Projet extends TestCase{

	Projet p = new Projet(1, "Projet de test");
	@Test
	public void testGetIdProjet() {
		int expected = 1;
		assertEquals(expected, p.getIdProjet());
	}

	@Test
	public void testSetIdProjet() {
		int expected = 1;
		p.setIdProjet(expected);
		assertEquals(expected, p.getIdProjet());
	}

	@Test
	public void testGetLabel() {
		String expected ="Projet de test";
		assertEquals(expected, p.getLabel());
	}

	@Test
	public void testSetLabel() {
		String expected ="Projet de test unitaire";
		p.setLabel(expected);
		assertEquals(expected, p.getLabel());
	}

}
