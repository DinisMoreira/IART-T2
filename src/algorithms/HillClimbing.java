package algorithms;

import elements.*;
import java.util.ArrayList;

public class HillClimbing{

    private Problem prob;


    public HillClimbing(Problem p){
        this.prob = p;
    }

    public Solution getSolution(){
        Solution sol = new Solution(prob);

        ArrayList<Solution> betterNeigh = new ArrayList<Solution>();

        ArrayList<Student> studList;
        ArrayList<Event> eventList;


        /*TESTING STUFF*/
        System.out.println();

        //System.out.println("Event 0 - Time Slot: " + sol.getEventList().get(0).getTimeSlot() + " / Room: " + sol.getEventList().get(0).getRoom().getID());
        //System.out.println("Event 1 - Time Slot: " + sol.getEventList().get(1).getTimeSlot() + " / Room: " + sol.getEventList().get(1).getRoom().getID());
        //sol.showSolutionOrderedByEventId();
        
        sol.generateRandomSolution();

        int numSoftInfrac = sol.getNumberOfSoftInfractions();

        //betterNeigh = getBetterNeighbours(sol);

        //System.out.println("Num better neigh. = " + betterNeigh.size());

        return sol;
    }

    public ArrayList<Solution> getBetterNeighbours(Solution sol){
        ArrayList<Solution> betterNeigh = new ArrayList<Solution>();
        
        int currSolScore = sol.getSolutionEval();
        int n = 0;

        System.out.println("Curr. Score = " + currSolScore);
        
        for(Event e : sol.getEventList()){
            for(Room r : e.getAcceptableRooms()){
                for(int i = 0; i < sol.getProb().getTimeSlots(); i++){
                    Solution newNeigh = new Solution(sol);

                    newNeigh.allocateEventNoConstraints(e.getID(), i, r);
                    int newNeighScore = newNeigh.getSolutionEval();

                    
                    System.out.println("New Score = " + n + " / event id = " + e.getID());

                    if(newNeighScore < currSolScore){
                        betterNeigh.add(newNeigh);
                    }
                    n++;
                }
            }
        }

        return betterNeigh;
    }

}