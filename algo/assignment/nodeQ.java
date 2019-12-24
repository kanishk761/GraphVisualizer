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
public class nodeQ {
  
        public int vertex; 
        public int cost; 
        public int parent;
        
        nodeQ(int a, int b, int c) 
        { 
            vertex = a; 
            cost = b; 
            parent =c;
        } 
        
        public static int get_index(nodeQ [] ref, int v){
            for(int i = 0; i < ref.length; i++){
                if(v == ref[i].vertex){
                    return i;
                }
            }
            return -1;
        }
} 
