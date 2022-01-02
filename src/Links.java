//package Algo;

import java.util.ArrayList;

public class Links {
	public City c1;
	public City c2;
	public int distance;
	public static ArrayList<Links> all_links = new ArrayList<Links>();

	public Links(City c1, int distance, City c2) { // graph non orienté
		this.c1 = c1;
		this.c2 = c2;
		this.distance = distance;
		this.all_links.add(this);
	}

	public static void print_all_links() {
		System.out.println("All Links{");
		for (Links l : Links.all_links) {
			System.out.println(
					l.c1.name + "[ID=" + l.c1.ID + "] ---[d=" + l.distance + "]" + l.c2.name + "[ID=" + l.c2.ID + "]");
		}
		System.out.println("}");
	}
}
