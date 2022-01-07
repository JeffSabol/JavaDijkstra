//==============================================================
// Shortest Path Program - Dijkstra
//==============================================================
// Jeff Sabol

public class Dijkstra {
    static final int V = 6;
    int minDistance(int[] dist, Boolean[] sptSet){
        // initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for ( int v = 0; v < V; v++){
            if(!sptSet[v] && dist[v] <= min){
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }


    void printSolution(int[] dist) {
        System.out.println("Vertex \t\t |Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println((i+1) + "------------| " + ((dist[i] == Integer.MAX_VALUE) ? "Infinity" : dist[i]));
    }


    void dijkstra(int[][] graph, int src){
        int[] dist = new int[V];
        Boolean[] sptSet = new Boolean[V];
        // initialize everything as very far away and outside the solution set
        for(int i = 0; i < V; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        //initialize start node to zero distance
        dist[src] = 0;

        // iterate over nodes - 1
        for(int count = 0; count < V - 1; count++){
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for(int v = 0; v < V; v++){
                // (dist[u] != Integer.MAX_VALUE) accounts for a sink node
                // because, if there are no undiscovered nodes, with a path to this node
                // then the min distance will be MAX_VALUE
                // and the min_index will be the greatest undiscovered node
                if(!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    System.out.println((u+1) + "-----" + graph[u][v] + "------>" + (v+1));
                    System.out.println("src----" + (dist[u]+graph[u][v]) + "----->" + (v+1));
                    System.out.println("____________________");
                }
            }
        }
        printSolution(dist);
    }
    public static void main(String[] args){
        int src = 0;
        int[][] graph = new int[][]{
                { 0,  7,  9,  0, 0, 14},
                { 7,  0, 10, 15, 0,  0},
                { 9, 10,  0, 11, 0,  2},
                { 0, 15, 11, 0,  6,  0},
                { 0,  0,  0, 6,  0,  9},
                {14,  0,  2, 0,  9,  0}
        };
        Dijkstra t = new Dijkstra();
        t.dijkstra(graph, src);
    }
}
 