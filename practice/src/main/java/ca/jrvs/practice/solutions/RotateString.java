package main.java.ca.jrvs.practice.solutions;

public class RotateString {

  public static String rotateString (String string, int lettersToRotate) {
    StringBuilder rotatedString = new StringBuilder();
    char[] letters = string.toCharArray();
    int sign = lettersToRotate / Math.abs(lettersToRotate);
    
    if (lettersToRotate == 0 || Math.abs(lettersToRotate) == string.length()) {
      return rotatedString.append(letters).toString();
    }
    
    if (Math.abs(lettersToRotate) > string.length()) {
      while(Math.abs(lettersToRotate) > string.length()) {
        lettersToRotate = Math.abs(lettersToRotate) - string.length();
      }
      lettersToRotate = lettersToRotate * sign;
    }
    
    if (lettersToRotate > 0) {
      rotatedString.append(letters, string.length() - lettersToRotate, lettersToRotate);
      rotatedString.append(letters, 0, string.length() - lettersToRotate);
    } else {
      rotatedString.append(letters, Math.abs(lettersToRotate), string.length() + lettersToRotate);
      rotatedString.append(letters, 0, Math.abs(lettersToRotate));
    }

    return rotatedString.toString();
  }
  
  public static void main (String[] args) {
    String toBeRotated = "racecar";
    String toBeRotated2 = "streetcar";
    
    int lettersToRotate = 3;
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated,
        lettersToRotate));
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated2,
        lettersToRotate));
    
    lettersToRotate = toBeRotated.length();
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated,
        lettersToRotate));
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated2,
        lettersToRotate));
    
    lettersToRotate = toBeRotated2.length();
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated2,
        lettersToRotate));
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated,
        lettersToRotate));
    
    lettersToRotate = -3;
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated,
        lettersToRotate));
    System.out.println("Rotated " + lettersToRotate + " times(s) >> " + rotateString(toBeRotated2,
        lettersToRotate));
  }
}
