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
public class weightedGraph {
    weightedNode adjList[];
    int vertices;
    public weightedGraph(int vertices){
        this.vertices = vertices;
        adjList = new weightedNode[vertices];
        for(int i=0;i<vertices;i++)
            adjList[i] = null;
    }
    public void addEdge(int src, int desc, int weight){
        if(adjList[src] == null){
            adjList[src] = new weightedNode(desc, weight, null);
        }
        else{
            weightedNode head = adjList[src];
            while(head.next != null){
                head = head.next;
            }
            head.next = new weightedNode(desc, weight, null);
        }
        if(adjList[desc] == null){
            adjList[desc] = new weightedNode(src, weight, null);
        }
        else{
            weightedNode head = adjList[desc];
            while(head.next != null){
                head = head.next;
            }
            head.next = new weightedNode(src, weight, null);
        }
    }
}
