package sample;

/**
 * Joseph Cisar, 11/8/2019, This file allows the applications to retrieve the name
 * and manufacturers of the items entered.
 */
public interface Item {

  /**
   * Updates the name of the item.
   * @param name
   */
  void setName(String name);

  /**
   * Retrieves the name.
   * @return
   */
  String getName();

  /**
   * Updates the same of the manufacturer.
   * @param manufacturer
   */
  void setManufacturer(String manufacturer);

  /**
   * Retrieves the manufacturer.
   * @return
   */
  String getManufacturer();

}
