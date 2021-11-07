

public class Icehockey extends Sports {

    public Icehockey(String name) {
        super(name);
    }
    public void pointCal(){
        points = wins*3+draws;
        avg = totalScored-totalScoredAgaints;

    }

}

