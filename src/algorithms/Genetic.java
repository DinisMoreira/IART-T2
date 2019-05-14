package algorithms;

import elements.*;
import java.util.ArrayList;
import java.util.Random;

public class Genetic{

    private Problem prob;
    private int populationSize;
    private int mutationProbability;


    public Genetic(Problem p, int popSize, int mutationProbability){
        this.prob = p;
        this.populationSize = popSize;
        this.mutationProbability = mutationProbability;
    }

    public Solution getSolution(){
        Solution finalSol = new Solution(prob);
        ArrayList<Solution> initialPop = generateInitialPopulation();

        //Solution solToMut = initialPop.get(initialPop.size()-1);
        Solution child = reproduce(initialPop.get(initialPop.size()-1), initialPop.get(initialPop.size()-2));

        for(Solution s : initialPop){
            System.out.println("----------------------------------");
            s.showSolutionOrderedByEventId();
        }

        
        System.out.println("************************************");

        //mutateSolutionOneEvent(solToMut);
        //solToMut.showSolutionOrderedByEventId();
        //child.showSolutionOrderedByEventId();



        return finalSol;
    }


    public void mutateSolutionOneEvent(Solution original){ 
        Random rand = new Random();

        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int eventId = rand.nextInt(original.getEventList().size());

        int roomIndex = rand.nextInt(original.getEventList().get(eventId).getAcceptableRooms().size());
        Room room = original.getEventList().get(eventId).getAcceptableRooms().get(roomIndex);

        System.out.println("Mutated event " + eventId);

        original.allocateEventNoConstraints(eventId, timeSlot, room);

        return;
    }

    public Solution reproduce(Solution p1, Solution p2){
        Random rand = new Random();
        Solution child = new Solution(p1);
        int randomNum;

        for(Event e : child.getEventList()){
            randomNum = rand.nextInt(2);
            if(randomNum == 1){
                System.out.println("P2 event " + e.getID());
                e = p2.getEventList().get(e.getID());
            }
        }


        return child;
    }


    public ArrayList<Solution> generateInitialPopulation(){
        ArrayList<Solution> initialPop = new ArrayList<Solution>();

        Solution sol = null;

        for(int i = 0; i < populationSize; i++){
            sol = new Solution(prob);
            initialPop.add(sol);
        }

        for(Solution s : initialPop){
            s.generateRandomSolution();
        }

        return initialPop;
    }

}