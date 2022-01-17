package fr.dauphine.Astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class State {
	public static int number_of_states = 0;
	public int id_state;
	public City my_city;
	public boolean intial_state = false;
	public ArrayList<City> visited_cities;
	public int distance_from_root; // distance from the initial state
	public State father; // state father so we can comeback at the end of the exploration and find the
							// path
	public static HashMap<State, Integer> exploration = new HashMap<>(); // HashMap with state as key and f the value
																			// for A* as value
	public ArrayList<Go> list_actions; // list of actions possibile from this state

	public State(City c) { // first state the root
		this.my_city = c;
		this.number_of_states++;
		this.id_state = number_of_states;
		this.visited_cities = new ArrayList<City>();
		;
		this.visited_cities.add(c); // we're in this city therefore we have already visited it
		this.intial_state = true;
		this.distance_from_root = 0;
		this.father = null; // because this state's the root
	}

	public State(City c, State father) { // first state will have ID=1
		this.my_city = c;
		this.father = father;
		this.number_of_states++;
		this.id_state = number_of_states;
		this.visited_cities = new ArrayList<City>(father.visited_cities); // same father's Arraylist<City> , just we
																			// need to add this city now
		this.visited_cities.add(c);
		this.distance_from_root = father.distance_from_root + path_cost_with_city(c, father.my_city); // distance equal
																										// to distance
																										// from the
																										// intial state
																										// to the faher
																										// + the cost of
																										// the arc
	}

	public void print_visited_cities() {
		System.out.println("Visited_cities of state number [" + this.id_state + "]{");
		for (City c : this.visited_cities) {
			c.print_city();
		}
		System.out.println("}");
	}

	public void print_state() {
		System.out.println("I'm the state number: " + this.id_state + " and my city's : " + this.my_city.name + "[ID ="
				+ this.my_city.ID + "] My distance from root =" + this.distance_from_root);
	}

	public ArrayList<City> list_of_cities_possible() { // return list of cities that can be discovered
		ArrayList<City> list_cities_poss = new ArrayList<City>();
		for (City c : City.List_of_cities) { // all the cities
			if (this.visited_cities.contains(c) == false) { // this city is not visited yet
				list_cities_poss.add(c);
			}
		}
		return list_cities_poss;
	}

	public void action() {
		ArrayList<Go> set_of_actions_possible = new ArrayList<Go>();
		ArrayList<City> list_cities_possible = list_of_cities_possible(); // list of cities that can be discovered
		for (City c : list_cities_possible) {
			// c.print_city();
			int cout = path_cost_with_city(this.my_city, c); // the cost from the city of the state to this city
			set_of_actions_possible.add(new Go(c, cout)); // new possibility
		}
		// System.out.println(set_of_actions_possible.size());
		this.list_actions = set_of_actions_possible; // all the actions possible from this state
	}

	public void print_all_actions() {
		System.out.println("All actions possible from state [" + this.id_state + "]{");
		for (Go g : list_actions) {
			g.print_go();
		}
		System.out.println("}");
	}

	public static void print_all_Map_exploration() {
		System.out.println("Map Exploration{");
		for (Entry<State, Integer> entry : exploration.entrySet())
			System.out.println("f= " + entry.getValue() + " with :" + " I'm the state number: "
					+ entry.getKey().id_state + " and my city's : " + entry.getKey().my_city.name + "[ID ="
					+ entry.getKey().my_city.ID + "]");
	}

	public State results(Go g, State s) { // create new state with an action
		State a = new State(g.city_goal, s);
		// a.print_state();
		return a;
	}

	public ArrayList<State> results_action() { // create states with all the actions possibles from this state
		ArrayList<State> s = new ArrayList<State>();
		action(); // update the list_actions with all the actions possible
		// this.print_all_actions();
		for (Go g : this.list_actions) {
			s.add(results(g, this));
		}
		// this.print_visited_cities();
		return s;
	}

	public static int path_cost_with_city(City a, City b) { // cost for an arc between 2 cities
		for (Links l : Links.all_links) { // all the links or edges
			if ((a == l.c1 && b == l.c2) || (b == l.c1 && a == l.c2)) { // non orienté
				return l.distance;
			}
		}
		return -999; // error code
	}

	public static State minimum_exploration() { // null == error ; find the minimum value of f in the map and return the
												// key wich's the state of this value
		int min = Collections.min(exploration.values());
		for (Entry<State, Integer> entry : exploration.entrySet())
			if (min == entry.getValue())
				return entry.getKey();
		return null;
	}
}
