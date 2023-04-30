package org.example;

import java.util.Scanner;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

public class CLI {
    Graph graph=new Graph();
    Scanner sc =new Scanner(System.in);
    int cost[],parents[];
    int src;
    int[][] costs;
    int[][] pro;
    void Ini(){
        boolean pathFound=false;
        do{
            System.out.println("Enter abolute path of graph");
            String dic =sc.nextLine();
            pathFound= graph.graphInit(dic);
        }while(!pathFound);
    }
    void calculatePath(int [] parents,int src,int dst)
    {
         if(src==dst)
         {System.out.println("path: "+src);
             return;}
        Stack<Integer> stk = new Stack<>();
        stk.push((dst));
        int cur =dst;
        while(true)
        {
            cur=parents[cur];
            stk.push(cur);
            if(cur==src)
                break;
        }
        System.out.print("path: ");
        while(!stk.isEmpty())
        {
            System.out.print(stk.peek()+" ");
            stk.pop();
        }
        System.out.print("\n");
    }
    void calculatePathes(int [][] pro,int src,int dst)
    {
        if(src==dst)
        {System.out.println("path: "+src);
            return;}
        System.out.print("path: ");
        Stack<Integer> stk = new Stack<>();
        stk.push((dst));
        int cur =dst;
        while(true)
        {
            cur=pro[src][cur];
            stk.push(cur);
            if(cur==src)
                break;
        }
        while(!stk.isEmpty())
        {
            System.out.print(stk.peek()+" ");
            stk.pop();
        }
        System.out.print("\n");
    }
    void sunMenu1()
      {cost=new int[graph.Size()];
          parents=new int[graph.Size()];
          costs=new int[graph.Size()][graph.Size()];
          pro=new int[graph.Size()][graph.Size()];
        System.out.println("Enter source Node:");
        src =Integer.valueOf(sc.nextLine());
        System.out.println("choose a command number:\n" +
                "[1] Dikstra Algorithm. \n" +
                "[2]  Bellman-Ford Algorithm.\n" +
                "[3]  Floyd-Warshall Algorithm\n" +
                "[4] retutn to main menu");
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
            graph.Dijkstra(src,cost,parents);
                break;
            case 2:
                graph.bellmanFord(src,cost,parents);
                break;
            case 3:
                graph.floyd(costs,pro);
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("choose a valid number");}
                while(true){
                    System.out.println("Enter distance Node:");
                    int dst=Integer.valueOf(sc.nextLine());
                    System.out.println("choose a command number:\n" +
                            "[1] cost. \n" +
                            "[2]  path.\n"+
                            "[3]  Main Menu.\n"+
                            "[4]  Back.\n");
                    int choicee =Integer.valueOf(sc.nextLine());
                    switch (choicee){
                        case 1:
                            if(choice==1||choice==2)
                            { if(cost[dst]!=Integer.MAX_VALUE)
                            System.out.println(cost[dst]);
                                else System.out.println("No path betwen two nodes");}
                            else { if(costs[src][dst]!=Integer.MAX_VALUE)
                                System.out.println(costs[src][dst]);
                            else System.out.println("No path betwen two nodes");}
                            break;
                        case 2:
                            if(choice==1||choice==2)
                            { if(cost[dst]!=Integer.MAX_VALUE)
                                calculatePath(parents,src,dst);
                            else System.out.println("No path betwen two nodes");}
                            else
                            { if(costs[src][dst]!=Integer.MAX_VALUE)
                                calculatePathes(pro,src,dst);
                            else System.out.println("No path betwen two nodes");}
                            break;
                        case 3:
                            return ;
                        case 4:sunMenu1();
                        default:
                            System.out.println("choose a valid number");
                }}
    }
    void sunMenu2()
    {   cost=new int[graph.Size()];
        parents=new int[graph.Size()];
        costs=new int[graph.Size()][graph.Size()];
        pro=new int[graph.Size()][graph.Size()];
        System.out.println("choose a command number:\n" +
                "[1] Dikstra Algorithm. \n" +
                "[2]  Bellman-Ford Algorithm.\n" +
                "[3]  Floyd-Warshall Algorithm\n" +
                "[4]  Back\n."+
                "[5] retutn to main menu");
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                for(int i=0;i<graph.Size();i++)
                    graph.Dijkstra(i,costs[i],pro[i]);
                break;
            case 2:
                for(int i=0;i<graph.Size();i++)
                    graph.bellmanFord(i,costs[i],pro[i]);
                break;
            case 3:
                graph.floyd(costs,pro);
                break;
            case 4:
                 sunMenu2();
            case 5:
                return ;
            default:
                System.out.println("choose a valid number");
    }
        while(true){

            System.out.println("Enter Source Node:");
            int src=Integer.valueOf(sc.nextLine());
            System.out.println("Enter distance Node:");
            int dst=Integer.valueOf(sc.nextLine());
            System.out.println("choose a command number:\n" +
                    "[1] cost. \n" +
                    "[2]  path.\n"+
                    "[3]  Main Menu.\n" );
            int choicee =Integer.valueOf(sc.nextLine());
            switch (choicee){
                case 1:
                    if(costs[src][dst]!=Integer.MAX_VALUE)
                        System.out.println(costs[src][dst]);
                    else System.out.println("No path betwen two nodes");
                    break;
                case 2:
                    if(choice==1||choice==2)
                    { if(costs[src][dst]!=Integer.MAX_VALUE)
                        calculatePath(pro[src],src,dst);
                    else System.out.println("No path betwen two nodes");}
                    else
                    { if(costs[src][dst]!=Integer.MAX_VALUE)
                        calculatePathes(pro,src,dst);
                    else System.out.println("No path betwen two nodes");}
                    break;
                case 3:
                    mainMenu();
                    break;
                default:
                    System.out.println("choose a valid number");
            }}}
    void sunMen3()
    {   cost=new int[graph.Size()];
        parents=new int[graph.Size()];
        costs=new int[graph.Size()][graph.Size()];
        pro=new int[graph.Size()][graph.Size()];
        System.out.println("choose a command number:\n" +
                "[1]  Bellman-Ford Algorithm.\n" +
                "[2]  Floyd-Warshall Algorithm\n" +
                "[3] retutn to main menu\n"+
                "[4] Back."
                );
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                System.out.println(graph.bellmanFord(0,cost,parents));
                break;
            case 2:
                System.out.println(graph.floyd(costs,pro));
                break;
            case 3:
                return;
            case 4:
                sunMen3();
                break;
            default:
                System.out.println("choose a valid number");
    }}
    void mainMenu(){
        System.out.println("choose a command number:\n" +
                "[1] Finds  the  shortest  paths  from  source  node  to  all  other  nodes. \n" +
                "[2]  Finds the shortest paths between all the pairs of nodes.\n" +
                "[3]  Check if the graph contains a negative cycle\n" +
                "[4] exit");
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
               sunMenu1();
                break;
            case 2:
                sunMenu2();
                break;
            case 3:
                sunMen3();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("choose a valid number");
        }
        return;
    }

    public static void main(String[] args) {
        CLI userMenu = new CLI();
        userMenu.Ini();
        while (true){
            userMenu.mainMenu();
        }
    }
}
