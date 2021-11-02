package com.omidmsl.indoornav;

import com.google.ar.core.Pose;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private float x, z;

    public Node(float x, float z) {
        this.x = x;
        this.z = z;
    }

    public Node(Pose pose) {
        this.x = pose.tx();
        this.z = pose.tz();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public AStarNode isIn(ArrayList<AStarNode> aStarNodes){
        for (AStarNode n : aStarNodes){
            if (n.getCurrentNode().equals(this)){
                return n;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" + x +
                ", " + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Float.compare(node.x, x) == 0 && Float.compare(node.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}
