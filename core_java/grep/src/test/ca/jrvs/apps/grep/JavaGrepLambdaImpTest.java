package ca.jrvs.apps.grep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JavaGrepLambdaImpTest {
  JavaGrepLambdaImp grepImp;
  String inputFile;
  File sourceFile;
  String outputFile;
  String root;
  String regex;
  ArrayList<File> testList;
  ArrayList<String> testListName;

  @Before
  public void setUp() throws Exception {
    grepImp = new JavaGrepLambdaImp();
    inputFile = "./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/ca.jrvs.apps.grep.grep.grep.out";
    sourceFile = new File(inputFile);
    outputFile = "./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/ca.jrvs.apps.grep.grep.grep.out";
    root = "./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/";
    regex = ".*(java)$";

    testList = new ArrayList<File>();
    testList.add(new File("./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/JavaGrepImpTest.java"));
    testList.add(new File("./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/JavaGrepLambdaImpTest.java"));

    testListName = new ArrayList<String>();
    testListName.add("./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/JavaGrepImpTest.java");
    testListName.add("./grep/src/test/java/ca/jrvs/apps/ca.jrvs.apps.grep.grep.grep/JavaGrepLambdaImpTest.java");
    grepImp.setRegex(regex);
    grepImp.setRootPath(root);
    grepImp.setOutFile(outputFile);
  }

  @After
  public void tearDown() throws Exception {
    grepImp = null;
    inputFile = null;
    outputFile = null;
    root = null;
    regex = null;
    testList = null;
  }

  @Test
  public void listFiles() {
    assertEquals(testList, grepImp.listFiles(root));
  }

  @Test
  public void readLines() {
    assertEquals(testListName, grepImp.readLines(sourceFile));
  }

}
