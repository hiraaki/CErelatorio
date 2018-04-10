import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static int numeroAleatorio(int min, int max){
        return  (min + (int)(Math.random() * (max - min)));
    }
    public static double doubleAleatorio(double min, double max){
        return   min + (Math.random() * (max - min));
    }

    public static void main(String[] args){
        Populacao pop = new Populacao(20,5,-5);
        pop.printPopulaca();
        System.out.println("\n\n----------------------------------------------");
        //onePlusOne(pop,1000,-0.1,0.1);
        miplusmi(pop,50,-0.1,0.1);


    }
    public static void  onePlusOne(Populacao p,int geracao, double ini, double fim){

        Individuos oMelhor=new Individuos();
        ArrayList<Populacao> geracoes= new ArrayList<>();
        geracoes.add(p);
        int g=0;
        for(int i=0;i<geracao;i++){
            Populacao nova= new Populacao();
            for(int j=0;j<geracoes.get(i).individuos.size();j++){
                Individuos a = geracoes.get(geracoes.size()-1).individuos.get(numeroAleatorio(0,p.individuos.size()));
                Individuos b = geracoes.get(geracoes.size()-1).individuos.get(numeroAleatorio(0,p.individuos.size()));
                Individuos modificado = new Individuos();
                double x1=0,y1=0,resp1=0;
                if(a.resp>b.resp){
                    modificado.x=a.x+doubleAleatorio(ini,fim);
                    modificado.y=a.y+doubleAleatorio(ini,fim);
                    modificado.resp=nova.fun(modificado.x,modificado.y);
                }else {
                    modificado.x=b.x+doubleAleatorio(ini,fim);
                    modificado.y=b.y+doubleAleatorio(ini,fim);
                    modificado.resp=nova.fun(modificado.x,modificado.y);
                }
                modificado.resp=nova.fun(modificado.x,modificado.y);
                nova.individuos.add(modificado);

            }
            nova.printPopulaca();
            System.out.println("----------------------------------------------");

            Individuos resp=new Individuos(0,0,Double.MIN_VALUE);
            int id=0,ic=0;
            for (Individuos ind: nova.individuos) {
                if(resp.resp<ind.resp){
                    resp=ind;
                    id=ic;
                }
                ic++;
            }
            System.out.println(id+" "+resp.x+" "+resp.y+" "+resp.resp);
            System.out.println("\n\n----------------------------------------------");
            geracoes.add(nova);
            if(resp.resp>oMelhor.resp){
                oMelhor=resp;
                g=i;
            }
        }
        System.out.println(geracoes.size());
        System.out.println(g+" "+oMelhor.x+" "+oMelhor.y+" "+oMelhor.resp);


    }

    /*
    *
    * causa perturbaç�o em cada uma das soluç�es
    * a combinaç�o entre os melhores da gerada
    * e os melhores da anterior � a que cria uma nova eraç�o
    *
    * */
    public static void miplusmi(Populacao p,int geracao, double ini, double fim){

        Populacao q = new Populacao();
        for(int j=0;j<geracao;j++) {
            Collections.sort(p.individuos);
            for (int i = 0; i < p.individuos.size(); i++) {
                Individuos ind = new Individuos();
                ind.x = p.individuos.get(i).x + doubleAleatorio(ini, fim);
                ind.y = p.individuos.get(i).y + doubleAleatorio(ini, fim);
                ind.resp = q.fun(ind.x, ind.y);
                q.individuos.add(ind);
            }
            Collections.sort(q.individuos);
            for(int i=0;i<p.individuos.size()/2+1;i++){
                p.individuos.set(i,q.individuos.get(q.individuos.size()-i-1));
            }
            System.out.println("\n\n----------------------------------------------");
            p.printPopulaca();
        }

    }

    public static void metaEvolucianario(Populacao p,int geracao, double ini, double fim){

    }
    public static void localSearch(Populacao p,int geracao, double ini, double fim){

    }



}
