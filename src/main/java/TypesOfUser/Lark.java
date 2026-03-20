package TypesOfUser;

public class Lark extends UserType{
    @Override
    public String getGetDescription() {
        return "Вы являетесь Жаворонком!";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Lark;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
