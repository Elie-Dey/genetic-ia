package fr.dauphine.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
    private ArrayList<Individu> population;

    /**
     *
     * @param nombreIndividu
     */
    public Population(int nombreIndividu) {
        this.population = new ArrayList<Individu>();
        for (int i = 0; i < nombreIndividu; i++)
            this.population.add(new Individu());
    }


    public Population() {
        this.population = new ArrayList<>();
    }

    public Individu meilleurIndividu() {
        Collections.sort(this.population);
        return this.population.get(0);
    }

    /**
     *
     * @return
     */
    public ArrayList<Individu> getPopulation() {
        return population;
    }

    /**
     *
     * @return
     */
    public Population reproduire() {
        Random randGen = new Random();
        Population enfants = new Population();

        // Elitism : Les 2 meilleurs sont ajoutés office dans la nouvelle generation

        Collections.sort(this.population);
        for (int i=0 ; i<2 ; i++)
            enfants.population.add(this.population.get(i));

        // Croisement : croisements entre les individus restants

        while (enfants.population.size() < this.population.size()) {
            enfants.population.addAll(Individu.croisement(this.population.get(randGen.nextInt(this.population.size())),
                    this.population.get(randGen.nextInt(this.population.size()))));
        }

        return enfants;
    }
}
