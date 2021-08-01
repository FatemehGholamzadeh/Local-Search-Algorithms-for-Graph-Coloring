package challenge2;

import ir.aut.Graph;

import java.util.ArrayList;

import static challenge2.Problem2.evaluate;
import static challenge2.Problem2.getInitialState;

public class RandomRestartHC {
    int verticesNum;
    int colorNum;
    int oldH = 0;
    int newH = 0;
    int oldColor;
    int numberOfIterations=10;
    int number=10;
    ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();
    ArrayList<ArrayList<Integer>> bests = new ArrayList<>();

    public RandomRestartHC(Graph2 graph, int colorNum) {

        this.colorNum = colorNum;
        verticesNum = graph.nodes.size();
    }

    public void simpleHC(Graph2 graph) {

        for (int m = 0; m < numberOfIterations; m++) {


            colors = getInitialState(verticesNum, colorNum);
            for (int i = 0; i < verticesNum; i++) {
                graph.nodes.get(i).color = colors.get(i);
            }


            for (int k = 0; k < number; k++) {

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

                int index = 0;
                for (int i = 0; i < arrayLists.size(); i++) {
                    int old = evaluate(graph);
                    for (int j = 0; j < arrayLists.get(i).size(); j++) {
                        graph.nodes.get(j).color = arrayLists.get(i).get(j);
                    }
                    int new1 = evaluate(graph);
                    if (new1 < old) {
                        index = i;
                    }

                }
                for (int i = 0; i < verticesNum; i++) {
                    graph.nodes.get(i).color = arrayLists.get(index).get(i);
                }
                //  System.out.println(evaluate(graph));


                ArrayList<Integer> best = new ArrayList<>();
                for (int i = 0; i < verticesNum; i++) {
                    best.add(graph.nodes.get(i).color);
                }
                bests.add(best);
            }

//            System.out.println("conflict : " + evaluate(graph));
//            for (int i = 0; i < arrayLists.size(); i++) {
//                for (int j = 0; j < arrayLists.get(i).size(); j++) {
//                    System.out.print(arrayLists.get(i).get(j));
//                }
//                System.out.println();
//
//            }

        }
    }

    public void findBest(Graph2 graph) {

        int index = 0;
        for (int i = 0; i < bests.size(); i++) {
            int old = evaluate(graph);
            for (int j = 0; j < bests.get(i).size(); j++) {
                graph.nodes.get(j).color = bests.get(i).get(j);
            }
            int new1 = evaluate(graph);
            if (new1 < old) {
                index = i;
            }

        }
        for (int i = 0; i < verticesNum; i++) {
            graph.nodes.get(i).color = bests.get(index).get(i);
        }


        System.out.println("seen = " + (number*numberOfIterations*verticesNum*(colorNum-1) ));
        System.out.println("explored = " + numberOfIterations*number);
        System.out.println("conflict2 : " + evaluate(graph));

    }


}