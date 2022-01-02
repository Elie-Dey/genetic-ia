//package Algo;

import java.util.ArrayList;

public class City {
	public int ID;
	public static int compteur = 0;
	public String name;
	public static ArrayList<City> List_of_cities = new ArrayList<City>();

	public City(String name) { // First City has ID = 1
		this.compteur++;
		this.ID = this.compteur;
		this.name = name;
		this.List_of_cities.add(this);
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
