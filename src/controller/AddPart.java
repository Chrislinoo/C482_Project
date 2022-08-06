package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart {

    Stage stage;
    Parent scene;


    @FXML
    private Label addPartLbl;

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
    private RadioButton inHouseRadio;

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
    private Label nameLbl;

    @FXML
    private TextField nameTxtField;

    @FXML
    private RadioButton outSourcedRadio;

    @FXML
    private Label priceLbl;

    @FXML
    private TextField priceTxtField;

    @FXML
    private Button savePartBtn;


    @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();
    }

    public void inHouseRadioBtn(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            hybridLbl.setText("Machine ID");

        }
    }

    public void outSourcedRadioBtn(ActionEvent event) {
        if (outSourcedRadio.isSelected()) {
            hybridLbl.setText("Company Name");
        }
    }

    @FXML
    void onActionSavePartBtn(ActionEvent event) throws IOException {

        int id = Integer.parseInt(this.idTxtField.getText());
        String name = this.nameTxtField.getText();
        double price = Double.parseDouble(this.priceTxtField.getText());
        int stock = Integer.parseInt(this.priceTxtField.getText());
        int min = Integer.parseInt(this.minTxtField.getText());
        int max = Integer.parseInt(this.maxTxtField.getText());


        if (inHouseRadio.isSelected()) {
            int hybrid = Integer.parseInt(this.hybridTxtField.getText());
            Inventory.addPart(new InHouse(id, name,  price,  stock,  min,  max, hybrid));
        }

        if (outSourcedRadio.isSelected()) {
            String hybrid2 = this.hybridTxtField.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, hybrid2));
        }




        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        stage.setScene(new Scene(scene));

        stage.show();


    }



}
