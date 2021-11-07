import java.util.ArrayList;

public class Goblin extends Entitiy {
    public Goblin(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.AP=Constants.goblinAP;
        this.HP=80;
        this.maxHP=80;
        this.maxMove=Constants.goblinMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(80)";
    }
}
