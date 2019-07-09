package PT2017.demo.DemoProject;
import java.util.*;

public class Monom implements Comparable<Monom> {
	
	public float coefficient;
	public int degree;
	
	Monom(int degree, float coefficient) {
		this.coefficient = coefficient;
		this.degree = degree;
	}
	
	
	public float getCoefficient() {
		return coefficient;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void setDegree(int x) {
		degree = x;
	}
	
	public void setCoefficient(float y) {
		coefficient = y;
	}
	
	public void setMonom(int d, float c) {
		degree = d;
		coefficient = c;
	}
	
	public int compareTo(Monom other) {
		Integer i = new Integer(this.getDegree());
		return i.compareTo(other.getDegree()) * (-1);
	}
}
