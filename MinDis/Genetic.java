package MinDis;

import java.util.ArrayList;
import java.util.Collections;

public class Genetic extends Distance {
	 /* GA parameters */
    private  double mutationRate;
    private int tournamentSize = 5;
    private boolean elitism = true;

    public Genetic(){}
    
    public Genetic(int numOfNode,Node[] node){
    	mutationRate = 1/numOfNode;
    	fillTourManager(numOfNode, node);
    	
    	/**
    	Node city = new Node(60, 200);
        TourManager.addCity(city);
        Node city2 = new Node(180, 200);
        TourManager.addCity(city2);
        Node city3 = new Node(80, 180);
        TourManager.addCity(city3);
        Node city4 = new Node(140, 180);
        TourManager.addCity(city4);
        Node city5 = new Node(20, 160);
        TourManager.addCity(city5);
        Node city6 = new Node(10, 190);
        TourManager.addCity(city6);
        Node city7 = new Node(500, 400);
        TourManager.addCity(city7);
        */
    	
    	
    	
     // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
    
    public void fillTourManager(int numOfNode, Node[] node){
    	Node tmpcity = new Node();
    	for(int u=0 ; u < numOfNode; u++){
            TourManager.addCity(node[u]);
    	}
    }
    
    // Evolves a population over one generation
    public  Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private  void mutate(Tour tour) {
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                // Get the cities at target position in tour
                Node city1 = tour.getCity(tourPos1);
                Node city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // Selects candidate tour for crossover
    private  Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
    

    /**
     * INNERCLASS_POPULATION
     */
    public class Population {

        // Holds population of tours
        Tour[] tours;

        // Construct a population
        public Population(int populationSize, boolean initialise) {
            tours = new Tour[populationSize];
            // If we need to initialise a population of tours do so
            if (initialise) {
                // Loop and create individuals
                for (int i = 0; i < populationSize(); i++) {
                    Tour newTour = new Tour();
                    newTour.generateIndividual();
                    saveTour(i, newTour);
                }
            }
        }
        
        // Saves a tour
        public void saveTour(int index, Tour tour) {
            tours[index] = tour;
        }
        
        // Gets a tour from population
        public Tour getTour(int index) {
            return tours[index];
        }

        // Gets the best tour in the population
        public Tour getFittest() {
            Tour fittest = tours[0];
            // Loop through individuals to find fittest
            for (int i = 1; i < populationSize(); i++) {
                if (fittest.getFitness() <= getTour(i).getFitness()) {
                    fittest = getTour(i);
                }
            }
            return fittest;
        }

        // Gets population size
        public int populationSize() {
            return tours.length;
        }
    }
    
    /**
     * INNERCLASS_TOUR
     */
    public class Tour{

        // Holds our tour of cities
        private ArrayList tour = new ArrayList<Node>();
        // Cache
        private double fitness = 0;
        private int distance = 0;

        int citynum = TourManager.numberOfCities();
        // Constructs a blank tour
        public Tour(){
            for (int i = 0; i <citynum;  i++) {
                tour.add(null);
            }
        }
        
        public Tour(ArrayList tour){
            this.tour = tour;
        }

        // Creates a random individual
        public void generateIndividual() {
            // Loop through all our destination cities and add them to our tour
            for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
              setCity(cityIndex, TourManager.getCity(cityIndex));
            }
            // Randomly reorder the tour
            Collections.shuffle(tour);
        }

        // Gets a city from the tour
        public Node getCity(int tourPosition) {
            return (Node)tour.get(tourPosition);
        }

        // Sets a city in a certain position within a tour
        public void setCity(int tourPosition, Node city) {
            tour.set(tourPosition, city);
            // If the tours been altered we need to reset the fitness and distance
            fitness = 0;
            distance = 0;
        }
        
        // Gets the tours fitness
        public double getFitness() {
            if (fitness == 0) {
                fitness = 1/(double)getDistance();
            }
            return fitness;
        }
        
        // Gets the total distance of the tour
        public int getDistance(){
            if (distance == 0) {
                int tourDistance = 0;
                // Loop through our tour's cities
                for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                    // Get city we're travelling from
                    Node fromCity = getCity(cityIndex);
                    // City we're travelling to
                    Node destinationCity;
                    // Check we're not on our tour's last city, if we are set our 
                    // tour's final destination city to our starting city
                    if(cityIndex+1 < tourSize()){
                        destinationCity = getCity(cityIndex+1);
                    }
                    else{
                        destinationCity = getCity(0);
                    }
                    // Get the distance between the two cities
                    tourDistance += fromCity.distanceTo(destinationCity);
                }
                distance = tourDistance;
            }
            return distance;
        }

        // Get number of cities on our tour
        public int tourSize() {
            return tour.size();
        }
        
        // Check if the tour contains a city
        public boolean containsCity(Node city){
            return tour.contains(city);
        }
        
        @Override
        public String toString() {
            String geneString = "|";
            for (int i = 0; i < tourSize(); i++) {
                geneString += getCity(i)+"|";
            }
            return geneString;
        }
    }
    
    /**
     * INNERCLASS_TOURMANAGER
     */
    
    public static class TourManager {

        // Holds our cities
        private  static ArrayList destinationCities = new ArrayList<Node>();

        // Adds a destination city
        public static void addCity(Node city) {
            destinationCities.add(city);
        }
        
        // Get a city
        public  static Node getCity(int index){
            return (Node)destinationCities.get(index);
        }
        
        // Get the number of destination cities
        public static int numberOfCities(){
            return destinationCities.size();
        }
    }
    
}
