package main.java.ca.jrvs.practice.solutions;

import java.util.Stack;

public class ValidParentheses {
  public static boolean paranthesesCheck(String sample){
    Stack<String> stack = new Stack<>();
    String[] letters = sample.split("");
    
    for (String letter : letters) {
      switch (letter) {
        case "(":
        case "[":
        case "{":
          stack.push(letter);
          break;
        case ")":
          if(stack.peek().equals("(")) {
            stack.pop();
          } else {
            stack.push(letter);
          }
          break;
        case "]":
          if(stack.peek().equals("[")) {
            stack.pop();
          } else {
            stack.push(letter);
          }
          break;
        case "}":
            if(stack.peek().equals("{")) {
            stack.pop();
          } else {
            stack.push(letter);
          }
          break;
      }
    }
    return stack.empty();
  }

  public static void main (String[] args) {
    String fail = "(()[]{([";
    String success = "(()[]{([])})";
    
    System.out.println("Pass? " + paranthesesCheck(fail));
    System.out.println("Pass? " + paranthesesCheck(success));
  }
}
