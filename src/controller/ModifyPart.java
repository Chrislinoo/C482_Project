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



    @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    @FXML
    void onActionInHouseBtn(ActionEvent event) {

        if (inHouseRadioBtn.isSelected()) {
            hybridLbl.setText("Machine ID");

        }

    }

    public void partTransfer(int index, Part part){//Not Working Correctly
         selectedPart = part;
         selectedIndex = index;


        if (part instanceof InHouse){//Not Working Correctly

            InHouse renew = (InHouse) part;
            inHouseRadioBtn.setSelected(true);
            hybridLbl.setText("Machine ID");
            this.idTxtField.setText(String.valueOf(renew.getId()));
            this.nameTxtField.setText(renew.getName());
            this.invTxtField.setText(String.valueOf(renew.getStock()));
            this.minTxtField.setText(String.valueOf(renew.getMin()));
            this.maxTxtField.setText(String.valueOf(renew.getMax()));
            this.hybridTxtField.setText(String.valueOf(renew.getMachineId()));
            Inventory.updatePart(selectedIndex, renew);



        }

        if (part instanceof Outsourced){//Not Working Correctly

            Outsourced renew = (Outsourced) part;
            inHouseRadioBtn.setSelected(true);
            hybridLbl.setText("Machine ID");
            this.idTxtField.setText(String.valueOf(renew.getId()));
            this.nameTxtField.setText(renew.getName());
            this.invTxtField.setText(String.valueOf(renew.getStock()));
            this.minTxtField.setText(String.valueOf(renew.getMin()));
            this.maxTxtField.setText(String.valueOf(renew.getMax()));
            this.hybridTxtField.setText(renew.getCompanyName());
            Inventory.updatePart(selectedIndex, renew);
        }
    }







    @FXML
    void onActionModPartSave(ActionEvent event) throws IOException {//Not Working Correctly
        Inventory.updatePart(selectedIndex, selectedPart);

        int id = Integer.parseInt(this.idTxtField.getText());
        String name = nameTxtField.getText();
        double price = Double.parseDouble(priceTxtField.getText());
        int inventory = Integer.parseInt(invTxtField.getText());
        int max = Integer.parseInt(maxTxtField.getText());
        int min = Integer.parseInt(minTxtField.getText());

        if (inHouseRadioBtn.isSelected()){
            int machId = Integer.parseInt(hybridTxtField.getText());
            InHouse inhouse = new InHouse(id, name, price, inventory, min, max, machId);
            Inventory.getAllParts().set(selectedIndex, inhouse);
        }

        if (outSourcedRadioBtn.isSelected()){
            String compName = (hybridTxtField.getText());
            Outsourced outsourced = new Outsourced(id, name, price, inventory, min, max, compName);
            Inventory.getAllParts().set(selectedIndex,outsourced);
        }



        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();

    }


    @FXML
    void onActionOutSourcedBtn(ActionEvent event) {

        if (outSourcedRadioBtn.isSelected()) {
            hybridLbl.setText("Company Name");
        }
    }
}
