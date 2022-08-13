package controller;

import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

/**
 * This class is responsible for modifying products and its the controller for the "ModifyProduct.fxml" file
 */
public class ModifyProduct implements Initializable {

    Stage stage;
    Parent scene;

    private int  chosenIndex = 0;

    Product selectedProduct;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableColumn<Part, Integer> bottomCol1MP;

    @FXML
    private TableColumn<Part, String> bottomCol2MP;

    @FXML
    private TableColumn<Part, Integer> bottomCol3MP;

    @FXML
    private TableColumn<Part, Double> bottomCol4MP;

    @FXML
    private Button cancelProductBtn;

    @FXML
    private TableView<Part> modProdBottomTable;

    @FXML
    private Label modProdIDLabel;

    @FXML
    private TextField modProdIDTF;

    @FXML
    private Label modProdInvLabel;

    @FXML
    private TextField modProdInvTF;

    @FXML
    private Label modProdMaxLabel;

    @FXML
    private TextField modProdMaxTF;

    @FXML
    private Label modProdMinLabel;

    @FXML
    private TextField modProdMinTF;

    @FXML
    private Label modProdNameLabel;

    @FXML
    private TextField modProdNameTF;

    @FXML
    private Label modProdPriceLabel;

    @FXML
    private TextField modProdPriceTF;

    @FXML
    private TextField modProdSearchTF;

    @FXML
    private TableView<Part> modProdTopTable;

    @FXML
    private Label modProductLabel;

    @FXML
    private Button removeAssocPartBtn;

    @FXML
    private Button saveProductBtn;

    @FXML
    private TableColumn<Part, Integer> topCol1MP;

    @FXML
    private TableColumn<Part, String> topCol2MP;

    @FXML
    private TableColumn<Part, Integer> topCol3MP;

    @FXML
    private TableColumn<Part, Double> topCol4MP;
    /**
     * Used to hold the associated lists and methods involving it
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Redirects user to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancelModProd(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();

    }

    /**
     * Initializes and populates the tables
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modProdTopTable.setItems(Inventory.getAllParts());
        
        topCol1MP.setCellValueFactory(new PropertyValueFactory<>("id"));
        topCol2MP.setCellValueFactory(new PropertyValueFactory<>("name"));
        topCol3MP.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topCol4MP.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        modProdBottomTable.setItems(associatedParts);
        
        bottomCol1MP.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomCol2MP.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomCol3MP.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomCol4MP.setCellValueFactory(new PropertyValueFactory<>("price"));



    }

    /**
     * Searches for the input typed in the text box above the table view to filter desired parts by ID or name (full or partial).
     * @param event
     */
    public void onActionSearchText(ActionEvent event) {
        String textSearch = modProdSearchTF.getText();
        ObservableList<Part> outcome = Inventory.lookupPart(textSearch);
        try {
            while (outcome.size() == 0){
                int partId = Integer.parseInt(textSearch);
                outcome.add(Inventory.lookupPart(partId));
            }
            modProdTopTable.setItems(outcome);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Part not found.");
            alert.showAndWait();
        }
    }

    /**
     * Grabs information of a selected product from the main menu and gets the appropriate information.
     * Then that information is brought to the modify product screen so that it can be edited.
     * @param selectedIndex
     * @param product
     */
    public void productTransfer(int selectedIndex, Product product){
        chosenIndex = selectedIndex;

        this.modProdIDTF.setText(String.valueOf(product.getId()));
        this.modProdNameTF.setText(product.getName());
        this.modProdInvTF.setText(String.valueOf(product.getStock()));
        this.modProdPriceTF.setText(String.valueOf(product.getPrice()));
        this.modProdMaxTF.setText(String.valueOf(product.getMax()));
        this.modProdMinTF.setText(String.valueOf(product.getMin()));

        for (Part part: product.getAllAssociatedParts()){
            associatedParts.add(part);
        }
        Inventory.updateProduct(selectedIndex, product);
    }

    /**
     * Adds a part to the associated parts list. If no part is selected it throws a warning.
     * @param event
     */
    public void onActionAddPart(ActionEvent event) {
        Part partSelected = modProdTopTable.getSelectionModel().getSelectedItem();
        if (partSelected == null){
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Error");
            warning.setContentText("Select Part First.");
            warning.showAndWait();
            return;
        } else if (!associatedParts.contains(partSelected)) {
            associatedParts.add(partSelected);
            modProdBottomTable.setItems(associatedParts);
        }
    }

    /**
     * Takes the associated part and attaches it to the product that was being modified. Then it redirects the user to the main menu
     * Throws errors if any information needs to be revised
     * @param event
     */
    public void onActionSaveAssoPart(ActionEvent event) {
        try {
            Inventory.updateProduct(chosenIndex, selectedProduct);

            int id = Integer.parseInt(modProdIDTF.getText());
            String name = modProdNameTF.getText();
            int stock = Integer.parseInt(modProdInvTF.getText());
            double price = Double.parseDouble(modProdPriceTF.getText());
            int max = Integer.parseInt(modProdMaxTF.getText());
            int min = Integer.parseInt(modProdMinTF.getText());

            if (min > max){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Max must be greater than min");
                alert.showAndWait();
                return;
            } else if (stock < min || max < stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inventory should be less than max and greater than min.");
                alert.showAndWait();
                return;

            }

            Product product = new Product(id, name, price, stock, min, max);

            if (product != associatedParts){
                Inventory.updateProduct(chosenIndex, product);
            }

            for (Part part: associatedParts){
                if (part != associatedParts)
                    product.addAssociatedPart(part);
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            stage.setScene(new Scene(scene));

            stage.show();

        }
        catch (NumberFormatException | IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Check Input.");
            alert.showAndWait();
            return;
        }

    }

    /**
     * Removes a part that was attached to a product, throws a warning if the user tries to delete an unselected part.
     * @param event
     */
    public void onActionRemovePart(ActionEvent event) {
        Part selectedPart = modProdBottomTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select Part First.");
            alert.showAndWait();
            return;
        } else if (associatedParts.contains(selectedPart)) {
            associatedParts.remove(selectedPart);
            modProdBottomTable.setItems(associatedParts);

        }
    }
}








