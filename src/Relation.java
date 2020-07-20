import java.util.HashSet;
import java.util.Objects;

public class Relation {
    private String name;
    private HashSet<Argument> setOfAttacker;

    public Relation(String name, HashSet<Argument> setOfAttacker) {
        this.name = name;
        this.setOfAttacker = setOfAttacker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Objects.equals(name, relation.name) &&
                Objects.equals(setOfAttacker, relation.setOfAttacker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, setOfAttacker);
    }

    @Override
    public String toString() {
        return "Relation{" +
                "name='" + name + '\'' +
                ", setOfAttacker=" + setOfAttacker +
                '}';
    }



    /*
    private HashSet<Argument> attackers; //the sets of attacker
    private Argument attacked; // attacked

    public Relation(HashSet<Argument> attackers, Argument attacked) {
        this.attackers = attackers;
        this.attacked = attacked;
    }

    public HashSet<Argument> getAttackers() {
        return attackers;
    }

    public void setAttackers(HashSet<Argument> attackers) {
        this.attackers = attackers;
    }

    public Argument getAttacked() {
        return attacked;
    }

    public void setAttacked(Argument attacked) {
        this.attacked = attacked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return Objects.equals(attackers, relation.attackers) &&
                Objects.equals(attacked, relation.attacked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackers, attacked);
    }

    @Override
    public String toString() {
        return "Relation{" +
                "attackers=" + attackers +
                ", attacked=" + attacked +
                '}';
    }

 */
}
