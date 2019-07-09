package PT2017.demo.DemoProject;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolJUnit {

	@Test
	public void testAdd() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Operations o = new Operations();
		p1.addMonom(1, 1);
		p1.addMonom(0, 1);
		p2.addMonom(0, 1);
		Polinom res = new Polinom();
		res.addMonom(1,1);
		res.addMonom(0, 2);
		Polinom res2 = new Polinom();
		res2 = o.addPol(p1, p2);
		if (res.pol.size() != res2.pol.size()) {
			assertEquals(1,0);
		}
		else {
			for (int i=0; i<res.pol.size(); i++)
			{
				assertEquals((int)res.pol.get(i).getCoefficient(), (int)res2.pol.get(i).getCoefficient());
				assertEquals(res.pol.get(i).getDegree(),res2.pol.get(i).getDegree());
			}
		}
	}
	
	@Test
	public void testSub() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Operations o = new Operations();
		p1.addMonom(1, 1);
		p1.addMonom(0, 1);
		p2.addMonom(0, 1);
		Polinom res = new Polinom();
		res.addMonom(1,1);
		Polinom res2 = new Polinom();
		res2 = o.subPol(p1, p2);
		if (res.pol.size() != res2.pol.size()) {
			assertEquals(1,0);
		}
		else {
			for (int i=0; i<res.pol.size(); i++)
			{
				assertEquals((int)res.pol.get(i).getCoefficient(), (int)res2.pol.get(i).getCoefficient());
				assertEquals(res.pol.get(i).getDegree(),res2.pol.get(i).getDegree());
			}
		}
	}
	
	@Test
	public void testMul() {
		Polinom p1 = new Polinom();
		Polinom p2 = new Polinom();
		Operations o = new Operations();
		p1.addMonom(1, 1);
		p1.addMonom(0, 1);
		p2.addMonom(1, 1);
		Polinom res = new Polinom();
		res.addMonom(2, 1);
		res.addMonom(1, 1);
		Polinom res2 = new Polinom();
		res2 = o.mulPol(p1, p2);
		if (res.pol.size() != res2.pol.size()) {
			assertEquals(1,0);
		}
		else {
			for (int i=0; i<res.pol.size(); i++)
			{
				assertEquals((int)res.pol.get(i).getCoefficient(), (int)res2.pol.get(i).getCoefficient());
				assertEquals(res.pol.get(i).getDegree(),res2.pol.get(i).getDegree());
			}
		}
	}
	
	@Test
	public void testDer() {
		Polinom p1 = new Polinom();
		Operations o = new Operations();
		p1.addMonom(2, 3);
		p1.addMonom(1, 1);
		p1.addMonom(0, 1);
		Polinom res = new Polinom();
		res.addMonom(1,6);
		res.addMonom(0, 1);
		Polinom res2 = new Polinom();
		res2 = o.derive(p1); 
		if (res.pol.size() != res2.pol.size()) {
			assertEquals(1,0);
		}
		else {
			for (int i=0; i<res.pol.size(); i++)
			{
				assertEquals((int)res.pol.get(i).getCoefficient(), (int)res2.pol.get(i).getCoefficient());
				assertEquals(res.pol.get(i).getDegree(),res2.pol.get(i).getDegree());
			}
		}	
	}
	
	@Test
	public void testInt() {
		Polinom p1 = new Polinom();
		Operations o = new Operations();
		p1.addMonom(2, 3);
		p1.addMonom(1, 2);
		p1.addMonom(0, 1);
		Polinom res = new Polinom();
		res.addMonom(3, 1);
		res.addMonom(2, 1);
		res.addMonom(1, 1);
		Polinom res2 = new Polinom();
		res2 = o.integrate(p1);
		if (res.pol.size() != res2.pol.size())
			assertEquals(1,0);
		else 
			for (int i=0; i<res.pol.size(); i++)
			{
				assertEquals((int)res.pol.get(i).getCoefficient(), (int)res2.pol.get(i).getCoefficient());
				assertEquals(res.pol.get(i).getDegree(),res2.pol.get(i).getDegree());
			}
	}	
}
