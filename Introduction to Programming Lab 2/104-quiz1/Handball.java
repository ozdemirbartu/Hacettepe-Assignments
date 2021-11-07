import java.util.ArrayList;

public class Handball extends Sports {

    public Handball(String name) {
        super(name);
    }
    public void pointCal(){
        points = wins*2+draws;
        avg = totalScored-totalScoredAgaints;
    }

}
