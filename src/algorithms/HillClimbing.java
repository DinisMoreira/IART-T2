package algorithms;

import elements.*;
import java.util.ArrayList;
import java.util.Random;

public class HillClimbing{

    private Problem prob;
    private int maxTriesPerGen = 100;


    public HillClimbing(Problem p){
        this.prob = p;
        this.maxTriesPerGen = p.getEvents().size();
    }

    public Solution getSolution(){
        Solution sol = new Solution(prob);
        Solution betterSol;
        ArrayList<Student> studList;
        ArrayList<Event> eventList;

        System.out.println();
        sol.generateRandomSolution();

        while(sol.getScore() > 0){
            betterSol = getBetterSolution(sol);

            if(betterSol == null){
                System.out.println("Finished with score = " + sol.getScore());
                if(sol.getNumberOfHardInfractions() == 0){
                    System.out.println("Valid Solution");
                }
                else{
                    System.out.println("Invalid Solution");
                }
                break;
            }
            else{
                sol = betterSol;
                System.out.println("Best neighbour score = " + sol.getScore());

            }
        }
       

        

        return sol;
    }

    public Solution getBetterSolution(Solution sol){
        Solution bestNeigh = null;
        ArrayList<Solution> betterNeighs = new ArrayList<Solution>();

        betterNeighs = getBetterNeighbours(sol);
        //System.out.println("Num better neigh. = " + betterNeighs.size());

        bestNeigh = getBestNeighbour(betterNeighs);
        return bestNeigh;
    }

    public Solution getBestNeighbour(ArrayList<Solution> betterNeighs){
        Solution bestNeigh = null;
        int bestNeighScore = 2147483647;

        for(Solution s : betterNeighs){
            //System.out.println(s.getScore());
            if(s.getScore() < bestNeighScore){
                bestNeighScore = s.getScore();
                bestNeigh = s;
            }
        }

        return bestNeigh;
    }

    public ArrayList<Solution> getBetterNeighbours(Solution sol){
        ArrayList<Solution> betterNeighs = new ArrayList<Solution>();
        Random rand = new Random();
        
        int currSolScore;
        if(sol.getScore() != 2147483647)
            currSolScore = sol.getScore();
        else{
            currSolScore = sol.calculateSolutionEval();
        }
        int n = 0;
        int i = 0;
        int j = 0;

        //System.out.println("Curr. Score = " + currSolScore);
        
        if(maxTriesPerGen > 100){
            for(j = 0; j < sol.getEventList().size(); j++){
                Event e = sol.getEventList().get(j);


                for(Room r : e.getAcceptableRooms()){
                    for(i = 0; i < sol.getProb().getTimeSlots(); i++){
                        Solution newNeigh = new Solution(sol);

                        newNeigh.allocateEventNoConstraints(e.getID(), i, r);

                        int newNeighScore = newNeigh.calculateSolutionEval();

                        if(newNeighScore < currSolScore){
                            betterNeighs.add(newNeigh);
                        }

                        //System.out.println(n + " / " + e.getID());
                        i += rand.nextInt(sol.getProb().getTimeSlots());
                        if(n > maxTriesPerGen || i >= sol.getProb().getTimeSlots()){
                            break;
                        }

                        n++;
                    }
                    if(n > maxTriesPerGen || (i & 1) != 0){
                        break;
                    }
                }
                j += rand.nextInt(3);
                if(n > maxTriesPerGen || j >= sol.getEventList().size()){
                    break;
                }


            }
        }
        else{
            for(j = 0; j < sol.getEventList().size(); j++){
                Event e = sol.getEventList().get(j);
                for(Room r : e.getAcceptableRooms()){
                    for(i = 0; i < sol.getProb().getTimeSlots(); i++){
                        Solution newNeigh = new Solution(sol);

                        newNeigh.allocateEventNoConstraints(e.getID(), i, r);

                        int newNeighScore = newNeigh.calculateSolutionEval();

                        if(newNeighScore < currSolScore){
                            betterNeighs.add(newNeigh);
                        }

                        n++;
                    }
                }
            }
        }

        return betterNeighs;
    }

}