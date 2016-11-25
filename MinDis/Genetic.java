package MinDis;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Genetic extends Distance {
    LinkedList<Candidate> population = new LinkedList<Candidate>();
	final Random rand = new Random();
 
    final int populationSize = 10;
    final int parentUsePercent = 10;
 
    public Genetic(int numOfNode,Node[] node) {

    	//add 10? nodes in random to the population
    	
        Collections.sort(population); // sort method
        System.out.println("Init population sorted");
        print();
        run();
    }
 
    void print() {
        System.out.println("-- print");
        for (Candidate c : population) {
            System.out.println(c);
        }
    }
 
    
    void run() {
        final int maxSteps = 50000;
        int count = 0;
 
        while (count < maxSteps) {
            count++;
            produceNextGen();
        }
 
        System.out.println("\nResult");
        print();
    }
    
    /**
     * Selection strategy: Tournament method Replacement strategy: elitism 10%
     * and steady state find 4 random in population not same let 2 fight, and 2
     * fight the winners makes 2 children
     */
    void produceNextGen() {
        LinkedList<Candidate> newpopulation = new LinkedList<Candidate>();
 
        while (newpopulation.size() < populationSize * (1.0 - (parentUsePercent / 100.0))) {
 
            int size = population.size();
            int i = rand.nextInt(size);
            int j, k, l;
 
            j = k = l = i;
 
            while (j == i)
                j = rand.nextInt(size);
            while (k == i || k == j)
                k = rand.nextInt(size);
            while (l == i || l == j || k == l)
                l = rand.nextInt(size);
 
            Candidate c1 = population.get(i);
            Candidate c2 = population.get(j);
            Candidate c3 = population.get(k);
            Candidate c4 = population.get(l);
 
            int f1 = c1.fitness();
            int f2 = c2.fitness();
            int f3 = c3.fitness();
            int f4 = c4.fitness();
 
            Candidate w1, w2;
 
            if (f1 > f2)
                w1 = c1;
            else
                w1 = c2;
 
            if (f3 > f4)
                w2 = c3;
            else
                w2 = c4;
 
            Candidate child1, child2;
 
            // Method one-point crossover random pivot
            // int pivot = rand.nextInt(Candidate.SIZE-2) + 1; // cut interval
            // is 1 .. size-1
            // child1 = newChild(w1,w2,pivot);
            // child2 = newChild(w2,w1,pivot);
 
            // Method uniform crossover
            Candidate[] childs = newChilds(w1, w2);
            child1 = childs[0];
            child2 = childs[1];
 
            double mutatePercent = 0.01;
            boolean m1 = rand.nextFloat() <= mutatePercent;
            boolean m2 = rand.nextFloat() <= mutatePercent;
 
            if (m1)
                mutate(child1);
            if (m2)
                mutate(child2);
 
            boolean isChild1Good = child1.fitness() >= w1.fitness();
            boolean isChild2Good = child2.fitness() >= w2.fitness();
 
            newpopulation.add(isChild1Good ? child1 : w1);
            newpopulation.add(isChild2Good ? child2 : w2);
        }
 
        // add top percent parent
        int j = (int) (populationSize * parentUsePercent / 100.0);
        for (int i = 0; i < j; i++) {
            newpopulation.add(population.get(i));
        }
 
        population = newpopulation;
        Collections.sort(population);
    }
 
    // one-point crossover random pivot
    Candidate newChild(Candidate c1, Candidate c2, int pivot) {
        Candidate child = new Candidate();
 
        for (int i = 0; i < pivot; i++) {
            child.genotype[i] = c1.genotype[i];
        }
        for (int j = pivot; j < Candidate.SIZE; j++) {
            child.genotype[j] = c2.genotype[j];
        }
 
        return child;
    }
 
    // Uniform crossover
    Candidate[] newChilds(Candidate c1, Candidate c2) {
        Candidate child1 = new Candidate();
        Candidate child2 = new Candidate();
 
        for (int i = 0; i < Candidate.SIZE; i++) {
            boolean b = rand.nextFloat() >= 0.5;
            if (b) {
                child1.genotype[i] = c1.genotype[i];
                child2.genotype[i] = c2.genotype[i];
            } else {
                child1.genotype[i] = c2.genotype[i];
                child2.genotype[i] = c1.genotype[i];
            }
        }
 
        return new Candidate[] { child1, child2 };
    }
 
    void mutate(Candidate c) {
        int i = rand.nextInt(Candidate.SIZE);
        c.genotype[i] = !c.genotype[i]; // flip
    }
    
    /*
     * Inner Class:
     */
    public class Candidate implements Comparable<Candidate> {
        public static final int SIZE = 10;
        public boolean[] genotype;
        final Random rand = new Random();
     
        public Candidate() {
            genotype = new boolean[SIZE];
        }
     
        void random() {
            for (int i = 0; i < genotype.length; i++) {
                genotype[i] = 0.5 > rand.nextFloat();
            }
        }
     
        private String gene() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < genotype.length; i++) {
                sb.append(genotype[i] == true ? 1 : 0);
            }
            return sb.toString();
        }
     
        int fitness() {
            int sum = 0;
            for (int i = 0; i < genotype.length; i++) {
                if (genotype[i])
                    sum++;
            }
            return sum;
        }
     
        public int compareTo(Candidate o) {
            int f1 = this.fitness();
            int f2 = o.fitness();
     
            if (f1 < f2)
                return 1;
            else if (f1 > f2)
                return -1;
            else
                return 0;
        }
     
        @Override
        public String toString() {
            return "gene=" + gene() + " fit=" + fitness();
        }
    }
    
}
