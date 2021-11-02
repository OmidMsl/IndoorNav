package com.omidmsl.indoornav;

import android.util.Log;

import java.util.ArrayList;

public class AStarNode {
    private Node currentNode;
    private float g;
    private ArrayList<Node> crossedNodes;

    public AStarNode(Node currentNode, float g, ArrayList<Node> crossedNodes) {
        this.currentNode = currentNode;
        this.g = g;
        this.crossedNodes = crossedNodes;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public ArrayList<Node> getCrossedNodes() {
        return crossedNodes;
    }

    public void setCrossedNodes(ArrayList<Node> crossedNodes) {
        this.crossedNodes = crossedNodes;
    }

    public static ArrayList<Node> runAStar(ArrayList<Node> mesh, Node startPoint, ArrayList<Node> destinations){
        AStarNode lastNode = new AStarNode(startPoint, 0, new ArrayList<>());
        ArrayList<AStarNode> aStarNodes = new ArrayList<>();
        aStarNodes.add(lastNode);
        while(!destinations.isEmpty() && !aStarNodes.isEmpty()){
            // finding nearest node to destination
            Log.println(Log.DEBUG, "ooo", "destinations=" + destinations.size() + "\taStarNodes=" + aStarNodes.size());
            int minNodeIndex = 0;
            double minf = getDistance(aStarNodes.get(0).currentNode, getNearestNode(aStarNodes.get(0).currentNode, destinations)) + aStarNodes.get(0).getG();

            for (int i=1 ; i<aStarNodes.size() ; i++){
                double newf = getDistance(aStarNodes.get(i).currentNode, getNearestNode(aStarNodes.get(i).currentNode, destinations)) + aStarNodes.get(i).getG();
                if (newf<minf){
                    minf = newf;
                    minNodeIndex = i;
                }
            }
            // removing selected node
            lastNode = aStarNodes.remove(minNodeIndex);
            // check if a destination is discovered
            Log.println(Log.DEBUG, "ooo", "lastNode=" + lastNode.currentNode + "\tdestinations=" + destinations);
            while (destinations.contains(lastNode.currentNode)){
                for (AStarNode asNode : aStarNodes){
                    if (!asNode.crossedNodes.contains(lastNode.currentNode)) {
                        aStarNodes.remove(asNode);
                        break;
                    }
                }
                destinations.remove(lastNode.currentNode);
            }
            // fining minimum node neighbors
            Node top = new Node(lastNode.currentNode.getX(), (float) (lastNode.currentNode.getZ()-0.094079495));
            if (!top.equals(lastNode.currentNode) && mesh.contains(top)){
                AStarNode asTop = top.isIn(aStarNodes);
                if (asTop!=null){
                    if (asTop.getG()>lastNode.getG()+0.094079495){
                        asTop.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTop.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(top, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node bottom = new Node(lastNode.currentNode.getX(), (float) (lastNode.currentNode.getZ()+0.094079495));
            if (!bottom.equals(lastNode.currentNode) && mesh.contains(bottom)){
                AStarNode asTop = bottom.isIn(aStarNodes);
                if (asTop!=null){
                    if (asTop.getG()>lastNode.getG()+0.094079495){
                        asTop.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTop.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(bottom, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node left = new Node((float) (lastNode.currentNode.getX()-0.094079495), lastNode.currentNode.getZ());
            if (!left.equals(lastNode.currentNode) && mesh.contains(left)){
                AStarNode asTop = left.isIn(aStarNodes);
                if (asTop!=null){
                    if (asTop.getG()>lastNode.getG()+0.094079495){
                        asTop.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTop.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(left, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node right = new Node((float) (lastNode.currentNode.getX()+0.094079495), lastNode.currentNode.getZ());
            if (!right.equals(lastNode.currentNode) && mesh.contains(right)){
                AStarNode asTop = right.isIn(aStarNodes);
                if (asTop!=null){
                    if (asTop.getG()>lastNode.getG()+0.094079495){
                        asTop.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTop.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(right, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node topLeft = new Node((float) (lastNode.currentNode.getX()-0.094079495), (float)(lastNode.currentNode.getZ()-0.094079495));
            if (!topLeft.equals(lastNode.currentNode) && mesh.contains(topLeft)){
                AStarNode asTopLeft = topLeft.isIn(aStarNodes);
                if (asTopLeft!=null){
                    if (asTopLeft.getG()>lastNode.getG()+0.094079495){
                        asTopLeft.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTopLeft.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(topLeft, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node topRight = new Node((float) (lastNode.currentNode.getX()+0.094079495), (float)(lastNode.currentNode.getZ()-0.094079495));
            if (!topRight.equals(lastNode.currentNode) && mesh.contains(topRight)){
                AStarNode asTopRight = topRight.isIn(aStarNodes);
                if (asTopRight!=null){
                    if (asTopRight.getG()>lastNode.getG()+0.094079495){
                        asTopRight.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asTopRight.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(topRight, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node bottomLeft = new Node((float) (lastNode.currentNode.getX()-0.094079495), (float)(lastNode.currentNode.getZ()+0.094079495));
            if (!bottomLeft.equals(lastNode.currentNode) && mesh.contains(bottomLeft)){
                AStarNode asBottomLeft = bottomLeft.isIn(aStarNodes);
                if (asBottomLeft!=null){
                    if (asBottomLeft.getG()>lastNode.getG()+0.094079495){
                        asBottomLeft.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asBottomLeft.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(bottomLeft, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            Node bottomRight = new Node((float) (lastNode.currentNode.getX()+0.094079495), (float)(lastNode.currentNode.getZ()+0.094079495));
            if (!bottomRight.equals(lastNode.currentNode) && mesh.contains(bottomRight)){
                AStarNode asBottomRight = bottomRight.isIn(aStarNodes);
                if (asBottomRight!=null){
                    if (asBottomRight.getG()>lastNode.getG()+0.094079495){
                        asBottomRight.setG((float) (lastNode.getG()+0.094079495));
                        ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                        crossedNodes.add(lastNode.currentNode);
                        asBottomRight.setCrossedNodes(crossedNodes);
                    }
                } else {
                    ArrayList<Node> crossedNodes = lastNode.crossedNodes;
                    crossedNodes.add(lastNode.currentNode);
                    aStarNodes.add(new AStarNode(bottomRight, (float) (lastNode.getG() + 0.094079495), crossedNodes));
                }
            }
            //drawMesh(mesh, startPoint, destinations, lastNode);
        }
        return lastNode.crossedNodes;
    }

    public static double getDistance(Node n1, Node n2){
        if (n1==null || n2==null)
            return 0;
        return Math.sqrt(Math.pow(n2.getX()-n1.getX(), 2) + Math.pow(n2.getZ()-n1.getZ(), 2));
    }

    public static Node getNearestNode(Node startPoint, ArrayList<Node> destinations){
        if (destinations.isEmpty() || startPoint==null)
            return null;
        double mind = getDistance(startPoint, destinations.get(0));
        Node minNode = destinations.get(0);
        for (int i=1 ; i<destinations.size() ; i++){
            double newd = getDistance(startPoint, destinations.get(i));
            if (newd<mind){
                mind = newd;
                minNode = destinations.get(i);
            }
        }
        //Log.println(Log.DEBUG, "ooo", "nearest to " + startPoint + " is " + minNode);
        return minNode;
    }

    public static void drawMesh(ArrayList<Node> mesh, Node startPoint, ArrayList<Node> destinations, AStarNode lastNode){
        float minx=mesh.get(0).getX(), minz=mesh.get(0).getZ(), maxx=mesh.get(0).getX(), maxz=mesh.get(0).getZ();
        for (Node node : mesh) {
            if (node.getX()<minx)
                minx=node.getX();
            else if (node.getX()>maxx)
                maxx=node.getX();
            if (node.getZ()<minz)
                minz=node.getZ();
            else if (node.getZ()>maxz)
                maxz=node.getZ();
        }
        int xRange = (int) (((maxx-minx)/0.094079495)+2);
        int zRange = (int) (((maxz-minz)/0.094079495)+2);
        StringBuilder s= new StringBuilder();
        for (int i=0 ; i<xRange ; i++){
            for (int j=0 ; j<zRange ; j++){
                Node n = new Node((float) (i*0.094079495 + minx), (float) (j*0.094079495 + minz));
                if (!mesh.contains(n))
                    s.append(" ");
                else if (startPoint.equals(n))
                    s.append("S");
                else if (lastNode.currentNode.equals(n))
                    s.append("*");
                else if (lastNode.crossedNodes.contains(n))
                    s.append("+");
                else if (destinations.contains(n))
                    s.append("D");
                else
                    s.append("-");
            }
            s.append("\n");
        }
        System.out.println(s);
        System.out.println("g=" + lastNode.getG());
    }
}
