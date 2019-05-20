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

//        HillClimbing algo = new HillClimbing(prob);
        SimulatedAnnealing algo = new SimulatedAnnealing(prob);
//        Genetic algo = new Genetic(prob, 25, 3);

        Solution solution = algo.getSolution();
        solution.outputSolutionToFile("../solutions/" + fileName + ".sln");
    }

}