package fr.dauphine.ia;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individu  implements  Comparable<Individu>, Cloneable{

    private List<Ville> listeVilles;

    // Creation du circuit avec différentes villes
    public Individu() {
        this.listeVilles = new ArrayList<>(Villes.getVilles());
        Collections.shuffle(this.listeVilles);
        this.initVilles();
    }

    /**
     * Initialisation des villes lu dans le fichier Ville.txt crée
     */
    private void initVilles() {
        Ville tampon = this.listeVilles.get(0);
        int index = this.listeVilles.indexOf(Villes.getVille(0));
        this.listeVilles.set(index, tampon);
        this.listeVilles.set(0, Villes.getVille(0));
    }

    //FITNESS
    /**
     *
     * @return la fitness d'un circuit
     */

    public double performance() {
        double distance = 0.0;
        for (int i = 0; i < this.listeVilles.size() - 1; i++)
            distance += Villes.distance(this.listeVilles.get(i), this.listeVilles.get(i + 1));
        distance += Villes.distance(this.listeVilles.get(this.listeVilles.size() - 1), this.listeVilles.get(0));
        return distance;
    }

    // CROISSEMENT
    /**
     *
     * @param parent1 père
     * @param parent2 mère
     * @return
     */
    public static List<Individu> croisement(Individu parent1, Individu parent2) {

        // generation des deux points de coupe du circuit

        Random randGen = new Random();

        int pointDeCoupe1;
        int pointDeCoupe2;
        do {
            pointDeCoupe1 = randGen.nextInt(parent1.listeVilles.size());
            pointDeCoupe2 = randGen.nextInt(parent1.listeVilles.size());
        } while (pointDeCoupe1 >= pointDeCoupe2);

        // remplacement des villes entre les deux points de coupe du circuit

        Individu enfant1 = parent1.clone();
        Individu enfant2 = parent2.clone();

        // Creation de 2 enfants avec les gène des parents
        for (int i = pointDeCoupe1; i <= pointDeCoupe2; i++) {
            enfant1.listeVilles.set(i, parent2.listeVilles.get(i));
            enfant2.listeVilles.set(i, parent1.listeVilles.get(i));
        }

        // AJOUT DES AUTRES VILLES

        Ville tampon1 = null;
        Ville tampon2 = null;
        for (int i = 0; i < parent1.listeVilles.size(); i++)
            if (!(enfant1.listeVilles.contains(Villes.getVille(i)))) {
                tampon1 = Villes.getVille(i);
                for (int j = 0; j < parent1.listeVilles.size(); j++)
                    if (!(enfant2.listeVilles.contains(Villes.getVille(j)))) {
                        tampon2 = Villes.getVille(j);
                        j = parent1.listeVilles.size();
                    }
                enfant1.listeVilles.set(enfant1.listeVilles.indexOf(tampon2), tampon1);
                enfant2.listeVilles.set(enfant2.listeVilles.indexOf(tampon1), tampon2);
            }

        //MUTATION

        if (randGen.nextInt(100) < 50) {
            int point1;
            int point2;
            do {
                point1 = randGen.nextInt(parent1.listeVilles.size());
                point2 = randGen.nextInt(parent1.listeVilles.size());
            } while (point1 >= point2);

            //On reflete le chemin entre deux points

            for (int i=0 ; i<(point2-point1)/2 ; i++) {
                Collections.swap(enfant1.listeVilles, point1 + i, point2 - i);
                Collections.swap(enfant2.listeVilles, point1 + i, point2 - i);
            }
        }

        // Creation de la population d'enfant et ajout des enfant générés
        ArrayList<Individu> enfants = new ArrayList<Individu>();
        enfants.add(enfant1);
        enfants.add(enfant2);
        return enfants;
    }


    /**
     *
     * @return un clone d'un circuit
     */
    public Individu clone() {
        Individu clone = null;
        try {
            clone = (Individu) super.clone();
            clone.listeVilles = new ArrayList<Ville>(this.listeVilles);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return clone;
    }

    /**
     *
     * @return
     */
    public List<Ville> getListeVilles() {
        return listeVilles;
    }

    /**
     *
     * @param listeVilles
     */
    public void setListeVilles(List<Ville> listeVilles) {
        this.listeVilles = listeVilles;
    }

    /**
     *
     * @param ind
     * @return
     */
    @Override
    public int compareTo(Individu ind) {
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
                "listeVilles=" + listeVilles +
                ']';
    }
}
