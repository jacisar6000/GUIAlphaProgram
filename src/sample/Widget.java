/**
 * @author Joseph Cisar
 * Joseph Cisar, 11/8/2019, This file allows the application to extend the product and enter the name, manufacturer, and type
 * into the table view.
 */
package sample;

class Widget extends Product {

  public Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}