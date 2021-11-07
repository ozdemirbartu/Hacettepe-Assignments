import java.util.ArrayList;

public class Troll extends Entitiy {
    public Troll(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.AP=Constants.trollAP;
        this.HP=150;
        this.maxHP=150;
        this.maxMove=Constants.trollMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(150)";
    }
}
