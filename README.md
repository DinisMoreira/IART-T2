# IART - T2


## Compilation

To compile, from the src directory, type:

    $ cd .. && mkdir build && cd src
    $ javac -d ./../build/ ./Main.java ./algorithms/*.java ./elements/*.java

A build directory will be created if one does not exist containing the generated class files.

## Execution

To run, type:

    $ cd ./../build
    $ java Main

Input Files are searched inside directory ProblemFiles

## Cleaning:

To remove created files, from the src directory, type:

    $ make clean
