package algorithms;

import elements.*;
import java.util.ArrayList;

public class HillClimbing{

    private Problem prob;


    public HillClimbing(Problem p){
        this.prob = p;
    }


    public void getSolution(){
        Solution sol = new Solution(prob);

        ArrayList<Student> studList;
        ArrayList<Event> eventList;



        /*TESTING STUFF*/
        System.out.println();
        sol.generateRandomSolution();
        //sol.showSolutionOrderedByEventId();

    }


}