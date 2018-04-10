import java.util.ArrayList;
import java.util.Random;

public class Individuos implements Comparable<Individuos>{
    public double x;
    public double y;
    public double resp;

    public Individuos(double x, double y, double resp) {
        this.x = x;
        this.y = y;
        this.resp = resp;
    }
    public Individuos(){}

    @Override
    public int compareTo(Individuos i2) {
        if((this.resp-i2.resp)>0)
            return 1;
        else if((this.resp-i2.resp)<0)
            return -1;
        else return 0;
    }

}
