import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Main {

    public static void main(String[] args) throws IOException {
        long start;

        start = System.nanoTime();
        String simpleFichier = "out/les_exemple/Exempleducours";
        String dame = "out/les_exemple/dame";
        String pigeon = "out/les_exemple/pigeon";
        Pigeon.generePigeon(3);
       //Dame.genereDame(10);
        String program = new String(Files.readAllBytes(Paths.get(pigeon)));
        InstanceClause recupereClause = LireFichierExemple.lirefichier(program);
        DPLL dpll = new DPLL();
        dpll.AppelDPLL(recupereClause);
        List<List<Variable>> assignments = dpll.affectationVariable();
        dpll.SortieDPLL(assignments);


        //Tes instructions;
        long duree = (System.nanoTime() - start)/1000000000;

        System.out.println("le temps d'execution est  " +duree + " secondes");
    }
}