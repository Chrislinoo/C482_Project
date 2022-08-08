package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static void setAllParts(ObservableList<Part> allParts) {
        Inventory.allParts = allParts;
    }


    public static void setAllProducts(ObservableList<Product> allProducts) {
        Inventory.allProducts = allProducts;
    }

    //UML List-----------------------------------------------------
    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    //Look up Methods
    public static Part lookupPart(int partId){

        for (Part part : Inventory.getAllParts()){
            if (part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId){
        for (Product product : Inventory.getAllProducts()){
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> PartName = FXCollections.observableArrayList();
        for (Part part : allParts){
            if (part.getName().contains(partName)){
                PartName.add(part);
            }
        }
        return PartName;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> ProductName = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if (product.getName().contains(productName)){
                ProductName.add(product);
            }
        }
        return ProductName;
    }

    //Update Methods

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }



    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);

    }

    public static boolean deletePart(Part selectedPart){

        for (Part part : allParts){
            if (part.getId() == selectedPart.getId()){
                allParts.remove(part);
                return true;
            }
        }
        return false;
    }

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
