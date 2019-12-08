package sample;

/**
 * This class is set to create a new employee account and gives the employee a default
 * email and username when the employees name is entered.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

  StringBuilder name;
  String username;
  String password;
  String email;

  /**
   * This class sets the usernames and emails to be created when the new
   * employees name and password is entered.
   * @param name
   * @param password
   */
    public Employee(String name, String password){

    this.name = new StringBuilder(name);

    if (checkName(name)) {
      setUsername(name);
      setEmail(name);
    }
    /**
     * This else statement allows the username to become default and the users
     * first inital and last name will be placed in front of the @ symbol in the email
     */
    else {
      username = "default";
      email = "user@oracleacedemy.Test";
    }

    if (isValidPassword(password) == true) {
      this.password = password;
    }
    else {
      this.password = "pw";
    }
  }

  /**
   * This method checks for the name entered to set the username.
   * It grabs the name and sets it after the decimal that is needed to add into
   * the email.
   * @param name
   */
  private void setUsername (String name) {
    Pattern nameAfterSpace = Pattern.compile("\\s(.*)", Pattern.MULTILINE);
    Matcher nameAfterSpaceMatch = nameAfterSpace.matcher(name);
    nameAfterSpaceMatch.find();
    String lastName = nameAfterSpaceMatch.group(1);
    String initials = name.substring(0, 1) + lastName;
    this.username = initials.toLowerCase();
  }

  private boolean checkName(String name){
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(name);
    boolean found = matcher.find();

    return found;
  }

  /**
   * This method checks for the name entered to set the email.
   * It grabs the name and sets it after the decimal that is needed to add into
   * the email.
   * @param name
   */
  private void setEmail(String name) {
    Pattern nameBeforeSpace = Pattern.compile("(.*)\\s", Pattern.MULTILINE);
    Matcher nameBeforeSpaceMatch = nameBeforeSpace.matcher(name);
    nameBeforeSpaceMatch.find();

    String firstName = nameBeforeSpaceMatch.group(1);
    Pattern nameAfterSpace = Pattern.compile("\\s(.*)", Pattern.MULTILINE);
    Matcher nameAfterSpaceMatch = nameAfterSpace.matcher(name);
    nameAfterSpaceMatch.find();
    String lastName = nameAfterSpaceMatch.group(1);

    /**
     * This part
     */
    this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
  }

  private boolean isValidPassword(String password){

    String regex = "^(?=.[A-Z])+^(?!.[^!a-zA-Z0-9@#$^+=])";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(password);

    boolean found = matcher.find();
    return found;
  }

  public String toString() {
    return "Employee Details\n" + "Name  : " + name + "\n" + "Username : " + username + "\n"
        + "Email : " + email + "\n" + "Initial Password : " + password;
  }

}
