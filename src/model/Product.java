package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

/**
 * This class holds the methods for Product and depends on the Abstract Part class
 */
public class Product {

    Random rnd = new Random();

    //Fields
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for the Product class.
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
     * Returns id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets integer type id value
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets String type name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets double type price value
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns stock
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets integer type stock value
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns minimum
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets integer type minimum value
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns maximum
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the integer type maximum value
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part object to the Observable list associated parts.
     * @param part
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Takes an part from the associated parts list and it is then removed from said list, deleting it from the graph
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }


    /**
     * Returns all parts in the observable list associated parts .
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }






}
