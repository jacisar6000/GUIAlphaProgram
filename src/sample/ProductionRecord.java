package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to take record of each product that is produced, its:
//serial number, ID, the date it was produced,  and the number of products that have been produced. When used in the GUI
//the production record should show the information on any of the items entered into the database.
import java.util.Date;

public class ProductionRecord{
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  //each one of these parameters is pass through the method to show the product that was produced and how many times it has been
  public ProductionRecord(Product productProduced, int itemCount){
    //String format %05d allows the serieal number to end after 5 numbers
    String idNum = String.format("%05d" , itemCount);
    //(0,3) for the manufacturer will print the first 3 characters of the manufacturers name
    this.serialNumber = productProduced.manufacturer.substring(0,3)+ productProduced.getType().getCode()+idNum;
    //shows the date that the product was recorded
    this.dateProduced = new Date();
  }
  //each one of these parameters is pass through the method to show the ID of the product
  public ProductionRecord(int productID){
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0" ;
    dateProduced = new Date();
  }
  //each one of these parameters is pass through the method to show the production number, ID, serial number, and the date it was produced
  public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;

  }

  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: " + serialNumber + " Date: " + dateProduced;
  }
  // calls for the production number and returns it
  public int getProductionNum(){
    return productionNumber;
  }
  //updates the product number that is being called upon
  public void setProductionNum(int productionNumber){
    this.productionNumber = productionNumber;
  }
  // calls for the product ID and returns it
  public int getProductID(){
    return productID;
  }
  //updates the product ID that is being called upon
  public void setProductID(int productID){
    this.productID = productID;
  }
  // calls for the serial number and returns it
  public String getSerialNum(){
    return serialNumber;
  }
  //updates the serial number that is being called upon
  public void setSerialNum(String serialNumber){
    this.serialNumber = serialNumber;
  }
  // calls for the date produced and returns it
  public Date getProdDate(){
    return dateProduced;
  }
  //updates the date producted that is bieng called upon
  public void setProdDate(Date dateProduced){
    this.dateProduced = dateProduced;
  }

}