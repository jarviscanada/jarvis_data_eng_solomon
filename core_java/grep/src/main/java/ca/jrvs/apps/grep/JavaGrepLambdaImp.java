package ca.jrvs.apps.grep;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //creating JavaGrepLambdaImp instead of JavaGrepImp
    //JavaGrepLambdaImp inherits all methods except two override methods in this class.
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      //calling parent method, but it will call override method (in this class)
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  @Override
  public void process() throws IOException {
    File rootDir = new File(this.getOutFile());
    List<String> fileData = readLines(rootDir);

    rootDir.delete();
    rootDir.createNewFile();

    fileData.addAll(listFiles(this.rootPath)
            .stream()
            .map(File::getPath)
            .filter(line -> !fileData.contains(line) &&  containsPattern(line))
            .collect(Collectors.toList()));

    fileData.forEach((String line) -> {
      try {
        FileOutputStream outputStream =
                new FileOutputStream(new File(this.getOutFile()), true);
        outputStream.write((line + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * .
   * Implement using lambda and stream APIs
   */
  @Override
  public List<String> readLines(File inputFile) {
    try {        //Returns the string list generated from reading the input file as a Path object.
      return Files.lines(inputFile.toPath()).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * .
   * Implement using lambda and stream APIs
   */
  @Override
  public List<File> listFiles(String rootDir) {
    Path rootDirPath = new File(rootDir).toPath();
    try {    //Returns the file list from generated from traversing the directories and mapping them
      //from Path to File objects.
      return Files.walk(rootDirPath)
              .map(Path::toFile)
              .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}