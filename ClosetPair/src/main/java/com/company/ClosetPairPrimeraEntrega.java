package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosetPairPrimeraEntrega {

    ArrayList<List<Integer>> coords;
    double d_min;
    ArrayList<ArrayList<List<Integer>>> closetCoords;
    ArrayList<Double> distanceClosetCoords;
    List<Integer> firstCoord;
    List<Integer> secondCoord;


    public ClosetPairPrimeraEntrega(){}

    public void run(){
        createCoordinates();
        closetCoords = new ArrayList<>();
        distanceClosetCoords = new ArrayList<>();
        divideAndConquer(coords);
        int indexClosest = selectingShortestDistance(distanceClosetCoords);
        System.out.println("Closet Pair:");
        ArrayList<List<Integer>> closetPair = closetCoords.get(indexClosest);
        printCoords(closetPair);
    }

    public void createCoordinates(){
        coords = new ArrayList<>();
        coords.add(Arrays.asList(30,2));
        coords.add(Arrays.asList(10,5));
        coords.add(Arrays.asList(15,5));
        coords.add(Arrays.asList(1,7));
        coords.add(Arrays.asList(5,7));
        coords.add(Arrays.asList(17,7));
        coords.add(Arrays.asList(22,7));
        coords.add(Arrays.asList(14,9));
        coords.add(Arrays.asList(19, 10));
        coords.add(Arrays.asList(25,10));
        coords.add(Arrays.asList(4, 13));
        coords.add(Arrays.asList(29, 14));
        System.out.println("Coordinates Created");
    }

    public void brutalForce(ArrayList<List<Integer>> coords){
        d_min = 123456789;
        int n = coords.size();
        double distance;

        for(int i = 0; i <= n-1; i++){
            for(int j = i+1; j <= n-1; j++){
                distance = distance(coords, i, j);
                if(distance < d_min){
                    firstCoord = coords.get(i);
                    secondCoord = coords.get(j);
                    d_min = distance;
                }
            }
        }
        System.out.println("Closet Coord:");
        System.out.println(firstCoord + " " + secondCoord + " " + d_min);
    }

    /**
     * Funcion que usa BrutalForce para hallar la distancia dado una lista de coordenadas
     * @param coord: lista de coordenadas
     * @param i
     * @param j
     */
    public double distance(ArrayList<List<Integer>> coord, int i , int j){

        int x1 = coord.get(i).get(0);
        int y1 = coord.get(i).get(1);

        int x2 = coord.get(j).get(0);
        int y2 = coord.get(j).get(1);

        double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return d;
    }

    public void divideAndConquer(ArrayList<List<Integer>> coords) {
        //creating Lx and Rx
        ArrayList<List<Integer>> Lx;
        ArrayList<List<Integer>> Rx;

        /**
            creating Px: sorting dataset primarily with x and secondarily with y (Px)
         **/

        ArrayList<List<Integer>> Px;
        System.out.println("Sorting Coordinates by X...");
        Px = sorting(coords);
        int size = Px.size();
        Lx = new ArrayList<>(Px.subList(0, size / 2));
        Rx = new ArrayList<>(Px.subList(size / 2, size));

        //list created just to iterate between List
        ArrayList<ArrayList<List<Integer>>> lists = new ArrayList<>();
        lists.add(Lx);
        lists.add(Rx);

        firstCoord = new ArrayList<>();
        secondCoord = new ArrayList<>();

        for(ArrayList<List<Integer>> list: lists){
            //checking if brutalForce needs to be used
            if(list.size() <= 3){
                brutalForce(list);
                ArrayList<List<Integer>> pairOfCoords = new ArrayList<>();
                pairOfCoords.add(firstCoord);
                pairOfCoords.add(secondCoord);
                closetCoords.add(pairOfCoords);
                distanceClosetCoords.add(d_min);
            }else{
                divideAndConquer(list);
            }
        }

    }

    public ArrayList<List<Integer>> sorting(ArrayList<List<Integer>> coords) {
        int x = 0; int y = 1;
        ArrayList<List<Integer>> organizedCoords = new ArrayList<>();

        for(List<Integer> coord: coords) {
            //for the first time
            if (organizedCoords.isEmpty()) {
                organizedCoords.add(coord);
            } else {
                int i = 1;
                int size = organizedCoords.size();
                List<Integer> coordBefore = organizedCoords.get((size - i));
                //to check for x primarly and then check for y
                while (coordBefore.get(x) >= coord.get(x)) {
                    i++;
                    //in case x are equals, checks for y secondly
                    if (coordBefore.get(x).equals(coord.get(x))) {
                        if (coordBefore.get(y) > coord.get(y)) {
                            coordBefore = organizedCoords.get((size - i));
                        } else {
                            i--;
                            break;
                        }
                    }
                    //in case the "new coord" have to be in the first position
                    if (size - i == -1) {
                        break;
                    }
                    //in case x of coordBefore is > than "new coord" x
                    if (coordBefore.get(x) > coord.get(x)) {
                        coordBefore = organizedCoords.get((size - i));
                    }
                }
                organizedCoords.add(size - i + 1, coord);
            }
        }
        return organizedCoords;
    }

    public int selectingShortestDistance(ArrayList<Double> distances){
        int i = 0;
        int indexShortest = 0;
        double shortestDistance = distances.get(0);
        for(double distance: distances){
            if(distance < shortestDistance){
                shortestDistance = distance;
                indexShortest = i;
            }
            i++;
        }
        return indexShortest;
    }

    public void printCoords(ArrayList<List<Integer>> coords){
        coords.forEach(System.out::println);
        System.out.println("--------------------");
    }

}
