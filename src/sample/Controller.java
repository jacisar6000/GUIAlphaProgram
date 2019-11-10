package sample;

//Joseph Cisar, 11/8/2019, This file controls what the application is able to accomplish.
//it implements and initializes arrays, shows all of the objects and fx:id's that have been added tot he application,
//amd allows the user to interact with the application but inputting and outputting information into a database.

import java.beans.PropertyChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
    private ObservableList<Object> productLine;

    @Override
    // creating the array with numbers 1-10 to allow the user to select the quantity
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options =
            FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10");
        comboboxChooseQuantity.setItems(options);
        // .getSelectionModel().selectFirst(); puts the first number in the array as the default in the combo box
        comboboxChooseQuantity.getSelectionModel().selectFirst();
        // shows a list the the user can choose with type of product to choose from
        ObservableList<String> option =
            FXCollections.observableArrayList("AUDIO AU", "VISUAL VI", "AUDIO_MOBILE AM",
                "VISUAL_MOBILE VM");
        choiceviewItemType.setItems(option);
        choiceviewItemType.getSelectionModel();
    }

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
    private TableView<Product> tableView;

    @FXML
    private TableColumn<?, ?> productNameCol;

    @FXML
    private TableColumn<?, ?> manufacturerCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    void AddProduct(MouseEvent event) {

        // allows the text from the product name to be added to the database
        String productName = textfieldProductName.getText();
        // allows the text from the manufacturer to be added to the database
        String manufacturer = textfieldManufacturer.getText();
        // allows the quantity chosen to be added to the database
        ItemType itemType = (ItemType) comboboxChooseQuantity.getValue();

        // adds each of the following below to the table view
        productLine.add((new Widget(productName, manufacturer, itemType)));
        String type = "";
        Connection conn = null;
        Statement stmt = null;

        ObservableList options = null;
        comboboxChooseQuantity.setItems(options);
        // allows the items shown to be editable but the users choice
        comboboxChooseQuantity.setEditable(true);
        // shows the first option by default in the combobox
        comboboxChooseQuantity.getSelectionModel().selectFirst();
        productLine = FXCollections.observableArrayList();

        // adds a new row to the column name
        productNameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        // adds a new row to the column manufacturer
        manufacturerCol.setCellValueFactory(new PropertyValueFactory("Manufacturer"));

        ComboBox<Object> tableViewExisitngProducts = null;
        // allows the products that have already been produced to be seen
        tableViewExisitngProducts.setItems(productLine);
        ComboBox<Object> listViewChooseProducts = null;
        // allows the list of items to be chosen to be seen
        listViewChooseProducts.setItems(productLine);
        // tells the user that what they have entered has been added
        System.out.println("Product Added");
        System.out.println(productName);
        System.out.println(manufacturer);

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            // allows for what is entered in the text fields to be added to the database
            String sql =
                "INSERT INTO PRODUCT(type, manufacturer, name) VALUES ('', '" + manufacturer
                    + "', '" + productName + "');";
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

        public void initialize() {
        // adds the name, manufacturer, and type of product to the table view
            ObservableList<Product> data = populateList();
            productNameCol.setCellValueFactory(new PropertyValueFactory("productName"));
            manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
            typeCol.setCellValueFactory(new PropertyValueFactory("type"));
            tableView.setItems(data);

        }
        // create an array list that will, by default, show users what products are already in the table view
        public static ObservableList<Product> populateList() {
            return FXCollections.observableArrayList(
                new Product("Echo", "Amazon", ItemType.AUDIO),
                new Product("FireStick", "Amazon", ItemType.Visual),
                new Product("IPod", "Apple", ItemType.AudioMobile),
                new Product("LED", "Samsung", ItemType.VisualMobile),
                new Product("AirPods", "Apple", ItemType.AUDIO));
        }

        public void showItemType(MouseEvent mouseEvent) {
        }
    }


