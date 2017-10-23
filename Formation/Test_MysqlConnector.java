package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class Test_MysqlConnector {

	@Test
	public void testMysqlConnector() {
		MysqlConnector bdd = new MysqlConnector();
		// ???
	}

	@Test
	public void testMysqlClose() {
		MysqlConnector bdd = new MysqlConnector();
		bdd.MysqlClose();
		// ???
	}

	@Test
	public void testMysqlCreate() {
		
		MysqlConnector bdd = new MysqlConnector();
		String[] tabVariable = new String[1];
		tabVariable[0] = "idProjet";
		bdd.MysqlCreate("projet", tabVariable);
		// ???
		bdd.MysqlClose();
	}

	@Test
	public void testMysqlInsert() {
		
		MysqlConnector bdd = new MysqlConnector();
		String[] tabVariable = new String[1];
		tabVariable[0] = "1";
		tabVariable[1] = "Test MysqlInsert Projet";
		bdd.MysqlInsert("projet", tabVariable);
		// ???
		bdd.MysqlClose();
	}

	@Test
	public void testMysqlSelect() {
		
		MysqlConnector bdd = new MysqlConnector();
		String[] tabVariable = new String[1];
		tabVariable[0] = "idProjet";
		tabVariable[1] = "label";
		ArrayList<ArrayList<String>> bddSelect = bdd.MysqlSelect("projet", tabVariable);
		ArrayList<ArrayList<String>> expecteds = null;
		ArrayList<String> ligne = null;
		ligne.add("1");
		ligne.add("Test MysqlInsert Projet");
		expecteds.add(ligne);
		Assert.assertEquals(expecteds, bddSelect);
		bdd.MysqlClose();
	}

}
