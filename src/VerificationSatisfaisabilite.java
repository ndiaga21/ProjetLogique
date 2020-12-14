import java.util.*;

public class VerificationSatisfaisabilite {
    private List<Deque<Integer>> watchlist;
    private final InstanceClause instanceClause;

    public VerificationSatisfaisabilite(InstanceClause instanceClause) {
        this.instanceClause = instanceClause;
        initialiselistetoVerfication(instanceClause);
    }
    private void initialiselistetoVerfication(InstanceClause instanceClause) {
        // Initialize watchlist by letting each clause watch its first literal:
        List<List<Integer>> clauses = instanceClause.getClauses();
        // this.watchlist[i] est la liste des clauses surveillant le littéral i :
        this.watchlist = new ArrayList<>();
        for (int i = 0; i < instanceClause.getnbreVariable() * 2; ++i) {
            //System.out.println(i);
            this.watchlist.add(new LinkedList<>());
        }
        for (int c = 0; c < clauses.size(); ++c) {
            this.watchlist.get(clauses.get(c).get(0)).add(c);
        }
    }

    public boolean satisfaisabilityClause(int falseLiteral, List<Variable> assignment) {

        //Lorsqu'un littéral est attribué faux, nous faisons en sorte que toutes les clauses en surveillant que
       // littéral de regarder un autre littéral.
        //Si tous les autres littéraux sont faux => clause insatisfaite

       // Retour : Faux si ne peut pas mettre à jour la liste de surveillance, ce qui signifie que la formule est
        //insatisfaisant ; vrai autrement.
        LinkedList<Integer> wachingClauses = (LinkedList<Integer>) watchlist.get(falseLiteral);
        while (!wachingClauses.isEmpty()) {
            Integer c = wachingClauses.getFirst();
            var clause = instanceClause.getClause(c);
            boolean foundAlternative = false;
            for (Integer lit : clause) {
                if (lit == falseLiteral)
                    continue;
                Variable Varcorresp = Variable.litToVar(lit);
                Variable var = assignment.get(Varcorresp.getIdentique());
                // Une clause regarde soit un littéral non assigné, soit un littéral vrai
                if (!var.attribution() || (var.getValue() == Varcorresp.getValue())) {
                    foundAlternative = true;
                    verificatinLitteral(c, lit);
                    stopverificatinLitteral(c, falseLiteral);
                }
            }
            if (!foundAlternative) {
                return false;
            }
        }
        return true;
    }
    private void verificatinLitteral(int clause, int lit) {
        // regarde les litteral
        this.watchlist.get(lit).add(clause);
    }
    private void stopverificatinLitteral(int clause, int lit) {
        this.watchlist.get(lit).remove(clause);
    }
}
