import java.awt.geom.Rectangle2D;



public class BurningShip extends FractalGenerator{

    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range){
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
        return;
    }
    public int numIterations(double x, double y){
        int iteration = 0;
        double zR = 0;
        double zI = 0;
        double kv = zR * zR + zI * zI;

        while (iteration < MAX_ITERATIONS && kv < 4){
            double zR1 = zR * zR - zI *zI + x;
            double zI1 = 2 * Math.abs(zR) * Math.abs(zI) + y;
            zR = zR1;
            zI = zI1;
            kv = zR * zR + zI * zI;
            iteration += 1;
        }
        if (iteration == MAX_ITERATIONS) return -1;
        return iteration;
    }
    public String toString(){
        return "Burning Ship";
    }
}
