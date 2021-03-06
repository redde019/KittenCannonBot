package populationMethods
import roboTree.*

import java.util.Random
import operators.Crossovers
import operators.TournamentSelection
import applications.robocode.RobotBuilder

class GeneticAlgorithm {
    // Algorithm 20

    // We need popsize to be global so that we can use it in the toString method, also added a default value
    def popsize = 65
    def Id = 0
    // Our Algorithm takes a Genetic Algorithm Problem, a desired population size
    def maximize(problem, populationSize=popsize, selector=new TournamentSelection(), Crossover= new RoboCrossOver()) {
        popsize = populationSize
        println popsize

        def startingPopulation = []
        println startingPopulation.size()
        def tree
        
        for(int i = 0; i < popsize;i++) {
            tree = new RoboTree(id:Id)
            println Id
            tree.create()
            println "quality ${tree.quality()}"
            startingPopulation[i] = tree // Add a new random individual
            Id++
        }
        
        println Id
        def fossilOfBests = []
        def best = new RoboTree(id:Id)
        best.create()
        Id++
        def qualityOfBest = best.quality()
        fossilOfBests.add("GEN_N_individual_${best.id}_quality_${qualityOfBest}")
        def genCounter = 0
        while(!best.terminate(best, qualityOfBest) && genCounter < 50) {
            
            for(def individual: startingPopulation) {
                def newQuality = individual.quality()
                
                if(newQuality > qualityOfBest) {
                    fossilOfBests.add("GEN_${genCounter}_individual_${best.id}_quality_${qualityOfBest}")
                    best = individual.clone()
                    qualityOfBest = newQuality
                    

                }
                //println"bestQuality ${best.quality()}"
            }
            //println "end of Gen ${genCounter}"
            def endingPopulation = []

            for(i in 0..(popsize/2)) {

                def parentA = selector.select(startingPopulation)
                def parentB = selector.select(startingPopulation)
                def children = Crossover.crossover(parentA, parentB,Id)
                endingPopulation.add(children[0])
                endingPopulation.add(children[1])
                Id = children[2]
            }
            startingPopulation = endingPopulation
            genCounter++
        }
        println "best tree " + best
        return [best,fossilOfBests]
    }

    String toString() {
        "GA_" + popsize
    }

}