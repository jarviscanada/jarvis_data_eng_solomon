package ca.jrvs.apps.grep;

import java.io.File;

public abstract class JavaGrep {
    String regEx;
    File rootPath;
    File outFile;

    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public File getRootPath() {
        return rootPath;
    }

    public void setRootPath(File rootPath) {
        this.rootPath = rootPath;
    }

    public File getOutFile() {
        return outFile;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    public boolean isDirectory(File file){ return file.isDirectory();}

    public boolean isFile(File file){ return file.isFile();}

    public void searchCurrentDir(){}

    public void addResultToOutputFile(){}

}
