package ca.jrvs.apps.practice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImpTest {
    LambdaStreamExcImp lse;

    @Before
    public void setUp() throws Exception {
        lse = new LambdaStreamExcImp();
    }

    @After
    public void tearDown() throws Exception {
        lse = null;
    }

    @Test
    public void printGeneratedStreams() {
        Stream<String> strStream = lse.createStrStream("Hello","1-Keep", "World", "The2//se", "Hi", "Strings3 ");
        Stream<String> upperCaseStream = lse.toUpperCase("Hello", "world", "HoW aRe", "y  o  u");
        Stream<String> filteredStrStream = lse.filter(strStream, ".*[0-9]+.*");
        IntStream intStreamArr = lse.createIntStream(new int[] {0, 1, 2, 3, 4, 5, 6});
        IntStream intStreamRange = lse.createIntStream(1, 30);
        DoubleStream sqrtStream = lse.squareRootIntStream(intStreamRange);
        Stream<List<Integer>> nestedListInt = Stream.<List<Integer>>builder().build();

        List<String> strStreamList = lse.toList(strStream);
        List<String> filteredStrStreamList = lse.toList(filteredStrStream);
        List<String> upperCaseStreamList = lse.toList(upperCaseStream);
        List<Double> sqrtStreamList = lse.toList(sqrtStream.boxed());

        lse.printMessages(strStreamList.toArray(new String[6]),
        lse.getLambdaPrinter("strStream>>","<<"));
        lse.printMessages(filteredStrStreamList.toArray(new String[3]), lse.getLambdaPrinter("filteredStrStream>>","<<"));
        lse.printMessages(upperCaseStreamList.toArray(new String[5]), lse.getLambdaPrinter("upperCaseStream>>","<<"));
        lse.printMessages(sqrtStreamList.toArray(new String[30]), lse.getLambdaPrinter("sqrtStream>>","<<"));
        lse.printOdd(intStreamArr, lse.getLambdaPrinter("Odd number:", "!"));
        assertEquals("Check class result after flatNestedInt", "Stream<Integer>", lse.flatNestedInt(nestedListInt).getClass().getSimpleName());
    }
}
