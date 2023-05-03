package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalDetailsMenuController implements Initializable {
    public Label animalIdLbl;
    public Label animalBreedLbl;
    public Label animalLifespanLbl;
    public Label animalBehaviorLbl;
    public Label animalPriceLbl;
    public Label animalVaccinatedLbl;
    public Label animalSpecialLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionDisplayMainMenu(ActionEvent actionEvent) {
    }
}
