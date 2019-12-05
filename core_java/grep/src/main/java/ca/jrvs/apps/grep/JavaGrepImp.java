package ca.jrvs.apps.grep;
import ca.jrvs.apps.practice.LambdaStreamExc;
import ca.jrvs.apps.practice.LambdaStreamExcImp;
import java.io.File;
import java.util.Arrays;

public class JavaGrepImp extends JavaGrep {
    StringBuilder patternMatches;
    LambdaStreamExcImp lamStreImp;
    int numberOfsubDir;

    public void argumentCheck(String[] args) throws IllegalArgumentException{
        switch (args.length){
            case 3:
              if(new File(""+args[1]).isDirectory() && new File(""+args[2]).isFile()){
                  return;
              }else{
                  throw new IllegalArgumentException("rootPath must be a directory, outFile must be a file\n" +
                          "[Usage] JavaGrep regEx rootPath outFile");
              }
            default:
                throw new IllegalArgumentException( args.length +  " arguments provided.\n" +
                        "[Usage] JavaGrep regEx rootPath outFile");
        }
    }

    @Override
    public void searchCurrentDir(String regex, File directory) {
        if (directory.list() == null){
            return;
        } else {
            lamStreImp.toList(Arrays.stream(directory.list()).peek(
                    (File f) -> {
                        f.isDirectory() ? numberOfsubDir++ : numberOfsubDir;
                        return;
                    }
            ).filter(filename -> filename.matches(regex))).forEach(s -> patternMatches.append(s));
        }
    }

    @Override
    public void addResultToOutputFile() {

    }

    public static void main(String[] args) {
        JavaGrepImp grep = new JavaGrepImp();
        LambdaStreamExcImp lamStreImp = new LambdaStreamExcImp();

        grep.argumentCheck(args);
        grep.searchCurrentDir(args[0], new File(""+args[1]));


    }

}
