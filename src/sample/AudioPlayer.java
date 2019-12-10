/**
 * @author Joseph Cisar
 * Joseph Cisar, 11/8/2019, This file allows the application to show the user what the type of audio
 * players are capable of extending product will implement the MultimediaControl class.
 */
package sample;

public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String mediaType;

  /**
   * Each one of these parameters is pass through the method to show the name, manufacturer,
   * mediaType, and specification.
   *
   * @param name This is the product's name.
   * @param manufacturer This is the manufacturer that produced the product.
   * @param audioSpecification Supported audio formats for the product.
   * @param mediaType This is the media type of the product.
   */
  public AudioPlayer(String name, String manufacturer, String audioSpecification,
      String mediaType) {

    // Using ItemType.Audio, it allowed me to bypass errors of conflicting ItemType with Strings.
    super(name, manufacturer, ItemType.AUDIO);
    this.audioSpecification = audioSpecification;
    this.mediaType = mediaType;
  }

  public String toString() {

    //  When printed will show the specific audio format for the device.
    String spec = "Supported Audio Formats: " + this.audioSpecification + '\n';

    // When printed will show the specific playlist format for the mediaType.
    String media = "Supported Playlist Formats: " + this.mediaType;
    return super.toString() + spec + media;
  }

  public void play() {

    // Prints playing.
    System.out.println("Playing");

  }

  public void stop() {

    // Prints stopping.
    System.out.println("Stopping");

  }

  public void next() {

    // Prints next.
    System.out.println("Next");

  }

  public void previous() {

    // Prints previous.
    System.out.println("Previous");

  }

  public String getAudioSpecification() {

    // Calls for the audioSpecification and then returns it.
    return audioSpecification;

  }

  public void setAudioSpecification(String audioSpecification) {
    this.audioSpecification = audioSpecification;

  }

  public String getMediaType() {

    // Calls for the mediaType and then returns it to the user.
    return mediaType;

  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;

  }

}