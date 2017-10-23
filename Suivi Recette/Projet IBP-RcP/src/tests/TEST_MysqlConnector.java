package tests;

import static org.junit.Assert.*;
import beans.MysqlConnector;
import org.junit.Test;

public class TEST_MysqlConnector {

	@Test
	public void testMysqlConnector() {
		MysqlConnector bdd = new MysqlConnector();
		MysqlConnector expected = new MysqlConnector();
		assertEquals(expected, bdd);
	}

	@Test
	public void testMysqlClose() {

		MysqlConnector bdd = new MysqlConnector();
		bdd.MysqlClose();
		assertEquals(null, bdd);
	}

	@Test
	public void testMysqlCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testMysqlInsert() {
		
		MysqlConnector bdd = new MysqlConnector();
		String[] listeDonnee = {"1","Projet1"};
		assertEquals(1, bdd.MysqlInsert("produit", listeDonnee));
	}

	@Test
	public void testMysqlSelect() {
		fail("Not yet implemented");
	}

}
