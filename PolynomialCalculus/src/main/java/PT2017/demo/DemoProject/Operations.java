package PT2017.demo.DemoProject;

public class Operations {
	
	Operations() {}
	
	public Polinom addPol(Polinom pol1, Polinom pol2) {
		Polinom res1 = new Polinom();
		Polinom res2 = new Polinom();
		Polinom res = new Polinom();
		for (Monom i : pol1.pol) {
			res1.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : pol2.pol) {
			res2.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : res1.pol) {
			for (Monom j : res2.pol) {
				if (i.getDegree() == j.getDegree()) {
					i.setCoefficient(i.getCoefficient() + j.getCoefficient());
					j.setDegree(-1);
				}
			}
		}
		for (Monom j : res2.pol) {
			if (j.getDegree() != -1) {
				res1.addMonom(j.getDegree(), j.getCoefficient());
			}
		}
		for (Monom i : res1.pol) {
			if (i.getCoefficient() != 0f) {
				res.addMonom(i.getDegree(), i.getCoefficient());
			}
		}
		return res;
	}
	
	public Polinom subPol(Polinom pol1, Polinom pol2) {
		Polinom res1 = new Polinom();
		Polinom res2 = new Polinom();
		Polinom res = new Polinom();
		for (Monom i : pol1.pol) {
			res1.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : pol2.pol) {
			res2.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : res1.pol) {
			for (Monom j : res2.pol) {
				if (i.getDegree() == j.getDegree()) {
					i.setCoefficient(i.getCoefficient() - j.getCoefficient());
					j.setDegree(-1);
				}
			}
		}
		for (Monom j : res2.pol) {
			if (j.getDegree() != -1) {
				res1.addMonom(j.getDegree(), j.getCoefficient());
			}
		}
		for (Monom i : res1.pol) {
			if (i.getCoefficient() != 0f) {
				res.addMonom(i.getDegree(), i.getCoefficient());
			}
		}
		return res;
	}
	
	public Polinom mulPol(Polinom pol1, Polinom pol2) {
		Polinom res = new Polinom();
		Polinom res1 = new Polinom();
		for (Monom i : pol1.pol) {
			for (Monom j : pol2.pol) {
				res.addMonom(i.getDegree() + j.getDegree(), i.getCoefficient() * j.getCoefficient());
			}
		}
		return res;
	}
	
	public Polinom derive(Polinom pol1) {
		Polinom res = new Polinom();
		Polinom res1 = new Polinom();
		for (Monom i : pol1.pol) {
			res.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : res.pol) {
			i.setCoefficient(i.getCoefficient() * i.getDegree());
			i.setDegree(i.getDegree() - 1);
		}
		for (Monom i : res.pol) {
			if (i.getCoefficient() != 0f) {
				res1.addMonom(i.getDegree(), i.getCoefficient());
			}
		}
		return res1;
	}
	
	public Polinom integrate(Polinom pol1) {
		Polinom res = new Polinom();
		Polinom res1 = new Polinom();
		for (Monom i : pol1.pol) {
			res.addMonom(i.getDegree(), i.getCoefficient());
		}
		for (Monom i : res.pol) {
			i.setCoefficient(i.getCoefficient() / (i.getDegree() + 1));
			i.setDegree(i.getDegree() + 1);
		}
		for (Monom i : res.pol) {
			if (i.getCoefficient() != 0f) {
				res1.addMonom(i.getDegree(), i.getCoefficient());
			}
		}
		return res1;
	}
}

