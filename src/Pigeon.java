import java.io.*;
import java.io.*;
import java.util.ArrayList;

public class Pigeon {
public static ArrayList<ArrayList<Integer>> pigeon( int nbPigeon ){
        int nbCase = nbPigeon - 1;
        ArrayList<ArrayList<Integer>> tab = new ArrayList<>();
        for (int i = 1 ; i <= nbPigeon ; i++){
        ArrayList<Integer> clause = new ArrayList<>();
        for (int  j = 1 ; j <= nbCase ; j++){
        int local = i*10 + j;
        // System.out.println(local);
        clause.add(local);
        }
        ArrayList<Integer> clause2 = new ArrayList<>();
        for (int k : clause){
        clause2.add(-k);
        }
        tab.add(clause);
        tab.add(clause2);
        }
        //  System.out.println(tab);
        ArrayList<ArrayList<Integer>> local = new ArrayList<>();

        for (int i = 1 ; i <= 2 ; i++){
        ArrayList<Integer> local2 = new ArrayList<>();
        for (int j = 1 ; j <= 3 ; j++){
        local2.add(-(j*10+i));
        }
        local.add(local2);

        }
        for (ArrayList<Integer> i : local){
        for (int j = 0 ; j < i.size() - 1 ; j++){

        for (int k = j+1 ; k < i.size() ; k++){
        ArrayList<Integer> inter = new ArrayList<>();
        inter.add(i.get(j));
        inter.add(i.get(k));
        tab.add(inter);
        }

        }

        }
       // System.out.println(tab);
        return tab;
}
   public static void generePigeon(int n) throws IOException {

           int nbPigeon = n;
           ArrayList<ArrayList<Integer>> tab = pigeon(nbPigeon);
           ArrayList<Integer> maxi = new ArrayList<>();
           for (ArrayList<Integer> i : tab){
                   maxi.addAll(i);
           }
           int maximum = maxi.stream().max(Integer :: compare).get();
           PrintWriter ecrivain;
           File file = new File("out/les_exemple/pigeon") ;
           ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
           // ecrivain.println("c "+" "+"simple_v"+nbPigeon+"_c"+2+".cnf");
           // ecrivain.println("c");
           ecrivain.println("p cnf "+ maximum +" "+tab.size());
           for (int i = 0 ; i < tab.size() ; i++){
                   ecrivain.println(tab.get(i).get(0)+" "+tab.get(i).get(1)+" "+ 0);
           }
           ecrivain.close();
   }
public static void main(String[] args) throws IOException {
        int nbPigeon = 3;
        ArrayList<ArrayList<Integer>> tab = pigeon(nbPigeon);
        ArrayList<Integer> maxi = new ArrayList<>();
        for (ArrayList<Integer> i : tab){
        maxi.addAll(i);
        }
        int maximum = maxi.stream().max(Integer :: compare).get();
        PrintWriter ecrivain;
        File file = new File("out/les_exemple/pigeon") ;
        ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
        // ecrivain.println("c "+" "+"simple_v"+nbPigeon+"_c"+2+".cnf");
        // ecrivain.println("c");
        ecrivain.println("p cnf "+ maximum +" "+tab.size());
        for (int i = 0 ; i < tab.size() ; i++){
        ecrivain.println(tab.get(i).get(0)+" "+tab.get(i).get(1)+" "+ 0);
        }
        ecrivain.close();
        }
}