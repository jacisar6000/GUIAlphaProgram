package sample;
//Joseph Cisar, 11/8/2019, This file allows the applications to retrieve the name and manufacturers of the items entered
public interface Item {
  //updates the name of the item
  void setName(String name);
  //retrieves the name
  String getName();
  //updates the same of the manufacturer
  void setManufacturer(String manufacturer);
  //retrieves the manufacturer
  String getManufacturer();

}
