package algorithms;

import elements.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing extends Algorithm {

    private Problem prob;
    private int maxTriesPerGen = 100;
    private Random random;

    public SimulatedAnnealing(Problem p) {
        this.prob = p;
        this.maxTriesPerGen = p.getEvents().size();
        this.random = new Random();
    }

    public Solution getSolution() {
        Solution sol = new Solution(prob);
        Solution randomSol;

        System.out.println();
        sol.generateRandomSolution();

        double temperature = Double.MAX_VALUE;
        while (sol.getScore() > 0) {
            randomSol = getRandomSolution(sol);

            if (randomSol == null) {
                System.out.println("Finished with score = " + sol.getScore());
                if (sol.getNumberOfHardInfractions() == 0) {
                    System.out.println("Valid Solution");
                } else {
                    System.out.println("Invalid Solution");
                }
                break;
            } else {
                final double deltaE = randomSol.getScore() - sol.getScore();
                if (deltaE > 0)
                    sol = randomSol;
                else {
                    final double probability = Math.exp(deltaE / temperature);
                    final double randomDouble = this.random.nextDouble();
                    if (randomDouble < probability)
                        sol = randomSol;
                }

                System.out.println("Best neighbour score = " + sol.getScore());

            }
            temperature /= 2;
        }

        return sol;
    }

    public Solution getRandomSolution(Solution sol) {
        final ArrayList<Solution> betterNeighs = getBetterNeighbours(sol);

        final Solution bestNeigh = getRandomNeighbour(betterNeighs);

        return bestNeigh;
    }

    public Solution getRandomNeighbour(ArrayList<Solution> betterNeighs) {
        final int size = betterNeighs.size();
        if (size < 1)
            return null;

        final int randomIndex = this.random.nextInt(size);
        final Solution randomNeigh = betterNeighs.get(randomIndex);

        return randomNeigh;
    }

    public ArrayList<Solution> getBetterNeighbours(Solution sol) {
        ArrayList<Solution> betterNeighs = new ArrayList<Solution>();
        Random rand = new Random();

        int currSolScore;
        if (sol.getScore() != ~0)
            currSolScore = sol.getScore();
        else {
            currSolScore = sol.calculateSolutionEval();
        }
        int n = 0;
        int i = 0;
        int j = 0;

        // System.out.println("Curr. Score = " + currSolScore);

        if (maxTriesPerGen > 100) {
            for (j = 0; j < sol.getEventList().size(); j++) {
                Event e = sol.getEventList().get(j);

                for (Room r : e.getAcceptableRooms()) {
                    for (i = 0; i < sol.getProb().getTimeSlots(); i++) {
                        Solution newNeigh = new Solution(sol);

                        newNeigh.allocateEventNoConstraints(e.getID(), i, r);

                        int newNeighScore = newNeigh.calculateSolutionEval();

                        if (newNeighScore < currSolScore) {
                            betterNeighs.add(newNeigh);
                        }

                        // System.out.println(n + " / " + e.getID());
                        i += rand.nextInt(sol.getProb().getTimeSlots());
                        if (n > maxTriesPerGen || i >= sol.getProb().getTimeSlots()) {
                            break;
                        }

                        n++;
                    }
                    if (n > maxTriesPerGen || (i & 1) != 0) {
                        break;
                    }
                }
                j += rand.nextInt(3);
                if (n > maxTriesPerGen || j >= sol.getEventList().size()) {
                    break;
                }

            }
        } else {
            for (j = 0; j < sol.getEventList().size(); j++) {
                Event e = sol.getEventList().get(j);
                for (Room r : e.getAcceptableRooms()) {
                    for (i = 0; i < sol.getProb().getTimeSlots(); i++) {
                        Solution newNeigh = new Solution(sol);

                        newNeigh.allocateEventNoConstraints(e.getID(), i, r);

                        int newNeighScore = newNeigh.calculateSolutionEval();

                        if (newNeighScore < currSolScore) {
                            betterNeighs.add(newNeigh);
                        }

                        n++;
                    }
                }
            }
        }

        return betterNeighs;
    }

    public Solution getSolutionDynamicMutProb() {
        return null;
    }

    public Solution getSolutionOptimized() {
        return null;
    }
}