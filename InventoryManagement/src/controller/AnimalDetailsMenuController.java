package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimalDetailsMenuController implements Initializable {
    Stage stage;
    Parent scene;
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

    public void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/DisplayAnimalsMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
