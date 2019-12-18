package ca.jrvs.apps.twitter.example;

import static ca.jrvs.apps.twitter.example.JsonParser.companyStr;
import static org.junit.Assert.*;
import ca.jrvs.apps.twitter.example.dto.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonParserTest {
  JsonParser jsonParser;
  StringBuilder stringBuilder;
  String exampleJson;
  Company company;
  
  @Before
  public void setUp () throws Exception {
    jsonParser = new JsonParser();
    File file = new File("./src/main/java/ca/jrvs/apps/twitter" +
                                                         "/example" +
                                              "/jsonExample");
    stringBuilder = new StringBuilder();
    List<String> fileContents = new ArrayList<>(Files.readAllLines(file.toPath(),
        Charset.defaultCharset()));
    fileContents.forEach((String str) -> {stringBuilder.append(str);});
    exampleJson = stringBuilder.toString();
    company = new Company();
  }
  
  @After
  public void tearDown () {
    jsonParser = null;
    exampleJson = null;
  }

  @Test
  public void toObjectFromJson () throws IOException {
    Company company = new Company();
    assertEquals(JsonParser.toObjectFromJson(exampleJson, Company.class).getClass(), company.getClass());
  }
  
  @Test
  public void toJson () throws IOException {
    assertEquals(companyStr, JsonParser.toJson(JsonParser.toObjectFromJson(exampleJson,
        Company.class), true, false));
  }
}