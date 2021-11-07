import java.util.ArrayList;

public class Human extends Entitiy {
    public Human(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.AP=Constants.humanAP;
        this.HP=100;
        this.maxHP=100;
        this.maxMove=Constants.humanMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(100)";
    }
}
