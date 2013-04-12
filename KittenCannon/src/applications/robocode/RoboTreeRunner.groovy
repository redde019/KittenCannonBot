package applications.robocode
import roboTree.*
class RoboTreeRunner {
static main(args){
    def forest = []
    def robotBuilder
    def values
    robotBuilder = new RobotBuilder("templates/Leopard.template")
    for(int i = 0; i<5;i++){
        forest.add(new RoboTree())
        forest[i].create()
        println forest[i]
        println forest[i].head
        println forest[i].treeToString()
        values = ["id" : i, "new_angle": forest[i].treeToString()]
            
           robotBuilder.buildJarFile(values)
    }
    
   
}
}