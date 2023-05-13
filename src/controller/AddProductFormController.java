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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is a controller for the AddProductForm view.
 * It contains the methods initialize, onActionAddAssociatedPart,
 * onActionRemoveAssociatedPart, onActionProductSaved, onActionSearchParts,
 * and onActionDisplayMenu
 */
public class AddProductFormController implements Initializable {
    /**
     * TextField element for the gui
     */
    public TextField name;
    /**
     * TextField element for the gui
     */
    public TextField inv;
    /**
     * TextField element for the gui
     */
    public TextField price;
    /**
     * TextField element for the gui
     */
    public TextField max;
    /**
     * TextField element for the gui
     */
    public TextField min;
    /**
     * TableView element for the gui
     */
    public TableView<Part> partsTableView;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Integer> partIdCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, String> partNameCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Integer> partInventoryLevelCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Double> partPriceCol;
    /**
     * TextField element for the gui
     */
    public TextField partSearchField;
    /**
     * TableView element for the gui
     */
    public TableView<Part> associatedPartsTableView;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Integer> associatedPartIdCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, String> associatedPartNameCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Integer> associatedPartInventoryLevelCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Part, Double> associatedPartPriceCol;
    /**
     * the stage the application is running in
     */
    Stage stage;
    /**
     * the ui to be displayed
     */
    Parent scene;
    /**
     * an ObservableList for the associated parts for product.
     */
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * this method initializes the controller by populating the table views.
     * @param url
     * @param resourceBundle
     */
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
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * method for the add button under the parts table view.
     * adds the part to the associated parts table view.
     */
    public void onActionAddAssociatedPart()  {
        Part associatedPart = partsTableView.getSelectionModel().getSelectedItem();
        if(associatedPart != null) {
            associatedParts.add(associatedPart);
        }
    }

    /**
     * method for removing an associated part from the associated parts table view.
     */
    public void onActionRemoveAssociatedPart() {
        if(associatedPartsTableView.getSelectionModel().getSelectedItem() == null) {
            // if no part is selected, nothing happens
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part?");
        alert.setTitle("Associated Parts");
        alert.setHeaderText("Remove");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            associatedParts.remove(partsTableView.getSelectionModel().getSelectedItem());
        }
    }
    /**
     * this method is triggered when the save button is clicked.
     * it performs error checks and then adds the newly created product to the inventory.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionProductSaved(ActionEvent actionEvent) throws IOException {
        try {
            ObservableList<Product> allProducts = Inventory.getAllProducts();
            int id = allProducts.size() + 1;
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
            Product newProduct = new Product(id, productName, productPrice, productInv, productMin, productMax);
            for(int i=0 ; i < associatedParts.size(); i++) {
                newProduct.addAssociatedPart(associatedParts.get(i));
            }
            Inventory.addProduct(newProduct);
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

    /**
     * method for the search field for the parts table view.
     * it is triggered after a user enters a value and then presses enter.
     * if there are no parts found, an error dialogue box appears.
     */
    public void onActionSearchParts() {
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
    /**
     * this method is triggered when the cancel button is clicked.
     * it returns the user to the main menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionDisplayMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
