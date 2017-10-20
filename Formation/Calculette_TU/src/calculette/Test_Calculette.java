package calculette;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class Test_Calculette {

	@Test
	public void testAddition() {
		Calculette Calc = new Calculette();
		float Test1 = Calc.addition(1, 3);
		Assert.assertEquals(Test1, 4, 0.00001);
		float nbA = (float) 1.3;
		float nbB = (float) 1.7;
		float Test2 = Calc.addition(nbA, nbB);
		Assert.assertEquals(Test2,3, 0.00001);
		
	}

	@Test
	public void testSoustraction() {
		Calculette Calc = new Calculette();
		float Test1 = Calc.soustraction(1, 3);

		Assert.assertEquals(Test1, -2 , 0.00001);
		float nbA = (float) 1;
		float nbB = (float) 1.7;
		float Test2 = Calc.soustraction(nbB, nbA);

		Assert.assertEquals(Test2,0.7, 0.00001);
	}
	@Test
	public void testDiviser() {
		Calculette Calc = new Calculette();
		float Test1 = Calc.diviser(10, 2);

		Assert.assertEquals(Test1,5, 0.00001);
	}

	@Test
	public void testMultiplier() {
		Calculette Calc = new Calculette();
		float Test1 = Calc.multiplier(2, 3);

		Assert.assertEquals(Test1,6, 0.00001);
		float nbA = (float) 1.5;
		float nbB = (float) 2;
		float Test2 = Calc.multiplier(nbB, nbA);

		Assert.assertEquals(Test2,3, 0.00001);
	}

}
