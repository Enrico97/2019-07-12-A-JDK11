package it.polito.tdp.food.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.Adiacenza;
import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	FoodDao dao = new FoodDao();
	Graph<Food, DefaultWeightedEdge> grafo;
	Simulator s = new Simulator(grafo, this);
	
	public Graph<Food, DefaultWeightedEdge> creaGrafo(int id) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.vertici(id));
		for (Adiacenza a : dao.archi()) {
			if (grafo.vertexSet().contains(a.getF1()) && grafo.vertexSet().contains(a.getF2()) && !(a.getF1().equals(a.getF2()))) {
				Graphs.addEdge(grafo, a.getF1(), a.getF2(), a.getPeso());
			}
		}
		return grafo;
	}
	
	public List<vicini> calorieMax(Food food) {
		int x=0;
		List<vicini> list = new ArrayList<>();
		List<vicini> risultato = new ArrayList<>();
		for (Food f : Graphs.neighborListOf(grafo, food)) {
			list.add(new vicini(f, grafo.getEdgeWeight(grafo.getEdge(food, f))));
		}
		Collections.sort(list, new ComparatorVicini());
		for (vicini v : list) {
			if (x<5) {
				risultato.add(v);
				x++;
		}}
		return risultato;
	}

	public List<vicini> simulaCalorieMax(Food food, int k) {
		int x=0;
		List<vicini> list = new ArrayList<>();
		List<vicini> risultato = new ArrayList<>();
		for (Food f : Graphs.neighborListOf(grafo, food)) {
			list.add(new vicini(f, grafo.getEdgeWeight(grafo.getEdge(food, f))));
		}
		Collections.sort(list, new ComparatorVicini());
		for (vicini v : list) {
			if (x<k) {
				risultato.add(v);
				x++;
		}}
		return risultato;
	}
	
	public void simula(Graph<Food, DefaultWeightedEdge> grafo, Food food, int id, int k) {
		s.run(grafo, food, id, k);
	}
	
	public int cibiPreparati() {
		return s.getCibiPreparati();
	}

	public double tempoTotale() {
		return (s.getTot().getSeconds())/60;
	}
}
