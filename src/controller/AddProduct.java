package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Part;

import java.io.IOException;

public class AddProduct {

     Stage stage;
     Parent scene;

     @FXML
     private TableColumn<Part, String> BottomCol2;

     @FXML
     private TableColumn<Part, Integer> BottomCol3;

     @FXML
     private TableColumn<Part, Double> BottomCol4;

     @FXML
     private Button addProductBtn;

     @FXML
     private TableColumn<Part, Integer> bottomCol1;

     @FXML
     private Button cancelProductBtn;

     @FXML
     private Label idLabel;

     @FXML
     private Label invLabel;

     @FXML
     private Label maxLabel;

     @FXML
     private Label minLabel;

     @FXML
     private Label nameLabel;

     @FXML
     private Label priceLabel;

     @FXML
     private Button removeAssocPartBtn;

     @FXML
     private Button saveProductBtn;

     @FXML
     private TextField searchTextField;

     @FXML
     private TableView<?> tableViewAssoPart;

     @FXML
     private TableView<?> tableViewPart;

     @FXML
     private TextField textFieldID;

     @FXML
     private TextField textFieldInv;

     @FXML
     private TextField textFieldMax;

     @FXML
     private TextField textFieldMin;

     @FXML
     private TextField textFieldName;

     @FXML
     private TextField textFieldPrice;

     @FXML
     private TableColumn<Part, Integer> topCol1;

     @FXML
     private TableColumn<Part, String> topCol2;

     @FXML
     private TableColumn<Part, Integer> topCol3;

     @FXML
     private TableColumn<Part, Double> topCol4;

     @FXML
     void onActionCancelProd(ActionEvent event) throws IOException {
          stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

          scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

          stage.setScene(new Scene(scene));

          stage.show();
     }









}
