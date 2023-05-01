package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MainMenuController {

    public int count = 1;
    public Label label;

    public void onClicked(ActionEvent actionEvent) {
        label.setText("Count: " + count++);
    }
}
