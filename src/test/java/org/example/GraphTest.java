package org.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

class GraphTest {
    Graph graph = new Graph();

    @Test
        //test_neg_edge negative edge in dijkstra
        // file test_neg_edge
    void Test_Neg_Dijkstra() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_neg_edge");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        graph.Dijkstra(0, cost, parents);
        assertEquals(4, cost[3]);
    }

    @Test
    void Test_Neg_Bellman() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_neg_edge");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        graph.bellmanFord(0, cost, parents);
        assertEquals(3, cost[3]);
    }

    @Test
    void Test_Neg_floyd() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_neg_edge");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        graph.floyd(costs, pro);
        assertEquals(3, costs[0][3]);
    }

    @Test
    void Negative_Cycle_Bell() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_Neg_Cycle");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        boolean cycle = graph.bellmanFord(0, cost, parents);
        assertEquals(false, cycle);
    }

    @Test
    void Negative_Cycle_floyd() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_Neg_Cycle");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        boolean cycle = graph.floyd(costs, pro);
        assertEquals(false, cycle);
    }

    @Test
    void Test_AllNode_Bellman() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\All_node");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        graph.bellmanFord(4, cost, parents);
        assertEquals(8, cost[0]);
        assertEquals(14, cost[1]);
        assertEquals(3, cost[2]);
        assertEquals(17, cost[3]);
        assertEquals(0, cost[4]);
        assertEquals(2147483647, cost[5]);
        assertEquals(2147483647, cost[6]);
        assertEquals(2147483647, cost[7]);
    }

    @Test
    void Test_AllNode_floyd() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\All_node");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        graph.floyd(costs, pro);
        assertEquals(8, costs[4][0]);
        assertEquals(14, costs[4][1]);
        assertEquals(3, costs[4][2]);
        assertEquals(17, costs[4][3]);
        assertEquals(0, costs[4][4]);
        assertEquals(2147483647, costs[4][5]);
        assertEquals(2147483647, costs[4][6]);
        assertEquals(2147483647, costs[4][7]);
    }

    // node cannot go any way
    @Test
    void Test_cannot_go_floyd() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\All_node");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        graph.floyd(costs, pro);
        assertEquals(2147483647, costs[7][0]);
        assertEquals(2147483647, costs[7][1]);
        assertEquals(2147483647, costs[7][2]);
        assertEquals(2147483647, costs[7][3]);
        assertEquals(2147483647, costs[7][4]);
        assertEquals(2147483647, costs[7][5]);
        assertEquals(2147483647, costs[7][6]);
        assertEquals(0, costs[7][7]);
    }

    // node isolated
    @Test
    void Test_isolated_floyd() {
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\All_node");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        graph.floyd(costs, pro);
        assertEquals(2147483647, costs[0][6]);
        assertEquals(2147483647, costs[1][6]);
        assertEquals(2147483647, costs[2][6]);
        assertEquals(2147483647, costs[3][6]);
        assertEquals(2147483647, costs[4][6]);
        assertEquals(2147483647, costs[5][6]);
        assertEquals(0, costs[6][6]);
        assertEquals(2147483647, costs[7][6]);
    }

    // empty file
    @Test
    void Test_empty_file(){
        boolean valid = graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\empty_graph");
        assertEquals(false, valid);

    }

    @Test
    void Test_ALL_floyd() {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                return Arrays.compare(a1, a2);
            }
        };
        graph.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\All_node");

        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        graph.floyd(costs, pro);
        int[][] true_costs = {{0,6,1,9,6,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {5,11,0,14,11,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,3,Integer.MAX_VALUE,0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {8,14,3,17,0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,6,Integer.MAX_VALUE,3,Integer.MAX_VALUE,0,Integer.MAX_VALUE,3},
                {Integer.MAX_VALUE,11,Integer.MAX_VALUE,8,Integer.MAX_VALUE,5,0,4},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}};
        assertEquals(true, Arrays.equals(true_costs ,costs, comparator));


    }
    //  test for time for dijkstra with density 0.25
    @Test
    void Test_file_50_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\50nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();

        assertEquals(24, cost[49]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_100_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\100nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(9, cost[99]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_150_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\150nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(14, cost[149]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_200_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\200nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(17, cost[199]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_250_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\250nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(14, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_500_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\500nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(11, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_750_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(7, cost[749]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_1000_density_0_25(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\1000nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(5, cost[999]);
        System.out.println((end-start)/1000);
    }
    //  test for time for bellman ford with density 0.25
    @Test
    void Test_Bellman_50_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\50nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(24, cost[49]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_100_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\100nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(9, cost[99]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_150_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\150nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(14, cost[149]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_200_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\200nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(17, cost[199]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_250_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\250nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(14, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_500_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\500nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        int start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        int end=(int)System.nanoTime();
        assertEquals(11, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_750_density_0_25() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (int) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
        assertEquals(7, cost[749]);
    }
    @Test
    void Test_Bellman_1000_density_0_25() {
        graph.graphInit("C:\\Users\\Lava Twins\\Downloads\\Graph\\density 0.25\\1000nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(5, cost[999]);
        System.out.println((end-start)/1000);
    }
// test floyd for density 0.25
@Test
void Test_floyd_50_node() {

    graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\50nodes_graph.txt");
    int costs[][] = new int[graph.Size()][graph.Size()];
    int pro[][] = new int[graph.Size()][graph.Size()];
    long start= (long) System.nanoTime();
    graph.floyd(costs, pro);
    long end=(long)System.nanoTime();
    assertEquals(24, costs[0][49]);
    System.out.println((end-start)/1000);

}
    @Test
    void Test_floyd_100_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\100nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(9, costs[0][99]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_150_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\150nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(14, costs[0][149]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_200_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\200nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(17, costs[0][199]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_250_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\250nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(14, costs[0][249]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_500_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\500nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(11, costs[0][499]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_750_node() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.25\\750nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(7, costs[0][749]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_1000_node() {

        graph.graphInit("C:\\Users\\Lava Twins\\Downloads\\Graph\\density 0.25\\1000nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(5, costs[0][999]);
        System.out.println((end-start)/1000);
    }
    //---------------------------------------------------------------------------------------------------
    //------------------Density 0.5 -------------------------------------------
    //  test for time for dijkstra with density 0.75

    @Test
    void Test_file_250_density_0_5(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\250nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(9, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_500_density_0_5(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\500nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(6, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_750_density_0_5(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(5, cost[749]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_1000_density_0_5(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\1000nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(5, cost[999]);
        System.out.println((end-start)/1000);
    }
    //  test for time for bellman ford with density 0.5

    @Test
    void Test_Bellman_250_density_0_5() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\250nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(9, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_500_density_0_5() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\500nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(6, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_750_density_0_5() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        System.out.println((end-start)/1000);
        assertEquals(5, cost[749]);
    }
    @Test
    void Test_Bellman_1000_density_0_5() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\1000nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(5, cost[999]);
        System.out.println((end-start)/1000);
    }
    // test floyd for density 0.5


    @Test
    void Test_floyd_250_node_0_5() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\250nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(9, costs[0][249]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_500_node_0_5() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\500nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(6, costs[0][499]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_750_node_0_5() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\750nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(5, costs[0][749]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_1000_node_0_5() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.5\\1000nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(5, costs[0][999]);
        System.out.println((end-start)/1000);
    }
    //---------------------------------------------------------------------------------------------------
    //------------------Density 0.75 -------------------------------------------
    //  test for time for dijkstra with density 0.75

    @Test
    void Test_file_250_density_0_75(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\250nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(6, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_500_density_0_75(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\500nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(3, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_750_density_0_75(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[749]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_1000_density_0_75(){
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\1000nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[999]);
        System.out.println((end-start)/1000);
    }
    //  test for time for bellman ford with density 0.75

    @Test
    void Test_Bellman_250_density_0_75() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\250nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(6, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_500_density_0_75() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\500nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(3, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_750_density_0_75() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        System.out.println((end-start)/1000);
        assertEquals(4, cost[749]);
    }
    @Test
    void Test_Bellman_1000_density_0_75() {
        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\1000nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[999]);
        System.out.println((end-start)/1000);
    }
    // test floyd for density 0.75


    @Test
    void Test_floyd_250_node_0_75() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\250nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(6, costs[0][249]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_500_node_0_75() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\500nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(3, costs[0][499]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_750_node_0_75() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\750nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][749]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_1000_node_0_75() {

        graph.graphInit("F:\\year2\\term2\\data\\density 0.75\\1000nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][999]);
        System.out.println((end-start)/1000);
    }
    //---------------------------------------------------------------------------------------------------
    //------------------Density 1 -------------------------------------------
    //  test for time for dijkstra with density 1

    @Test
    void Test_file_250_density_1(){
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\250nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_500_density_0_1(){
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\500nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_750_density_1(){
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[749]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_file_1000_density_1(){
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\1000nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.Dijkstra(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[999]);
        System.out.println((end-start)/1000);
    }
    //  test for time for bellman ford with density 0.75

    @Test
    void Test_Bellman_250_density_1() {
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\250nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[249]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_500_density_1() {
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\500nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[499]);
        System.out.println((end-start)/1000);
    }
    @Test
    void Test_Bellman_750_density_1() {
        graph.graphInit("F:\\year2\\term2\\data\\density 1\\750nodes_graph.txt");
        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        System.out.println((end-start)/1000);
        assertEquals(4, cost[749]);
    }
    @Test
    void Test_Bellman_1000_density_1() {
        graph.graphInit("C:\\Users\\Lava Twins\\Downloads\\Graph\\density 1\\1000nodes_graph.txt");

        int cost[] = new int[graph.Size()];
        int parents[] = new int[graph.Size()];
        long start= (long) System.nanoTime();
        graph.bellmanFord(0, cost, parents);
        long end=(long)System.nanoTime();
        assertEquals(4, cost[999]);
        System.out.println((end-start)/1000);
    }
    // test floyd for density 0.75


    @Test
    void Test_floyd_250_node_1() {

        graph.graphInit("F:\\year2\\term2\\data\\density 1\\250nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][249]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_500_node_1() {

        graph.graphInit("F:\\year2\\term2\\data\\density 1\\500nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][499]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_750_node_1() {

        graph.graphInit("F:\\year2\\term2\\data\\density 1\\750nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][749]);
        System.out.println((end-start)/1000);

    }
    @Test
    void Test_floyd_1000_node_1() {

        graph.graphInit("C:\\Users\\Lava Twins\\Downloads\\Graph\\density 1\\1000nodes_graph.txt");
        int costs[][] = new int[graph.Size()][graph.Size()];
        int pro[][] = new int[graph.Size()][graph.Size()];
        long start= (long) System.nanoTime();
        graph.floyd(costs, pro);
        long end=(long)System.nanoTime();
        assertEquals(4, costs[0][999]);
        System.out.println((end-start)/1000);
    }

}
