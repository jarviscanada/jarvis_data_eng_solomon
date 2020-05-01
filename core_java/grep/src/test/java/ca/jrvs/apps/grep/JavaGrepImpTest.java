package ca.jrvs.apps.grep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

public class JavaGrepImpTest {
  JavaGrepImp grepImp;
  String inputFile;
  String outputFile;
  String root;
  String regex;
  ArrayList<File> testList;
  ArrayList<String> testListName;

  @Before
  public void setUp() throws Exception {
    grepImp = new JavaGrepImp();
    inputFile = "grep.out";
    outputFile = "grep.out";
    root = ".";
    regex = ".*(xml).*";

    testList = new ArrayList<File>();
    testList.add(new File("./README.md"));
    testList.add(new File("./grep.out"));
    testList.add(new File("./pom.xml"));
    testList.add(new File("./grep.iml"));

    testListName = new ArrayList<String>();
    testListName.add("./README.md");
    testListName.add("./grep.out");
    testListName.add("./pom.xml");
    testListName.add("./grep.iml");
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
    assertEquals(testListName, grepImp.readLines(new File(inputFile)));
  }

  @Test
  public void containsPattern() {
    assertTrue(grepImp.containsPattern(testList.get(2).getName()));
    assertFalse(grepImp.containsPattern(testList.get(3).getName()));
  }

  @Test
  public void writeToFile() {
    try {
      grepImp.writeToFile(testListName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(grepImp.readLines(new File(inputFile)), testListName);
  }
}