package fr.dauphine;

import java.util.Scanner;

import fr.dauphine.Astar.LauncherAstar;
import fr.dauphine.Genetic.LauncherGenetic;

public class Main {

    public static void main(String[] args) {
        System.out.println("IA PROJECT");

        System.out.println("Choix de l'algorithme : ");
        System.out.println("1- Informed Search  : A* ");
        System.out.println("2- Local Search  : Genetic algorithm ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {

            LauncherAstar.Launch();
        } else if (choice == 2) {
            // launcherGenetic = new LauncherGenetic();
            LauncherGenetic.Launch();
        } else {
            System.out.println("Choice not valid");
        }

    }
}
