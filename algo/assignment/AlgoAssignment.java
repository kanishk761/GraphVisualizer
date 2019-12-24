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

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class AlgoAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int option = Integer.parseInt(JOptionPane.showInputDialog("what you want to do i.e.\n1->DFS\n2->BFS\n3-> Spanning tree"));
        Thread workThread = null;
        int vertices = 0;
        graph g1 = null;
        int startVertex = 0;
        weightedGraph g = null;
        Random random = new Random();
        int choice;
        visgraph mainPanel = null;
        if(option == 1 || option == 2){
            choice =Integer.parseInt(JOptionPane.showInputDialog("1->Input graph manually\n2-> make a default graph"));
            if(choice == 2){
                vertices = 4+random.nextInt(7);
                //vertices =Integer.parseInt(JOptionPane.showInputDialog("no of vertices"));
                g1 = new graph(vertices);
                boolean[][] visited = new boolean[vertices][vertices];
                for(int i = 0;i<vertices;i++){
                    visited[i][i] = true;
                }
                for(int j = 0; j<vertices;j++){
                    int no_of_edges = random.nextInt(vertices - 1);
                    int madeedges = 0;
                    while(madeedges !=no_of_edges){
                        int proposed_vertex = random.nextInt(vertices);
                        if(visited[j][proposed_vertex] == false){
                            visited[j][proposed_vertex] = true;
                            visited[proposed_vertex][j] = true;
                            g1.addEdge(j, proposed_vertex);

                        }
                        madeedges++;
                    }
                }
            }
            else if(choice == 1){
                JOptionPane.showMessageDialog(null, "This creates a undirected graph\n so make sure you dont repeat any edge");
                vertices =Integer.parseInt(JOptionPane.showInputDialog("how many vertices you want"));
                g1 = new graph(vertices);
                while(true){
                    int c =Integer.parseInt(JOptionPane.showInputDialog("1-> add a edge\n2->continue"));
                    if(c == 1){
                        int e1 = Integer.parseInt(JOptionPane.showInputDialog("enter edge 1"));
                        int e2 = Integer.parseInt(JOptionPane.showInputDialog("enter edge 2"));
                        g1.addEdge(e1,e2);
                    }
                    else if(c == 2){
                        break;
                    }
                }
            }
            List<Integer> scores = new ArrayList<>();
            int maxScore = 20;
            for (int i = 0; i < vertices ; i++) {
               scores.add(random.nextInt(maxScore));
            }
            mainPanel = new visgraph(scores, g1);
            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            startVertex = Integer.parseInt(JOptionPane.showInputDialog("which vertex to start from ?"));
        }
        else if(option == 3){
            choice =Integer.parseInt(JOptionPane.showInputDialog("1->Input graph manually\n2-> make a default graph"));
            if(choice == 2){
                vertices = 4+random.nextInt(7);
                //vertices =Integer.parseInt(JOptionPane.showInputDialog("no of vertices"));
                g = new weightedGraph(vertices);
                boolean[][] visited = new boolean[vertices][vertices];
                for(int i = 0;i<vertices;i++){
                    visited[i][i] = true;
                }
                for(int j = 0; j<vertices;j++){
                    int no_of_edges = random.nextInt(vertices - 1);
                    int madeedges = 0;
                    while(madeedges !=no_of_edges){
                        int proposed_vertex = random.nextInt(vertices);
                        int weight = random.nextInt(20);
                        if(visited[j][proposed_vertex] == false){
                            visited[j][proposed_vertex] = true;
                            visited[proposed_vertex][j] = true;
                            g.addEdge(j, proposed_vertex,weight);

                        }
                        madeedges++;
                    }
                }
            }
            else if(choice == 1){
                JOptionPane.showMessageDialog(null, "This creates a undirected graph\n so make sure you dont repeat any edge");
                vertices =Integer.parseInt(JOptionPane.showInputDialog("how many vertices you want"));
                g = new weightedGraph(vertices);
                while(true){
                    int c =Integer.parseInt(JOptionPane.showInputDialog("1-> add a edge\n2->continue"));
                    if(c == 1){
                        int e1 = Integer.parseInt(JOptionPane.showInputDialog("enter edge 1"));
                        int e2 = Integer.parseInt(JOptionPane.showInputDialog("enter edge 2"));
                        int w = Integer.parseInt(JOptionPane.showInputDialog("enter the weight"));
                        g.addEdge(e1,e2,w);
                    }
                    else if(c == 2){
                        break;
                    }
                }
            }
            
           // weightedNode n = g.adjList[0];
           // while(n != null){
           //     System.out.print(n.vertex);
           //     System.out.print("hello");
           // }
            
            List<Integer> scores = new ArrayList<>();
            int maxScore = 20;
            for (int i = 0; i < vertices ; i++) {
               scores.add(random.nextInt(maxScore));
            }
            mainPanel = new visgraph(scores, g);
            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        }
        
        JOptionPane.showMessageDialog(null, "click to start");
        switch(option){
		case 1:
                    workThread = new Thread(new DFS(g1, vertices, mainPanel, startVertex));
                    break;
                case 2:
                    workThread = new Thread(new BFS(g1, vertices, mainPanel, startVertex));
                    break;
                case 3:
                    workThread = new Thread(new prim(g, vertices, mainPanel));
                default:
                    break;
        }
        workThread.start();
    }
    
}
    
