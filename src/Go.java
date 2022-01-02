//package Algo;

public class Go {
	public City city_goal;
	public int cout;

	public Go(City c, int cout) {
		this.city_goal = c;
		this.cout = cout;
	}

	public void print_go() {
		System.out.println("Go(" + this.city_goal.name + "[" + this.city_goal.ID + "]" + ")--cost:[" + this.cout + "]");
	}
}
