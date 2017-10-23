package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import beans.*;

public class TEST_ReaderExcel {

	beans.ReaderExcel excel = new ReaderExcel();
	
	
	@Test
	public void testSetNameFile() {
		String expected = "nom";
		excel.setNameFile(expected);
		assertEquals(expected,excel.getNameFile());
		expected = "double test";
		assertEquals(expected,excel.getNameFile());
	}

	@Test
	public void testGetNameFile() {
		String expected = "file";
		excel.setNameFile(expected);
		assertEquals(expected,excel.getNameFile());
	}

	@Test
	public void testGetNameFeuille() {
		String expected = "doc";
		excel.setNameFeuille(expected);
		assertEquals(expected,excel.getNameFeuille());
	}

	@Test
	public void testSetNameFeuille() {
		String expected = "setting";
		excel.setNameFeuille(expected);
		assertEquals(expected,excel.getNameFeuille());
	}
	
/*
	@Test
	public void testInitReader() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testRead() {
		excel.setNameFeuille("Query1");
		excel.setNameFile("Listetest.xls");
		excel.initReader();
		ReaderExcel expected = new ReaderExcel();
		expected.equals(excel);
		
		//asssertEquals(expected.read(),excel.read());
	}
*/

	@Test
	public void testReadExcel() {
		excel.setNameFeuille("Query1");
		excel.setNameFile("Listetest.xls");
		excel.initReader();
		ReaderExcel expected = new ReaderExcel();
		expected.equals(excel);
		
		assertEquals(expected.ReadExcel(),excel.ReadExcel());
	}

}
