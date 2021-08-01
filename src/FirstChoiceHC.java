package challenge2;

import java.util.ArrayList;
import java.util.Random;

import static challenge2.Problem2.evaluate;
import static challenge2.Problem2.getInitialState;

public class FirstChoiceHC {

    int seen = 0;
    int explored = 0;
    int verticesNum;
    int colorNum;
    int numberOfIterations = 150;

    public FirstChoiceHC(Graph2 graph, int colorNum) {

        this.colorNum = colorNum;
        verticesNum = graph.nodes.size();
    }


    public void coloring(Graph2 graph) {
        int color;
        int oldH = 0;
        int newH = 0;
        int delta = 0;
        int oldColor = 0;

        Random r = new Random();
        ArrayList<Integer> colors = new ArrayList();

        colors = getInitialState(verticesNum, colorNum);
        for (int i = 0; i < verticesNum; i++) {
            graph.nodes.get(i).color = colors.get(i);
        }

        for (int i = 0; i < numberOfIterations; i++) {
            oldH = evaluate(graph);

            int verNum = r.nextInt(verticesNum);
            for (int j = 0; j < colorNum; j++) {
                int random = r.nextInt(colorNum) + 1;
                oldColor = graph.nodes.get(verNum).color;
                if (random != oldColor) {
                    graph.nodes.get(verNum).color = random;
                    newH = evaluate(graph);
                    delta = newH - oldH;
                    if (delta > 0) {

                        graph.nodes.get(verNum).color = oldColor;
                        seen++;

                    }

                    break;
                }


            }


            for (int j = 0; j < graph.nodes.size(); j++) {
                System.out.print(graph.nodes.get(j).color);
            }
            System.out.println();

        }


        System.out.println("conflict : " + newH);
        System.out.println("seen : " + (numberOfIterations+seen));
        System.out.println("explored : " + numberOfIterations);


    }
} 