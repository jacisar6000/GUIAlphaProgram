/**
 * @author Joseph Cisar
 * Joseph Cisar, 11/8/2019, This file allows the applications to retrieve the name
 * and manufacturers of the items entered.
 */
package sample;

public interface Item {

   // Updates the name of the item.
   // @param name This is the name of the item.
  void setName(String name);

  // Retrives the name
  String getName();

  // Updates the name of the manufacturer
  void setManufacturer(String manufacturer);

  // Retrives the manufacturer
  String getManufacturer();

}
