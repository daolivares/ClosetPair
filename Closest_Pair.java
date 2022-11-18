
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Closest_Pair {

    private ArrayList<ArrayList<Coord>> closetCoords;
    private Coord firstCoord, secondCoord;


    public Closest_Pair(){
        closetCoords = new ArrayList<>();
    }

    /* Funcion que crea las coordenadas */
    public ArrayList<Coord> createCoordinates(int tamaño){
        ArrayList<Coord> coords = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < tamaño; i++){
            int y = rand.nextInt(20);
            if(i < tamaño/2){
                int x = rand.nextInt(25) / 2;
                coords.add(new Coord(x, y));
                continue;
            }
            int x = rand.nextInt(25) + 12;
            coords.add(new Coord(x,y));
        }
        return coords;
    }

    /* Funcion que implementa algoritmo fuerza bruta */
    public void brutalForce(ArrayList<Coord> coords){
        double d_min = 99999999;
        int n = coords.size();
        double distance;

        for(int i = 0; i <= n-1; i++){
            for(int j = i+1; j <= n-1; j++){
                distance = coords.get(i).getDistance(coords.get(j));
                if(distance < d_min){
                    firstCoord = coords.get(i);
                    secondCoord = coords.get(j);
                    d_min = distance;
                }
            }
        }
    }

    /* Funcion que implementa algoritmo divide y venceras */
    public void divideAndConquer(ArrayList<Coord> coords) {
        //creating Lx and Rx
        ArrayList<Coord> Lx;
        ArrayList<Coord> Rx;

        /**
            creating Px: sorting dataset primarily with x and secondarily with y (Px)
         */

        ArrayList<Coord> Px = coords;
        int size = Px.size();

        Lx = new ArrayList<Coord>(Px.subList(0, size / 2));
        Rx = new ArrayList<Coord>(Px.subList(size / 2, size));

        //list created just to iterate between List
        ArrayList<ArrayList<Coord>> lists = new ArrayList<>();
        lists.add(Lx);
        lists.add(Rx);

        for(ArrayList<Coord> list: lists){
            size = list.size();
            //checking if brutalForce needs to be used
            if(size <= 3){
                brutalForce(list);
                ArrayList<Coord> pairOfCoords = new ArrayList<>();
                pairOfCoords.add(firstCoord);
                pairOfCoords.add(secondCoord);
                closetCoords.add(pairOfCoords);
            }else{
                divideAndConquer(list);
            }
        }

    }

    public ArrayList<Coord> sort(ArrayList<Coord> coords) {
        Collections.sort(coords, new Comparator<Coord>(){
            public int compare(Coord p1, Coord p2) {
                return Integer.valueOf(p1.getX()).compareTo(p2.getX());
            }
        });
        return coords;
    }

}
