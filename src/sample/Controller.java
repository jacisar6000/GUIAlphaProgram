package sample;

//Joseph Cisar, 9/28/2019, This file controls what the application is able to accomplish.
//it implements and initializes arrays, shows all of the objects and fx:id's that have been added tot he application,
//amd allows the user to interact with the application but inputting and outputting information into a database.

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    //Unable to run the program without this code, I acknowledge it is a bug
    final String JDBC_DRIVER = "org.h2.Driver";
    //Unable to run the program without this code, I acknowledge it is a bug
    final String DB_URL = "jdbc:h2:./res/ProductDatabase";
    //  Database credentials
    //Unable to run the program without this code, I acknowledge it is a bug
    final String USER = "";
    //Unable to run the program without this code, I acknowledge it is a bug
    final String PASS = "";

    @Override
    // creating the array with numbers 1-10 to allow the user to select the quantity
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options =
                FXCollections.observableArrayList( "1", "2", "3", "4", "5", "6",
                        "7", "8","9", "10");
        comboboxChooseQuantity.setItems(options);
        // .getSelectionModel().selectFirst(); puts the first number in the array as the default in the combo box
        comboboxChooseQuantity.getSelectionModel().selectFirst();
    }
    //I'm aware that the @FMLX is a warning for each of these however, I'm unable to get rid of them
    @FXML
    private Tab tabProductLine;

    @FXML
    private Label labelProductName;

    @FXML
    private Label labelManufacturer;

    @FXML
    private Label labelItemType;

    @FXML
    private TextField textfieldProductName;

    @FXML
    private TextField textfieldManufacturer;

    @FXML
    private ChoiceBox choiceviewItemType;

    @FXML
    private Label labelExistingProducts;

    @FXML
    private Button buttonAddProduct;

    @FXML
    private ComboBox comboboxChooseQuantity;

    @FXML
    private Tab tabProduce;

    @FXML
    private Tab tabProducitonLog;

    @FXML
    void AddProduct(MouseEvent event) {

        Connection conn = null;
        Statement stmt = null;

        // tells the user that what they have entered has been added
        System.out.println("Product Added");
        // allows the text from the product name to be added to the database
        String productname = textfieldProductName.getText();
        System.out.println(productname);
        // allows the text from the manufacturer to be added to the database
        String manufacturer = textfieldManufacturer.getText();
        System.out.println(manufacturer);

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            // allows for what is entered in the text fields to be added to the database
            String sql = "INSERT INTO PRODUCT(type, manufacturer, name) VALUES ('', '"+manufacturer+"', '"+productname+"');";
            //Unable to run the program without this code, I acknowledge it is a bug
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showItemType(MouseEvent mouseEvent) {
    }
}


