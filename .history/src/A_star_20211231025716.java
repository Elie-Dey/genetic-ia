//package Algo;

import java.util.ArrayList;
import java.util.List;

public class A_star {

	public A_star(City c) {
		State Initial_state = new State(c); // intial_state
		int f_0 = heuristic();
		ArrayList<State> close_state = new ArrayList<State>(); // list_of_closed_state
		State.exploration.put(Initial_state, f_0); // put the first state in the Hashmap exploration
		int compteur = 0;
		State minimum_state_exploration;

		while (true) {
			compteur++;

			minimum_state_exploration = State.minimum_exploration(); // we find the state with the minimum f
			// minimum_state_exploration.print_state();
			if (minimum_state_exploration.visited_cities.size() == City.List_of_cities.size())
				break;

			close_state.add(minimum_state_exploration); // this state is now closed
			State.exploration.remove(minimum_state_exploration); // delete this state from the exploration HashMap
																	// because this state's close now

			ArrayList<State> results_of_the_action = minimum_state_exploration.results_action(); // all the states that
																									// can be explored
																									// with all the
																									// actions possible

			for (State s : results_of_the_action) { // this new states
				int f = s.distance_from_root + heuristic();
				State.exploration.put(s, f); // we add this state to the Hashmap exploration
			}

		}
		System.out.println("------");
		minimum_state_exploration.print_state();
		String path = "";
		for (City m : minimum_state_exploration.visited_cities) {
			path += m.name + ";";
		}
		path += Initial_state.my_city.name;
		System.out.println(path);

	}

	public int heuristic() {
		return 0;
	}
}
