package algorithms;

import elements.*;
import java.util.ArrayList;

public class HillClimbing{

    private Problem prob;


    public HillClimbing(Problem p){
        this.prob = p;
    }


    public void getSolution(String outputPath){
        Solution sol = new Solution(prob);

        ArrayList<Student> studList;
        ArrayList<Event> eventList;


        /*TESTING STUFF*/
        System.out.println();

//      System.out.println("Event 0 - Time Slot: " + sol.getEventList().get(0).getTimeSlot() + " / Room: " + sol.getEventList().get(0).getRoom().getID());
        System.out.println("Event 1 - Time Slot: " + sol.getEventList().get(1).getTimeSlot() + " / Room: " + sol.getEventList().get(1).getRoom().getID());
        //sol.showSolutionOrderedByEventId();
        
        sol.generateRandomSolution();
        sol.outputSolutionToFile(outputPath);
    }

}