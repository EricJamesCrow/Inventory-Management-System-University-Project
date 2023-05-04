package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Dog dog1 = new Dog(1, "Siberian Husky", 15, "Crafty", 599.99, true, "Whistles");
        Dog dog2 = new Dog(2, "Alaskan Malimute", 12, "Energetic", 999.99, true, "Climbs");
        Dog dog3 = new Dog(3, "Dalmation", 13, "Energetic", 1999.99, true, "Gymnast");
        Dog dog4 = new Dog(4, "Dogo Argentino", 12, "Protective", 1599.99, true, "Whistles");
        Dog dog5 = new Dog(5, "German Shepherd", 12, "Intelligent", 2599.99, true, "Barks");

        DataProvider.addAnimal(dog1);
        DataProvider.addAnimal(dog2);
        DataProvider.addAnimal(dog3);
        DataProvider.addAnimal(dog4);
        DataProvider.addAnimal(dog5);

        launch();
    }
}