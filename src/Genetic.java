package challenge2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static challenge2.Problem2.evaluate;
import static challenge2.Problem2.getInitialState;

public class Genetic {
    Graph2 graph;
    int verticesNum;
    int colorNum;
    int populationSize = 100;
    int tornumentSize = 4;
    double mutationRate = 0.2;
    int numberOfGenerations = 50;

    Random r = new Random();
    ArrayList<Integer> max;
    ArrayList<Integer> min;
    ArrayList<Integer> mid;
    ArrayList<Integer> generationEvaluate;
    ArrayList<ArrayList<Integer>> colorsArray;
    ArrayList<ArrayList<Integer>> newGeneration;
    ArrayList<ArrayList<Integer>> newGeneration2;
    ArrayList<Integer> allGens;


    public Genetic(Graph2 graph, int colorNum) {

        this.verticesNum = graph.nodes.size();
        this.colorNum = colorNum;
        colorsArray = new ArrayList<>();
        this.graph = graph;
    }

    public void coloring() {
        int index=0;
        boolean flag=true;

        for (int i = 0; i < populationSize; i++) {

            ArrayList<Integer> colors = new ArrayList<>();
            colors = getInitialState(verticesNum, colorNum);
            colorsArray.add(colors);

        }

        ArrayList<ArrayList<Integer>> arrayLists;
        crossOver(selectParents(colorsArray));
        max=new ArrayList<>();
        min=new ArrayList<>();
        mid=new ArrayList<>();
        for (int i = 0; i < numberOfGenerations; i++) {


            generationEvaluate=new ArrayList<>();
            arrayLists=new ArrayList<>();
            arrayLists = mutation();
            crossOver(selectParents(arrayLists));


            for (int j = 0; j <arrayLists.size() ; j++) {
                ArrayList<Integer> c=new ArrayList();
                c=arrayLists.get(j);
                for (int k = 0; k < verticesNum; k++) {
                    graph.nodes.get(k).color=c.get(k);
                }
                generationEvaluate.add(evaluate(graph));
               if(evaluate(graph)==1 && flag){
                   index=i;
                   flag = false;

               }
            }
//            System.out.println();
//            System.out.println("for Generation " + (i+1));
        //    System.out.println("best : ");
            min.add( Collections.min(generationEvaluate));
            max.add(Collections.max(generationEvaluate));

       //     System.out.println(   Collections.min(generationEvaluate));
//            System.out.println("worst : " );
//            System.out.println(Collections.max(generationEvaluate));
//            System.out.println("mid : ");
//            System.out.println("mid : ");
            int sum=0;
            for (int j = 0; j <generationEvaluate.size() ; j++) {
                sum+=generationEvaluate.get(j);
            }
            mid.add(sum/generationEvaluate.size());
//            System.out.println(sum/generationEvaluate.size());
//            System.out.println();
//
      }

        System.out.println("worst : ");
        for (int i = 0; i <max.size() ; i++) {
            System.out.println(max.get(i));
        }
        System.out.println("mid : ");

        for (int i = 0; i <mid.size() ; i++) {

            System.out.println(mid.get(i));
        }
        System.out.println("best : ");

        for (int i = 0; i < min.size(); i++) {

            System.out.println(min.get(i));
        }

        if(!flag)
            System.out.println("number of Generations to optimal solution : " + (index+1) );

/** compare evaluate of generations*/
//        for (int i = 0; i <colorsArray.size() ; i++) {
//            for (int j = 0; j < verticesNum; j++) {
//                graph.nodes.get(j).color=colorsArray.get(i).get(j);
//
//            }
//            System.out.println(evaluate(graph));
//        }
//        System.out.println("new ***************");
//        for (int i = 0; i <newGeneration.size() ; i++) {
//            for (int j = 0; j < verticesNum; j++) {
//                graph.nodes.get(j).color=newGeneration.get(i).get(j);
//
//            }
//            System.out.println(evaluate(graph));
//        }


    }

    public ArrayList<Integer> selectParents(ArrayList<ArrayList<Integer>> colorsArray) {
        ArrayList<Integer> selectedindex = new ArrayList();
        int[] indices = new int[tornumentSize];
        ArrayList evaluates = new ArrayList();
        for (int i = 0; i < tornumentSize; i++) {
            evaluates.add(0);
        }
        for (int i = 0; i < populationSize / tornumentSize; i++) {
            for (int j = 0; j < tornumentSize; j++) {
                indices[j] = r.nextInt(populationSize);
            }


            for (int j = 0; j < tornumentSize; j++) {
                for (int k = 0; k < verticesNum; k++) {
                    graph.nodes.get(k).color = colorsArray.get(indices[j]).get(k);
                    evaluates.set(j, evaluate(graph));
                }
            }

            int minIndex = evaluates.indexOf(Collections.min(evaluates));
            selectedindex.add(indices[minIndex]);

        }

        return selectedindex;
    }

    public void crossOver(ArrayList<Integer> selectedParents) {

        int p1;
        int p2;
        newGeneration=new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            p1 = r.nextInt(populationSize / tornumentSize);
            p2 = r.nextInt(populationSize / tornumentSize);
            ArrayList<Integer> parentColors1 = new ArrayList();
            ArrayList<Integer> parentColors2 = new ArrayList();
            ArrayList<Integer> childColors = new ArrayList();
            parentColors1 = colorsArray.get(selectedParents.get(p1));
            parentColors2 = colorsArray.get(selectedParents.get(p2));

            for (int j = 0; j < verticesNum / 2; j++) {
                childColors.add(parentColors1.get(j));
            }
            for (int j = verticesNum / 2; j < verticesNum; j++) {
                childColors.add(parentColors2.get(j));
            }
            newGeneration.add(childColors);
        }

//        System.out.println("Generation After CrossOver : ");
//        for (int i = 0; i < newGeneration.size(); i++) {
//            for (int j = 0; j < newGeneration.get(i).size(); j++) {
//                System.out.print(newGeneration.get(i).get(j));
//            }
//            System.out.println();
//        }


    }

    public ArrayList<ArrayList<Integer>> mutation() {
        newGeneration2=new ArrayList<>();
        allGens=new ArrayList<>();
        int random;
        int randColor;
        for (int i = 0; i < newGeneration.size(); i++) {
            for (int j = 0; j < newGeneration.get(i).size(); j++) {
                allGens.add(newGeneration.get(i).get(j));
            }
        }


        for (int i = 0; i < mutationRate * populationSize * verticesNum; i++) {

            random = r.nextInt(allGens.size());
            for (int j = 0; j < colorNum; j++) {
                randColor = 1 + r.nextInt(colorNum);
                if (randColor != allGens.get(random)) {
                    allGens.set(random, randColor);
                    break;
                }
            }
        }


        ArrayList arrayList = null;
        for (int i = 0; i < allGens.size(); i += verticesNum) {
            arrayList = new ArrayList();
            for (int j = i; j < i + verticesNum; j++) {

                arrayList.add(allGens.get(j));

            }
            newGeneration2.add(arrayList);


        }


//        System.out.println("Generation After Mutation : ");
//        for (int i = 0; i < newGeneration2.size(); i++) {
//            for (int j = 0; j < newGeneration2.get(i).size(); j++) {
//                System.out.print(newGeneration2.get(i).get(j));
//            }
//            System.out.println();
//        }
        return newGeneration2;


    }


}


//
// for (int i = 0; i < 100; i++) {
//        for (int j = 0; j < verticesNum; j++) {
//        System.out.print(colorsArray.get(i).get(j));
//
//        }
//        System.out.println();
//        }