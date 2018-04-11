import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class Populacao {
    public ArrayList<Individuos> individuos;
    public Populacao() {
        this.individuos=new ArrayList<>();
    }

    public double fun(double X, double Y){
        return (0.97 * exp(-(pow(X + 3,2) + pow(Y + 3,2)) / 5)
                + 0.98 * exp(-(pow(X + 3,2) + pow(Y - 3,2)) / 5)
                + 0.99 * exp(-(pow(X - 3,2) + pow(Y + 3,2))/ 5)
                + 1.0 * exp(-(pow(X - 3 , 2) + pow(Y - 3,2))/ 5));
    }
    public Populacao(int qtd, double max, double min){
        this.individuos = new ArrayList<>();
        for (int i=0;i<qtd;i++){
            Random gerador = new Random();
            double x = min + (Math.random() * (max - min));
            double y = min + (Math.random() * (max - min));
            this.individuos.add(new Individuos(x,y,fun(x,y)));
        }
    }
    public void printPopulaca(){
        int c=0;
        for (Individuos i:this.individuos) {
            System.out.println(c+" "+i.x+" "+i.y+" "+i.resp);
            c++;
        }
    }

    public double var(){
        calc c= new calc();
        return c.variancia(this.individuos);
    }

}
