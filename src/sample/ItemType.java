package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to ItemTypes to be abbreviated for when they are shown in the
//observable array list it allows a more polished look for the user
public enum ItemType {
  //allows for each of the following below to be shown in the observable array list
  AUDIO("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  public final String codes;
  //instead of typing the full words, code allows the programmer to enter abbreviations
  ItemType(String code){
    codes = code;
  }
  // returns the code in the observable array list
  public String getCode(){
    return this.codes;
  }
}