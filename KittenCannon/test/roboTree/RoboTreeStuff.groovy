package roboTree

import spock.lang.Specification;

class RoboTreeStuff extends Specification {
    
    def 'size stuff'(){
        when:
        def pine = new RoboTree()
        def tree = new RoboTree()
        tree.head = new AbsoluteNode()
        tree.head.child = new HeadingNode()
        pine.head = new PlusNode()
        pine.head.child = new BearingsNode()
        pine.head.child2 = new VelocityNode()
        then:
        tree.size() == 2
        pine.size() == 3
        
    }
    def 'get stuff'(){
        when:
        def tree = new RoboTree()
        tree.head = new AbsoluteNode()
        tree.head.child = new HeadingNode()
        
        then:
        
        tree.getNode(0) instanceof AbsoluteNode
        tree.getNode(1) instanceof HeadingNode
    }
    def'clone stuff'(){
        when:
        def tree = new RoboTree()
        tree.head = new PlusNode()
        tree.head.child = new HeadingNode()
        tree.head.child2 = new TimesNode()
        tree.head.child2.child = new RandConstantNode()
        tree.head.child2.child2 = new GunHeadingNode()
        def clon = tree.clone()
//        println "tree ${tree.head.child2.child}"
//        println "clone ${clon.head.child2.child}"
//        println "clone ${clon.head.child2.child2}"
//        println "clone ${clon.head.child2}"
        
        then:
        clon.head != tree.head
        clon.head instanceof PlusNode
        clon.head.child != tree.head.child
        clon.head.child instanceof HeadingNode
        clon.head.child2 instanceof TimesNode
        clon.head.child2.child instanceof RandConstantNode
        clon.head.child2.child2 instanceof GunHeadingNode
    }
    def 'tree to string stuff'(){
        when:
        def tree = new RoboTree()
        def pine = new RoboTree()
        def fur = new RoboTree()
        tree.head = new AbsoluteNode()
        tree.head.child = new NearAbsoluteNode()
        tree.head.child.child = new RelativeNode()
        tree.head.child.child.child = new BearingsNode()
        def string = tree.treeToString()
        
       
        
        then:
        string == "Utils.normalAbsoluteAngle(Utils.normalNearAbsoluteAngle(Utils.normalRelativeAngle(e.getBearingRadians())))"
    }
    def 'tree with plus and minus stuff'(){
        when:
        def tree = new RoboTree()
        tree.head = new PlusNode()
        tree.head.child = new VelocityNode()
        tree.head.child2 = new HeadingNode()
        def string = tree.treeToString()
        
       
        then:
        string == "(e.getVelocity())+(e.getHeadingRadians())"
        tree.getNode(1) instanceof VelocityNode
        tree.getNode(2) instanceof HeadingNode
    }
    
    def 'cross over stuff at head'(){
        when:
        def cross = new RoboCrossOver()
        def tree = new RoboTree()
        def pine = new RoboTree()
        tree.head = new BearingsNode()
        pine.head = new VelocityNode()
        def treeArray = cross.crossover(tree,pine,9)
        then:
        treeArray[0].head instanceof VelocityNode
        treeArray[1].head instanceof BearingsNode
        
        cleanup:
        clean([10,11])
        
    }
    
    def 'Node clone stuff'(){
        when:
        def randy = new RandConstantNode()
        def randyClone = randy.clone()
        
        then:
        randyClone instanceof RandConstantNode
        randyClone.value == randy.value
        randyClone != randy
    }
    
    def'test clone with size'(){
        when:
        def tree = new RoboTree()
        tree.head = new PlusNode()
        tree.head.child = new BearingsNode()
        tree.head.child2 = new VelocityNode()
        def clone = tree.clone()
        then:
        clone.size() == tree.size()
    }
    def 'cross over stuff at specified point'(){
        when:
        def cross = new RoboCrossOver()
        def tree = new RoboTree()
        def pine = new RoboTree()
        tree.head = new PlusNode()
        tree.head.child = new BearingsNode()
        tree.head.child2 = new VelocityNode()
        pine.head = new NearAbsoluteNode()
        pine.head.child = new VelocityNode()
      
        def pineArray = cross.crossover(tree,pine,1,1,5)
        def treeArray = cross.crossover(tree,pine,0,1,20)
        then:
        treeArray[0].head instanceof VelocityNode
        treeArray[1].head.child instanceof PlusNode
        pineArray[0].head.child instanceof VelocityNode
        pineArray[1].head.child instanceof BearingsNode
        cleanup:
        clean([6,7,21,22])
    }
    def 'test tree id stuff'(){
        when:
        def tree = new RoboTree(id:5)
        then:
        tree.id == 5
    }
    def'test ++stuff vs stuff++'(){
        when:
        int g
        int z
        int B = 6
        int A = 6
        g =++B
        z = A++
        then:
        g == 7
        z == 6
        
    }
    def'randConstNode stuff'(){
        when:
          def tree = new RoboTree()
          def constant = new RandConstantNode()
          tree.head = constant
          def constClone = tree.clone()
        then:
            constant.value.toString() == constant.String()
            constClone.head.value.toString() == constant.String()
    }
    def clean(arrId){
        for(id in arrId){
        removeJavaFile(id)
        removeClassFile(id)
        removePropertiesFile(id)
        
        }
        
    }
    def removeJavaFile(id) {
        new File("evolved_robots/evolved/KittenCannon_${id}.java").delete()
    }

    def removeClassFile(id) {
        new File("evolved_robots/evolved/KittenCannon_${id}.class").delete()
        //new File("evolved_robots/evolved/Individual_${id}\$MicroEnemy.class").delete()
    }
    
    def removePropertiesFile(id) {
        new File("evolved_robots/evolved/KittenCannon_${id}.properties").delete()
    }
}
