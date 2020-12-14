import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LireFichierExemple {
    public static InstanceClause lirefichier(String program) {
        System.out.println(program);
        List<String> line = Arrays.asList(program.split("\n"));
        String ligneclause;
        ligneclause = "";
        Iterator<String> iterator = line.iterator();

        while (!ligneclause.startsWith("p") && line.iterator().hasNext()) {
            ligneclause = iterator.next(); //LES PREMIERES LIGNE
        }
        String[] tokens = ligneclause.split("\\s+");
        int nombredevariable = Integer.parseInt((tokens[2]));
        int nbredeClause = Integer.parseInt(tokens[3]);
        List<List<Integer>> clauses = new ArrayList<>();
        while (iterator.hasNext() && nbredeClause > 0) {
            --nbredeClause;
            ligneclause = iterator.next();
            tokens = ligneclause.split("\\s+");
            List<Integer> clause = new ArrayList<>();
            for (int i = 0; i < tokens.length - 1; ++i) {
                clause.add(encodeLiteral(Integer.parseInt(tokens[i])));
            }
            clauses.add(clause);
        }
        return new InstanceClause(nombredevariable, clauses);
    }
    public static int[] decodeAssignments(List<Variable> assignment) {
        int[] result = new int[assignment.size()];
        for (int i = 0; i < assignment.size(); ++i) {
            result[i] = decodeVariable(assignment.get(i));
        }
        return result;
    }
    private static int encodeLiteral(int v) {
        if (v > 0)
            return (v - 1) * 2;
        else
        return (-v - 1) * 2 + 1;
    }
    private static int decodeVariable(Variable var) {
        int varIdentique = var.getIdentique();
        if (!var.getValue())
            varIdentique = -varIdentique;
        return varIdentique;
    }
}
