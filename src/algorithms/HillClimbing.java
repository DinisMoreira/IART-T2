package algorithms;

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
        System.out.println("Event 0 - Time Slot: " + sol.getEventList().get(0).getTimeSlot() + " / Room: " + sol.getEventList().get(0).getRoom().getID());
        System.out.println("Event 1 - Time Slot: " + sol.getEventList().get(1).getTimeSlot() + " / Room: " + sol.getEventList().get(1).getRoom().getID());

        sol.generateRandomSolution();

        
    }


}