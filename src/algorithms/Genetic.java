package algorithms;

import elements.*;
import java.util.ArrayList;
import java.util.Random;

public class Genetic{

    private Problem prob;
    private int populationSize;


    public Genetic(Problem p, int popSize){
        this.prob = p;
        this.populationSize = popSize;
    }

    public Solution getSolution(){
        Solution finalSol = new Solution(prob);
        ArrayList<Solution> initialPop = generateInitialPopulation();

        



        return finalSol;
    }


    public ArrayList<Solution> generateInitialPopulation(){
        ArrayList<Solution> initialPop = new ArrayList<Solution>();

        for(int i = 0; i < populationSize; i++){
            Solution sol = new Solution(prob);
            initialPop.add(sol);
        }

        for(Solution s : initialPop){
            s.generateRandomSolution();
            System.out.println("----------------------------------");
            s.showSolutionOrderedByEventId();
        }

        return initialPop;
    }

}