import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;

import algorithms.*;
import elements.Problem;
import elements.Solution;

public class Main {

    public static void main(String[] args) {
        final Scanner scn = new Scanner(System.in);
        Boolean stopRunning = false;
        String fileName = null;
        Problem prob = null;
        int algorithmSelection;

        while(! stopRunning) {
            try {
                fileName = getFileName(scn);
                prob = createAndDisplayProblem("../ProblemFiles/" + fileName + ".tim");
                algorithmSelection = getAlgorithmSelection(scn);
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Stoping execution");
                stopRunning = true;
                continue;
            } catch (NullPointerException | FileNotFoundException e) {
                System.err.println("File Not Found");
                stopRunning = true;
                continue;
            }

            Algorithm algo;
            switch(algorithmSelection) {
                case(1):
                    algo = new HillClimbing(prob);
                break;
                case(2):
                    algo = new SimulatedAnnealing(prob);
                break;
                case(3):
                    algo = new Genetic(prob, 10, 8); //10 elements per generation, 8% chance of mutation
                break;
                case(4):
                    algo = new Genetic(prob, 20, 8); //20 elements per generation, 8% chance of mutation
                break;
                case(5):
                    algo = new Genetic(prob, 30, 8); //30 elements per generation, 8% chance of mutation
                break;
                case(6):
                    algo = new Genetic(prob, 40, 8); //50 elements per generation, 8% chance of mutation
                break;
                default:
                    System.err.println("Unexpeted code path");
                    stopRunning = true;
                    continue;
            }

            Solution solution;
            
            /*if(algorithmSelection == 3){
                solution = algo.getSolutionNormal();
            }
            else*/ if(algorithmSelection >= 3){
                solution = algo.getSolutionOptimized();
            }
            else{
                solution = algo.getSolution();
            }
            
            solution.outputSolutionToFile("../solutions/" + fileName + ".sln");
        }
        
        scn.close();
    }


    private static String getFileName(Scanner scanner)
        throws InputMismatchException {
        final String namePrefix = "ourFile";
        final int maxSchedule = 3;
        int schedule = -1;        
        while (schedule < 1 || schedule > maxSchedule) {
            System.out.print("\nWhich schedule would you like? (1-" + maxSchedule + "): ");
            schedule = scanner.nextInt();
        }
        return namePrefix + schedule;
    }

    private static Problem createAndDisplayProblem(String completeFilePath) 
    throws FileNotFoundException {
        final Problem problem = new Problem();
        final File file = new File(completeFilePath);

        problem.getProblemFromFile(file);
        problem.showProblem();

        return problem;
    }

    private static int getAlgorithmSelection(Scanner scanner)
        throws InputMismatchException {
        final int maxNumberAlgorithms = 6;
        int algorithm = -1;
        while (algorithm < 1 || algorithm > maxNumberAlgorithms) {
            System.out.println();
            System.out.println("1- Hill Climbing Algorithm");
            System.out.println("2- Simulated Annealing Algorithm");
            System.out.println("3- Genetic Algorithm (10 elements per generation, 8% chance of mutation)");
            System.out.println("4- Genetic Algorithm (20 elements per generation, 8% chance of mutation)");
            System.out.println("5- Genetic Algorithm (30 elements per generation, 8% chance of mutation)");
            System.out.println("6- Genetic Algorithm (40 elements per generation, 8% chance of mutation)");
            System.out.print("Which algorithm would you like? (1-" + maxNumberAlgorithms + "): ");
            algorithm = scanner.nextInt();
        }
        return algorithm;
    }
}

