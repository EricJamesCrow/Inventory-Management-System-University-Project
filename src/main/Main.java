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

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 400);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

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