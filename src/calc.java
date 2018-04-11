import java.util.ArrayList;

public class calc {
    public double somatorio(ArrayList<Individuos> individuos){
        double soma=0;
        for (Individuos i: individuos) {
            soma+=i.resp;
        }
        return soma;
    }
    public double media(ArrayList<Individuos> individuos){
        double soma=0;
        for (Individuos i: individuos) {
            soma+=i.resp;
        }
        return soma/individuos.size();
    }
    public double somaQuadrada(ArrayList<Individuos> individuos){
        double soma=0;
        for (Individuos i: individuos) {
            soma+=Math.pow(i.resp,2);
        }
        return soma;
    }
    public double variancia(ArrayList<Individuos> ind){
        double var=0;
        var=(somaQuadrada(ind)-somatorio(ind)/ind.size())/ind.size()-1;
        return var;
    }
}
