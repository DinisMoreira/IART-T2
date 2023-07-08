# IART - T2

This is an application capable of solving a typical university course timetabling problem,
without any external interaction, using optimisation algorithms and meta-heuristics of Artificial Intelligence,
namely HillClimbing algorithm, Simulated Annealing, and Genetic Algorithms.

![59429742_807678219604678_4097608240169222144_n](https://github.com/DinisMoreira/IART-T2/assets/28096691/b79e72a3-da68-443e-bfa4-d56fb1fb3ae5)

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

![image](https://github.com/DinisMoreira/IART-T2/assets/28096691/9f5fa391-3476-45f1-af8a-f8feb82d5c0c)

After selecting a timetable challenge, the details are presented in text format.
Select an algorithm to solve this problem:

![image](https://github.com/DinisMoreira/IART-T2/assets/28096691/ddbe460f-c6e2-42ee-94ce-83bf1a13bc49)

The solution reached is then presented as text and stored in the solutions directory

![image](https://github.com/DinisMoreira/IART-T2/assets/28096691/85abb7af-3039-4d3e-909a-4b671aa37d9c)
