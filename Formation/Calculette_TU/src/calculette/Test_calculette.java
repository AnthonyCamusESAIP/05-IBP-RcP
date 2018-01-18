package calculette;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_calculette {
	
	public Test_calculette(){
		testAddition();
		testSoustraction();
		testDiviser();
		testMultiplier();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddition() {
		Calculette calcul = new Calculette();
		float a = 1;
		float b =2;
		float expected = a+b;
		float actual = calcul.addition(a, b);
		assertEquals((double)expected, (double)actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSoustraction() {
		Calculette calcul = new Calculette();
		float a =2;
		float b =6;
		float expected = a-b;
		float actual = calcul.soustraction(a, b);
		assertEquals(expected,actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDiviser() {
		Calculette calcul = new Calculette();
		float a =8;
		float b = 75;
		float expected = a/b;
		float actual = calcul.diviser(a, b);
		assertEquals(expected,actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMultiplier() {
		Calculette calcul = new Calculette();
		float a =(float) 12.5;
		float b = (float) 2.4;
		float expected = a*b;
		float actual = calcul.multiplier(a, b);
		assertEquals(expected,actual);
		
	}

}
