import java.io.*;

import algorithms.HillClimbing;
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

        
        showProblem(prob);


        HillClimbing hc = new HillClimbing(prob);
        hc.getSolution();

        hc.outputSolutionToFile("../solutions/" + fileName + ".sln");
    }


    public static void showProblem(Problem prob){

        System.out.println();
        System.out.println("Number of Students: " + prob.getStudents().size());
        System.out.println("Number of Events: " + prob.getEvents().size());
        System.out.println();

        for(int s = 0; s < prob.getStudents().size(); s++){
            for(int e = 0; e < prob.getEvents().size(); e++){
                if(prob.getStudentEvents().get(s).get(e)){
                    System.out.println("Student " + s + " goes to event " + e);
                }
            }
        }

        System.out.println();
        System.out.println("Number of Rooms: " + prob.getRooms().size());
        System.out.println();

        for(int r = 0; r < prob.getRooms().size(); r++){
            for(int f = 0; f < prob.getRooms().get(r).getFeatures().size(); f++){
                System.out.println("Room " + r + " as feature " + prob.getRooms().get(r).getFeatures().get(f));
            }
        }

        
        System.out.println();
        System.out.println("Number of Events: " + prob.getEvents().size());
        System.out.println();

        for(int e = 0; e < prob.getEvents().size(); e++){
            for(int f = 0; f < prob.getEvents().get(e).getRequiredFeatures().size(); f++){
                System.out.println("Event " + e + " as required feature " + prob.getEvents().get(e).getRequiredFeatures().get(f));
            }
        }

    }

}