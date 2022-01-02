//package Algo;

public class Main {

	public static void main(String[] args) {
		City a = new City("A");
		City b = new City("B");
		City c = new City("C");
		City d = new City("D");
		City e = new City("E");
		Links l1 = new Links(a, 21, b); // Paris -[3]- Newyork
		Links l2 = new Links(a, 9, c);
		Links l3 = new Links(a, 21, d);
		Links l4 = new Links(a, 1, e);
		Links l5 = new Links(b, 4, c);
		Links l6 = new Links(b, 8, d);
		Links l7 = new Links(b, 20, e);
		Links l8 = new Links(c, 6, d);
		Links l9 = new Links(c, 7, e);
		Links l10 = new Links(d, 11, e);
		// Links.print_all_links();
		// City.print_all_cities();
		A_star algo = new A_star(a);
	}

}
