# IART - T2


## Compilation

To compile, from the src directory, type:

    $ make

A build directory will be created if one does not exist containing the generated class files.

Alternatively, the following commands can also be used instead from the src directory:

    $ cd .. && mkdir build && cd src
    $ javac -d ./../build/ ./Main.java ./algorithms/*.java ./elements/*.java


## Execution

To run, from the src directory, type:

    $ make run

Input Files are searched inside directory ProblemFiles

Alternatively, the following command can also be used from the src directory:

    $ java -cp ./../build Main


## Cleaning:

To remove created files, from the src directory, type:

    $ make clean