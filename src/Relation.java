import java.util.HashSet;
import java.util.Objects;

public class Relation {
    private Argument attacked;
    private HashSet<Argument> setOfAttacker;

    public Relation(Argument a, HashSet<Argument> setOfAttacker) {
        this.attacked = a;
        this.setOfAttacker = setOfAttacker;
    }

    public Argument getAttacked() {
        return attacked;
    }

    public void setAttcked(Argument a) {
        this.attacked = a;
    }

    public HashSet<Argument> getSetOfAttacker() {
        return setOfAttacker;
    }

    public void setSetOfAttacker(HashSet<Argument> setOfAttacker) {
        this.setOfAttacker = setOfAttacker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return Objects.equals(attacked, relation.attacked) &&
                Objects.equals(setOfAttacker, relation.setOfAttacker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacked, setOfAttacker);
    }

    @Override
    public String toString() {
        return  attacked + "<--" + setOfAttacker;
    }

}
