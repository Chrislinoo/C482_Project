package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyProduct {

    Stage stage;
    Parent scene;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableColumn<Product, Integer> bottomCol1MP;

    @FXML
    private TableColumn<Product, String> bottomCol2MP;

    @FXML
    private TableColumn<Product, Integer> bottomCol3MP;

    @FXML
    private TableColumn<Product, Double> bottomCol4MP;

    @FXML
    private Button cancelProductBtn;

    @FXML
    private TableView<?> modProdBottomTable;

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
    private TableView<?> modProdTopTable;

    @FXML
    private Label modProductLabel;

    @FXML
    private Button removeAssocPartBtn;

    @FXML
    private Button saveProductBtn;

    @FXML
    private TableColumn<Product, Integer> topCol1MP;

    @FXML
    private TableColumn<Product, String> topCol2MP;

    @FXML
    private TableColumn<Product, Integer> topCol3MP;

    @FXML
    private TableColumn<Product, Double> topCol4MP;

    @FXML
    void onActionCancelModProd(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized!");
    }

}








