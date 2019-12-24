/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algo.assignment;

import java.util.*;
/**
 *
 * @author kanishk chaudhary
 */
public class graph {
    public LinkedList<Integer> adjList[];
    public int Vertices;
    graph(int vertices){
        Vertices = vertices;
        adjList = new LinkedList[vertices];
        
        for(int i=0;i<vertices;i++)
            adjList[i] = new LinkedList<>();
    }
    
    public void addEdge(int src, int des){
        //implementation for undirected graph
        if(des>=Vertices || src>=Vertices)
        {
            System.out.print("not a valid input");
        }
        adjList[src].add(des);
        adjList[des].add(src);
    }
}
