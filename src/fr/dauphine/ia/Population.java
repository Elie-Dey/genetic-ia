package fr.dauphine.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
    private ArrayList<Individual> population;

    /**
     *
     * @param numberOfindividuals
     */
    public Population(int numberOfindividuals) {
        this.population = new ArrayList<>();
        for (int i = 0; i < numberOfindividuals; i++)
            this.population.add(new Individual());
    }


    public Population() {
        this.population = new ArrayList<>();
    }

    public Individual bestIndividual() {
        Collections.sort(this.population);
        return this.population.get(0);
    }

    /**
     *
     * @return
     */
    public ArrayList<Individual> getPopulation() {
        return population;
    }

    /**
     *
     * @return
     */
    public Population reproduce() {
        Random randGen = new Random();
        Population children = new Population();

        // Elitism : Les 2 meilleurs sont ajoutés office dans la nouvelle generation

        Collections.sort(this.population);
        for (int i=0 ; i<2 ; i++)
            children.population.add(this.population.get(i));

        // CrossOver : CrossOvers entre les Individuals restants

        while (children.population.size() < this.population.size()) {
            children.population.addAll(Individual.CrossOver(this.population.get(randGen.nextInt(this.population.size())),
                    this.population.get(randGen.nextInt(this.population.size()))));
        }

        return children;
    }
}
