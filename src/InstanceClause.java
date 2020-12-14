import java.util.List;
public class InstanceClause {
    private final int nbrevariable;
    private final List<List<Integer>> clauses;
    public InstanceClause(int varCount, List<List<Integer>> clauses) {
        this.nbrevariable = varCount;
        this.clauses = clauses;
    }
    public int getnbreVariable() {
        return nbrevariable;
    }
    public List<List<Integer>> getClauses() {
        return clauses;
    }
    public List<Integer> getClause(Integer i) {
        return clauses.get(i);
    }
}
