package fr.dauphine.ia;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual  implements  Comparable<Individual>, Cloneable{

    private List<City> listOfCity;

    // Creation du circuit avec différentes Cities
    public Individual() {
        this.listOfCity = new ArrayList<>(Cities.getCities());
        Collections.shuffle(this.listOfCity);
        this.initCities();
    }

    /**
     * Initialisation des Cities lu dans le File City.txt crée
     */
    private void initCities() {
        City buffer = this.listOfCity.get(0);
        int index = this.listOfCity.indexOf(Cities.getCity(0));
        this.listOfCity.set(index, buffer);
        this.listOfCity.set(0, Cities.getCity(0));
    }

    //FITNESS
    /**
     *
     * @return la fitness d'un circuit
     */

    public double performance() {
        double distance = 0.0;
        for (int i = 0; i < this.listOfCity.size() - 1; i++)
            distance += Cities.distance(this.listOfCity.get(i), this.listOfCity.get(i + 1));
        distance += Cities.distance(this.listOfCity.get(this.listOfCity.size() - 1), this.listOfCity.get(0));
        return distance;
    }

    // CROISSEMENT

    /**
     *
     * @param firstParent
     * @param secondParent
     * @return
     */
   
    public static List<Individual> CrossOver(Individual firstParent, Individual secondParent) {

        // generation des deux points de coupe du circuit

        Random randGen = new Random();

        int firstCutPoint;
        int secondCutPoint;
        do {
            firstCutPoint = randGen.nextInt(firstParent.listOfCity.size());
            secondCutPoint = randGen.nextInt(firstParent.listOfCity.size());
        } while (firstCutPoint >= secondCutPoint);

        // remplacement des Cities entre les deux points de coupe du circuit

        Individual firstChild = firstParent.clone();
        Individual secondChild = secondParent.clone();

        // Creation de 2 children avec les gène des parents
        for (int i = firstCutPoint; i <= secondCutPoint; i++) {
            firstChild.listOfCity.set(i, secondParent.listOfCity.get(i));
            secondChild.listOfCity.set(i, firstParent.listOfCity.get(i));
        }

        // AJOUT DES AUTRES Cities

        City firstBuffer = null;
        City secondBuffer = null;
        for (int i = 0; i < firstParent.listOfCity.size(); i++)
            if (!(firstChild.listOfCity.contains(Cities.getCity(i)))) {
                firstBuffer = Cities.getCity(i);
                for (int j = 0; j < firstParent.listOfCity.size(); j++)
                    if (!(secondChild.listOfCity.contains(Cities.getCity(j)))) {
                        secondBuffer = Cities.getCity(j);
                        j = firstParent.listOfCity.size();
                    }
                firstChild.listOfCity.set(firstChild.listOfCity.indexOf(secondBuffer), firstBuffer);
                secondChild.listOfCity.set(secondChild.listOfCity.indexOf(firstBuffer), secondBuffer);
            }

        //MUTATION

        if (randGen.nextInt(100) < 50) {
            int point1;
            int point2;
            do {
                point1 = randGen.nextInt(firstParent.listOfCity.size());
                point2 = randGen.nextInt(firstParent.listOfCity.size());
            } while (point1 >= point2);

            //On reflete le chemin entre deux points

            for (int i=0 ; i<(point2-point1)/2 ; i++) {
                Collections.swap(firstChild.listOfCity, point1 + i, point2 - i);
                Collections.swap(secondChild.listOfCity, point1 + i, point2 - i);
            }
        }

        // Creation de la population d'enfant et ajout des enfant générés
        ArrayList<Individual> children = new ArrayList<Individual>();
        children.add(firstChild);
        children.add(secondChild);
        return children;
    }


    /**
     *
     * @return un clone d'un circuit
     */
    public Individual clone() {
        Individual clone = null;
        try {
            clone = (Individual) super.clone();
            clone.listOfCity = new ArrayList<City>(this.listOfCity);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return clone;
    }

    /**
     *
     * @return
     */
    public List<City> getlistOfCity() {
        return listOfCity;
    }

    /**
     *
     * @param listOfCity
     */
    public void setlistOfCity(List<City> listOfCity) {
        this.listOfCity = listOfCity;
    }

    /**
     *
     * @param ind
     * @return
     */
    @Override
    public int compareTo(Individual ind) {
        if (this.performance() < ind.performance())
            return -1;
        else if (this.performance() > ind.performance())
            return 1;
        else
            return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Circuit[" +
                "listOfCity=" + listOfCity +
                ']';
    }
}
