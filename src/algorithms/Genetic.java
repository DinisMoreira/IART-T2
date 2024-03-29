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

    public Solution getSolutionDynamicMutProb(){
        Random rand = new Random();
        Solution bestSol = null;
        TreeSet<Solution> population = generateInitialPopulation();

        Solution p1, p2, child, bestSolEver;
        int oldBestSolScore = 2147483647;

        int elegibleParents = populationSize/2;
        int limitNumberOfTries = 100;
        int numberOfTries = 0;

        int bestScoreEver = 2147483647;


        bestSol = population.first();
        bestSolEver = bestSol;

        while(bestSol.getScore() > 0 && numberOfTries < limitNumberOfTries){
            TreeSet<Solution> newPopulation = new TreeSet<Solution>();

            bestSol = population.first();

            if(bestSolEver.getScore() > bestSol.getScore()){
                bestSolEver = bestSol;
            }

            if(bestSol.getScore() != oldBestSolScore){
                System.out.println("Distance to perfect solution = " + bestSol.getScore());
                oldBestSolScore = bestSol.getScore();

                if(bestScoreEver > bestSol.getScore()){
                    bestScoreEver = bestSol.getScore();
                    numberOfTries = 0;
                    mutationProbability = 0;
                }
                   
            }

            while(newPopulation.size() < populationSize){
                p1 = getPopulationElementIndex(population, rand.nextInt(elegibleParents));
                p2 = getPopulationElementIndex(population, rand.nextInt(elegibleParents));

                child = reproduce(p1, p2);

                int isMutated = rand.nextInt(100);

                if(mutationProbability >= isMutated){
                    mutateSolutionOneEvent(child);
                }

                child.calculateSolutionEval();

                newPopulation.add(child);
            }

            if(mutationProbability < 50){
                mutationProbability++;
            }

            numberOfTries++;

            population = newPopulation;
        }

        return bestSolEver;
    }

    public Solution getSolutionOptimized(){
        Random rand = new Random();
        Solution bestSol = null;
        TreeSet<Solution> population = generateInitialPopulation();

        Solution p1, p2, child, bestSolEver;
        int oldBestSolScore = 2147483647;

        int elegibleParents = populationSize/2;
        int limitNumberOfTries = 100;
        int numberOfTries = 0;

        int bestScoreEver = 2147483647;

        bestSol = population.first();
        bestSolEver = bestSol;

        while(bestSol.getScore() > 0 && numberOfTries < limitNumberOfTries){
            TreeSet<Solution> newPopulation = new TreeSet<Solution>();

            bestSol = population.first();

            if(bestSolEver.getScore() > bestSol.getScore()){
                bestSolEver = bestSol;
            }

            if(bestSol.getScore() != oldBestSolScore){
                System.out.println("Distance to perfect solution = " + bestSol.getScore());
                oldBestSolScore = bestSol.getScore();

                if(bestScoreEver > bestSol.getScore()){
                    bestScoreEver = bestSol.getScore();
                    numberOfTries = 0;
                }
                   
            }

            while(newPopulation.size() < populationSize){
                p1 = getPopulationElementIndex(population, rand.nextInt(elegibleParents));
                p2 = getPopulationElementIndex(population, rand.nextInt(elegibleParents));

                child = reproduce(p1, p2);

                int isMutated = rand.nextInt(100);

                if(mutationProbability >= isMutated){
                    mutateSolutionOneEvent(child);
                }

                child.calculateSolutionEval();

                newPopulation.add(child);
            }


            numberOfTries++;

            population = newPopulation;
        }

        return bestSolEver;
    }


    


    public Solution getRandomPopulationElement(TreeSet<Solution> population){
        Random rand = new Random();
        int index = rand.nextInt(population.size());
        
        return getPopulationElementIndex(population, index);
    }

    public Solution getPopulationElementIndex(TreeSet<Solution> population, int index){
        int i = 0;

        

        for(Solution s : population){
            if(i == index){
                //System.out.println("Getting sol. " + i + " of " + population.size());
                return s;
            }
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

        //System.out.println("Mutated event " + eventId);

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

        sol = new Solution(prob);
        sol.generateRandomSolution();
        initialPop.add(sol);

        while(initialPop.size() < populationSize){
            sol = new Solution(prob);
            sol.generateRandomSolution();
            initialPop.add(sol);
            
        }
        return initialPop;
    }

    public Solution getSolution(){
        return null;
    }

}