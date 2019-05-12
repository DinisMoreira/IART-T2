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
        
        sol.generateRandomSolution();



        int numInfrac = sol.getTotalDaysWith2MoreConsClasses();

        Solution newNeigh = new Solution(sol);

        newNeigh.allocateEventNoConstraints(4, 33, newNeigh.getEventList().get(0).getAcceptableRooms().get(0));
        newNeigh.allocateEventNoConstraints(5, 34, newNeigh.getEventList().get(0).getAcceptableRooms().get(0));
        newNeigh.allocateEventNoConstraints(6, 35, newNeigh.getEventList().get(0).getAcceptableRooms().get(0));

        int newNumInfrac = newNeigh.getTotalDaysWith2MoreConsClasses();

        System.out.println("Event " + newNeigh.getEventList().get(4).getID() + " / Room - " +  newNeigh.getEventList().get(4).getRoom().getID() + " / Timeslot - " +  newNeigh.getEventList().get(4).getTimeSlot());
        System.out.println("Event " + newNeigh.getEventList().get(5).getID() + " / Room - " +  newNeigh.getEventList().get(5).getRoom().getID() + " / Timeslot - " +  newNeigh.getEventList().get(5).getTimeSlot());
        System.out.println("Event " + newNeigh.getEventList().get(6).getID() + " / Room - " +  newNeigh.getEventList().get(6).getRoom().getID() + " / Timeslot - " +  newNeigh.getEventList().get(6).getTimeSlot());
        System.out.println(numInfrac + " / " + newNumInfrac);

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

                    //Check why score isn't changing
                    newNeigh.allocateEventNoConstraints(e.getID(), i, r);

                    int newNeighScore = newNeigh.getSolutionEval();

                    System.out.println("Event - " + newNeigh.getEventList().get(e.getID()).getID() + " / Timeslot - " + newNeigh.getEventList().get(e.getID()).getTimeSlot() + " / Room - " + newNeigh.getEventList().get(e.getID()).getRoom().getID());
                    System.out.println("Moved event " + e.getID() + " to timeslot " + i + " in room " + r.getID() + " // New Score = " + newNeighScore);

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