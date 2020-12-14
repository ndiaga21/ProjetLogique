public class Optionderesolution {
    private Choixvariable varChoice;
    private boolean solveAll;

    public Optionderesolution() {
        this(Choixvariable.INPUT_ORDER, false);
    }
    public Optionderesolution(Choixvariable varChoice, boolean solveAll) {
        this.varChoice = varChoice;
        this.solveAll = solveAll;
    }
}
