# Introduction

This application was devised as an exercise in understanding and utilizing core Java fundamentals. The task at hand is to create a Java-based implementation of the shell command *grep*, initially with a naive approach, and once again with knowledge of the original implementations' limitations.

## Program Usage

JavaGrepImp provides equivalent functionality to the shell command: 

``` >> egrep -r {regEx} {rootPath} > {outfile}```

The result of this command is, recursively search beginning form the provided directory and return files that match the given regular expression. Upon completion, store the results into the desired output file. The Java implementation has the following syntax:

```[Usage] JavaGrepImp regEx rootPath outFile ```

## Pseudo-code

The high-level description of what is occurring within this application is described by the *process* method's pseudo-code:

``` java
Process:
   fileLinesRead = readLines(OutputFile)
    filesToBeWritten = listedFiles(rootDirectory)

    for line in fileLinesRead
            if containsPattern(line) is false
                fileLinesRead.remove(line)

    for file in filesToBeWritten
            if containsPattern(file.name) and fileLinesRead.contains(file.name) is false
            filesToBeWritten.add(file)

    writeToFile(filesToBeWritten)
```

This method calls on helper methods in order to provide it with the necessary information to execute the command as desired:

### `readLines()`

This method opens the provided output file, and stores its content in a list, line by line as strings. Once the list contains all that was originally contained in the file, it is then returned. If the output file did not exist, the method will create it and will return `null` as there is no file content. 

### `listedFiles()`

When provided with the directory with which to begin the search, this method generated a list of contained files and subdirectories, which are then returned.

### `containsPattern()`

This method is provided with a string which represents a particular filename. The name is then checked against the provided regular expression, if it matches the rule then `true`, otherwise, it does not match and returns `false`.

### `writeToFile()`

Once a fully compiled list of files that match the regular expression's criteria is generated, the list is then parsed by this method. The listed filenames, including the directory within which it was found, are then printed line by line into the output file, overwriting its original content.

## Performance Issues

This application works perfectly well for small to medium size workloads, as they are able to be stored in memory without much issue. In the case that a very large amount of data needed to be processed, the host's memory would be quickly filled. This is an issue with buffering the file data in memory, if the data was able to be processed without being stored, this could be avoided. 

Java 8 Streams API allow for data to be processed as it is encountered, removing the need to store anything in memory.

## Improvements

- By re-implementing this application using Java 8 Streams, lack of memory will no longer be an issue.
- Going over argument logic in order to remove any redundant or unnecessary lines of code
- Going over comments to make them more readable
*...And more that I can't think of at the moment*
