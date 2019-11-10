package sample;
//Joseph Cisar, 11/8/2019, This file allows the application to show the user what the type of audio players are capable of
// extending product will implement the MultimediaControl class
public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String mediaType;
  //each one of these parameters is pass through the method to show the name, manufacturer, mediaType, and specification
  public AudioPlayer(String name, String manufacturer, String audioSpecification, String mediaType) {
    // using ItemType.Audio, it allowed me to bypass errors of conflicting ItemType with Strings
    super(name, manufacturer, ItemType.AUDIO);
    this.audioSpecification = audioSpecification;
    this.mediaType = mediaType;
  }

  public String toString() {
    // when printed will show the specific audio format for the device
    String spec = "Supported Audio Formats: " + this.audioSpecification + '\n';
    // when printed will show the specific playlist format for the mediaType
    String media = "Supported Playlist Formats: " + this.mediaType;
    return super.toString() + spec + media;
  }

  public void play() {
    //Prints playing
    System.out.println("Playing");

  }

  public void stop() {
    //Prints stopping
    System.out.println("Stopping");

  }

  public void next() {
    //Prints Next
    System.out.println("Next");

  }

  public void previous() {
    // Prints previous
    System.out.println("Previous");

  }

  public String getAudioSpecification() {
    // calls for the audioSpecification and then returns it
    return audioSpecification;

  }

  public void setAudioSpecification(String audioSpecification) {
    this.audioSpecification = audioSpecification;

  }

  public String getMediaType() {
    // calls for the mediaType and then returns it to the user
    return mediaType;

  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;

  }

}