/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_e1;

/**
 *
 * @author tungi
 */
public class Dijkstra_E1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] g = {
            // A  B  C  D  E  F
            {0, 7, 9, 0, 0, 14},
            {7, 0, 10, 15, 0, 0},
            {9, 10, 0, 11, 0, 2},
            {0, 15, 11, 0, 6, 0},
            {0, 0, 0, 6, 0, 9},
            {14, 0, 2, 0, 9, 0}
        };

        disjkstra(g, 0);
    }

    private static void disjkstra(int[][] g, int start) {
        int n = g.length; // Số lượng đỉnh
        int[] distance = new int[n];
        int[] prev = new int[n];
        boolean[] sptSet = new boolean[n];
        
        // Khởi tạo giá trị
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        distance[start] = 0; // start -> start = 0
        
        for (int k= 0; k<n-1; k++){
            int u = minDistance(distance, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < n; v++) {
                if(!sptSet[v]){ // chưa được chọn
                    if(g[u][v]!=0){ // có đường đi
                        if(distance[u]+g[u][v]<distance[v]){
                            // Đường đi mới ngắn hơn
                            distance[v] = distance[u]+g[u][v];
                            prev[v] = u;
                        }
                    };
                }
            }
        }
        
        printSolution(distance, prev, start);
    }
    
    private static int minDistance(int[] distance, boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v=0; v<distance.length; v++){
            if(!sptSet[v] && distance[v]<=min){
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printSolution(int[] distance, int[] prev, int start) {
        System.out.println("Vertex\tDistance\t |Path: ");
        for (int i = 0; i < distance.length; i++) {
            if(i!=start){
                System.out.print(cvt(start) +" -> "+ cvt(i)+" : " + distance[i]+" | ");
                printPath(prev,i);
            }
            System.out.println("");
        }
    }

    private static void printPath(int[] prev, int i) {
        if(prev[i] == -1){
            System.out.print(cvt(i));
            return;
        }
        printPath(prev, prev[i]);
        System.out.print(" -> "+ cvt(i));
    }

    private static char cvt(int i) {
        return (char)(i+65);
    }
}
