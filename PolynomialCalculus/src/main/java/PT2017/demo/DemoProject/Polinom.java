package PT2017.demo.DemoProject;
import java.util.ArrayList;
import java.util.*;
import java.util.*;

public class Polinom {

	public List<Monom> pol = new ArrayList<Monom>();
	public String polinom;
	//
	public Polinom() {}
	
	public Polinom(String x) {
		polinom = x;
	}
	
	void addMonom(int grad, float coef) {
		Monom mon = new Monom(grad, coef);
		mon.setCoefficient(coef);
		mon.setDegree(grad);
		pol.add(mon);
		Collections.sort(pol);
	}
	
	void removeMonom(Monom m) {
		pol.remove(m);
	}
	
	void clearPol()
	{
		pol.clear();
	}
	
	public void showPolinom() {
		for (Monom i : pol) {
			if (i.coefficient < 0) {
				System.out.print(i.coefficient + "X^" + i.degree);
			}
			if (i.coefficient >= 0 ) {
				System.out.print("+" + i.coefficient + "X^" + i.degree);
			}
		}
	}
	
	public String toString() {
		String s = "";
		for (Monom i : pol) {
			if (i.coefficient < 0) {
				s = s + String.valueOf(i.coefficient) + "X^" + String.valueOf(i.degree);
			}
			if (i.coefficient >= 0 ) {
				s = s + "+" + String.valueOf(i.coefficient) + "X^" + String.valueOf(i.degree);
			}
		}
		return s;
	}
}
