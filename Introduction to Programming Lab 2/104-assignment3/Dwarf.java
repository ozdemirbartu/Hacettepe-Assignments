import java.util.ArrayList;

public class Dwarf extends Entitiy {
    public Dwarf(String name, ArrayList<Integer> pos) {
        super(name, pos);
        this.AP=Constants.dwarfAP;
        this.HP=120;
        this.maxHP=120;
        this.maxMove=Constants.dwarfMaxMove;
    }
    @Override
    public String toString() {
        return name +"\t"+HP+"\t"+"(120)";
    }
}
