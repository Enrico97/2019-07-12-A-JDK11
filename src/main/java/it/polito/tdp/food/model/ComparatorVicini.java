package it.polito.tdp.food.model;

import java.util.Comparator;

public class ComparatorVicini implements Comparator<vicini> {

	@Override
	public int compare(vicini o1, vicini o2) {
		int x=0;
		if (o1.getPeso()-o2.getPeso()>0)
			x=-1;
		if (o1.getPeso()-o2.getPeso()==0)
			x=0;
		if (o1.getPeso()-o2.getPeso()<0)
			x=1;
		return x;
	}

	
	
}
