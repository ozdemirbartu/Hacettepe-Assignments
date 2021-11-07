import java.util.ArrayList;

public class Ork extends Entitiy {
    int heal=Constants.orkHealPoints;

    public Ork(String name, ArrayList<Integer> pos) {
        super(name,pos);
        this.AP=Constants.orkAP;
        this.HP=200;
        this.maxHP=200;
        this.maxMove=Constants.orkMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(200)";
    }
}
