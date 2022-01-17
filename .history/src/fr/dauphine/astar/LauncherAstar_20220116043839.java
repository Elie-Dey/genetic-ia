package fr.dauphine.Astar;

import java.util.ArrayList;
import java.util.Scanner;

public class LauncherAstar {

    public static void Launch() {
        // City Paris = new City("Paris");
        // City Marseille = new City("Marseille");
        // City Bordeaux = new City("Bordeaux");
        // City Dijon = new City("Dijon");
        // City Nantes = new City("Nantes");
        // City Nice = new City("Nice");
        // City Rennes = new City("Rennes");
        // City Lille = new City("Lille");
        // City Strasbourg = new City("Strasbourg");

        // Links l1 = new Links(Paris, 21, b);
        // Links l2 = new Links(Paris, 9, c);
        // Links l3 = new Links(Paris, 21, d);
        // Links l4 = new Links(Paris, 1, e);
        // Links l5 = new Links(Paris, 21, d);
        // Links l6 = new Links(Paris, 1, e);
        // Links l4 = new Links(Paris, 21, d);
        // Links l4 = new Links(Paris, 1, e);
        // Links l5 = new Links(Marseille, 4, c);
        // Links l6 = new Links(Marseille, 8, d);
        // Links l7 = new Links(Marseille, 20, e);
        // Links l8 = new Links(Marseille, 6, d);
        // Links l9 = new Links(Marseille, 7, e);
        // Links l10 = new Links(Bordeaux, 11, e);
        // Links l5 = new Links(Bordeaux, 4, c);
        // Links l6 = new Links(Bordeaux, 8, d);
        // Links l7 = new Links(Bordeaux, 20, e);
        // Links l8 = new Links(Bordeaux, 6, d);
        // Links l9 = new Links(Bordeaux, 7, e);
        // Links l10 = new Links(Dijon, 11, e);
        // Links l10 = new Links(Dijon, 11, e);
        // Links l5 = new Links(Dijon, 4, c);
        // Links l6 = new Links(Dijon, 8, d);
        // Links l7 = new Links(Dijon, 20, e);
        // Links l8 = new Links(Dijon, 6, d);
        // Links l9 = new Links(Dijon, 7, e);
        // Links l10 = new Links(Dijon, 11, e);
        // A_star algo = new A_star(a);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of City :");

        int numberOfCity = scanner.nextInt();
        ArrayList<City> generatedListOfCities = new ArrayList<>();
        for (int i = 0; i < numberOfCity; i++) {
            double x = Math.random() * 100;
            int xVal = (int) x;
            double y = Math.random() * 100;
            int yVal = (int) y;

            generatedListOfCities.add(new City(xVal, yVal, i));
        }
        City[] cityArray = generatedListOfCities.toArray(new City[0]);
        // for (int i = 0; i < cityArray.length; i++) {
        // System.out.println(cityArray[i]);
        // }
        // create link

        for (int i = 0; i < cityArray.length; i++) {
            for (int j = i + 1; j < cityArray.length; j++) {
                City fromCity = cityArray[i];
                City toCity = cityArray[j];

                double distBetweenTwoCities = Math.sqrt(Math.pow((int) toCity.getCoordX() - fromCity.getCoordX(), 2)
                        + Math.pow(toCity.getCoordY() - fromCity.getCoordY(), 2));
                int valDist = (int) distBetweenTwoCities;
                Links links = new Links(fromCity, valDist, toCity);
                links.print_all_links();
            }
        }
    }
}
