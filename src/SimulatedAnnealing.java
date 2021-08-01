package challenge2;

import java.util.ArrayList;
import java.util.Random;

import static challenge2.Problem2.evaluate;
import static challenge2.Problem2.getInitialState;

public class SimulatedAnnealing {


    int verticesNum;
    int colorNum;

    public SimulatedAnnealing(Graph2 graph, int colorNum) {

        this.colorNum = colorNum;
        verticesNum = graph.nodes.size();
    }


    public void coloring(Graph2 graph) {
        int seen=0;
        int explored=0;
        int color;
        int oldH = 0;
        int newH = 0;
        int delta = 0;
        int oldColor = 0;
        double p;
        int T = 1;
        Random r = new Random();
        ArrayList<Integer> colors = new ArrayList();

        colors = getInitialState(verticesNum, colorNum);
        for (int i = 0; i < verticesNum; i++) {
            graph.nodes.get(i).color = colors.get(i);
        }

        for (int i = 0; i < 150; i++) {
            oldH = evaluate(graph);

            int verNum = r.nextInt(verticesNum);
            for (int j = 0; j < colorNum; j++) {
                int random = r.nextInt(colorNum) + 1;
                oldColor = graph.nodes.get(verNum).color;
                if (random != oldColor) {
                    graph.nodes.get(verNum).color = random;
                    newH = evaluate(graph);
                    seen++;
                    delta = newH - oldH;
                    if (delta > 0) {
                        p = Math.exp(-T);
                        if (p < Math.random()) {
                            graph.nodes.get(verNum).color = oldColor;
                            explored++;
                        }
                    }

                    break;
                }
                T += 2;

            }


        }

        System.out.println("conflict : " + newH);
        System.out.println("seen : " + seen);
        System.out.println("explored : " + (seen-explored));


    }


    public void print(Graph2 graph2) {
        System.out.println("coloring is : ");
        for (int i = 0; i < graph2.nodes.size(); i++) {
            System.out.println(graph2.nodes.get(i).name + " : " + graph2.nodes.get(i).color);
        }
    }
} 