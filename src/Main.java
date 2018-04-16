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
        //pop.printPopulaca();
        //System.out.println("\n\n----------------------------------------------");
        Individuos result= new Individuos();
        System.out.println(pop.fun(3.0,3.0));
        System.out.println(pop.fun(3.0,-3.0));
        System.out.println(pop.fun(-3.0,3.0));
        System.out.println(pop.fun(-3.0,-3.0));
        double x=0;
        double y=0;
//        double resp=0;
        double resp=0;
        //media para 1+1
        for(int i=0;i<10;i++){
            pop.individuos.clear();
            pop=new Populacao(20,5,-5);
//            result=onePlusOne(pop,80,-0.1,0.1);
            resp+=onePlusOne(pop,100,-0.1,0.1);
//            x+=result.x;
//            y+=result.y;
//            resp+=result.resp;
        }
        //System.out.println(x/10+" "+y/10+" "+resp/10);
        System.out.println(resp/10);
        System.out.println("----------------------------");
        //m�dia para mi+mi
        x=0;y=0;resp=0;result= new Individuos();
        for(int i=0;i<10;i++){
            pop.individuos.clear();
            pop=new Populacao(20,5,-5);
//            result=miplusmi(pop,20,-0.1,0.1);
              resp+=miplusmi(pop,100,-0.1,0.1);
//            x+=result.x;
//            y+=result.y;
//            resp+=result.resp;
        }
//        System.out.println(x/10+" "+y/10+" "+resp/10);
        System.out.println(resp/10);
        System.out.println("----------------------------");
        x=0;y=0;resp=0;result= new Individuos();
        for(int i=0;i<10;i++){
            pop.individuos.clear();
            pop=new Populacao(20,5,-5);
//            result=metaEvolucianario(pop,20,-0.1,0.1);
            resp+=metaEvolucianario(pop,100,-0.1,0.1);
//            x+=result.x;
//            y+=result.y;
//            resp+=result.resp;
        }
//        System.out.println(x/10+" "+y/10+" "+resp/10);
        System.out.println(resp/10);
        System.out.println("----------------------------");
        x=0;y=0;resp=0;result= new Individuos();
        for(int i=0;i<10;i++){
            pop.individuos.clear();
            pop=new Populacao(20,5,-5);
            result=localSearch(100,20,-5,5,0.05,1);
            x+=result.x;
            y+=result.y;
            resp+=result.resp;
        }
        System.out.println(x/10+" "+y/10+" "+resp/10);
        System.out.println("----------------------------");

//        pop.individuos.clear();
//        pop = new Populacao(20,5,-5);
//        miplusmi(pop,50,-0.1,0.1);
//        pop.individuos.clear();
//        pop = new Populacao(20,5,-5);
//        metaEvolucianario(pop,50,-0.1,0.1);
//        localSearch(20,50,-5,5,0.1,1);




    }
    public static int  onePlusOne(Populacao p,int geracao, double ini, double fim){
//    public static Individuos  onePlusOne(Populacao p,int geracao, double ini, double fim){

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
            //nova.printPopulaca();
            //System.out.println("----------------------------------------------");

            Individuos resp=new Individuos(0,0,Double.MIN_VALUE);
            int id=0,ic=0;
            for (Individuos ind: nova.individuos) {
                if(resp.resp<ind.resp){
                    resp=ind;
                    id=ic;
                }
                ic++;
            }
            //System.out.println(id+" "+resp.x+" "+resp.y+" "+resp.resp);
            //System.out.println("\n\n----------------------------------------------");
            geracoes.add(nova);
            /*if para considerar até a minima mudança*/
//            if (resp.resp > oMelhor.resp) {
//                oMelhor = resp;
//                g = i;
//            }
            /*if para controle de aperfeçoamento de resposta*/
            if(Math.abs(resp.resp-oMelhor.resp)>0.00001) {
                if (resp.resp > oMelhor.resp) {
                    oMelhor = resp;
                    g = i;
                }
            }else if(Math.abs(resp.resp-oMelhor.resp)<0.00001){
//                System.out.println("----------------------------------------------");
                System.out.println(g+" "+resp.x+" "+resp.y+" "+resp.resp);
                break;
            }
        }

        //System.out.println(g+" "+oMelhor.x+" "+oMelhor.y+" "+oMelhor.resp);
        return g;
        //return oMelhor;
    }

    /*
    *
    * causa perturbaç�o em cada uma das soluç�es
    * a combinaç�o entre os melhores da gerada
    * e os melhores da anterior � a que cria uma nova eraç�o
    *
    * */
    //public static Individuos miplusmi(Populacao p,int geracao, double ini, double fim){
    public static int miplusmi(Populacao p,int geracao, double ini, double fim){

        Populacao q = new Populacao();
        Individuos resp = new Individuos();
        Individuos oMelhor = new Individuos();
        Collections.sort(p.individuos);
        oMelhor.x=p.individuos.get(p.individuos.size()-1).x;
        oMelhor.y=p.individuos.get(p.individuos.size()-1).y;
        oMelhor.resp=p.individuos.get(p.individuos.size()-1).resp;
        int g=0;
        for(int j=0;j<geracao;j++) {
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
            Collections.sort(p.individuos);
            resp.x=p.individuos.get(p.individuos.size()-1).x;
            resp.y=p.individuos.get(p.individuos.size()-1).y;
            resp.resp=p.individuos.get(p.individuos.size()-1).resp;

            if((Math.abs(resp.resp-oMelhor.resp)<0.00001)&&(resp.resp!=oMelhor.resp)) {
                //System.out.println("\n\n----------------------------------------------");
                //p.printPopulaca();
                //System.out.println(oMelhor.x+" "+oMelhor.y+" "+oMelhor.resp);
                System.out.println(g+" "+ resp.x+" "+resp.y+" "+resp.resp);
                break;
            }
            if (resp.resp > oMelhor.resp) {
                oMelhor.x=resp.x;
                oMelhor.y=resp.y;
                oMelhor.resp=resp.resp;
                g = j;

            }
        }
        //return oMelhor;
        return g;
    }

    //public static Individuos metaEvolucianario(Populacao p,int geracao, double ini, double fim){
    public static int metaEvolucianario(Populacao p,int geracao, double ini, double fim){
        Individuos resp = new Individuos();
        Individuos oMelhor = new Individuos();
        Populacao q = new Populacao();
        int g=0;
        Collections.sort(p.individuos);
        oMelhor.x=p.individuos.get(p.individuos.size()-1).x;
        oMelhor.y=p.individuos.get(p.individuos.size()-1).y;
        oMelhor.resp=p.individuos.get(p.individuos.size()-1).resp;
        for(int j=0;j<geracao;j++) {
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
            //System.out.println("\n\n----------------------------------------------");
            //p.printPopulaca();
            Collections.sort(p.individuos);
            resp.x=p.individuos.get(p.individuos.size()-1).x;
            resp.y=p.individuos.get(p.individuos.size()-1).y;
            resp.resp=p.individuos.get(p.individuos.size()-1).resp;
            if((Math.abs(resp.resp-oMelhor.resp)<0.00001)&&(resp.resp!=oMelhor.resp)) {
                //System.out.println("\n\n----------------------------------------------");
                //p.printPopulaca();
//                System.out.println(j);
//                System.out.println(oMelhor.x+" "+oMelhor.y+" "+oMelhor.resp);
                g = j;
                //System.out.println(resp.x+" "+resp.y+" "+resp.resp);
                break;
            }else if (resp.resp > oMelhor.resp) {
                oMelhor.x=resp.x;
                oMelhor.y=resp.y;
                oMelhor.resp=resp.resp;
                g = j;

            }
        }

//        System.out.println("\n\n----------------------------------------------");
//        Collections.sort(p.individuos);
//        p.printPopulaca();
        System.out.println(g+" "+oMelhor.x+" "+oMelhor.y+" "+oMelhor.resp);

//
//        return oMelhor;
        return g;
    }
    public static Individuos localSearch(int interacoes,int geracao, double ini, double fim,double exploracao,double exportacao){
        Populacao p=new Populacao();
        Individuos Best=new Individuos();
        Best.x=doubleAleatorio(ini,fim);
        Best.y=doubleAleatorio(ini,fim);
        Best.resp=p.fun(Best.x,Best.y);
        double x=0;
        double y=0;
        Individuos best=new Individuos(Best.x,Best.y,Best.resp);
        for(int i=0; i< geracao;i++){
            for(int j=0;j<interacoes;j++){
                x=best.x;
                y=best.y;
                best.x+=doubleAleatorio(-exploracao,exploracao);
                best.y+=doubleAleatorio(-exploracao,exploracao);
                while (best.x>fim||best.x<ini)
                    best.x+=doubleAleatorio(-exploracao,exploracao);
                while (best.y>fim||best.y<ini)
                    best.y+=doubleAleatorio(-exploracao,exploracao);
                best.resp=p.fun(best.x,best.y);
                if(p.fun(x,y)>best.resp) {
                    best.x=x;
                    best.y=y;
                    best.resp=p.fun(x,y);
                }
                if(best.resp>Best.resp) {
                    Best.x = best.x;
                    Best.y = best.y;
                    Best.resp = best.resp;
                }
            }
            //System.out.println("\n\n--------------------------");
            //System.out.println(best.x+" "+best.y+" "+best.resp);
            //System.out.println(Best.x+" "+Best.y+" "+Best.resp);
            best.x+=doubleAleatorio(-exportacao,exportacao);
            best.y+=doubleAleatorio(-exportacao,exportacao);
            while (best.x>fim||best.x<ini)
                best.x+=doubleAleatorio(-exportacao,exportacao);
            while (best.y>fim||best.y<ini)
                best.y+=doubleAleatorio(-exportacao,exportacao);
            best.resp=p.fun(best.x,best.y);
        }
//        System.out.println("\n\n\n--------------------------");
//        System.out.println(Best.x+" "+Best.y+" "+Best.resp);
        return Best;
    }


}
