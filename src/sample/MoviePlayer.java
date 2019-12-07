package sample;

/**
 * Joseph Cisar, 11/8/2019, This file allows the application to show different types of
 * monitors, extend and implement other classes, show the user interactive options while
 * viewing movies. Extending product will allow the implementation of MultimediaControl
 * to be used by the MoviePlayer.
 */
public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  /**
   *   Each one of these parameters is pass through the method to show
   *   the name, manufacturer, screen, and monitorType.
   * @param name
   * @param manufacturer
   * @param screen
   * @param monitorType
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    /**
     * Using ItemType.Visual, it allowed me to bypass errors of conflicting ItemType with
     * Strings.
     */
    super(name, manufacturer, ItemType.Visual);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public String toString() {
    /**
     * Returns the type of monitor.
     */
    return super.toString() + screen + "Monitor Type:" + monitorType;
  }

  /**
   * Each of these print statements below will print the text shown onto to screen
   * for the user to see.
   */
  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public Screen getScreen() {
    return screen;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
  }

  public MonitorType getMonitorType() {
    return monitorType;

  }

  public void setMonitorType(MonitorType monitorType) {
    this.monitorType = monitorType;
  }

}