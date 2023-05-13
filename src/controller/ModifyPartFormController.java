package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.inHouse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartFormController implements Initializable {
    Stage stage;
    Parent scene;
    public RadioButton inHouseBtn;
    public RadioButton outsourcedBtn;
    public Label companyNameMachineIdLabel;
    public TextField id;
    public TextField name;
    public TextField inv;
    public TextField price;
    public TextField max;
    public TextField companyNameMachineIdTextField;
    public TextField min;
    public Button addPartSaveBtn;
    public Button addPartCancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionDisplayMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionSaveModifiedPart(ActionEvent actionEvent) throws IOException {
        try {
            int partId = Integer.parseInt(id.getText());
            int index = partId - 1;
            String partName = name.getText();
            if(partName == "") {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Enter a string for name");
                alert.showAndWait();
                return;
            }
            double partPrice;
            int partInv;
            int partMin;
            int partMax;
            int machineId;
            String companyName;
            try {
                partPrice = Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Price is not a double");
                alert.showAndWait();
                return;
            }
            try {
                partInv = Integer.parseInt(inv.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Inventory is not an integer");
                alert.showAndWait();
                return;
            }
            try {
                partMin = Integer.parseInt(min.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Min is not an integer");
                alert.showAndWait();
                return;
            }
            try {
                partMax = Integer.parseInt(max.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Max is not an integer");
                alert.showAndWait();
                return;
            }
            if(!(partMin < partMax)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Min should be less than Max");
                alert.showAndWait();
                return;
            }
            if(!(partMin < partInv && partInv < partMax)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Inv must be between Min and Max");
                alert.showAndWait();
                return;
            }
            if (inHouseBtn.isSelected()) {
                try {
                    machineId = Integer.parseInt(companyNameMachineIdTextField.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("Machine ID is not an integer");
                    alert.showAndWait();
                    return;
                }
                inHouse selectedPart = new inHouse(partId, partName, partPrice, partInv, partMin, partMax, machineId);
                Inventory.updatePart(index, selectedPart);
            } else if (outsourcedBtn.isSelected()) {
                companyName = companyNameMachineIdTextField.getText();
                if(companyName == "") {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("Enter a string for companyName");
                    alert.showAndWait();
                    return;
                }
                Outsourced selectedPart = new Outsourced(partId, partName, partPrice, partInv, partMin, partMax, companyName);
                Inventory.updatePart(index, selectedPart);
            }
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field!");
            alert.showAndWait();
        }
    }

    public void onRadioButtonSelected(ActionEvent actionEvent) {
        if(inHouseBtn.isSelected()) {
            companyNameMachineIdLabel.setText("Machine ID");
        } else if (outsourcedBtn.isSelected()) {
            companyNameMachineIdLabel.setText("Company Name");
        }
    }

    public void sendPart(Part part) {
        id.setText(String.valueOf(part.getId()));
        name.setText(part.getName());
        inv.setText(String.valueOf(part.getStock()));
        price.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));
        if(part instanceof inHouse) {
            inHouse inHousePart = (inHouse) part;
            companyNameMachineIdTextField.setText(String.valueOf(inHousePart.getMachineId()));
            inHouseBtn.setSelected(true);
            companyNameMachineIdLabel.setText("Machine ID");
        } else if(part instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) part;
            companyNameMachineIdTextField.setText(outsourcedPart.getCompanyName());
            outsourcedBtn.setSelected(true);
            companyNameMachineIdLabel.setText("Company Name");
        }
    }
}
