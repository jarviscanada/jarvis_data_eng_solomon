package ca.jrvs.apps.grep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JavaGrepImpTest {
  JavaGrepImp grepImp;
  String inputFile;
  String outputFile;
  String root;
  String regex;

  @Before
  public void setUp() throws Exception {
    grepImp = new JavaGrepImp();
    inputFile = "grep.out";
    outputFile = "grep.out";
    root = ".";
    regex = "(grep)";
  }

  @After
  public void tearDown() throws Exception {
    grepImp = null;
    inputFile = null;
    outputFile = null;
    root = null;
    regex = null;
  }

  @Test
  public void listFiles() {
    assertTrue(new List<String> {"grep", "grep.out", "jbdc", "README.md", "twitter"}, grepImp.listFiles(root));
  }

  @Test
  public void readLines() {
  }

  @Test
  public void containsPattern() {
  }

  @Test
  public void writeToFile() {
  }

  @Test
  public void getRootPath() {
  }

  @Test
  public void setRootPath() {
  }

  @Test
  public void getRegex() {
  }

  @Test
  public void setRegex() {
  }

  @Test
  public void getOutFile() {
  }

  @Test
  public void setOutFile() {
  }
}