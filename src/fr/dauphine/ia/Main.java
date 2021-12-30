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

        System.out.println("Nombre de générations : " );
        int nombreDeGeneration = scanner.nextInt();
        System.out.println("Nombre de générations : "   + nombreDeGeneration);
        scanner.nextLine();
        //===============Creation fichier villes.txt ====================
        try {
            File myfile = new File("Villes.txt");
            FileWriter writer = new FileWriter(myfile);
            if (myfile.createNewFile()) {
                System.out.println("Fichier crée: " + myfile.getName());
            } else {
                System.out.println("Fichier déjà existant...mise à jour des villes");
            }
            for (int i = 0; i < nombreDeVille ; i++) {
                double x = Math.random();
                double y = Math.random();

                writer.write(x +";"+ y + ";" + i + "\n");
            }
            System.out.println("Creation aleatoire de ville");
            writer.close();

        } catch (IOException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
        // Lecture des villes
        Villes.LectureFichier("Villes.txt");
        int nombreIndividus = nombreDeCircuit;
//
//        JFrame fen = new JFrame("Algo génétique");
//        Panneau panneau = new Panneau();
//
//        JLabel longueur = new JLabel("");
//        JLabel temps = new JLabel("");
//        temps.setBackground(Color.white);
//        JMenuBar barre = new JMenuBar();
//        barre.add(longueur);
//        barre.add(temps);
//        fen.setJMenuBar(barre);
//        barre.add(Box.createRigidArea(new Dimension(50,70)));
//        barre.setBackground(Color.black);
//
//
//        fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        fen.setContentPane(panneau);
//        fen.getContentPane().setBackground(Color.BLACK);
//        //fen.setTitle("Algorithme génétique");
//        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //fen.setVisible(true);

        long startTime = System.currentTimeMillis();
        Population p = new Population(nombreIndividus);
        double a =  p.getPopulation().get(0).performance();
        System.out.println("Meilleur individu départ : " +  p.getPopulation().get(0));
        System.out.println("Population initiale meilleur fitness : " + a);
        System.out.println("Running....");
        int i = 0;
        while ((System.currentTimeMillis() - startTime) / 1000 < nombreDeGeneration) {
            i++;
            p = p.reproduire();
            //panneau.recupMeilleurIndiv(p);
            //System.out.println("Génération " + i + " : " + p.getPopulation().get(0).performance());
            //longueur.setText(" Longueur du chemin : " + String.format("%.4f", p.getPopulation().get(0).performance())
             //       + "          ");
            //temps.setText("Fonctionne depuis " + (System.currentTimeMillis() - startTime) / 1000 + " secondes");
        }

        double b =  p.getPopulation().get(0).performance();
        System.out.println("Meilleur individu fin : " +  p.getPopulation().get(0));
        System.out.println("Population finale fitness: " + b);
        double taux = -(b*10 - a*10)/(b*10) * 100;
        double roundTaux = Math.round(taux*100)/100;
        System.out.println("Taux d'evolution par rapport à la fitness de départ : " +  roundTaux + " %");
        System.out.println("Temps d'execution : " + ((System.currentTimeMillis()- startTime)/1000)*0.6 + "s");
       // System.exit(0);
    }



    }
