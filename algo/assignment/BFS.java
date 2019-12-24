/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.assignment;

import java.util.Iterator;
import java.util.LinkedList; 
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author kanishk chaudhary
 */
public class BFS implements Runnable{
    
    private graph g1;
    private int Vertices;
    private visgraph viz;
    private int startVertex;
    
    public BFS(graph g1, int Vertices, visgraph viz, int startVertex){
        this.g1 = g1;
        this.Vertices = Vertices;
        this.viz = viz;
        this.startVertex = startVertex;
    }

    @Override
    public void run() {
        boolean[] visited = new boolean[Vertices];
        Queue<Integer> q = new LinkedList<>();
        q.add(startVertex);
        visited[startVertex] = true;
        System.out.print(startVertex+"  ");
        viz.paintNode(startVertex, -1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Iterator<Integer> i = g1.adjList[0].listIterator();
        while(!q.isEmpty()){
            int current = q.remove();
            Iterator<Integer> i = g1.adjList[current].listIterator();
            while(i.hasNext()){
                int node = i.next();
                if(!visited[node]){
                    System.out.print(node+"  ");
                    visited[node] = true;
                    q.add(node);
                    viz.paintNode(node, current);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, "done");
    }
    
}
