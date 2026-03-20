package TypesOfUser;

public class Owl extends UserType {

    @Override
    public String getGetDescription() {
        return "Вы являетесь Совой!";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Owl;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
