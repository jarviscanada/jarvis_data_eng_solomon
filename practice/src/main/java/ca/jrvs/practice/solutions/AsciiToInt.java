package main.java.ca.jrvs.practice.solutions;

public class AsciiToInt {
  public static int aToI(String string) {
    int stringValue = 0;
    char[] letters =  string.toCharArray();
    
    for (int i = 0; i < letters.length; i++) {
      if (letters[i] < '0' || letters[i] > '9') {
        throw new NumberFormatException("Non-digit character: " + letters[i] );
      }
  stringValue += (letters[i] - '0') * Math.pow(10, letters.length - i - 1);
    }
    
    return stringValue;
  }

  public static void main (String[] args) {
    int number = aToI("221356");
    System.out.println(number);
  }
}
