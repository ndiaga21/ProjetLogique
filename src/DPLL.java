import java.util.*;

public class DPLL {
    private InstanceClause instanceClause;
    private List<Variable> valeuraffectation;
    private List<List<Variable>> satisfyAssignments;
    private VerificationSatisfaisabilite verififySatisfaisability;


    public DPLL() {
        this(new Optionderesolution());
    }

    public Optionderesolution option;
    public DPLL(Optionderesolution option) {
        this.option = option;
    }
    public void AppelDPLL(InstanceClause instanceClause) {
        this.instanceClause = instanceClause;
        this.valeuraffectation = ordredebranchement(instanceClause);
        this.verififySatisfaisability = new VerificationSatisfaisabilite(instanceClause);
        this.satisfyAssignments = new ArrayList<>();
        Satisfiablite();
    }
    public void Satisfiablite() {
        int depth = 0;
        int[] state = new int[instanceClause.getnbreVariable()];
        Arrays.fill(state, -1);
        while (true) { //depth= nombre de variable
            if (depth == instanceClause.getnbreVariable()) {
                conservationaffectation();
                return;
            }

            boolean triedSomething = true;
            // Choisissez une variable et essayez de lui attribuer une valeur :
            Variable var = valeuraffectation.get(depth);
            // Backtrack
            switch (state[depth]) {
                case -1 -> {
                    boolean[] values = ordreaffectation();
                    var.affecte(values[0]);
                    state[depth] = values[0] ? 1 : 0;
                }
                case 0 -> {
                    var.affecte(true);
                    state[depth] = 2;
                }
                case 1 -> {
                    var.affecte(false);
                    state[depth] = 2;
                }
                case 2 -> triedSomething = false;
            }
            if (triedSomething) {
                if (verififySatisfaisability.satisfaisabilityClause(var.toLitteral(), this.valeuraffectation)) {
                    depth=depth+1;
                } else {
                    var.nonattribution();
                }
            } else {
                // Backtrack:
                if (depth > 0) {
                    var.nonattribution();
                    state[depth] = -1;
                    depth=depth-1;
                } else {
                    return;
                }
            }
        }
    }

    public void conservationaffectation() {
        List<Variable> assignment = new ArrayList<>();
        for (Variable v : this.valeuraffectation) {
            assignment.add(v.clone());
        }
        this.satisfyAssignments.add(assignment);
    }
    public List<List<Variable>> affectationVariable() {
        return this.satisfyAssignments;
    }

    private List<Variable> ordredebranchement(InstanceClause instanceClause) {
        List<Variable> ordrearrive = new ArrayList<>();
        for (int i = 1; i <= instanceClause.getnbreVariable(); i++) {
            ordrearrive.add(new Variable(i));
        }
        return ordrearrive;
    }

    private boolean[] ordreaffectation() {
        Random random = new Random();
        boolean value = random.nextBoolean();
        return new boolean[] {value, !value};
    }
    public void SortieDPLL(List<List<Variable>> retourlistevariable){
        if (retourlistevariable.size() == 0) {
            System.out.println("la clause donnée est insatisfaite");
            System.out.println(Arrays.toString(new List[]{retourlistevariable}));
        }
        else {
            for (List<Variable> assignment : retourlistevariable) {
                int[] result = LireFichierExemple.decodeAssignments(assignment);
                System.out.println("la clause est satisfaite les litterales qui prennent la valeur 1 et qui satistifaitent cette clause donnée est:");
                System.out.println(Arrays.toString(result));

            }
        }
    }


}
