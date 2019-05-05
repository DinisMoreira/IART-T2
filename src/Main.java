import java.io.*;
import java.util.Scanner;

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

        System.out.println("Num Students: " + prob.getStudents().size());
        System.out.println("Num Events: " + prob.getEvents().size());

        for(int s = 0; s < prob.getStudents().size(); s++){
            for(int e = 0; e < prob.getEvents().size(); e++){
                if(prob.getStudentEvents().get(s).get(e)){
                    System.out.println("Student " + s + " goes to event " + e);
                }
            }
        }

        

        



    }

}