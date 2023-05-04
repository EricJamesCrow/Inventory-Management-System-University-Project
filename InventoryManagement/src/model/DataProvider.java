package model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class DataProvider {
    private static ObservableList<Animal> allAnimals = FXCollections.observableArrayList();

    public static void addAnimal(Animal animal)
    {
        allAnimals.add(animal);
    }

    public static ObservableList<Animal> getAllAnimals()
    {
        return allAnimals;
    }
}
