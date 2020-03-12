package main.java.ca.jrvs.practice.solutions;

public class PalindromeCheck {

  public static boolean palindromeCheck (String text) {
    int numOfChecks = Math.floorDiv(text.length(), 2);
    char[] letters = text.toCharArray();
  
    for (int i = 0; i < numOfChecks; i++) {
      if (letters[i] != letters[letters.length - 1 - i]) return false;
    }
    return true;
  }
  
  public static boolean palindromeCheckRec (String text) {
    char[] letters = text.toCharArray();
    boolean result;
    if(letters.length == 1) {
      return true;
    } else {
      if (letters[0] == letters[letters.length - 1]) {
        result = true;
      } else {
        result = false;
      }
    }
    return result & palindromeCheckRec(text.substring(1, text.length() - 1));
  }
  
  public static void main (String[] args) {
    System.out.println(palindromeCheck("racecar"));
    System.out.println(palindromeCheck("streetcar"));
    System.out.println(palindromeCheck("11:11"));
  
    System.out.println(palindromeCheckRec("racecar"));
    System.out.println(palindromeCheckRec("streetcar"));
    System.out.println(palindromeCheckRec("11:11"));
  }
}
