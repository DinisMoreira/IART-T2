# IART - T2

This is an application capable of solving a typical university course timetabling problem,
without any external interaction, using optimisation algorithms and meta-heuristics of Artificial Intelligence,
namely HillClimbing algorithm, Simulated Annealing, and Genetic Algorithms.

More information regarding the details of this challenge and the approach to solve it are available in the Report.pdf file

## Compilation

To compile, from the src directory, type:

    $ cd .. && mkdir build && cd src
    $ javac -d ./../build/ ./Main.java ./algorithms/*.java ./elements/*.java

A build directory will be created if one does not exist containing the generated class files.

## Execution

Before the first run, create a folder called solutions:

     $ cd ..
     $ mkdir solutions

To run from the "build" folder, type:

    $ cd build
    $ java Main

Input Files are searched inside directory ProblemFiles

