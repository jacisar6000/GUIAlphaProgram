/**
 * @author Joseph Cisar
 * Joseph Cisar, 11/8/2019, This file allows the application to take record of each product
 * that is produced, its: serial number, ID, the date it was produced,  and the number of
 * products that have been produced. When used in the GUI the production record should show
 * the information on any of the items entered into the database.
 */
package sample;

import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /**
   *   Each one of these parameters is pass through the method to show the product that
   *   was produced and how many times it has been.
   * @param productProduced This is the produced product in the production record.
   * @param itemCount This counts how many items have been chosen.
   */
  public ProductionRecord(Product productProduced, int itemCount) {
    /**
     * String format %05d allows the serial number to end after 5 numbers.
     */
    String idNum = String.format("%05d", itemCount);
    /**
     * (0,3) for the manufacturer will print the first 3 characters of the manufacturers name.
     */
    this.serialNumber =
        productProduced.manufacturer.substring(0, 3) + productProduced.getType().getCode() + idNum;
    /**
     * Shows the date that the product was recorded.
     */
    this.dateProduced = new Date();
  }

  /**
   * Each one of these parameters is pass through the method to show the ID of the product.
   * @param productID This is the identification of the product.
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   *   Each one of these parameters is pass through the method to show the production
   *   number, ID, serial number, and the date it was produced.
   * @param productionNumber
   * @param productID
   * @param serialNumber
   * @param dateProduced
   */
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;

  }

  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
        + serialNumber + " Date: " + dateProduced + "\n";

  }

  /**
   * Calls for the production number and returns it.
   * @return // This is the production number return.
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Updates the product number that is being called upon.
   * @param productionNumber This is the number of the product.
   */
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Calls for the product ID and returns it.
   * @return
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Updates the product ID that is being called upon.
   * @param productID This is the identification of the product.
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Calls for the serial number and returns it.
   * @return
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Updates the serial number that is being called upon.
   * @param serialNumber This is the serial number of the product.
   */
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  // Calls for the date produced and returns it.
  public Date getProdDate() {
    return dateProduced;
  }

  // Updates the date produced that is being called upon.
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}