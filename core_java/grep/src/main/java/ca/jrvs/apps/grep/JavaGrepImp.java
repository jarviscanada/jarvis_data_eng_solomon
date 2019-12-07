package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JavaGrepImp implements JavaGrep {
  String regex;
  String outFile;
  String rootPath;

  public static void main(String[] args) throws IOException {
    JavaGrepImp grep = new JavaGrepImp();

    if (args.length == 3) {
      if (!args[1].equals("")) {
        File providedDir = new File(args[1]);
        if (!providedDir.exists()) {
          throw new IllegalArgumentException("Provided directory does not exist\n"
                 + "[Usage] JavaGrep regEx rootPath outputFile");
        } else {
          grep.setRootPath(args[1]);
        }
      } else {
        throw new IllegalArgumentException("No directory provided\n"
                + "[Usage] JavaGrep regEx rootPath outputFile");
      }

      if (args[2].equals("")) {
        throw new IllegalArgumentException("No output file name provided\n"
               + "[Usage] JavaGrep regEx rootPath outputFile");
      } else {
        File outputFile = new File(args[2]);
        if (!outputFile.exists()) {
          if (!outputFile.createNewFile()) {
            throw new IOException("File named " + args[2] + "could not be created");
          }
          grep.setOutFile(args[2]);
        } else {
          grep.setOutFile(args[2]);
        }
      }
      grep.setRegex(args[0]);

      grep.process();
    } else {
      throw new IllegalArgumentException("Three arguments required\n"
             + "[Usage] JavaGrep regEx rootPath outputFile");
    }
  }

  @Override
  public void process() throws IOException {
    File outputFile = new File(this.getOutFile());
    List<String> outFileCurrentContent = new ArrayList<>(readLines(outputFile));
    List<File> rootDirFiles = this.listFiles(this.getRootPath());

    for (String line : outFileCurrentContent) {
      if (!this.containsPattern(line)) {
        outFileCurrentContent.remove(line);
      }
      if (outFileCurrentContent.size() == 0) {
        break;
      }
    }

    for (File file : rootDirFiles) {
      if (this.containsPattern(file.getName())) {
        outFileCurrentContent.add(file.getPath());
      }
    }

    writeToFile(outFileCurrentContent);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> filesListed = new ArrayList<>();
    File[] listedFiles = new File(rootDir).listFiles();

    if (listedFiles == null) {
      return null;
    }

    for (File file : listedFiles) {
      if (file.isFile()) {
        filesListed.add(file);
      }
      if (file.isDirectory()) {
        filesListed.add(file);
      }
    }

    return filesListed;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> linesRead = new ArrayList<>();

    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(inputFile));
      String currentLine = bufferedReader.readLine();

      while (currentLine != null) {
        linesRead.add(currentLine);
        currentLine = bufferedReader.readLine();
      }
      bufferedReader.close();
    } catch (IOException ioex) {
      ioex.printStackTrace();
    }

    return linesRead;
  }

  @Override
  public boolean containsPattern(String line) {
    return line.matches(getRegex());
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getOutFile()));

      for (String line : lines) {
        try {
          bufferedWriter.write(line);
          bufferedWriter.newLine();
        } catch (IOException ioex) {
          bufferedWriter.close();
          throw new IOException("Unable to write to file");
        }
      }
      bufferedWriter.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}