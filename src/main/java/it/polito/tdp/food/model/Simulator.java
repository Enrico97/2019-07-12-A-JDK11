package it.polito.tdp.food.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;


public class Simulator {

	private PriorityQueue<Event> queue = new PriorityQueue<>();
	private Graph<Food, DefaultWeightedEdge> grafo;
	private Model model;
	private List<Food> cibi = new ArrayList<>();
	
	private int cibiPreparati;
	private Duration tot;
	int y;
	
	public int getCibiPreparati() {
		return cibiPreparati;
	}
	public Duration getTot() {
		return tot;
	}
	
	public Simulator (Graph<Food, DefaultWeightedEdge> grafo, Model model) {
		this.grafo=grafo;
		this.model=model;
	}
	
	public void run(Graph<Food, DefaultWeightedEdge> grafo, Food food, int id, int k) {
		this.cibiPreparati=0;
		this.tot=null;
		y=k;
		this.queue.clear();
		this.cibi.clear();
		
		for (vicini v : model.simulaCalorieMax(food, k)) {
			if(!cibi.contains(v.getF1())) {
				Event ee = new Event(Duration.of((long) v.getPeso(), ChronoUnit.MINUTES), v.getF1(), k);
				queue.add(ee);
				cibi.add(v.getF1());
				y--;
			//	System.out.println(ee);
		}}
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
	}
	}
	
	private void processEvent(Event e) {
			y++;
			cibiPreparati++;
			tot=e.getTime();
			for (vicini v : model.simulaCalorieMax(e.getFood(), e.getK())) {
				if (!cibi.contains(v.getF1()) && y>0) {
					cibi.add(v.getF1());
					Event ee = new Event(e.getTime().plusMinutes((long) v.getPeso()), v.getF1(), e.getK());
					queue.add(ee);
					y--;
				//	System.out.println(ee);
				}
			}
	}
}
