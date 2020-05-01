package ca.jrvs.apps.practice;

import  org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class RegexExcImpTest{
    private RegexExcImp regexTest;

    @Before
    public void setUp() throws Exception {
        regexTest = new RegexExcImp();
    }

    @After
    public void tearDown() throws Exception {
        regexTest = null;
    }

    @Test
    public void testMatchJpeg() {
        Assert.assertFalse(regexTest.matchJpeg(".jpg"));
        Assert.assertFalse(regexTest.matchJpeg(".jpeg"));
        Assert.assertTrue(regexTest.matchJpeg("hello.jpg"));
        Assert.assertFalse(regexTest.matchJpeg(" .jpg"));
        Assert.assertFalse(regexTest.matchJpeg(" .jpeg"));
        Assert.assertFalse(regexTest.matchJpeg(".jpeeg"));
        Assert.assertTrue(regexTest.matchJpeg("o.jpg"));
        Assert.assertTrue(regexTest.matchJpeg("1.jpg"));
        Assert.assertFalse(regexTest.matchJpeg("..jpg"));
    }

    @Test
    public void testMatchIp() {
        Assert.assertFalse(regexTest.matchIp("1000.2.3.5"));
        Assert.assertTrue(regexTest.matchIp("100.2.3.5"));
        Assert.assertTrue(regexTest.matchIp("999.999.999.999"));
        Assert.assertTrue(regexTest.matchIp("0.0.0.0"));
        Assert.assertTrue(regexTest.matchIp("127.0.0.1"));
        Assert.assertFalse(regexTest.matchIp("0.0.0.1000"));
    }

    @Test
    public void testIsEmptyLine() {
        Assert.assertTrue(regexTest.isEmptyLine(""));
        Assert.assertFalse(regexTest.isEmptyLine("."));
        Assert.assertTrue(regexTest.isEmptyLine("   "));
        Assert.assertTrue(regexTest.isEmptyLine(" "));
        Assert.assertTrue(regexTest.isEmptyLine("           "));
        Assert.assertTrue(regexTest.isEmptyLine("           "));
        Assert.assertFalse(regexTest.isEmptyLine("           ."));
        Assert.assertFalse(regexTest.isEmptyLine(".      "));
    }
}