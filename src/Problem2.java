package challenge2;

import ir.aut.Graph;

import java.util.ArrayList;
import java.util.Random;

public class Problem2 {



    public static ArrayList getInitialState(int verticesNum,int colorNum){
        ArrayList arrayList=new ArrayList();
        Random r = new Random();
        for (int i = 0; i < verticesNum; i++) {
            arrayList.add(r.nextInt(colorNum) + 1);
        }
        return arrayList;
    }




    public  static int evaluate(Graph2 graph) {

        int sum = 0;
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).edgeList.size(); j++) {
                if (graph.nodes.get(i).edgeList.get(j).start.color == graph.nodes.get(i).edgeList.get(j).end.color)
                    sum++;
            }
        }
        return sum / 2;
    }
}