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

        while (!stopRunning) {
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

            double startTime = System.nanoTime();
            Algorithm algo;
            switch (algorithmSelection) {
            case (1):
                algo = new HillClimbing(prob);
                break;
            case (2):
                algo = new SimulatedAnnealing(prob);
                break;
            case (3):
                algo = new Genetic(prob, 10, 3);
                break;
            case (4):
                algo = new Genetic(prob, 20, 3);
                break;
            case (5):
                algo = new Genetic(prob, 30, 3);
                break;
            case (6):
                algo = new Genetic(prob, 10, 12);
                break;
            case (7):
                algo = new Genetic(prob, 20, 12);
                break;
            case (8):
                algo = new Genetic(prob, 30, 12);
                break;
            case (9):
                algo = new Genetic(prob, 10, 1);
                break;
            case (10):
                algo = new Genetic(prob, 20, 1);
                break;
            case (11):
                algo = new Genetic(prob, 30, 1);
                break;
            
            default:
                System.err.println("Unexpeted code path");
                stopRunning = true;
                continue;
            }

            Solution solution;

            if (algorithmSelection >= 3 && algorithmSelection < 9) {
                solution = algo.getSolutionOptimized();
            } 
            else if(algorithmSelection >= 9){
                solution = algo.getSolutionDynamicMutProb();
            }
            else {
                System.out.println("Getting Solution...");
                solution = algo.getSolution();
            }

            double endTime = System.nanoTime();
            double totalTime = endTime - startTime;

            solution.showSolutionOrderedByEventId();

            if(solution.getNumberOfHardInfractions() > 0){
                System.out.println("\nCouldn't get valid solution");
                System.out.println("\nBest solution Reached has:");
                System.out.println(solution.getNumberOfHardInfractions() + " Hard Infractions");
                System.out.println(solution.getNumberOfSoftInfractions() + " Soft Infractions");
            }
            else if(solution.getScore() == 0){
                System.out.println("\nPerfect Solution Reached");
            }
            else{
                System.out.println("\nBest solution Reached has:");
                System.out.println(solution.getNumberOfSoftInfractions() + " Soft Infractions");
            }

            
            System.out.println("\nExecution time: " + totalTime / Math.pow(10, 9) + " seconds");

            solution.outputSolutionToFile("../solutions/" + fileName + ".sln");
        }

        scn.close();
    }

    private static String getFileName(Scanner scanner) throws InputMismatchException {
        final String namePrefix = "ourFile";
        final int maxSchedule = 5;
        int schedule = -1;
        while (schedule < 1 || schedule > maxSchedule) {
            System.out.print("\nWhich schedule would you like? (1-" + maxSchedule + "): ");
            schedule = scanner.nextInt();
        }
        return namePrefix + schedule;
    }

    private static Problem createAndDisplayProblem(String completeFilePath) throws FileNotFoundException {
        final Problem problem = new Problem();
        final File file = new File(completeFilePath);

        problem.getProblemFromFile(file);
        problem.showProblem();

        return problem;
    }

    private static int getAlgorithmSelection(Scanner scanner) throws InputMismatchException {
        final int maxNumberAlgorithms = 11;
        int algorithm = -1;
        while (algorithm < 1 || algorithm > maxNumberAlgorithms) {
            System.out.println();
            System.out.println(" 1 - Hill Climbing Algorithm");
            System.out.println(" 2 - Simulated Annealing Algorithm");
            System.out.println(" 3 - Genetic Algorithm (10 elements per generation, 3% chance of mutation)");
            System.out.println(" 4 - Genetic Algorithm (20 elements per generation, 3% chance of mutation)");
            System.out.println(" 5 - Genetic Algorithm (30 elements per generation, 3% chance of mutation)");
            System.out.println(" 6 - Genetic Algorithm (10 elements per generation, 12% chance of mutation)");
            System.out.println(" 7 - Genetic Algorithm (20 elements per generation, 12% chance of mutation)");
            System.out.println(" 8 - Genetic Algorithm (30 elements per generation, 12% chance of mutation)");
            System.out.println(" 9 - Genetic Algorithm (10 elements per generation, dynamic chance of mutation");
            System.out.println("10 - Genetic Algorithm (20 elements per generation, dynamic chance of mutation");
            System.out.println("11 - Genetic Algorithm (30 elements per generation, dynamic chance of mutation");
            System.out.print("Which algorithm would you like? (1-" + maxNumberAlgorithms + "): ");
            algorithm = scanner.nextInt();
        }
        return algorithm;
    }
}
