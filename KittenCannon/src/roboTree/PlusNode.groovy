package roboTree

class PlusNode {
    def parent
    def child
    def child2
    def arity = 2
    // normalNearAbsoluteAngle normalizes angle in range 0Pi to 2Pi and return 0,Pi/2, Pi, 3*Pi/2 or 2*Pi when the angle is near those values
    def String(){
        return "+"
    }
    def setChild(node){
        child = node
    }
    def setChild2(node){
        child2 = node
    }
    def clone(){
        def tempNode = new PlusNode()
        return tempNode
    }
}
