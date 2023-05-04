package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAnimalMenuController implements Initializable {
    Stage stage;
    Parent scene;
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

    public void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
