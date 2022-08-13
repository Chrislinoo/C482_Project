package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;

/**
 * This class is responsible for modifying parts and is the controller for the "ModifyPart.fxml" file
 */
public class ModifyPart {

    Stage stage;
    Parent scene;

    Part selectedPart;
    int selectedIndex;

    @FXML
    private Button cancelPartBtn;

    @FXML
    private Label hybridLbl;

    @FXML
    private TextField hybridTxtField;

    @FXML
    private Label idLbl;

    @FXML
    private TextField idTxtField;

    @FXML
    private RadioButton inHouseRadioBtn;

    @FXML
    private ToggleGroup inOrOutsourced;

    @FXML
    private Label invLbl;

    @FXML
    private TextField invTxtField;

    @FXML
    private Label maxLbl;

    @FXML
    private TextField maxTxtField;

    @FXML
    private Label minLbl;

    @FXML
    private TextField minTxtField;

    @FXML
    private Button modPartSaveBtn;

    @FXML
    private Label modifyPartLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private TextField nameTxtField;

    @FXML
    private RadioButton outSourcedRadioBtn;

    @FXML
    private Label priceLbl;

    @FXML
    private TextField priceTxtField;


    /**
     * Returns the user to the main menu screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    /**
     * If radio button "In-House" is selected, it sets the hybrid text field to "Machine ID".
     * @param event
     */
    @FXML
    void onActionInHouseBtn(ActionEvent event) {

        if (inHouseRadioBtn.isSelected()) {
            hybridLbl.setText("Machine ID");

        }

    }

    /**
     * Grabs information of a selected partfrom the main menu and determines whether it is in house or outsourced to get the appropriate information.
     *Then that information is brought to the modify part screen so that it can be edited.
     * @param index
     * @param part
     */
    public void partTransfer(int index, Part part){
         selectedPart = part;
         selectedIndex = index;

         //This is here to invoke the catch exception in the event that
        //the part object is null. getId is a function in both objects so it's a safe
        //key to call.
        this.idTxtField.setText(String.valueOf(part.getId()));

        if (part instanceof InHouse){

            InHouse renew = (InHouse) part;
            inHouseRadioBtn.setSelected(true);
            hybridLbl.setText("Machine ID");
            this.idTxtField.setText(String.valueOf(renew.getId()));
            this.nameTxtField.setText(renew.getName());
            this.invTxtField.setText(String.valueOf(renew.getStock()));
            this.priceTxtField.setText(String.valueOf(renew.getPrice()));
            this.minTxtField.setText(String.valueOf(renew.getMin()));
            this.maxTxtField.setText(String.valueOf(renew.getMax()));
            this.hybridTxtField.setText(String.valueOf(renew.getMachineId()));
            Inventory.updatePart(selectedIndex, renew);



        }

        if (part instanceof Outsourced){

            Outsourced renew = (Outsourced) part;
            outSourcedRadioBtn.setSelected(true);
            hybridLbl.setText("Company Name");
            this.idTxtField.setText(String.valueOf(renew.getId()));
            this.nameTxtField.setText(renew.getName());
            this.invTxtField.setText(String.valueOf(renew.getStock()));
            this.priceTxtField.setText(String.valueOf(renew.getPrice()));
            this.minTxtField.setText(String.valueOf(renew.getMin()));
            this.maxTxtField.setText(String.valueOf(renew.getMax()));
            this.hybridTxtField.setText(renew.getCompanyName());
            Inventory.updatePart(selectedIndex, renew);
        }
    }


    /**
     * Updates the part that was selected to be edited with the newly introduced field values.
     * Throws an error if some values need to be re evaluated to make sense. Lastly it redirects the user to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModPartSave(ActionEvent event) throws IOException {

        try {
            Inventory.updatePart(selectedIndex, selectedPart);

            int id = Integer.parseInt(this.idTxtField.getText());
            String name = nameTxtField.getText();
            double price = Double.parseDouble(priceTxtField.getText());
            int inventory = Integer.parseInt(invTxtField.getText());
            int max = Integer.parseInt(maxTxtField.getText());
            int min = Integer.parseInt(minTxtField.getText());


            if (min > max){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max must be greater than min.");
                alert.showAndWait();
                return;
            } else if (inventory < min || max < inventory) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory should be less than max and greater than min.");
                alert.showAndWait();
                return;
            }

            if (inHouseRadioBtn.isSelected()) {
                int machId = Integer.parseInt(hybridTxtField.getText());
                InHouse inhouse = new InHouse(id, name, price, inventory, min, max, machId);
                Inventory.getAllParts().set(selectedIndex, inhouse);
            }

            if (outSourcedRadioBtn.isSelected()) {
                String compName = (hybridTxtField.getText());
                Outsourced outsourced = new Outsourced(id, name, price, inventory, min, max, compName);
                Inventory.getAllParts().set(selectedIndex, outsourced);
            }


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            stage.setScene(new Scene(scene));

            stage.show();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Check input values.");
            alert.showAndWait();
            return;
        }

    }

    /**
     * If radio button "Outsourced" is selected, it sets the hybrid text field to "Company Name".
     * @param event
     */
    @FXML
    void onActionOutSourcedBtn(ActionEvent event) {

        if (outSourcedRadioBtn.isSelected()) {
            hybridLbl.setText("Company Name");
        }
    }
}
