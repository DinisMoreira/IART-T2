package algorithms;

import elements.*;
import java.util.TreeSet;
import java.util.Random;
import java.util.TreeSet;

public class Genetic extends Algorithm {

    private Problem prob;
    private int populationSize;
    private int mutationProbability;


    public Genetic(Problem p, int popSize, int mutationProbability){
        this.prob = p;
        this.populationSize = popSize;
        this.mutationProbability = mutationProbability;
    }

    public Solution getSolution(){
        Random rand = new Random();
        Solution bestSol = null;
        TreeSet<Solution> population = generateInitialPopulation();

        Solution p1, p2, child;

        bestSol = population.first();

        while(bestSol.getScore() > 0){
            TreeSet<Solution> newPopulation = new TreeSet<Solution>();

            bestSol = population.first();

            System.out.println(bestSol.getScore());

            for(int i = 0; i < populationSize; i++){
                p1 = getRandomPopulationElement(population);
                p2 = getRandomPopulationElement(population);

                child = reproduce(p1, p2);

                int isMutated = rand.nextInt(101);

                if(mutationProbability > isMutated){
                    mutateSolutionOneEvent(child);
                }

                child.calculateSolutionEval();

                newPopulation.add(child);
            }

            population = newPopulation;
        }




        return bestSol;
    }

    public Solution getRandomPopulationElement(TreeSet<Solution> population){
        Random rand = new Random();
        int index = rand.nextInt(population.size());
        int i = 0;

        for(Solution s : population){
            if(i == index)
                return s;
            i++;
        }

        return null;
    }


    public void mutateSolutionOneEvent(Solution original){ 
        Random rand = new Random();

        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int eventId = rand.nextInt(original.getEventList().size());

        int roomIndex = rand.nextInt(original.getEventList().get(eventId).getAcceptableRooms().size());
        Room room = original.getEventList().get(eventId).getAcceptableRooms().get(roomIndex);

        System.out.println("Mutated event " + eventId);

        original.allocateEventNoConstraints(eventId, timeSlot, room);

        original.calculateSolutionEval();

        return;
    }

    public Solution reproduce(Solution p1, Solution p2){
        Random rand = new Random();
        Solution child = new Solution(p1);
        int randomNum;


        for(Event e : child.getEventList()){
            randomNum = rand.nextInt(2);
            if(randomNum == 1){
                child.allocateEventNoConstraints(e.getID(), p2.getEventList().get(e.getID()).getTimeSlot(), p2.getEventList().get(e.getID()).getRoom());
            }
        }

        child.calculateSolutionEval();


        return child;
    }


    public TreeSet<Solution> generateInitialPopulation(){
        TreeSet<Solution> initialPop = new TreeSet<Solution>();

        Solution sol = null;

        for(int i = 0; i < populationSize; i++){
            sol = new Solution(prob);
            sol.generateRandomSolution();
            initialPop.add(sol);
        }
        return initialPop;
    }

}