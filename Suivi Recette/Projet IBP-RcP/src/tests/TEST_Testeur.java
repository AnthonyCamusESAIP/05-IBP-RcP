package tests;

import org.junit.jupiter.api.Test;
import beans.*;

import junit.framework.TestCase;

public class TEST_Testeur extends TestCase{

	Testeur t = new Testeur(1, "Anthony");
	@Test
	public void testGetIdTesteur() {
		int expected = 1;
		assertEquals(expected, t.getIdTesteur());
	}

	@Test
	public void testSetIdTesteur() {
		int expected = 2;
		t.setIdTesteur(expected);
		assertEquals(expected, t.getIdTesteur());
	}

	@Test
	public void testGetNomTesteur() {
		String expected = "Anthony";
		assertEquals(expected, t.getNomTesteur());
	}

	@Test
	public void testSetNomTesteur() {
		String expected = "Maryan";
		t.setNomTesteur(expected);
		assertEquals(expected, t.getNomTesteur());
	}

}
