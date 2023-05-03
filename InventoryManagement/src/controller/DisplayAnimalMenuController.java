package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalMenuController implements Initializable {
    public TableView animalTableView;
    public TableColumn animalIdCol;
    public TableColumn animalBreedCol;
    public TableColumn animalLifespanCol;
    public TableColumn animalPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionDisplayAnimalDetailsMenu(ActionEvent actionEvent) {
    }

    public void onActionDisplayMenu(ActionEvent actionEvent) {
    }
}
