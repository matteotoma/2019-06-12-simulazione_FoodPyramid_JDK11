package it.polito.tdp.food.model;

public class Adiacenza {
	
	private Condiment c1;
	private Condiment c2;
	private Integer peso;
	
	public Adiacenza(Condiment c1, Condiment c2, Integer peso) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.peso = peso;
	}

	public Condiment getC1() {
		return c1;
	}

	public void setC1(Condiment c1) {
		this.c1 = c1;
	}

	public Condiment getC2() {
		return c2;
	}

	public void setC2(Condiment c2) {
		this.c2 = c2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
