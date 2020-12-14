public class Variable{
    private int identique;
    private Boolean value;

    public Variable(int identique) {
        this.identique = identique;
        this.value = null;
    }
    public Variable(int identique, boolean value) {
        this.identique = identique;
        this.value = value;

    }
    public static Variable litToVar(int literal) {
        int id = literal / 2;
        boolean value = literal % 2 == 0;
        return new Variable(id, value);
    }
    public Variable clone() {
        return new Variable(this.identique, this.value);
    }
    public void affecte(boolean value) {
        this.value = value;
    }
    public void nonattribution() {
        this.value = null;
    }
    public boolean attribution() {
        return this.value != null;
    }
    public int toLitteral() {
        return (identique - 1) * 2 + (value ? 1 : 0);
    }
    public int getIdentique() {
        return identique;
    }
    public Boolean getValue() {
        return value;
    }
}
