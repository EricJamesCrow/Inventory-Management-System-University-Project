package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * class for Inventory which contains the methods:
 * addPart, addProduct, getAllParts, getAllProducts,
 * lookupPart (by id), lookupProduct (by id), lookupPart (by name),
 * lookupProduct (by name), updatePart, updateProduct, deletePart,
 * and deleteProduct.
 * FUTURE ENHANCEMENT: Incorporate data persistence. Add a method that saves
 * the current inventory to a text file.
 */
public class Inventory {
    /**
     * ObservableList for all the parts in inventory
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * ObservableList for all the products in inventory
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * adds a part to the allParts ObservableList
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    /**
     * adds a part to the allProducts ObservableList
     * @param newProduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * returns allParts ObservableList
     * @return allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * returns allProducts ObservableList
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * looks up a part by id
     * @param partId
     * @return Part
     */
    public static Part lookupPart(int partId) {
        for(Part part : allParts) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * looks up a product by id
     * @param productId
     * @return Product
     */
    public static Product lookupProduct(int productId) {
        for(Product product : allProducts) {
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * looks up a part by name
     * @param partName
     * @return Part
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();

        for(Part part: allParts) {
            if(part.getName().contains(partName)) {
                filteredParts.add(part);
            }
        }

        if(filteredParts.isEmpty() && partName == "") {
            return allParts;
        } else {
            return filteredParts;
        }
    }

    /**
     * looks up a product by name
     * @param productName
     * @return Product
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

        for(Product product: allProducts) {
            if(product.getName().contains(productName)) {
                filteredProducts.add(product);
            }
        }

        if(filteredProducts.isEmpty() && productName == "") {
            return allProducts;
        } else {
            return filteredProducts;
        }
    }

    /**
     * updates a part by index
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * updates a product by index
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * removes the selected part from allParts
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * removes the selected product from allProducts
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
}
