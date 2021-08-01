package challenge2;

import java.util.ArrayList;
import java.util.Random;

import static challenge2.Problem2.evaluate;
import static challenge2.Problem2.getInitialState;

public class StocasticHC {

    int verticesNum;
    int colorNum;
    int oldH = 0;
    int newH = 0;
    int oldColor;
    int numberOfIterations = 100;
    int seen = 0;
    int exploered = 0;

    public StocasticHC(Graph2 graph, int colorNum) {

        this.colorNum = colorNum;
        verticesNum = graph.nodes.size();
    }

    public void HC(Graph2 graph) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        colors = getInitialState(verticesNum, colorNum);
        for (int i = 0; i < verticesNum; i++) {
            graph.nodes.get(i).color = colors.get(i);
        }


        for (int k = 0; k < numberOfIterations; k++) {

            for (int i = 0; i < verticesNum; i++) {
                oldH = evaluate(graph);
                oldColor = graph.nodes.get(i).color;
                for (int j = 0; j < colorNum; j++) {
                    if (oldColor != j + 1) {
                        graph.nodes.get(i).color = j + 1;
                        newH = evaluate(graph);
                        ArrayList<Integer> colors2 = new ArrayList<>();
                        for (int l = 0; l < colors.size(); l++) {
                            colors2.add(colors.get(l));
                        }

                        if (newH < oldH) {
                            colors2.set(i, j + 1);
                            arrayLists.add(colors2);
                        }
                        graph.nodes.get(i).color = oldColor;

                    }
                }
            }

            seen += arrayLists.size();
            Random r = new Random();
            int rand = r.nextInt(arrayLists.size());
            for (int i = 0; i < verticesNum; i++) {
                graph.nodes.get(i).color = arrayLists.get(rand).get(i);
            }

            // System.out.println(evaluate(graph));
        }
        System.out.println("seen = " + seen);
        System.out.println("explored : " + numberOfIterations);
        System.out.println("conflict : " + evaluate(graph));

//        for (int i = 0; i < arrayLists.size(); i++) {
//            for (int j = 0; j < arrayLists.get(i).size(); j++) {
//                System.out.print(arrayLists.get(i).get(j));
//            }
//            System.out.println();
//
//        }


    }


}