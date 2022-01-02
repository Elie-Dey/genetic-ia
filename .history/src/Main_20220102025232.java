import java.util.ArrayList;
import java.util.Scanner;

//package Algo;

public class Main {

	public static void main(String[] args) {
		// City a = new City("A");
		// City b = new City("B");
		// City c = new City("C");
		// City d = new City("D");
		// City e = new City("E");

		// Links l1 = new Links(a, 1, b); // Paris -[3]- Newyork
		// Links l2 = new Links(a, 9, c);
		// Links l3 = new Links(a, 21, d);
		// Links l4 = new Links(a, 1, e);
		// Links l5 = new Links(b, 4, c);
		// Links l6 = new Links(b, 3, d);
		// Links l7 = new Links(b, 5, e);
		// Links l8 = new Links(c, 6, d);
		// Links l9 = new Links(c, 7, e);
		// Links l10 = new Links(d, 11, e);
		// // Links.print_all_links();
		// // City.print_all_cities();
		// A_star algo = new A_star(d);

		// Test generation de ville aleatoirement
		// Entrer le nombre de ville
		// Creer des distance entre chaque ville sachant que la ville 1 a n-1 liens la
		// ville 2 n-2
		// Choisir la ville de depart de l'algo

		ArrayList<City> listCities = new ArrayList<City>();
		ArrayList<Links> listLinks = new ArrayList<Links>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of city");
		int numberOfCity = sc.nextInt();

		for (int index = 0; index <= numberOfCity; index++) {
			City a = new City("Ville numero " + index);
			listCities.add(a);
		}
		System.out.println(listCities);
		int indexCity = 1;
		while (numberOfCity > 0) {
			double x = Math.random() * 10;
			Links link = new Links(listCities.get(0), (int) x, listCities.get(indexCity));
			listLinks.add(link);
			numberOfCity--;
			indexCity++;
		}
		indexCity = 2;
		while (numberOfCity > 0) {
			double x = Math.random() * 10;
			Links link = new Links(listCities.get(1), (int) x, listCities.get(indexCity));
			listLinks.add(link);
			numberOfCity--;
			indexCity++;
		}

		listCities.get(0).print_all_cities();
		System.out.println("\n");
		listLinks.get(0).print_all_links();
	}

}
