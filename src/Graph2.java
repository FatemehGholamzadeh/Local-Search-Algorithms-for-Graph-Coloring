package challenge2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph2 {

    public ArrayList<Vertex> nodes;

    public Graph2() {
        nodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).edgeList = new LinkedList<>();
        }
    }

    public void addNode(Vertex vertex) {
        nodes.add(vertex);

    }

    public void addEdge(Vertex start, Vertex end) {
        Edge2 edge = new Edge2(start, end);
        Edge2 edge1 = new Edge2(end, start);
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).name.equals(start.name)) {
                nodes.get(i).edgeList.add(edge);
                break;
            }

        }

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).name.equals(end.name)) {
                nodes.get(i).edgeList.add(edge1);
                break;
            }

        }

    }



    public void printGraph(){
        for (int i = 0; i <nodes.size() ; i++) {
            System.out.print(nodes.get(i).name + " : ");
            for (int j = 0; j <nodes.get(i).edgeList.size(); j++) {
                System.out.print(nodes.get(i).edgeList.get(j).end.name);

            }
            System.out.println();
        }
    }

    public void printGraphColoring(){
        for (int i = 0; i <this.nodes.size() ; i++) {
            System.out.println(this.nodes.get(i).name + " : "+this.nodes.get(i).color);
        }
    }


}