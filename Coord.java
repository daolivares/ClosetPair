
public class Coord{

    private int x, y;

    public Coord(int x, int y){
        this. x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public double getDistance(Coord p){
        return Math.sqrt(((this.x - p.getX()) * (this.x - p.getX())) + ((this.y - p.getY()) * (this.y - p.getY())));
    }

}
