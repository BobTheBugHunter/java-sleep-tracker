package types.of.users;

public class Pigeon extends UserType{

    @Override
    public String getGetDescription() {
        return "Вы являетесь голубем!";
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof Pigeon;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
