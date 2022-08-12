package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * This is the controller for the "MainScreen.fxml" file. It's responsible for what you see as soon as the application comes up.
 */
public class MainScreen implements Initializable {


    Stage stage;
    Parent scene;

    ObservableList parts;
    ObservableList products;

    @FXML
    private Button menuExitBtn;

    @FXML
    private Button partAddBtn;

    @FXML
    private Button partDelBtn;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private Button partModBtn;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private Button productAddBtn;

    @FXML
    private Button productDelBtn;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private Button productModBtn;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TextField searchPartText;

    @FXML
    private TextField searchProductText;

    /**
     * Redirects user to the Add Part page.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();

    }

    /**
     * Deletes Part. If a part is selected from the parts table , said part will be deleted.
     * If no part is selected and "Delete" button is clicked , it will throw an error code demanding a part to be selected first.
     * @param event
     */
    @FXML
    void onActionDelPart(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete selected part, do you wish to continue?");
            alert.setTitle("Delete");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Part onePart;
                onePart = partsTableView.getSelectionModel().getSelectedItem();
                Inventory.deletePart(onePart);
            }
        }
        catch ( NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a Part to delete first.");
            alert.show();
        }
    }

    /**
     * Closes the application.
     * @param event
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }

    /**
     * Redirects user to the Modify Part page. If no part is selected , throws an error page.
     * If a part is selected , takes the information of that part by using the "partTransfer" method (from the "ModifyPart.java" controller) and inputs it into the appropriate text fields.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {//Doesnt show Alert!!!
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            loader.load();

            ModifyPart modifyPart = loader.getController();
            modifyPart.partTransfer(partsTableView.getSelectionModel().getSelectedIndex(),partsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
        catch (NullPointerException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Make a part selection first.");
            alert.show();

        }


    }

    /**
     * Redirects user to the Add Product page.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /**
     * Redirects user to the Modify Product page. If a product is not selected , it throws an error.
     * If a product is selected , takes the information of that product by using the "productTransfer" method (from the "ModifyProduct.java" controller) and inputs it into the appropriate text fields.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();

            ModifyProduct modifyProduct = loader.getController();
            modifyProduct.productTransfer(productsTableView.getSelectionModel().getSelectedIndex(), productsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Make a product selection first");
            alert.show();
        }
    }


    /**
     * Initializes and populates both tables in the Main Screen.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(Inventory.getAllProducts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));






    }

    /**
     * Deletes Product. Upon "Delete" button clicked, deletes the selected Product.
     * If no product is selected before button is clicked , throws an error.
     * @param event
     */
    public void onActionDeleteProduct(ActionEvent event) {
        try {

            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete selected product, do you wish to continue?");
            alert.setTitle("Delete");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() & result.get() == ButtonType.OK) {
                Product deletedProduct = productsTableView.getSelectionModel().getSelectedItem();
                if (deletedProduct.getAllAssociatedParts().size() > 0) {
                    Alert invalidDel = new Alert(Alert.AlertType.ERROR);
                    invalidDel.setTitle("Error");
                    invalidDel.setContentText("Clear Associated Parts Before Deleting Desired Product.");
                    invalidDel.showAndWait();
                    return;
                }
                Inventory.deleteProduct(deletedProduct);
            }
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Clear Associated Parts Before Deleting Desired Product.");
            alert.showAndWait();
        }
    }

    /**
     * Table data filter. Searches for the input typed into the part search box.
     * Can be filtered through ID or name (full or partial). Throws an error if no part is found.
     * @param event
     */
    public void onActionMSPartSearch(ActionEvent event) {
        String textSearch = searchPartText.getText();
        ObservableList<Part> output = Inventory.lookupPart(textSearch);

        try {
            while(output.size() == 0 ){
                int partId = Integer.parseInt(textSearch);
                output.add(Inventory.lookupPart(partId));
            }
            partsTableView.setItems(output);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Nonexistent Part.");
            alert.showAndWait();
        }
    }


    /**
     * Table data filter. Searches for the input typed into the product search box.
     * Can be filtered through ID or name (full or partial). Throws an error if no part is found.
     * @param event
     */
    public void onActionMSProductSearch(ActionEvent event) {
        String productSearch = searchProductText.getText();
        ObservableList<Product> outcome = Inventory.lookupProduct(productSearch);

        try {
            while(outcome.size() == 0){
                int productId = Integer.parseInt(productSearch);
                outcome.add(Inventory.lookupProduct(productId));
            }
            productsTableView.setItems(outcome);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Nonexistent Product.");
            alert.showAndWait();
        }
    }
}

