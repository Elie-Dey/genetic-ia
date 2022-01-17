package fr.dauphine.Astar;

import java.util.ArrayList;

public class City {
	public int ID;
	public static int compteur = 0;
	private int numberOfCity;
	public String name;
	double coordX, coordY;
	public static ArrayList<City> List_of_cities = new ArrayList<City>();

	public City(String name) { // First City has ID = 1
		this.compteur++;
		this.ID = this.compteur;
		this.name = name;
		this.List_of_cities.add(this);
	}

	public City(double coordX, double coordY, int numberOfCity, String name) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.numberOfCity = numberOfCity;
		this.ID = numberOfCity;
		this.name = name;
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	@Override
	public String toString() {
		return "City [" + coordX + "," + coordY + ", numberOfCity=" + numberOfCity + "]";
	}

	public void print_city() {
		System.out.println("I'm " + this.name + " and my ID :" + this.ID);
	}

	public static void print_all_cities() {
		System.out.println("All cities{");
		for (City c : City.List_of_cities) {
			c.print_city();
		}
		System.out.println("}");
	}

}
