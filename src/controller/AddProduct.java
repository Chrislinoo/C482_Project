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

public class AddProduct implements Initializable {

     Stage stage;
     Parent scene;

     private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();

     @FXML
     private TableColumn<Part, String> bottomCol2;

     @FXML
     private TableColumn<Part, Integer> bottomCol3;

     @FXML
     private TableColumn<Part, Double> bottomCol4;

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
     private TableView<Part> tableViewAssoPart;

     @FXML
     private TableView<Part> tableViewPart;

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
     void onActionAddPartBtn(ActionEvent event){       //WORKS!!!!
          Part selectedPart = tableViewPart.getSelectionModel().getSelectedItem();
          if (selectedPart == null){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Error");
               alert.setContentText("Select Part First");
               alert.showAndWait();
               return;
          } else if (!associatedPartList.contains(selectedPart)) {
               associatedPartList.add(selectedPart);
               tableViewAssoPart.setItems(associatedPartList);

          }
     }

     @FXML
     void onActionRemoveAsso(ActionEvent event){       //WORKS!!
          Part selectedPart = tableViewAssoPart.getSelectionModel().getSelectedItem();
          if (selectedPart == null){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Error");
               alert.setContentText("Select Part First");
               alert.showAndWait();
               return;
          } else if (associatedPartList.contains(selectedPart)) {
               associatedPartList.remove(selectedPart);
               tableViewAssoPart.setItems(associatedPartList);

          }
     }

     @FXML
     void onActionSaveAssoProd(ActionEvent event) throws IOException {     //Not Working!!!
          try{
               int id = Integer.parseInt(textFieldID.getText());
               String name = textFieldName.getText();
               int stock = Integer.parseInt(textFieldInv.getText());
               double price = Double.parseDouble(textFieldPrice.getText());
               int max = Integer.parseInt(textFieldMax.getText());
               int min = Integer.parseInt(textFieldMin.getText());


               if (min > max){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Max must be greater than min!");
                    alert.showAndWait();
                    return;
               } else if (stock < min || max < stock) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory should be less than max & greater than min.");
                    alert.showAndWait();
                    return;
               }

               Product product = new Product(id, name, price, stock, min, max);

               for (Part part: associatedPartList){
                    if (part != associatedPartList){
                         product.addAssociatedPart(part);
                    }
               }

               Inventory.getAllProducts().add(product);


               stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

               scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

               stage.setScene(new Scene(scene));

               stage.show();

          }
          catch (NumberFormatException e){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Error");
               alert.setContentText("Check Input");
               alert.showAndWait();
               return;
          }

     }



     @Override
     public void initialize(URL url, ResourceBundle resourceBundle){  //Not Working!!!
          tableViewPart.setItems(Inventory.getAllParts());

          topCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
          topCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
          topCol3.setCellValueFactory(new PropertyValueFactory<>("stock"));
          topCol4.setCellValueFactory(new PropertyValueFactory<>("price"));

          tableViewAssoPart.setItems(associatedPartList);

          bottomCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
          bottomCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
          bottomCol3.setCellValueFactory(new PropertyValueFactory<>("stock"));
          //bottomCol4.setCellValueFactory(new PropertyValueFactory<>("price"));
     }

     @FXML
     void onActionCancelProd(ActionEvent event) throws IOException {
          stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

          scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

          stage.setScene(new Scene(scene));

          stage.show();
     }

     @FXML
     void onActionSearchTxtField (ActionEvent event){//Not Searching Up by ID
          String textSearch = searchTextField.getText();
          ObservableList<Part> outcome = Inventory.lookupPart(textSearch);

          try {
               while (outcome.size() == 0){
                    int id = Integer.parseInt(String.valueOf(outcome));
                    outcome.add(Inventory.lookupPart(id));
               }
               tableViewPart.setItems(outcome);
          }
          catch (NumberFormatException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setContentText("Not Found");
               alert.showAndWait();
          }
     }


}











