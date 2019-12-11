package ca.jrvs.apps.grep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    inputFile = "/tmp/grep.out";
    outputFile = "/tmp/grep.out";
    root = ".";
    regex = ".*(xml).*";

    testListName = new ArrayList<String>(Arrays.asList(new String[]{"./.idea/workspace.xml",
            "./.idea/junitgenerator-prj-settings.xml",
            "./.idea/modules.xml",
            "./.idea/misc.xml",
            "./.idea/checkstyle-idea.xml",
            "./.idea/vcs.xml",
            "./.idea/libraries/Maven__org_hamcrest_hamcrest_core_1_3.xml",
            "./.idea/libraries/Maven__junit_junit_4_13_rc_2.xml",
            "./.idea/codeStyles/codeStyleConfig.xml",
            "./.idea/.gitignore",
            "./.idea/compiler.xml",
            "./README.md",
            "./src/test/java/ca/jrvs/apps/grep/JavaGrepImpTest.java",
            "./src/test/java/ca/jrvs/apps/grep/JavaGrepLambdaImpTest.java",
            "./src/test/java/ca/jrvs/apps/practice/LambdaStreamExcImpTest.java",
            "./src/test/java/ca/jrvs/apps/practice/RegexExcImpTest.java",
            "./src/main/java/ca/jrvs/apps/grep/JavaGrepLambdaImp.java",
            "./src/main/java/ca/jrvs/apps/grep/JavaGrep.java",
            "./src/main/java/ca/jrvs/apps/grep/JavaGrepImp.java",
            "./src/main/java/ca/jrvs/apps/practice/LambdaStreamExcImp.java",
            "./src/main/java/ca/jrvs/apps/practice/RegexExcImp.java",
            "./src/main/java/ca/jrvs/apps/practice/RegexExc.java",
            "./src/main/java/ca/jrvs/apps/practice/LambdaStreamExc.java",
            "./src/main/java/ca/jrvs/apps/practice/HelloWorld.java",
            "./pom.xml",
            "./grep.iml",
            "./target/classes/META-INF/grep.kotlin_module",
            "./target/classes/ca/jrvs/apps/grep/JavaGrepLambdaImp.class",
            "./target/classes/ca/jrvs/apps/grep/JavaGrepImp.class",
            "./target/classes/ca/jrvs/apps/grep/JavaGrep.class",
            "./target/classes/ca/jrvs/apps/practice/RegexExc.class",
            "./target/classes/ca/jrvs/apps/practice/LambdaStreamExcImp.class",
            "./target/classes/ca/jrvs/apps/practice/RegexExcImp.class",
            "./target/classes/ca/jrvs/apps/practice/LambdaStreamExc.class",
            "./target/classes/ca/jrvs/apps/practice/HelloWorld.class",
            "./target/test-classes/META-INF/grep.kotlin_module",
            "./target/test-classes/ca/jrvs/apps/grep/JavaGrepLambdaImpTest.class",
            "./target/test-classes/ca/jrvs/apps/grep/JavaGrepImpTest.class",
            "./target/test-classes/ca/jrvs/apps/practice/LambdaStreamExcImpTest.class",
            "./target/test-classes/ca/jrvs/apps/practice/RegexExcImpTest.class"}));
    testList = new ArrayList<File>();
    testList.addAll(testListName.stream().map(File::new).collect(Collectors.toList()));

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