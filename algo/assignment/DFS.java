/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.assignment;

import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author kanishk chaudhary
 */
public class DFS implements Runnable{
    
    private graph g1;
    private int Vertices;
    private visgraph viz;
    private int startVertex;
    public DFS(graph g1, int Vertices, visgraph viz, int startVertex){
        this.g1 = g1;
        this.Vertices = Vertices;
        this.viz = viz;
        this.startVertex = startVertex;
    }

    @Override
    public void run() {
        boolean visited[] = new boolean[Vertices];
        
        viz.paintNode(startVertex, -1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dfsRecHelper(startVertex,visited);
        JOptionPane.showMessageDialog(null, "done");
        }
    private void dfsRecHelper(int v, boolean visited[]){
        
        visited[v] = true;
        System.out.print(v+" ");
        
       Iterator<Integer> i = g1.adjList[v].listIterator();
       while(i.hasNext()){
           int n = i.next();
           if(visited[n] == false){
               viz.paintNode(n, v);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                dfsRecHelper(n, visited);
           }
       }
    }
}

                    