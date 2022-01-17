package fr.dauphine.Genetic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cities {
    private static List<City> coords;

    public static double min;
    public static double max;

    public static void ReadingFile(String File, int numberOfInstancesInFile) {
        coords = new ArrayList<City>();
        min = 0;
        max = 0;
        try {
            InputStream ips = new FileInputStream(File);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String line;
            while ((line = br.readLine()) != null) {
                numberOfInstancesInFile--;
                int distToCut = line.indexOf(";");
                coords.add(new City(Double.parseDouble(line.substring(0, distToCut)),
                        Double.parseDouble(line.substring(distToCut + 1)), numberOfInstancesInFile));
                if (Double.parseDouble(line.substring(0, distToCut)) < min)
                    min = Double.parseDouble(line.substring(0, distToCut));
                if (Double.parseDouble(line.substring(0, distToCut)) > max)
                    max = Double.parseDouble(line.substring(0, distToCut));
            }
            System.out.println(coords);
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString() + " : Error when opening the file of cities");
        }
        System.out.println(coords);
    }

    public static City getCity(int index) {
        return coords.get(index);
    }

    public static List<City> getCities() {
        return coords;
    }

    public static double distance(City City1, City City2) {
        return Math.sqrt(Math.pow(City2.getCoordX() - City1.getCoordX(), 2)
                + Math.pow(City2.getCoordY() - City1.getCoordY(), 2));
    }

    public static double getMin() {
        return min;
    }

    public static double getMax() {
        return max;
    }
}
