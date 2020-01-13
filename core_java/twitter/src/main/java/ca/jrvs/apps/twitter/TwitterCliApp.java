package ca.jrvs.apps.twitter;

public class TwitterCliApp {
  private TwitterController twitterController;
  
  public void run(){
  
  }

  public static void main (String[] args) {
    if (args[1].equals("delete") && args.length != 2) {
  
    } else if (args.length != 3) {
        throw new IllegalArgumentException("[Usage]: twitterlcli post|show|delete [text|postId]");
    }
    
  }
}
