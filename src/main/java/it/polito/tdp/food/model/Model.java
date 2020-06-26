package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDAO;

public class Model {
	
	private Graph<Condiment, DefaultWeightedEdge> grafo;
	private FoodDAO dao;
	private Map<Integer, Condiment> idMap;
	private List<Condiment> best;
	private Double max;
	
	public Model() {
		this.dao = new FoodDAO();
		this.idMap = new HashMap<>();
	}
	
	public void creaGrafo(Double c) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(grafo, dao.getVertici(c, idMap));
		
		// aggiungo gli archi
		for(Adiacenza a: dao.getAdiacenze(idMap))
			if(grafo.containsVertex(a.getC1()) && grafo.containsVertex(a.getC2()) && grafo.getEdge(a.getC1(), a.getC2()) == null)
				Graphs.addEdge(grafo, a.getC1(), a.getC2(), a.getPeso());
	}
	
	public List<Ingrediente> getIngredienti(){
		List<Ingrediente> result = new ArrayList<>();
		List<Condiment> list = new ArrayList<>(grafo.vertexSet());
		Collections.sort(list);
		for(Condiment c: list) {
			int somma = 0;
			for(Condiment vicino: Graphs.neighborListOf(grafo, c))
				somma += grafo.getEdgeWeight(grafo.getEdge(c, vicino));
			result.add(new Ingrediente(c, somma));
		}
		return result;
	}
	
	public List<Condiment> trovaDieta(Condiment c){
		this.best = new ArrayList<>();
		List<Condiment> parziale = new ArrayList<>();
		this.max = 0.0;
		parziale.add(c);
		cerca(parziale, 1, c, c.getCondiment_calories());
		return best;
	}
	
	private void cerca(List<Condiment> parziale, int i, Condiment precedente, Double totCalorie) {
		if(totCalorie > max) {
			this.best = new ArrayList<>(parziale);
			max = totCalorie;
		}
		
		for(Condiment daInserire: grafo.vertexSet()) {
			if(!parziale.contains(daInserire) && grafo.getEdge(precedente, daInserire)==null) {
				parziale.add(daInserire);
				cerca(parziale, i+1, daInserire, totCalorie+daInserire.getCondiment_calories());
				parziale.remove(daInserire);
			}
		}
	}

	public Double getMax() {
		return max;
	}

	public List<Condiment> getVertici(){
		List<Condiment> list = new ArrayList<>(grafo.vertexSet());
		return list;
	}
	
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}

}
