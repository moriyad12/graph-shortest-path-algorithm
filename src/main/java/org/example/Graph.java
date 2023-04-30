package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import javafx.util.Pair;
public class Graph {
    class Edge {
        int s, d, w;
        Edge() {};
    };
   private Vector<Vector<Vector<Integer>>> graph;
   private Edge[] edges;
   private int V;
   private int E;

    public Edge[] getEdges() {
        return edges;
    }

    public Vector<Vector<Vector<Integer>>> getGraph() {
        return graph;
    }

    public int Size() {
        return V;
    }

    public int getE() {
        return E;
    }

    boolean graphInit(String src)
    {
        try {
            File myObj = new File(src);
            Scanner myReader = new Scanner(myObj);
            if(myReader.hasNext()==false){
                System.out.println("text file is empty");
                return false;
            }
            String data = myReader.nextLine();

            List<Integer> ints = (List<Integer>) Arrays.stream(data.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            V= ints.get(0);
            E=ints.get(1);
            graph=new Vector<>(V);
            edges=new Edge[E];
            for(int i=0;i<V;i++)
                graph.add(new Vector<>());
           for(int i=0;i<E;i++)
                edges[i]=new Edge();
            int cnt=0;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                List<Integer> intss = (List<Integer>) Arrays.stream(data.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                Vector<Integer> temp=new Vector<>(2);
                int i=intss.get(0);
                temp.add(0,intss.get(1));
                temp.add(1, intss.get(2));
                graph.get(i).add(temp);
                edges[cnt].s=i;
                edges[cnt].d=intss.get(1);
                edges[cnt].w=intss.get(2);
                cnt++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("there's no such path \ntry again.");
            return false;
            // e.printStackTrace();
        }
        return true;
    }
    void Dijkstra(int src,int[] cost,int[] parents)
    {
        Vector<Boolean> visited=new Vector<Boolean>(V);
        for(int i=0;i<V;i++)
        {cost[i]=Integer.MAX_VALUE;
            visited.add(i, false);
        parents[i]=-1;}
        cost[src]=0;
        PriorityQueue<Pair<Integer, Integer>> q =
                new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        q.add(new Pair<>(cost[src],src));
        for(int i=0;i<V;i++)
        {

         while(!q.isEmpty()&& visited.get(q.peek().getValue()))
         {
             q.poll();
         }
            if(q.isEmpty())
                break;
         int curNode=q.peek().getValue();
         visited.set(curNode, true);
         int curCost=cost[curNode];
         q.poll();
         int size=graph.get(curNode).size();
         for(int j=0;j<size;j++)
         {
             int nig=graph.get(curNode).get(j).get(0);
             int nigCost=graph.get(curNode).get(j).get(1);
             if(cost[nig]>curCost+nigCost&&curCost!=Integer.MAX_VALUE)
             {
                 cost[nig]=curCost+nigCost;
                 parents[nig]=curNode;
                 if(!visited.get(nig))
                 q.add(new Pair<>(cost[nig],nig));
             }
         }
        }

    }
  Boolean  bellmanFord (int src,int[] cost,int[] parents)
  {
      for(int i=0;i<V;i++)
      {cost[i]=Integer.MAX_VALUE;
          parents[i]=-1;}
      cost[src]=0;
      boolean flag =true;
      for(int i=0;i<=V-1;i++)
          for(int j=0;j<E;j++)
          {   int d=edges[j].d,s=edges[j].s;
              if(cost[d]>cost[s]+edges[j].w&&cost[s]!=Integer.MAX_VALUE)
              {
                  cost[d]=cost[s]+edges[j].w;
                  parents[d]=s;
                  if(i==V-1)
                      flag=false;
              }
             
          }


     return flag;
  }
  boolean floyd(int[][]cost,int[][]procedures)
  {
      for(int i=0;i<V;i++)
          for(int j=0;j<V;j++)
          {if(i!=j)
          {cost[i][j]=Integer.MAX_VALUE;
              procedures[i][j]=i;}
            else {cost[i][j]=0;
              procedures[i][j]=-1;}
          }
      for(int i=0;i<E;i++)
      {
          cost[edges[i].s][edges[i].d]=edges[i].w;
      }
      for (int k = 0; k < V; k++) {
          for (int i = 0; i < V; i++) {
              for (int j = 0; j < V; j++) {
                  if (cost[i][k] + cost[k][j] < cost[i][j] && cost[i][k]!=Integer.MAX_VALUE&&cost[k][j]!=Integer.MAX_VALUE)
                  {cost[i][j] = cost[i][k] + cost[k][j];
                      procedures[i][j] = procedures[k][j];}
              }
          }
      }

      for (int i = 0; i < V; i++)
          if (cost[i][i] < 0)
              return false;

      return true;
  }


}
