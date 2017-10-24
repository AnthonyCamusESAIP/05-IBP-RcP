package tests;

import org.junit.Test;
<<<<<<< HEAD
=======
import beans.*;
>>>>>>> master

import junit.framework.TestCase;

public class TEST_Testeur extends TestCase{

	Testeur t = new Testeur(1, "Anthony");
	@Test
	public void testGetIdTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 1;
		assertEquals(expected, t.getIdTesteur());
>>>>>>> master
	}

	@Test
	public void testSetIdTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		int expected = 2;
		t.setIdTesteur(expected);
		assertEquals(expected, t.getIdTesteur());
>>>>>>> master
	}

	@Test
	public void testGetNomTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "Anthony";
		assertEquals(expected, t.getNomTesteur());
>>>>>>> master
	}

	@Test
	public void testSetNomTesteur() {
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		String expected = "Maryan";
		t.setNomTesteur(expected);
		assertEquals(expected, t.getNomTesteur());
>>>>>>> master
	}

}
