package ca.jrvs.apps.grep;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JavaGrepLambdaImpTest extends JavaGrepImpTest {

  @Before
  public void setUp() throws Exception {
    super.setUp();
  }

  @After
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void listFiles() {
    assertEquals(testList, grepImp.listFiles(grepImp.getRootPath()));
  }

  @Override
  public void readLines() {
    assertEquals(testListName, grepImp.readLines(new File(grepImp.getOutFile())));
  }
}