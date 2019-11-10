package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to show the user specifications of the screen by implementing
// screen specifications.
public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshRate;
  private int responseTime;

  // returns the type of resolution the screen has to the user
  public String getResolution(){
    return resolution;
  }
  // returns the refresh rate of the screen to the user
  public int getRefreshRate(){
    return refreshRate;
  }
  // returns the screens response time to the user
  public int getResponseTime(){
    return responseTime;
  }

  //each one of these parameters is pass through the method to show the resolution, refresh rate, and response time of the screen
  public Screen(String resolution, int refreshRate, int responseTime) {

    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String toString() {
    // returns the parameters to the user
    return "Screen:" + '\n' + "Resolution: " + resolution + '\n' + "Refresh rate: " + refreshRate + '\n' + "Response time: " + responseTime;

  }

}