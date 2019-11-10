package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to implements the item class, return specific information on the product
//such as its name, manufacturer, type of product, prints the product to the user and is the main driver behind the controller
//to edit the GUI
import javafx.scene.Scene;
// implementing the Item class will allow the Product class to get and set the names of the manufacturer and product
public class Product implements Item {

  public ItemType type;
  public String manufacturer;
  public String name;
  //each one of these parameters is pass through the method to show the name, manufacturer, and type
  public Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }
  // Updates and retrieves the type of item chosen
  public ItemType getType() {
    return type;
  }
  public void setType(ItemType type) {
    this.type = type;
  }
  // Updates and retrieves the type of manufacturer chosen
  public String getManufacturer() {
    return manufacturer;
  }
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
  // Updates and retrieves the name chosen
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  //prints out the type of product, manufacturer, and name of the product
  public String toString() {
    return "Product{"
        + ", Type = '" + type + '\'' + ", Manufacturer = '"
        + manufacturer + '\'' + ", Name = '" + name + '\'';
  }

  public void setScene(Scene scene) {
  }

  public void show() {
  }
}






