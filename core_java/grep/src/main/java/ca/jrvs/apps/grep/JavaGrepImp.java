package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


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
    List<String> outFileContent = new ArrayList<>(readLines(outputFile));

    for (File filename:listFiles(this.getRootPath())) {
      if (containsPattern(filename.getName())) {
        outFileContent.add(filename.getName());
      }
    }
    writeToFile(outFileContent);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> listedFiles = new ArrayList<>();
    File root = new File(rootDir);
    File[] dirContents;
    if (root.exists()) {
      if (root.isFile()) {
        listedFiles.add(root);
      }
      if (root.isDirectory()) {
        listedFiles.add(root);
        dirContents = root.listFiles();
        if (!dirContents.equals(null)) {
          listedFiles.addAll(Arrays.asList(dirContents));
          listedFiles.addAll(listFiles(root.getAbsolutePath()));
        }
      }
    }
    return listedFiles;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> linesRead = new ArrayList<>();
    BufferedReader bufferedReader = null;

    if (inputFile.exists()) {
      if (inputFile.isFile() && inputFile.canRead()) {
        try {
          bufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        String newLineRead = null;
        try {
          newLineRead = bufferedReader.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        while (!newLineRead.equals(null)) {
          linesRead.add(newLineRead);
          try {
            newLineRead = bufferedReader.readLine();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        try {
          bufferedReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        try {
          if (!(inputFile.createNewFile() && inputFile.setReadable(true) && inputFile.setWritable(true))) {
            throw new IOException("Unable to create file");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return linesRead;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.compile(this.getRegex()).matcher(line).matches();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    if (!lines.equals(null)) {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getOutFile()));

      for (String line : lines) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
    } else {
      throw new IOException("Unable to write to file");
    }
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