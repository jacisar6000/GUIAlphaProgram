package sample;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

  @FXML
  private Tab tabProductLine;

  @FXML
  private Label labelProductName;

  @FXML
  private Label labelManufacturer;

  @FXML
  private Label labelItemType;

  @FXML
  private TextField productName;

  @FXML
  private TextField manufacturer;

  @FXML
  private ChoiceBox<String> type;

  @FXML
  private TableView<Product> tvProduct;

  @FXML
  private TableColumn<?, ?> productNameCol;

  @FXML
  private TableColumn<?, ?> manufacturerCol;

  @FXML
  private TableColumn<?, ?> typeCol;

  @FXML
  private Label labelExistingProducts;

  @FXML
  private Button addProduct;

  @FXML
  private Tab tabProduce;

  @FXML
  private Label labelChooseProduct;

  @FXML
  private ListView<Product> lvProduct;

  @FXML
  private Label labelChooseQuantity;

  @FXML
  private ComboBox<Integer> chooseQuantity;

  @FXML
  private Button recordProduction;

  @FXML
  private Tab tabProducitonLog;

  @FXML
  private TextArea productionLog;

  // a local observable array list drops down when the type of product is is clicked
  private ObservableList<Product> productLine = FXCollections.observableArrayList();
  // a local observable array list drops down when the number of products has been clicked
  private ObservableList<String> choiceList = FXCollections.observableArrayList();

  // when the record button is clicked, the amount of products produced is assigned to the production log once
  final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  // these are both local variables
  // conn creates the connection to the database and stmt allows for SQL statements to be created
  private Connection conn;
  private Statement stmt;
  // the prepared statement allows the SQL queries to be executed
  private PreparedStatement pstmt;

  // this function pulls the type of product from the database by using SQL statements
  // the strings are the pulled and when selected are added to the database
  private void loadProductList(ObservableList<Product> productLine) throws SQLException {
    String sql = "SELECT * FROM PRODUCT";
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String name = rs.getString(2);
      String manu = rs.getString(3);
      String chooseItem = rs.getString(4);

      // enters the name, manufacturer, and type of product into the database
      Product dbProduct = new Product(name, manu, ItemType.valueOf(chooseItem));
      productLine.add(dbProduct);
    }
  }

  // this function takes the values entered and sets them up to be displayed
  // in each cell of the column
  private void setupProductLine(ObservableList<Product> productLine) {
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    // this action sets the items entered into the table view
    tvProduct.setItems(this.productLine);
    // this action sets the items in a horizontal/vertical list in the table view
    lvProduct.setItems(this.productLine);
  }

  // this function displays the records produced into the production log in
  // the format of a string
  private void displayLog() {
    ProductionRecord record = new ProductionRecord(0);
    String product = record.toString();
    recordProduction.setText(product);
  }

  // this functions sets the text into the production log
  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    productionLog.setText(productionRun.toString());
  }


  // this function selects the information from the SQL query and dispalys it into
  // the text area of the production log
  private void loadProductionLog(ArrayList<ProductionRecord> productionRun) throws SQLException {
    String sql = "SELECT * FROM PRODUCTIONRECORD";
    stmt = conn.createStatement();

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      int number = rs.getInt("PRODUCTION_NUM");
      int id = rs.getInt("PRODUCT_ID");
      String serial = rs.getString("SERIAL_NUM");
      Date date = rs.getDate("DATE_PRODUCED");

      ProductionRecord dbRecord = new ProductionRecord(number, id, serial, date);

      productionRun.add(dbRecord);
      showProduction(productionRun);
    }
  }

  // this function adds the information entered into the production table
  private void addToProductionLog(ArrayList<ProductionRecord> productionRun) throws SQLException {
    // these are fields for the serial number, timestamp and productID
    for (ProductionRecord pr : productionRun) {
      int prodID = pr.getProductID();
      String serialNum = pr.getSerialNum();
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());

      // this string enters the productID, serial number, and date into a query in the database
      String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES (?,?,?)";
      pstmt = conn.prepareStatement(sql);
      // the prepared statement sets the the productionID, serialNumber, and date
      pstmt.setInt(1, prodID);
      pstmt.setString(2, serialNum);
      pstmt.setTimestamp(3, timestamp);

      pstmt.executeUpdate();
      // this displays the prepared statement to the production log
      showProduction(productionRun);
    }
  }

  @FXML
    // when the add product button is clicked, the action adds the information
    // to the database
  void buttonAddProduct(ActionEvent event) throws SQLException {

    // allows the text from the product name to be added to the database
    String name = productName.getText();
    // allows the text from the manufacturer to be added to the database
    String manu = manufacturer.getText();
    // allows the quantity chosen to be added to the database
    String chooseItem = type.getValue();
    // Values (?,?,?) is a placeholder for the name, manufacturer and type
    // When add product is clicked, the varibales take place of the placeholders
    String query = "INSERT INTO PRODUCT (NAME, MANUFACTURER, TYPE) VALUES (?,?,?)";

    pstmt = conn.prepareStatement(query);
    // sets the product name string into the first placeholder
    pstmt.setString(1, name);
    // sets the manufacturer string into the second placeholder
    pstmt.setString(2, manu);
    // sets the item type into the third placeholder
    pstmt.setString(3, chooseItem);
    pstmt.executeUpdate();

    System.out.println("Entered");

    // clears the data entered that was entered into the text field once add prudct
    // has been clicked
    productName.clear();
    manufacturer.clear();

    // calling the loadProductList function and adding the product line to it
    loadProductList(productLine);
    // calling the setupProductLine function and adding the product line to it
    setupProductLine(productLine);
    // displays the product line in the production log
    displayLog();

  }

  @FXML
  // this function allows the product quantity and string chosen to be added into the
  //production log when record button is clicked
  void buttonRecordProduction(ActionEvent event) throws SQLException {
    // selects the item in the list vew text area
    Product record = lvProduct.getSelectionModel().getSelectedItem();

    int quantity;
    // choose the quantity of items that were produced from the observable list
    quantity = Integer
        .parseInt(String.valueOf(chooseQuantity.getSelectionModel().getSelectedItem()));

    ProductionRecord pr;
    // this for loop is for adding the text into the production log for a certain amount of times
    for (int i = 0; i < quantity; i++) {
      pr = new ProductionRecord(record, i );
      productionRun.add(pr);
      // adds the production record in the product log
      addToProductionLog(productionRun);
      // shows the production record
      showProduction(productionRun);
      // loads the production record into the production log
      loadProductionLog(productionRun);
    }
  }

  // this function initializes the the choice list and enters the number of strings into the
  // production log
  public void initialize() throws SQLException {
    // initializes the database
    initializeDB();

    for (ItemType it : ItemType.values()) {
      choiceList.add(String.valueOf(it));
    }

    type.getItems().addAll(choiceList);
    // this observable array list creates a dropdown box that contains quantities
    ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    // sets a quantity for the amount of items
    chooseQuantity.setItems(list);
    // selects the amount of products ordered of the certain product
    chooseQuantity.getSelectionModel().selectFirst();
    // the quantity isn't universal and it's able  to be changed
    chooseQuantity.setEditable(true);

    loadProductList(productLine);
    setupProductLine(productLine);
    loadProductionLog(productionRun);

  }

  private void initializeDB() {

    //Unable to run the program without this code, I acknowledge it is a bug
    final String JDBC_DRIVER = "org.h2.Driver";
    //Unable to run the program without this code, I acknowledge it is a bug
    final String db_url = "jdbc:h2:./res/ProductDatabase";
    //  Database credentials
    //Unable to run the program without this code, I acknowledge it is a bug
    final String user = "";
    //Unable to run the program without this code, I acknowledge it is a bug
    final String pass = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}


