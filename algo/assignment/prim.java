/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.assignment;

import javax.swing.JOptionPane;

/**
 *
 * @author kanishk chaudhary
 */
public class prim implements Runnable{

    private final weightedGraph g1;
    private final int Vertices;
    private final visgraph viz;
    
    public prim(weightedGraph g1, int Vertices, visgraph viz){
        this.g1 = g1;
        this.Vertices = Vertices;
        this.viz = viz;
    }
    @Override
    public void run() {
        nodeQ[] valTable = new nodeQ[Vertices];
        for(int i = 0; i<Vertices; i++){
            valTable[i] = new nodeQ(0,0,-1);
        }
        valTable[0].vertex = 0;//setting cost and vertex of 0th node
        valTable[0].cost = 0;
        valTable[0].parent = -1;
        
        for(int i = 1;i < Vertices;i++){
            valTable[i].vertex = i;
            valTable[i].cost = Integer.MAX_VALUE;
            valTable[i].parent = -1;
        }
        int current_node = 0;
        boolean[] done = new boolean[Vertices];
        while(true){
            if(current_node == -1)
                break;
            weightedNode n = g1.adjList[current_node];
            while(n != null){
                int index = nodeQ.get_index(valTable, n.vertex);
                if(n.weight < valTable[index].cost && done[index] == false){
                   valTable[index].cost = n.weight;
                   valTable[index].parent = current_node;
                }
                n = n.next;
            }
            done[current_node] = true;
            //if(i == Vertices -1)
           // {
           //     break;
           // }
            int min = -1;
            int minNode = -1;
            for(int j = 0;j<Vertices;j++){
                if(done[j] == false){
                    if(min == -1){
                        min = valTable[j].cost;
                        minNode = valTable[j].vertex;
                    }
                    else if(min > valTable[j].cost){
                        min = valTable[j].cost;
                        minNode = valTable[j].vertex;
                    }
                }
                viz.paintNodeMst(valTable);
                try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            current_node = minNode; 
        }
        
        JOptionPane.showMessageDialog(null, "done");
    }
}
