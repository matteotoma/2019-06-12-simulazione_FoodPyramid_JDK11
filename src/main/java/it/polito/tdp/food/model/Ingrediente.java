package it.polito.tdp.food.model;

public class Ingrediente {
	
	private Condiment c;
	private Integer peso;
	
	public Ingrediente(Condiment c, Integer peso) {
		super();
		this.c = c;
		this.peso = peso;
	}

	public Condiment getC() {
		return c;
	}

	public void setC(Condiment c) {
		this.c = c;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
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
		Ingrediente other = (Ingrediente) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

}
