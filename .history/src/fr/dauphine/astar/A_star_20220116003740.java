package Algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class A_star {
	
	public A_star(City c) {
		State Initial_state = new State(c); //intial_state
		int f_0 = heuristic(Initial_state,Initial_state.my_city); 
		//System.out.println("f0="+f_0); //h0=f0
		ArrayList<State> close_state = new ArrayList<State>(); //list_of_closed_state
		State.exploration.put(Initial_state,f_0); // put the first state in the Hashmap exploration 
		//int compteur = 0;
		State minimum_state_exploration;
	
		while(true) {
			//compteur++;
	
			minimum_state_exploration = State.minimum_exploration(); //we find the state with the minimum f
			//minimum_state_exploration.print_state();
			if(minimum_state_exploration.visited_cities.size()==City.List_of_cities.size())break;	
			
			close_state.add(minimum_state_exploration); // this state is now closed
			State.exploration.remove(minimum_state_exploration); //delete this state from the exploration HashMap because this state's close now
			
			
			ArrayList<State> results_of_the_action = minimum_state_exploration.results_action(); //all the states that can be explored with all the actions possible
			//if(compteur==1)minimum_state_exploration.print_all_actions();
			
			for(State s : results_of_the_action) { //this new states
				//System.out.println("["+s.my_city.name+"]");
				int h = heuristic(s,Initial_state.my_city);
				int f = s.distance_from_root+h;
				//System.out.println(s.my_city.name+" "+f+" "+h);
				
				State.exploration.put(s,f); // we add this state to the Hashmap exploration 
				
			}
			//System.out.println("-------");
			//break;
			
		}
		//System.out.println("------");
		//minimum_state_exploration.print_state();
		String path = "";
		for(City m : minimum_state_exploration.visited_cities) {
			path+=m.name+";";
		}
		path+=Initial_state.my_city.name;
		System.out.println("Cycle Hamiltonien de coût min : "+path);
		//System.out.println("------");
	}
		
	public int heuristic(State a,City ville_initial) {
		City ville_actuel = a.my_city;
		ArrayList<City> ville_non_visite = a.list_of_cities_possible(); // cities that have not yet visited
		
		if(ville_actuel==ville_initial) {
			ville_non_visite.add(ville_actuel);
		}else {
			ville_non_visite.add(ville_actuel);
			ville_non_visite.add(ville_initial);
		}
		//for(City c1: ville_non_visite)System.out.println(c1.name);
	
		
		HashMap<Links, Integer> prim_algo = new HashMap<>();
		
		ArrayList<City> ville_visite_by_prim = new ArrayList<City>();
		ville_visite_by_prim.add(ville_actuel); //prim algo starts at the actual city
		
		City var_city = ville_actuel;
		ArrayList<Links> links_results_prim = new ArrayList<Links>(); // we gonna put the links that prim will chose at each step
		
		while(ville_visite_by_prim.size()<ville_non_visite.size()) { // Prim Algo 
			
			for(City c : ville_non_visite) {
				if(c==var_city)continue;
				Links l1 = Links.give_link(c,var_city); //the link between the actual city and c 
				
				if(verif_links_in(links_results_prim,l1)==false) { //we haven't explored it yet
					prim_algo.put(l1, l1.distance); //put this link in the hashmap
				}
			}
			
			Links min_links = null; //code error null
			int min = Collections.min(prim_algo.values()); //find the minimum link
			for(Entry<Links, Integer> entry: prim_algo.entrySet())
				if(min==entry.getValue()) {
					min_links=entry.getKey();
				}
			//System.out.println("min="+min_links.c1.name+" ["+min_links.distance+"] "+min_links.c2.name);
			prim_algo.remove(min_links); //delete this link from the links exploration hashmap
			links_results_prim.add(min_links); //add this link 
			
			if(min_links.c1==var_city) { //move to the other city
				var_city = min_links.c2;
			}if(min_links.c2==var_city) {
				var_city = min_links.c2;
			}
			// /!/ link where one of them is explored 
			if(is_explored(ville_visite_by_prim,min_links.c1)==true) { // we already explored the city c1 before
				var_city = min_links.c2;
			}else{ //it's the other
				var_city = min_links.c1;
			}
				
			//System.out.println(var_city.name);
			
			ville_visite_by_prim.add(var_city); //we're now exploring this new city
		}
		
		int cout_min=0;
		for(Links l : links_results_prim) {
			//System.out.println(l.c1.name+" ["+l.distance+"] "+l.c2.name);
			cout_min+=l.distance;
		}
		//System.out.println("cout= "+cout_min);
		//System.out.println("-----");
		return cout_min;
	}
	public boolean verif_links_in(ArrayList<Links> l,Links l1) {
		for(Links c : l) {
			if(c==l1)return true;
		}
		return false;
	}
	public boolean is_explored(ArrayList<City> c,City c1) {
		for(City b : c) {
			if(b==c1)return true;
		}return false;
	}
}
