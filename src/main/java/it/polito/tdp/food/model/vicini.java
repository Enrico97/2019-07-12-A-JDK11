package it.polito.tdp.food.model;

public class vicini {

	private Food f1;
	private double peso;
	
	public vicini(Food f1, double peso) {
		super();
		this.f1 = f1;
		this.peso = peso;
	}

	public Food getF1() {
		return f1;
	}

	public double getPeso() {
		return peso;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		vicini other = (vicini) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return f1.getDisplay_name() + " " + peso;
	}
	
}
