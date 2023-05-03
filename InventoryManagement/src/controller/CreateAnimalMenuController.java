package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAnimalMenuController implements Initializable {
    public TextField animalidTxt;
    public TextField animalbreedTxt;
    public TextField animalifespantxt;
    public TextField animalbehaviorTxt;
    public TextField animalpriceTxt;
    public RadioButton vaccYesRBtn;
    public RadioButton vaccNoRBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionSaveAnimal(ActionEvent actionEvent) {
    }

    public void onActionDisplayMainMenu(ActionEvent actionEvent) {
    }
}
