package fr.dauphine.Genetic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LauncherGenetic {
    public static int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return IntStream.rangeClosed(2, f).reduce((x, y) -> x * y).getAsInt();
        }
    }

    public static void Launch() {
        System.out.println("GENETIC ALGORITHM INFORMATION");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of City :");

        int numberOfCity = scanner.nextInt();
        // System.out.println(" le nombre de City :" + numberOfCity);

        int factorial = getFactorial(numberOfCity);
        System.out.println("The number of circuits possible for " + numberOfCity + " Cities is " + factorial);
        System.out.println("Enter a number of circuits for the algorithm: ");

        int numberOfCircuits = scanner.nextInt();

        System.out.println(" Number of cities  :" + numberOfCity);
        System.out.println("Number of circuits : " + numberOfCircuits + " in " + factorial);

        // ===============Creation of city file Cities.txt ====================
        try {
            File myfile = new File("Cities_geneticAlgorithm.txt");
            FileWriter writer = new FileWriter(myfile);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists ... updating\n");
            }
            for (int i = 0; i < numberOfCity; i++) {
                double x = Math.random() * 10;
                double y = Math.random() * 10;

                writer.write(x + ";" + y + "\n");
            }
            System.out.println("Random creation of cities\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        // Reading cities
        Cities.ReadingFile("Cities_geneticAlgorithm.txt", numberOfCity);
        int numberOfindividuals = numberOfCircuits;
        long startTime = System.currentTimeMillis();
        Population p = new Population(numberOfindividuals);
        double a = p.getPopulation().get(0).performance();
        System.out.println("Running....");
        int i = 0;
        while ((System.currentTimeMillis() - startTime) / 1000 < 100) {
            i++;
            p = p.reproduce();
        }

        double b = p.getPopulation().get(0).performance();
        System.out.println("Performance initiale: " + a + "\n");
        System.out.println("Performance finale: " + b + "\n");
        System.out.println("Individual (Circuit) : " + p.getPopulation().get(0));
        double taux = -(b * 10 - a * 10) / (b * 10) * 100;
        double roundTaux = Math.round(taux * 100) / 100;
        System.out.println("Taux d'evolution par rapport à la performance de départ : " + roundTaux + " %");
        System.out.println("Temps d'execution : " + ((System.currentTimeMillis() - startTime) / 1000) * 0.6 + "s");

    }
    // public static void main(String[] args) {

    // System.out.println("GENETIC ALGORITHM INFORMATION");
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Enter the number of City :");

    // int numberOfCity = scanner.nextInt();
    // // System.out.println(" le nombre de City :" + numberOfCity);

    // int factorial = getFactorial(numberOfCity);
    // System.out.println("The number of circuits possible for " + numberOfCity + "
    // Cities is " + factorial);
    // System.out.println("Enter a number of circuits for the algorithm: ");

    // int numberOfCircuits = scanner.nextInt();

    // System.out.println(" Number of cities :" + numberOfCity);
    // System.out.println("Number of circuits : " + numberOfCircuits + " in " +
    // factorial);

    // // ===============Creation of city file Cities.txt ====================
    // try {
    // File myfile = new File("Cities.txt");
    // FileWriter writer = new FileWriter(myfile);
    // if (myfile.createNewFile()) {
    // System.out.println("File created: " + myfile.getName());
    // } else {
    // System.out.println("File already exists ... updating\n");
    // }
    // for (int i = 0; i < numberOfCity; i++) {
    // double x = Math.random() * 10;
    // double y = Math.random() * 10;

    // writer.write(x + ";" + y + "\n");
    // }
    // System.out.println("Random creation of cities\n");
    // writer.close();

    // } catch (IOException e) {
    // System.out.println("Error");
    // e.printStackTrace();
    // }
    // // Reading cities
    // Cities.ReadingFile("Cities.txt", numberOfCity);
    // int numberOfindividualss = numberOfCircuits;
    // long startTime = System.currentTimeMillis();
    // Population p = new Population(numberOfindividualss);
    // double a = p.getPopulation().get(0).performance();
    // System.out.println("Running....");
    // int i = 0;
    // while ((System.currentTimeMillis() - startTime) / 1000 < 300) {
    // i++;
    // p = p.reproduce();
    // }

    // double b = p.getPopulation().get(0).performance();
    // System.out.println("Performance initiale: " + a + "\n");
    // System.out.println("Performance finale: " + b + "\n");
    // System.out.println("Individual (Circuit) : " + p.getPopulation().get(0));
    // double taux = -(b * 10 - a * 10) / (b * 10) * 100;
    // double roundTaux = Math.round(taux * 100) / 100;
    // System.out.println("Taux d'evolution par rapport à la performance de départ :
    // " + roundTaux + " %");
    // System.out.println("Temps d'execution : " + ((System.currentTimeMillis() -
    // startTime) / 1000) * 0.6 + "s");
    // }

}
