import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        Populacao pop = new Populacao(20,5,-5);
        pop.printPopulaca();
        System.out.println("\n\n----------------------------------------------");
        onePlusOne(pop,50,0.005,0.005);

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
                Individuos b = geracoes.get(geracoes.size()-1).individuos.get(numeroAleatorio(0,19));
                Individuos modificado = new Individuos();
                double x1=0,y1=0,resp1=0;
                if(a.resp>b.resp){
                    /*x1=a.x+0.05;
                    if(nova.fun(x1,a.y)>a.resp)
                        modificado.x=x1;
                    else
                        modificado.x=a.x-0.05;
                    y1=a.y+0.05;
                    if (nova.fun(a.x,y1)>a.resp)
                        modificado.y=y1;
                    else
                        modificado.y=a.y-0.05;*/
                    modificado.x=a.x+doubleAleatorio(ini,fim);
                    modificado.y=a.y+doubleAleatorio(ini,fim);
                    modificado.resp=nova.fun(modificado.x,modificado.y);
                }else {
                    /*x1=b.x+0.05;
                    if(nova.fun(x1,b.y)>b.resp)
                        modificado.x=x1;
                    else
                        modificado.x=b.x-0.05;
                    y1=b.y+0.05;
                    if (nova.fun(b.x,y1)>b.resp)
                        modificado.y=y1;
                    else
                        modificado.y=b.y-0.05;*/
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

    public static int numeroAleatorio(int min, int max){
        return  (min + (int)(Math.random() * (max - min)));
    }
    public static double doubleAleatorio(double min, double max){
        return   min + (Math.random() * (max - min));
    }

}
