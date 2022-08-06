package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args){
//Test Data
        Part CPU = new InHouse(1, "Ryzen 7 3700x", 304.79, 4, 1, 20, 1);
        Inventory.addPart(CPU);

        Part RAM = new InHouse(2, "Crucial 16GB", 84.99, 8, 1, 20, 1);
        Inventory.addPart(RAM);

        Part SSD = new InHouse(3, "CS3030 1TB", 65.00, 10, 1, 15, 1);
        Inventory.addPart(SSD);

        Part PSU = new InHouse(4, "Super Flower 750W", 100.49, 9, 1, 15, 1);
        Inventory.addPart(PSU);

        Part GPU = new Outsourced(5, "MSI 3060TI", 540.00, 5, 1, 10, "NVIDIA");
        Inventory.addPart(GPU);

        Part GPU2 = new Outsourced(6, "Radeon 6650XT", 359.06, 4, 1, 10, "AMD");
        Inventory.addPart(GPU2);

        Product PC = new Product(1, "BlockDragon", 1089.00, 2, 1, 5 );
        Inventory.addProduct(PC);

        Product PC2 = new Product(2, "MiniCat", 1421.00, 1, 1, 5);
        Inventory.addProduct(PC2);



        launch(args);

    }

}
