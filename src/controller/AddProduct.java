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
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is responsible for adding products and is the controller for the "AddProduct.fxml" file
 */
public class AddProduct implements Initializable {
     Random rnd = new Random();

     Stage stage;
     Parent scene;

     private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

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

     /**
      * Adds a Part to the associated parts table. Uses the variable 'selectedPart' and assigns the part selected in the parts table view and then attaches to the Product.
      * If no part is selected, it throws a warning.
      * The selected Part then appears in the associated parts table.
      * @param event
      */
     @FXML
     void onActionAddPartBtn(ActionEvent event){
          Part selectedPart = tableViewPart.getSelectionModel().getSelectedItem();

          if (selectedPart == null){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Error");
               alert.setContentText("Select Part First");
               alert.showAndWait();
               return;
          } else if (!associatedParts.contains(selectedPart)) {
               associatedParts.add(selectedPart);
               tableViewAssoPart.setItems(associatedParts);

          }
     }

     /**
      * Removes the selected part from the associated parts table.
      * If no part is selected, it throws a warning.
      * @param event
      */
     @FXML
     void onActionRemoveAsso(ActionEvent event){
          Part selectedPart = tableViewAssoPart.getSelectionModel().getSelectedItem();
          if (selectedPart == null){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Error");
               alert.setContentText("Select Part First");
               alert.showAndWait();
               return;
          } else if (associatedParts.contains(selectedPart)) {
               associatedParts.remove(selectedPart);
               tableViewAssoPart.setItems(associatedParts);

          }
     }

     /**
      * Saves associated parts to a product. On action , saves the information added into the text fields to create a new Product and along with any associated parts attached.
      * Throws an error screen if any text field is blank or has any incorrect values.
      * Then redirects the user to the main screen.
      * @param event
      * @throws IOException
      */
     @FXML
     void onActionSaveAssoProd(ActionEvent event) throws IOException {
          try{
               int id = rnd.nextInt(1000);
               String name = textFieldName.getText();
               int stock = Integer.parseInt(textFieldInv.getText());
               double price = Double.parseDouble(textFieldPrice.getText());
               int max = Integer.parseInt(textFieldMax.getText());
               int min = Integer.parseInt(textFieldMin.getText());


               if (min > max){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Max must be greater than min.");
                    alert.showAndWait();
                    return;
               } else if (stock < min || max < stock) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory should be less than max and greater than min.");
                    alert.showAndWait();
                    return;
               }

               Product product = new Product(id, name, price, stock, min, max);

               for (Part part: associatedParts){
                    if (part != associatedParts){
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
               alert.setContentText("Check Input.");
               alert.showAndWait();
               return;
          }

     }


     /**
      * Initializes and populates all four columns of the table. Allows for data to be placed inside the associated table view when data is added.
      * RUNTIME ERROR: The command to set value into the cell "bottomCol4.setCellValueFactory(new PropertyValueFactory<>("price"));" was not working correctly. The application would not
      * start since it was returning a NULL value and could not initialize.
      * Solution: The solution was very simple but took me a few hours to notice. I had to restart the build up of the .fxml file that controlled the associated parts table
      * and re-name the columns so that I wouldn't get mixed up. After doing this it worked, so I am assuming the NULL that was returned was due to a misspelling or something like that.
      * @param url
      * @param resourceBundle
      */
     @Override
     public void initialize(URL url, ResourceBundle resourceBundle){
          tableViewPart.setItems(Inventory.getAllParts());

          topCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
          topCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
          topCol3.setCellValueFactory(new PropertyValueFactory<>("stock"));
          topCol4.setCellValueFactory(new PropertyValueFactory<>("price"));

          tableViewAssoPart.setItems(associatedParts);

          bottomCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
          bottomCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
          bottomCol3.setCellValueFactory(new PropertyValueFactory<>("stock"));
          bottomCol4.setCellValueFactory(new PropertyValueFactory<>("price"));
     }

     /**
      * Upon "Cancel" button click, redirects user to the main screen.
      * @param event
      * @throws IOException
      */
     @FXML
     void onActionCancelProd(ActionEvent event) throws IOException {
          stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

          scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

          stage.setScene(new Scene(scene));

          stage.show();
     }

     /**
      * Filters the parts' table to look for desired part by ID or name (full or partial).
      * If input does not satisfy search conditions it throws up an error code.
      * @param event
      */
     @FXML
     void onActionSearchTxtField (ActionEvent event){
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











