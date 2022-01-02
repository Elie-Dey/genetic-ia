package fr.dauphine.ia;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Main {
    public static int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        }
        else {
            return IntStream.rangeClosed(2, f).reduce((x, y) -> x * y).getAsInt();
        }
    }

    public static void main(String[] args) {


        System.out.println("INFORMATION ALGORITHME GENETIQUES");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nombre de City :");

        int nombreDeCity = scanner.nextInt();
       //System.out.println(" le nombre de City  :" + nombreDeCity);

        System.out.println("Le nombre de circuit possible pour " + nombreDeCity + " Cities est de " + getFactorial(nombreDeCity));
        System.out.println("Entrer un nombre de circuits pour l'algorithme  :");

        int nombreDeCircuit = scanner.nextInt();
        System.out.println("Nombre de circuits : " + nombreDeCircuit);

       // System.out.println("Nombre de générations : " );
       // int nombreDeGeneration = scanner.nextInt();
        //System.out.println("Nombre de générations : "   + nombreDeGeneration);
       // scanner.nextLine();


        //===============Creation of city file Cities.txt ====================
        try {
            File myfile = new File("Cities.txt");
            FileWriter writer = new FileWriter(myfile);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists ... updating\n");
            }
            for (int i = 0; i < nombreDeCity ; i++) {
                double x = Math.random()*10;
                double y = Math.random()*10;

                writer.write(x +";"+ y  + "\n");
            }
            System.out.println("Random creation of cities\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        // Lecture des Cities
        Cities.ReadingFile("Cities.txt", nombreDeCity);
        int numberOfindividualss = nombreDeCircuit;
        long startTime = System.currentTimeMillis();
        Population p = new Population(numberOfindividualss);
        double a =  p.getPopulation().get(0).performance();
        System.out.println("Running....");
        int i = 0;
        while ((System.currentTimeMillis() - startTime) / 1000 < 100) {
            i++;
            p = p.reproduce();
        }

        //System.out.println("Solution trouvée à la Génération n° " + i + " : " + p.getPopulation().get(0).performance());
        double b =  p.getPopulation().get(0).performance();
        System.out.println("Performance initiale: " + a+"\n");
        System.out.println("Performance finale: " + b+"\n");
        System.out.println("Individual (Circuit) : " +  p.getPopulation().get(0));
        double taux = -(b*10 - a*10)/(b*10) * 100;
        double roundTaux = Math.round(taux*100)/100;
        System.out.println("Taux d'evolution par rapport à la fitness de départ : " +  roundTaux + " %");
        System.out.println("Temps d'execution : " + ((System.currentTimeMillis()- startTime)/1000)*0.6 + "s");
    }



    }
