package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Animal;
import model.DataProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalMenuController implements Initializable {
    Stage stage;
    Parent scene;
    public TableView<Animal> animalTableView;
    public TableColumn<Animal, Integer> animalIdCol;
    public TableColumn<Animal, String> animalBreedCol;
    public TableColumn<Animal, Integer> animalLifespanCol;
    public TableColumn<Animal, Double> animalPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animalTableView.setItems(DataProvider.getAllAnimals());

        animalIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        animalBreedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        animalLifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
        animalPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void onActionDisplayAnimalDetailsMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AnimalDetailsMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDisplayMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
