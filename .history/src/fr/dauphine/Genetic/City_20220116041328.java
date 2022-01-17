package fr.dauphine.Genetic;

public class City {
    private double coordX;
    private double coordY;
    private int numberOfCity;

    public City(double coordX, double coordY, int numberOfCity) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.numberOfCity = numberOfCity;
    }

    @Override
    public String toString() {
        return "[" + numberOfCity + "]";
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (this.coordX != other.coordX || this.coordY != other.coordY)
            return false;
        return true;
    }

}
