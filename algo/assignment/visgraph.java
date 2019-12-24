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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class visgraph extends JPanel {
   private static final int MAX_SCORE = 20;
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 100;
   private static final Color GRAPH_COLOR_VISITED = Color.green;
   private static final Color GRAPH_COLOR_NOT_VISITED = Color.black;
   private static final Color GRAPH_POINT_COLOR_NOT_VISITED = Color.BLUE;
   private static final Color GRAPH_POINT_COLOR_VISITED = Color.RED;
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 30;
   private final List<Integer> scores;
   private graph mygraph = null;
   private weightedGraph myWeightedgraph = null;
   private Graphics2D g2 ;
   private final boolean[] visitedNode;
   private final boolean[][] visitedEdge;
   

   public visgraph(List<Integer> scores, graph g1) {
      this.scores = scores;
      mygraph = g1;
      visitedNode = new boolean [scores.size()];
      visitedEdge = new boolean [scores.size()][scores.size()];
   }
   public visgraph(List<Integer> scores, weightedGraph g1) {
      this.scores = scores;
      myWeightedgraph = g1;
      visitedNode = new boolean [scores.size()];
      visitedEdge = new boolean [scores.size()][scores.size()];
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      
      
      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      Stroke oldStroke = g2.getStroke();
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2, y2;
         if(mygraph != null){
            Iterator<Integer> j = mygraph.adjList[i].listIterator();
            while(j.hasNext()){
              int node = j.next();
              if(visitedEdge[i][node])
                  g2.setColor(GRAPH_COLOR_VISITED);
              else
                  g2.setColor(GRAPH_COLOR_NOT_VISITED);
              x2 = graphPoints.get(node).x;
              y2 = graphPoints.get(node).y;
              g2.drawLine(x1, y1, x2, y2);
            }
         }
         else{
             weightedNode n = myWeightedgraph.adjList[i];
             while(n != null){
                 int node = n.vertex;
                 if(visitedEdge[i][node])
                  g2.setColor(GRAPH_COLOR_VISITED);
              else
                  g2.setColor(GRAPH_COLOR_NOT_VISITED);
              x2 = graphPoints.get(node).x;
              y2 = graphPoints.get(node).y;
              g2.drawLine(x1, y1, x2, y2);
              g2.setColor(Color.MAGENTA);
              g2.drawString(String.valueOf(n.weight),(x1+x2)/2,(y1+y2)/2);
              
              n=n.next;
             }
         }  
      }
      
      g2.setStroke(oldStroke);      
      
      for (int i = 0; i < graphPoints.size(); i++) {
          if(visitedNode[i] == false)
              g2.setColor(GRAPH_POINT_COLOR_NOT_VISITED);
          else if(visitedNode[i] == true)
              g2.setColor(GRAPH_POINT_COLOR_VISITED);
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
         g2.drawString(String.valueOf(i),x,y);
      }
   }
   public void paintNode(int vertex_cur, int vertex_prv) {
        visitedNode[vertex_cur] = true;
        if(vertex_prv != -1){
            visitedEdge[vertex_cur][vertex_prv] = true;
            visitedEdge[vertex_prv][vertex_cur] = true;
        }
        repaint();
        
   }
   public void paintNodeMst(nodeQ[] valTable) {
        for(int i=0 ;i<visitedNode.length;i++){
            visitedNode[i] = false;
        }
        for(int i=0 ;i<visitedNode.length;i++){
            for(int j=0;j<visitedNode.length;j++)
                visitedEdge[i][j] = false;
        }
        visitedNode[0] = true;
        for(int i=1;i<valTable.length;i++){
            if(valTable[i].parent != -1)
            {
                visitedNode[valTable[i].parent] = true;
                visitedNode[valTable[i].vertex] = true;
                visitedEdge[valTable[i].vertex][valTable[i].parent] = true;
                visitedEdge[valTable[i].parent][valTable[i].vertex] = true;
            }
        }
        repaint();
        
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }


}
