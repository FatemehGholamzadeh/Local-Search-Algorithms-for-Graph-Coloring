package challenge2;

import com.sun.javafx.geom.Edge;

import java.util.LinkedList;

public class Vertex {
    public String name;
    public int color;
    LinkedList<Edge2> edgeList;

    public Vertex(String name){
        this.name=name;
        edgeList=new LinkedList<>();
    }
} 