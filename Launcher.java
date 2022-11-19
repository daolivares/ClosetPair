import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Daniel
 */

public class Launcher {

    public static void main(String[] args) throws IOException {
        Closest_Pair in = new Closest_Pair();
        int n = (int) Math.pow(2, 5);
        int d = 5;
        Writer w = new Writer("comprobaciones.txt");
        while (n <= Math.pow(2, 17)) {
            double time = 0;
            int comprobaciones = 0;
            int i;
            for(i = 0; i < 200; i++) {
                ArrayList<Coord> coords = in.createCoordinates(n);
                coords = in.sort(coords);
                int start = (int) System.nanoTime();
                in.divideAndConquer(coords);
                int end = (int) System.nanoTime();
                time = time + (end - start);
            }
            time /= i;
            comprobaciones = n;
            w.write(n + " " + time + " " + comprobaciones);
            System.out.println(n + " " + time + " " + comprobaciones);
            d++;
            n = (int) Math.pow(2, d);
        }


    }

}
