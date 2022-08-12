package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The inventory class depends on both Part and Product. This class handles the methods that will be used to add parts and or products
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Sets all parts into the Parts observable list
     * @param allParts
     */
    public static void setAllParts(ObservableList<Part> allParts) {
        Inventory.allParts = allParts;
    }

    /**
     * Sets all products into the Products observable list
     * @param allProducts
     */
    public static void setAllProducts(ObservableList<Product> allProducts) {
        Inventory.allProducts = allProducts;
    }

    /**
     * Adds part object to the observable Parts list
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Returns all the parts in the list
     * @return
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Adds product to the observable products list
     * @param product
     */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * returns all products to the observable products list
     * @return
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Looks up part by ID
     * @param partId
     * @return
     */
    public static Part lookupPart(int partId){

        for (Part part : Inventory.getAllParts()){
            if (part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    /**
     * Looks up product by ID
     * @param productId
     * @return
     */
    public static Product lookupProduct(int productId){
        for (Product product : Inventory.getAllProducts()){
            if (product.getId() == productId){
                return product;
            }

        }
        return null;
    }

    /**
     * Looks up specific part in the part observable list
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> PartName = FXCollections.observableArrayList();
        for (Part part : allParts){
            if (part.getName().contains(partName)){
                PartName.add(part);
            }

            //TODO: Fix this, it won't add a number
            if(!partName.equals("") && part.getId() == Integer.parseInt(partName)) {
                PartName.add(part);
            }
            //try/catch exists to check if the string is even a number
            try{
                if(!partName.equals("") && part.getId() == Integer.parseInt(partName)) {
                    PartName.add(part);
                }
            } catch (NumberFormatException e) {
            }

        }
        return PartName;
    }

    /**
     * Looks up specific product in the product observable list
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> ProductName = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if (product.getName().contains(productName)){
                ProductName.add(product);
            }

            if(!productName.equals("") && product.getId() == Integer.parseInt(productName)){
                ProductName.add(product);
            }
        }
        return ProductName;
    }

    //--------------------Update Methods-----------------------------

    /**
     * Pass in a Part object and the index for which that object will be put in its place.
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }


    /**
     * Updates the selected product
     * @param index
     * @param selectedProduct
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);

    }

    /**
     * Deletes selected part
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart){

        for (Part part : allParts){
            if (part.getId() == selectedPart.getId()){
                allParts.remove(part);

                return true;
            }
        }
        return false;
    }

    /**
     * deletes selected product
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct){

        for (Product product : allProducts){
            if (product.getId() == selectedProduct.getId()){
                allProducts.remove(product);
                return true;
            }
        }
        return false;
    }


}
