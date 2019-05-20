import java.io.*;

import algorithms.*;
import elements.*;

public class Main {

    public static void main(String[] args) {
        String fileName;// = scn.nextLine();

        fileName = args[0];

        File file = new File("../ProblemFiles/" + fileName + ".tim");

        Problem prob = new Problem();

        try {
            prob.getProblemFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        prob.showProblem();

        /*HillClimbing hc = new HillClimbing(prob);
        Solution solution = hc.getSolution();
        solution.outputSolutionToFile("../solutions/" + fileName + ".sln");*/

        Genetic gen = new Genetic(prob, 25, 5);
        Solution solution = gen.getSolutionOptimized();
        solution.outputSolutionToFile("../solutions/" + fileName + ".sln");
    }

}