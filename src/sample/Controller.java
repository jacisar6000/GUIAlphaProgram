package sample;

/**
 * Joseph Cisar, 12/5/2019. The controller contains all of the methods and functions that allow the
 * program to run correctly. It combines each of the files actions and runs them in the program. It
 * integrates the database, fxml, and normal java programming.
 */

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

  @FXML
  private Tab employeeTab;

  @FXML
  private TextField employeeNameTextField;

  @FXML
  private TextField employeePasswordTextField;

  @FXML
  private Label labelEmployeeDetails;

  @FXML
  private Button loginButton;

  @FXML
  private TextArea employeeTextArea;

  /**
   * A local observable array list drops down when the type of product is is clicked.
   */
  private ObservableList<Product> productLine = FXCollections.observableArrayList();
  /**
   * A local observable array list drops down when the number of products has been clicked.
   */
  private ObservableList<String> choiceList = FXCollections.observableArrayList();

  /**
   * When the record button is clicked, the amount of products produced is assigned to the
   * production log once.
   */
  final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  /**
   * These are both local variables conn creates the connection to the database and stmt allows for
   * SQL statements to be created.
   */
  private Connection conn;
  private Statement stmt;
  /**
   * The prepared statement allows the SQL queries to be executed.
   */
  private PreparedStatement pstmt;

  /**
   * This function pulls the type of product from the database by using SQL statements the strings
   * are the pulled and when selected are added to the database.
   *
   * @param productLine
   * @throws SQLException
   */
  private void loadProductList(ObservableList<Product> productLine) throws SQLException {
    String sql = "SELECT * FROM PRODUCT";
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String name = rs.getString(2);
      String manu = rs.getString(3);
      String chooseItem = rs.getString(4);

      /**
       * Enters the name, manufacturer, and type of product into the database.
       */
      Product dbProduct = new Product(name, manu, ItemType.valueOf(chooseItem));
      productLine.add(dbProduct);
    }
  }

  /**
   * This function takes the values entered and sets them up to be displayed in each cell of the
   * column.
   *
   * @param productLine
   */
  private void setupProductLine(ObservableList<Product> productLine) {
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    /**
     * This action sets the items entered into the table view.
     */
    tvProduct.setItems(this.productLine);
    /**
     * This action sets the items in a horizontal/vertical list in the table view.
     */
    lvProduct.setItems(this.productLine);
  }

  /**
   * This function displays the records produced into the production log in the format of a string.
   */
  private void displayLog() {
    ProductionRecord record = new ProductionRecord(0);
    String product = record.toString();
    recordProduction.setText(product);
  }

  /**
   * This functions sets the text into the production log.
   *
   * @param productionRun
   */
  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    productionLog.setText(productionRun.toString());
  }

  /**
   * This function selects the information from the SQL query and displays it into the text area of
   * the production log.
   *
   * @param productionRun
   * @throws SQLException
   */
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

  private void loadEmployee(ObservableList<Product> productLine) throws SQLException {
    String sql = "SELECT * FROM PRODUCT";
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String name = rs.getString(2);
      String manu = rs.getString(3);
      String chooseItem = rs.getString(4);

      /**
       * Enters the name, manufacturer, and type of product into the database.
       */
      Product dbProduct = new Product(name, manu, ItemType.valueOf(chooseItem));
      productLine.add(dbProduct);
    }
  }

  /**
   * This function adds the information entered into the production table.
   */
  private void addToProductionLog(ArrayList<ProductionRecord> productionRun) throws SQLException {
    /**
     * These are fields for the serial number, timestamp and productID.
     */
    for (ProductionRecord pr : productionRun) {
      int prodID = pr.getProductID();
      String serialNum = pr.getSerialNum();
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());

      /**
       * This string enters the productID, serial number, and date into a query in the database.
       */
      String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES (?,?,?)";
      pstmt = conn.prepareStatement(sql);
      /**
       * The prepared statement sets the the productionID, serialNumber, and date.
       */
      pstmt.setInt(1, prodID);
      pstmt.setString(2, serialNum);
      pstmt.setTimestamp(3, timestamp);

      pstmt.executeUpdate();
      /**
       * This displays the prepared statement to the production log.
       */
      showProduction(productionRun);
    }
  }

  @FXML
  /**
   * When the add product button is clicked, the action adds the information to the database.
   */
  void buttonAddProduct(ActionEvent event) throws SQLException {

    /**
     * Allows the text from the product name to be added to the.
     */
    String name = productName.getText();
    /**
     * A the text from the manufacturer to be added to the database.
     */
    String manu = manufacturer.getText();
    /**
     * Allows the quantity chosen to be added to the database.
     */
    String chooseItem = type.getValue();
    /**
     * Values (?,?,?) is a placeholder for the name, manufacturer and type.
     * When add product is clicked, the variables take place of the placeholders.
     */
    String query = "INSERT INTO PRODUCT (NAME, MANUFACTURER, TYPE) VALUES (?,?,?)";

    pstmt = conn.prepareStatement(query);
    /**
     * Sets the product name string into the first placeholder.
     */
    pstmt.setString(1, name);
    /**
     * Sets the manufacturer string into the second placeholder.
     */
    pstmt.setString(2, manu);
    /**
     * Sets the item type into the third placeholder.
     */
    pstmt.setString(3, chooseItem);
    pstmt.executeUpdate();

    System.out.println("Entered");

    /**
     * Clears the data entered that was entered into the text field once add
     * product has been clicked.
     */
    productName.clear();
    manufacturer.clear();

    /**
     * Calling the loadProductList function and adding the product line to it.
     */
    loadProductList(productLine);
    /**
     * Calling the setupProductLine function and adding the product line to it.
     */
    setupProductLine(productLine);
    /**
     * Displays the product line in the production log.
     */
    displayLog();

  }
  @FXML
  void createAccountButton(ActionEvent event) throws SQLException {
    String name = employeeNameTextField.getText();

    String password = employeePasswordTextField.getText();

    Employee employee = new Employee(name, password);

    employeeTextArea.setText(employee.toString());

    System.out.println("Your account has been created");

  }

  @FXML
  /**
   * This function allows the product quantity and string chosen to be added
   * into the production log when record button is clicked.
   */
  void buttonRecordProduction(ActionEvent event) throws SQLException {
    /**
     * Selects the item in the list vew text area.
     */
    Product record = lvProduct.getSelectionModel().getSelectedItem();

    int quantity;
    /**
     * Choose the quantity of items that were produced from the observable list.
     */
    quantity = Integer
        .parseInt(String.valueOf(chooseQuantity.getSelectionModel().getSelectedItem()));

    ProductionRecord pr;
    /**
     * This for loop is for adding the text into the production log for a certain
     * amount of times.
     */
    for (int i = 0; i < quantity; i++) {
      pr = new ProductionRecord(record, i);
      productionRun.add(pr);
      /**
       * Adds the production record in the product log.
       */
      addToProductionLog(productionRun);
      /**
       * Shows the production record.
       */
      showProduction(productionRun);
      /**
       * Loads the production record into the production log.
       */
      loadProductionLog(productionRun);
    }
  }

  /**
   * This function initializes the the choice list and enters the number of strings into the
   * production log.
   *
   * @throws SQLException
   */
  public void initialize() throws SQLException {
    /**
     * Initializes the database.
     */
    initializeDB();

    for (ItemType it : ItemType.values()) {
      choiceList.add(String.valueOf(it));
    }

    type.getItems().addAll(choiceList);
    /**
     * This observable array list creates a dropdown box that contains quantities.
     */
    ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    /**
     * Sets a quantity for the amount of items.
     */
    chooseQuantity.setItems(list);
    /**
     * Selects the amount of products ordered of the certain product.
     */
    chooseQuantity.getSelectionModel().selectFirst();
    /**
     * The quantity isn't universal and it's able  to be changed.
     */
    chooseQuantity.setEditable(true);

    loadProductList(productLine);
    setupProductLine(productLine);
    loadProductionLog(productionRun);

  }

  private void initializeDB() {

    /**
     * Unable to run the program without this code, I acknowledge it is a bug.
     */
    final String JDBC_DRIVER = "org.h2.Driver";
    /**
     * Unable to run the program without this code, I acknowledge it is a bug.
     */
    final String db_url = "jdbc:h2:./res/ProductDatabase";
    /**
     * Unable to run the program without this code, I acknowledge it is a bug.
     */
    final String user = "";
    /**
     * Unable to run the program without this code, I acknowledge it is a bug.
     */
    final String pass = "";

    try {
      /**
       * STEP 1: Register JDBC driver.
       */
      Class.forName(JDBC_DRIVER);

      /**
       * STEP 2: Open a connection.
       */
      conn = DriverManager.getConnection(db_url, user, pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}


