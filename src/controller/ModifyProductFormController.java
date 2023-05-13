package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductFormController implements Initializable {
    public TextField id;
    public TextField name;
    public TextField inv;
    public TextField price;
    public TextField max;
    public TextField min;
    public TableView<Part> partsTableView;
    public TableColumn<Part, Integer> partIdCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partInventoryLevelCol;
    public TableColumn<Part, Double> partPriceCol;
    public TextField partSearchField;
    public TableView<Part> associatedPartsTableView;
    public TableColumn<Part, Integer> associatedPartIdCol;
    public TableColumn<Part, String> associatedPartNameCol;
    public TableColumn<Part, Integer> associatedPartInventoryLevelCol;
    public TableColumn<Part, Double> associatedParttPriceCol;
    Stage stage;
    Parent scene;
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTableView.setItems(associatedParts);
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedParttPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    public void onActionSaveModifiedProduct(ActionEvent actionEvent) throws IOException {
        try {
            int productId = Integer.parseInt(id.getText());
            int index = productId - 1;
            String productName = name.getText();
            if(productName == "") {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Enter a string for name");
                alert.showAndWait();
                return;
            }
            double productPrice;
            int productInv;
            int productMin;
            int productMax;
            try {
                productPrice = Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Price is not a double");
                alert.showAndWait();
                return;
            }
            try {
                productInv = Integer.parseInt(inv.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Inventory is not an integer");
                alert.showAndWait();
                return;
            }
            try {
                productMin = Integer.parseInt(min.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Min is not an integer");
                alert.showAndWait();
                return;
            }
            try {
                productMax = Integer.parseInt(max.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Max is not an integer");
                alert.showAndWait();
                return;
            }
            if(!(productMin < productMax)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Min should be less than Max");
                alert.showAndWait();
                return;
            }
            if(!(productMin < productInv && productInv < productMax)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Inv must be between Min and Max");
                alert.showAndWait();
                return;
            }
            Product newProduct = new Product(productId, productName, productPrice, productInv, productMin, productMax);
            for(int i=0 ; i < associatedParts.size(); i++) {
                newProduct.addAssociatedPart(associatedParts.get(i));
            }
            Inventory.updateProduct(index, newProduct);
            associatedParts.clear();
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

    public void onActionAddAssociatedPart(ActionEvent actionEvent) throws IOException {
        Part associatedPart = partsTableView.getSelectionModel().getSelectedItem();
        if(associatedPart != null) {
            associatedParts.add(associatedPart);
        }
    }
    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) throws IOException {
        if(associatedPartsTableView.getSelectionModel().getSelectedItem() == null) {
            // if no part is selected, nothing happens
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part?");
        alert.setTitle("Associated Parts");
        alert.setHeaderText("Remove");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            associatedParts.remove(associatedPartsTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void onActionSearchParts(ActionEvent actionEvent) throws  IOException {
        if(partSearchField.getText().matches("[0-9]+")) {
            Part part = Inventory.lookupPart(Integer.parseInt(partSearchField.getText()));
            if(part == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist");
                alert.showAndWait();
            } else {
                // Set items before selecting the part in case parts were previously filtered with a string
                partsTableView.setItems(Inventory.getAllParts());
                partsTableView.getSelectionModel().select(part);
            }
        } else {
            ObservableList<Part> filteredParts = Inventory.lookupPart(partSearchField.getText());
            if(filteredParts.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist");
                alert.showAndWait();
            } else {
                partsTableView.setItems(filteredParts);
                if(filteredParts.size() == 1) {
                    partsTableView.getSelectionModel().select(filteredParts.get(0));
                }
            }
        }
    }

    public void onActionDisplayMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void sendProduct(Product product) {
        id.setText(String.valueOf(product.getId()));
        name.setText(product.getName());
        inv.setText(String.valueOf(product.getStock()));
        price.setText(String.valueOf(product.getPrice()));
        max.setText(String.valueOf(product.getMax()));
        min.setText(String.valueOf(product.getMin()));
        associatedParts.setAll(product.getAllAssociatedParts());
    }
}
