package fr.dauphine.ia;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i; // trouver la factorielle du nombre avec des boucles
        }
        return result;
    }
    public static void main(String[] args) {


        System.out.println("INFORMATION ALGORITHME GENETIQUES");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nombre de ville :");

        int nombreDeVille = scanner.nextInt();
        System.out.println("Entrer le nombre de ville  :" + nombreDeVille);

        System.out.println("Le nombre de circuit possible pour " + nombreDeVille + " villes est de " + getFactorial(nombreDeVille));
        System.out.println("Entrer un nombre de circuits pour l'algorithme  :");

        int nombreDeCircuit = scanner.nextInt();
        System.out.println("Nombre de circuits : " + nombreDeCircuit);

        scanner.nextLine();
        try {
            File myfile = new File("Villes.txt");
            FileWriter writer = new FileWriter(myfile);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());
            } else {
                System.out.println("File already exists.");
            }
            for (int i = 0; i < nombreDeVille ; i++) {
                double x = Math.random();
                double y = Math.random();

                writer.write(x +";"+ y + "\n");
            }
            System.out.println("Successfully wrote to the file.");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Villes.lireVilles("Villes.txt");
        int nombreIndividus = nombreDeCircuit;

        JFrame fen = new JFrame("Algo génétique");
        Panneau panneau = new Panneau();

        JLabel longueur = new JLabel("");
        JLabel temps = new JLabel("");
        temps.setBackground(Color.white);
        JMenuBar barre = new JMenuBar();
        barre.add(longueur);
        barre.add(temps);
        fen.setJMenuBar(barre);
        barre.add(Box.createRigidArea(new Dimension(50,70)));
        barre.setBackground(Color.black);


        fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fen.setContentPane(panneau);
        fen.getContentPane().setBackground(Color.BLACK);
        //fen.setTitle("Algorithme génétique");
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fen.setVisible(true);

        long startTime = System.currentTimeMillis();
        Population p = new Population(nombreIndividus);
        double a =  p.getPopulation().get(0).performance();
        System.out.println("Population initiale  fit: " + a);
        System.out.println("Running....");
        int i = 0;
        while ((System.currentTimeMillis() - startTime) / 1000 < 50) {
            i++;
            p = p.reproduire();
            panneau.recupMeilleurIndiv(p);
            System.out.println("Génération " + i + " : " + p.getPopulation().get(0).performance());
            // Thread.sleep(1000);
            longueur.setText(" Longueur du chemin : " + String.format("%.4f", p.getPopulation().get(0).performance())
                    + "          ");
            temps.setText("Fonctionne depuis " + (System.currentTimeMillis() - startTime) / 1000 + " secondes");
        }

        System.out.println("Population initiale  fit: " + a);
        System.out.println("Population finale  fit: " + p.getPopulation().get(0).performance());
        System.exit(0);
        fen.dispose();
        fen.setVisible(false);
    }



    }
