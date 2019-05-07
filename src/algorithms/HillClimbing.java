package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import elements.*;

public class HillClimbing{

    private Problem prob;


    public HillClimbing(Problem p){
        this.prob = p;
    }


    public void getSolution(){
        Solution sol = new Solution(prob);



        /*TESTING STUFF*/
        System.out.println();
        sol.allocateEvent(sol.getEventList().get(0).getID(), 0, prob.getRooms().get(0));
        sol.allocateEvent(sol.getEventList().get(1).getID(), 2, prob.getRooms().get(0));

        System.out.println();
//        System.out.println("Event 0 - Time Slot: " + sol.getEventList().get(0).getTimeSlot() + " / Room: " + sol.getEventList().get(0).getRoom().getID());
        System.out.println("Event 1 - Time Slot: " + sol.getEventList().get(1).getTimeSlot() + " / Room: " + sol.getEventList().get(1).getRoom().getID());

    }

    public void outputSolutionToFile(String fileName) {
        File file = new File(fileName);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch(FileNotFoundException e) {return;}
        for(Event e : this.prob.getEvents()) {
            //Pad number with max of 4 spaces
            printWriter.printf("%4d %4d\n",
                e.getTimeSlot(),
                ((e.getRoom() == null) ? -1 : e.getRoom().getID()));
        }
        printWriter.close();
    }

}