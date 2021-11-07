import java.util.ArrayList;

public class Elf extends Entitiy{
    int ranged=Constants.elfRangedAP;
    public Elf(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.AP=Constants.elfAP;
        this.HP=70;
        this.maxHP=70;
        this.maxMove=Constants.elfMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(70)";
    }

}
