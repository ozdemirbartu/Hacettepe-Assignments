import java.util.ArrayList;

public class Entitiy implements Cloneable {
    String name;
    int HP;
    int maxHP;
    int AP;
    int maxMove;
    ArrayList<Integer> pos;

    public Entitiy(String name, ArrayList<Integer> pos) {
        this.pos = pos;
        this.name = name;
    }
    //this one helps us to fill the map with neutral objects.
    protected Object clone() throws CloneNotSupportedException {
        Entitiy placeHolder = (Entitiy) super.clone();
        placeHolder.name = name;
        placeHolder.pos = pos;

        return placeHolder;
    }

    public String getName() {
        return name;
    }
}
