package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    public int count = 1;
    public Label label;

    public void onClicked(ActionEvent actionEvent) {
        label.setText("Count: " + count++);
    }

    public void onActionCreateAnimal(ActionEvent actionEvent) {
        System.out.println("Create button clicked.");
    }

    public void onActionDisplayAnimal(ActionEvent actionEvent) {
        System.out.println("Display button clicked.");
    }

    public void onActionExit(ActionEvent actionEvent) {
        System.out.println("Exit button clicked.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
