package org.example;

public class Main {
    public static void main(String[] args) {
        Graph g=new Graph();
        g.graphInit("F:\\year2\\term2\\data\\Graph\\src\\main\\java\\org\\example\\test_neg_edge");
        int cost[][] = new int[g.Size()][g.Size()],p[][]=new int[g.Size()][g.Size()];
        g.floyd(cost,p);
        System.out.println("Done");
    }
}