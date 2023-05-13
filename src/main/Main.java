package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Product;
import model.inHouse;

import java.io.IOException;

/**
 * this is the main class for running the program.
 * it inherits from the javafx.application.Application class.
 * it contains the methods start and main.
 * Note: The Javadoc folder is located at the root of the directory for this project
 */
public class Main extends Application {
    /**
     * this method is called by launch() in the main method.
     * it sets the scene to the MainForm view.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 400);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * this method starts the program
     * @param args
     */
    public static void main(String[] args) {
        Product product1 = new Product(1, "Chessboard", 24.99, 25, 1, 20);
        Product product2 = new Product(2, "Big Mac", 4.59, 150, 1, 20);
        Outsourced outsourced1 = new Outsourced(1, "Part", 12.99, 40, 1, 20, "Microsoft");
        inHouse inhouse1 = new inHouse(2, "Another part", 3.99, 30, 1, 20, 43);
        Inventory.addPart(outsourced1);
        Inventory.addPart(inhouse1);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        launch();
    }
}