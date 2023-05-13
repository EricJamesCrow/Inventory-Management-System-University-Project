package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * class for Product. contains a constructor along with
 * getters and setters for id, name, price, stock, min, and max.
 * Contains an associatedParts ObservableList that has three methods for
 * interacting with it: addAssociatedPart, deleteAssociatedPart, and getAllAssociatedParts.
 */
public class Product {
    /**
     * id of the product
     */
    private int id;
    /**
     * name of the product
     */
    private String name;
    /**
     * price of the product
     */
    private double price;
    /**
     * inventory of the product
     */
    private int stock;
    /**
     * min of the product
     */
    private int min;
    /**
     * max of the product
     */
    private int max;
    /**
     * constructor for Product
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * ObservableList for associatedParts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * add a part to associatedParts
     * @param part
     */
    public void addAssociatedPart(Part part) { associatedParts.add(part); }

    /**
     * delete a part from associatedParts
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return the associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
