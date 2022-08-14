package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * This class creates an app for the inventory system
 */
public class Main extends Application {
    /**
     * Sets the fxml file for main menu
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * Loads test data to populate the tables in the main menu and launches the program.
     * FUTURE ENHANCEMENT: It would be great if in a future update we could link a database to keep track of all parts and products to maintain recorded history of all that was added.
     * @param args
     */
    public static void main(String[] args){

        Part CPU = new InHouse(1, "Ryzen X", 304.79, 4, 1, 20, 1);
        Inventory.addPart(CPU);

        Part RAM = new InHouse(2, "Crucial GB", 84.99, 8, 1, 20, 1);
        Inventory.addPart(RAM);

        Part SSD = new InHouse(3, "CS3030 TB", 65.00, 10, 1, 15, 1);
        Inventory.addPart(SSD);

        Part PSU = new InHouse(4, "Super Flower", 100.49, 9, 1, 15, 1);
        Inventory.addPart(PSU);

        Part GPU = new Outsourced(5, "MSI TI", 540.00, 5, 1, 10, "NVIDIA");
        Inventory.addPart(GPU);

        Part GPU2 = new Outsourced(6, "Radeon XT", 359.06, 4, 1, 10, "AMD");
        Inventory.addPart(GPU2);

        Product PC = new Product(001, "BlockDragon", 1089.00, 2, 1, 5 );
        Inventory.addProduct(PC);

        Product PC2 = new Product(002, "MiniCat", 1421.00, 3, 1, 5);
        Inventory.addProduct(PC2);



        launch(args);

    }

}
