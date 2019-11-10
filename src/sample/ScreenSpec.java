package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to calls and return the screen specifications and return them to the screen
public interface ScreenSpec {

  public String getResolution();
  public int getRefreshRate();
  public int getResponseTime();

}