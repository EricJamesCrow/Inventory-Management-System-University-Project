package controller;

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

/**
 * this class is a controller for the MainForm view.
 * It contains the methods onActionSearchParts, onActionSearchProducts,
 * onActionDisplayAddPart, onActionDisplayAddProduct, onActionDisplayModifyPart,
 * onActionDisplayModifyProduct, onActionDeletePart, onActionDeleteProduct,
 * onActionExit, and initialize
 */
public class MainFormController implements Initializable {
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
     * TableView element for the gui
     */
    public TableView<Product> productsTableView;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Product, Integer> productIdCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Product, String> productNameCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Product, Integer> productInventoryLevelCol;
    /**
     * TableColumn element for the gui
     */
    public TableColumn<Product, Double> productPriceCol;
    /**
     * TextField element for the gui
     */
    public TextField partSearchField;
    /**
     * TextField element for the gui
     */
    public TextField productSearchField;
    /**
     * the stage the application is running in
     */
    Stage stage;
    /**
     * the ui to be displayed
     */
    Parent scene;
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
     * method for the search field for the products table view.
     * it is triggered after a user enters a value and then presses enter.
     * if there are no products found, an error dialogue box appears.
     */
    public void onActionSearchProducts() {
        if(productSearchField.getText().matches("[0-9]+")) {
            Product product = Inventory.lookupProduct(Integer.parseInt(productSearchField.getText()));
            if(product == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Product does not exist");
                alert.showAndWait();
            } else {
                // Set items before selecting the products in case products were previously filtered with a string
                productsTableView.setItems(Inventory.getAllProducts());
                productsTableView.getSelectionModel().select(product);
            }
        } else {
            ObservableList<Product> filteredProducts = Inventory.lookupProduct(productSearchField.getText());
            if(filteredProducts.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist");
                alert.showAndWait();
            } else {
                productsTableView.setItems(filteredProducts);
                if(filteredProducts.size() == 1) {
                    productsTableView.getSelectionModel().select(filteredProducts.get(0));
                }
            }
        }
    }
    /**
     * triggered when the add button under the parts pane is clicked.
     * brings up and sends part data to the AddPartForm view.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionDisplayAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * triggered when the modify button under the parts pane is clicked.
     * brings up and sends part data to the ModifyPartForm view.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionDisplayModifyPart(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyPartFormController MPFController = loader.getController();
            MPFController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
            /** RUNTIME ERROR: Had to use catch NullPointerException for when there is nothing selected to modify */
        } catch(NullPointerException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Must select a part to modify");
            alert2.showAndWait();
        }
    }

    /**
     * triggered when the add button under the products pane is clicked.
     * brings up the AddProductForm view.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionDisplayAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * triggered when the modify button under products pane is clicked.
     * brings up and sends product data to the ModifyProductForm view.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionDisplayModifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyProductFormController MPFController = loader.getController();
            MPFController.sendProduct(productsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Must select a product to modify");
            alert2.showAndWait();
        }
    }
    /**
     * triggered when the delete button is clicked under the parts pane.
     * a confirmation pops up confirming the user wants to delete the part
     * and a logical error check is performed to see if the part is successfully
     * deleted.
     */
    public void onActionDeletePart() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this part?");
        alert.setTitle("Delete");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            boolean deletedPart = Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
            if(!deletedPart) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("Unable to delete part");
                alert2.showAndWait();
            } else {
                partsTableView.setItems(Inventory.getAllParts());
                partSearchField.setText("");
            }
        }
    }

    /**
     * triggered when the delete button is clicked under the products pane.
     * a confirmation pops up confirming the user wants to delete the product
     * and a logical error check is performed for whether the product has
     * associated parts and if the product was able to be successfully
     * deleted.
     */
    public void onActionDeleteProduct() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this product?");
        alert.setTitle("Delete");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Product product = productsTableView.getSelectionModel().getSelectedItem();
            if(product.getAllAssociatedParts().size() > 0) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("This product has parts");
                alert2.showAndWait();
                return;
            }
            boolean deletedProduct = Inventory.deleteProduct(product);
            if(!deletedProduct) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("Unable to delete product");
                alert2.showAndWait();
            } else {
                productsTableView.setItems(Inventory.getAllProducts());
                productSearchField.setText("");
            }
        }
    }

    /**
     * triggered when the exit button is clicked.
     * closes the application.
     */
    public void onActionExit() {
        System.exit(0);
    }

    /**
     * initializes the controller by populating the table views
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

        productsTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
