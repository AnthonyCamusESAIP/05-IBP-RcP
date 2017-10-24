package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import beans.MysqlConnector;
import org.junit.Test;

public class TEST_MysqlConnector {

	@Test
	public void testMysqlConnector() {
		MysqlConnector bdd = new MysqlConnector();
		MysqlConnector expected = new MysqlConnector();
		assertEquals(expected, bdd);
		bdd.MysqlClose();
	}
	@Test
	public void testMysqlClose() {

		MysqlConnector bdd = new MysqlConnector();
		bdd.MysqlClose();
		assertEquals(null, bdd);
	}

	@Test
	public void testMysqlInsert() {
		
		MysqlConnector bdd = new MysqlConnector();
		bdd.MysqlClose();
	}

	@Test
	public void testMysqlSelectRequete() {
		

		MysqlConnector bdd = new MysqlConnector();
		
		/* test 1 Requete */
		String req = "SELECT projet.idProduit, projet.label, campagne.label FROM campagne NATURAL JOIN projet WHERE idProduit == 1 AND label LIKE '%P' ;";
		List<String> table = new ArrayList<String>();
		List<String> variable = new ArrayList<String>();
		List<String> condition = new ArrayList<String>();
		table.add("campagne");
		table.add("projet");
		variable.add("projet.idProduit");
		variable.add("projet.label");
		variable.add("campagne.label");
		
		condition.add("idProduit == 1");
		condition.add("label LIKE '%P'");
		assertEquals(req, bdd.MysqlSelectRequete(table, variable, condition));
		
		/* test 2 */
		req = "SELECT projet.idProduit FROM produit ;";
		table = new ArrayList<String>();
		variable = new ArrayList<String>();
		condition = null;
		variable.add("projet.idProduit");
		table.add("produit");
		assertEquals(req, bdd.MysqlSelectRequete(table, variable, condition));
		
		

		bdd.MysqlClose();
	}
	
	@Test
	public void testMysqlSelect() {
		

		MysqlConnector bdd = new MysqlConnector();
		
		/* test 1 Select */
		List<String> table = new ArrayList<String>();
		List<String> variable = new ArrayList<String>();
		List<String> condition = null;
		variable.add("projet.idProjet");
		variable.add("projet.label");
		table.add("projet");
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<String> d2 = new ArrayList<String>();
		d.add("1");
		d.add("Projet Test 1");
		expected.add(d);
		d2.add("2");
		d2.add("Projet Test 2");
		expected.add(d2);
		
		assertEquals(expected, bdd.MysqlSelect(table, variable, condition));
		
		/* test 2 */

		table = new ArrayList<String>();
		variable = new ArrayList<String>();
		condition = null;
		table.add("campagne");
		table.add("projet");
		variable.add("projet.idProduit");
		variable.add("projet.label");
		variable.add("campagne.label");
		
		condition.add("idProduit == 1");
		condition.add("label LIKE '%P'");
		
		expected = new ArrayList<ArrayList<String>>();
		d = new ArrayList<String>();
		d2 = new ArrayList<String>();
		d.add("1");
		d.add("Projet Test 1");
		expected.add(d);
		d2.add("2");
		d2.add("Projet Test 2");
		expected.add(d2);
		
		assertEquals(expected, bdd.MysqlSelect(table, variable, condition));
		bdd.MysqlClose();
	}

}
