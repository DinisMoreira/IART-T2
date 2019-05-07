import java.io.*;
import java.util.Scanner;

import algorithms.HillClimbing;
import elements.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // System.out.println("What is the name of the problem file?");
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


        HillClimbing hc = new HillClimbing(prob);
        hc.getSolution();
        
    }


    

}