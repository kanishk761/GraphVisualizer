/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.assignment;

/**
 *
 * @author kanishk chaudhary
 */
public class weightedNode {
    int vertex;
    int weight;
    weightedNode next;
    public weightedNode(int vertex, int weight, weightedNode next){
        this.vertex = vertex;
        this.weight = weight;
        this.next = next;
    }
    
}
