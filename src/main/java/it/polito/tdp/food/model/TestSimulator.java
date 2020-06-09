package it.polito.tdp.food.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestSimulator {

	static Model model = new Model();
	static Graph<Food, DefaultWeightedEdge> grafo;
	
	public static void main(String[] args) {
		Simulator s = new Simulator(grafo, model);
		Food f = null;
		for (Food ff : model.creaGrafo(4).vertexSet()) {
			if (ff.getDisplay_name().compareTo("Raw sweet pepper (bell pepper)")==0)
				f=ff;
		}
		s.run(model.creaGrafo(4), f, 4, 4);
	}

}
