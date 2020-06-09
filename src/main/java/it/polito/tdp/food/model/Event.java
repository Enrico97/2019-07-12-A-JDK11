package it.polito.tdp.food.model;

import java.time.Duration;

public class Event implements Comparable <Event>{
	
	public enum EventType {
		PreparaFood;
	}

	private Duration time;
	private Food food;
	private int k;
	
	public Event(Duration duration, Food food, int k) {
		super();
		this.time = duration;
		this.food = food;
		this.k = k;
	}
	
	public int getK() {
		return k;
	}

	public Duration getTime() {
		return time;
	}
	public Food getFood() {
		return food;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + k;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Event other = (Event) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (k != other.k)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return time + " - " + food.getDisplay_name();
	}
	@Override
	public int compareTo(Event o) {
		return this.getTime().compareTo(o.getTime());
	}
	
	
	
}
