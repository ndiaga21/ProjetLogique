import java.io.*;
import java.io.*;
import java.util.ArrayList;

public class Dame {
    public static ArrayList<ArrayList<Integer>> dame(int nbCase) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> local2 = new ArrayList<>();
        for (int j = 1; j <= nbCase; j++) {
            local2 = parcours(nbCase);
            for (ArrayList<Integer> local : local2){
                ArrayList<Integer> lo = new ArrayList<>();
                lo.add(-(local.get(0)*10+j));
                lo.add(-(local.get(1)*10+j));
                result.add(lo);
            }
        }
        for (int j = 1; j <= nbCase; j++) {
            local2 = parcours(nbCase);
            for (ArrayList<Integer> local : local2){
                ArrayList<Integer> lo = new ArrayList<>();
                lo.add(-(j*10+local.get(0)));
                lo.add(-(local.get(1)+j*10));
                result.add(lo);
            }
        }
       // System.out.println(result);
        for (int i = 1 ; i <= nbCase ; i++){
            for (int j = i+1 ; j <=nbCase ; j++) {
                ArrayList<Integer> local = new ArrayList<>();
                local.add(-(i*10+i));
                local.add(-(j*10+j));
                result.add(local);
            }
        }
        ArrayList<Integer> local = new ArrayList<>();
        local.add(11);
        local.add(nbCase*10+nbCase);
        for (int i = 1; i < nbCase - 1; i++ ){
            ArrayList<Integer> l = new ArrayList<>();
            l.add(-(11+i));
            l.add(-(nbCase*10+nbCase-(i*10)));
            result.add(l);
            ArrayList<Integer> l2 = new ArrayList<>();
            l2.add(-(11*2-i));
            l2.add(-(nbCase*10+nbCase-i));
            // System.out.println("l2"+l2);
            result.add(l2);

        }
        ArrayList<Integer> diago = new ArrayList<>();
        for (int i = 0 ; i < nbCase ; i++){
            diago.add((nbCase-i)*10 + (i+1));
        }
        ArrayList<Integer> ndiaga = new ArrayList<>();
        int m = nbCase-1;
        for (int i = nbCase ; i >=nbCase-1 ; i--){
            ndiaga.add(-Integer.valueOf(String.valueOf(i)+String.valueOf(m)));
            m++;
        }
        result.add(ndiaga);
        ArrayList<Integer> ndiaga2 = new ArrayList<>();
        int p = 1;
        for (int i = nbCase - 1; i >=1 ; i--){
            ndiaga2.add(-Integer.valueOf(String.valueOf(i)+String.valueOf(p)));
            p++;
        }
        result.add(ndiaga2);

        ArrayList<ArrayList<Integer>> local5 = new ArrayList<>();
        for (int i = 0 ; i < diago.size() ; i++){


            for (int j = i+1 ; j <diago.size() ; j++){
                ArrayList<Integer> local4 = new ArrayList<>();
                local4.add(-diago.get(i));
                local4.add(-diago.get(j));
                result.add(local4);
            }

        }
        //  System.out.println(result);
        for (int i = 1 ; i <= nbCase ; i++){
            ArrayList<Integer> lo = new ArrayList<>();
            for (int j = 1 ; j <= nbCase ; j++){
                lo.add(j*10+i);
            }
            result.add(lo);
        }
       // System.out.println(result);
       // System.out.println(result.size());
        return result;
    }

    public static ArrayList<ArrayList<Integer>> parcours (int n){
        ArrayList<ArrayList<Integer>> tab = new ArrayList<>();
        for (int i = 1; i <= n ; i++) {
            for (int j = i + 1; j <= n; j++) {
                ArrayList<Integer> local = new ArrayList<>();
                local.add(i);
                local.add(j);
                tab.add(local);
            }
        }
        return tab;
    }

    public static void genereDame(int n) throws IOException {
        int nbCase = n;
        int max = nbCase*10 + nbCase;
        dame(nbCase);
        ArrayList<ArrayList<Integer>> tab = dame(nbCase);
        PrintWriter ecrivain;
        File file = new File("out/les_exemple/dame") ;
        ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
        // ecrivain.println("c "+" "+"simple_v"+nbCase+"_c"+2+".cnf");
        // ecrivain.println("c");
        ecrivain.println("p cnf "+ max +" "+tab.size());
        for (int i = 0 ; i < tab.size() ; i++){
            for (int j = 0 ; j < tab.get(i).size() ; j++){
                ecrivain.print(tab.get(i).get(j)+" ");
            }
            ecrivain.println(" "+0);
        }
        ecrivain.close();
    }

    public static void main(String[] args) throws IOException, IOException {
        int nbCase = 4;
        int max = nbCase*10 + nbCase;
        dame(nbCase);
        ArrayList<ArrayList<Integer>> tab = dame(nbCase);
        PrintWriter ecrivain;
        File file = new File("out/les_exemple/dame") ;
        ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
        // ecrivain.println("c "+" "+"simple_v"+nbCase+"_c"+2+".cnf");
        // ecrivain.println("c");
        ecrivain.println("p cnf "+ max +" "+tab.size());
        for (int i = 0 ; i < tab.size() ; i++){
            for (int j = 0 ; j < tab.get(i).size() ; j++){
                ecrivain.print(tab.get(i).get(j)+" ");
            }
            ecrivain.println(" "+0);
        }
        ecrivain.close();

    }
}