import java.util.ArrayList;
import java.util.List;

public class Sports {
    String name;
    int points=0;
    int avg=0;
    int matchCount=0;
    int draws=0;
    int wins=0;
    int loses=0;
    int totalScored=0;
    int totalScoredAgaints=0;
    public Sports(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public int getAvg() {
        return avg;
    }

    public void calculator1(String score){
        int scored;
        int scoredAgaints;
        String[] splited =score.split(":");

        scored = Integer.parseInt(splited[0]);
        totalScored=totalScored+scored;

        scoredAgaints= Integer.parseInt(splited[1]);
        totalScoredAgaints=totalScoredAgaints+scoredAgaints;

        if (scored>scoredAgaints){
            wins++;
        }
        else if (scored<scoredAgaints){
            loses++;
        }
        else if (scored==scoredAgaints){
            draws++;
        }
        matchCount++;
    }
    public void calculator2(String score){
        int scored;
        int scoredAgaints;
        String[] splited =score.split(":");

        scored = Integer.parseInt(splited[1]);
        totalScored=totalScored+scored;

        scoredAgaints= Integer.parseInt(splited[0]);
        totalScoredAgaints=totalScoredAgaints+scoredAgaints;

        if (scored>scoredAgaints){
            wins++;
        }
        else if (scored<scoredAgaints){
            loses++;
        }
        else if (scored==scoredAgaints){
            draws++;
        }
        matchCount++;
    }

    @Override
    public String toString() {
        return name +"\t"+matchCount+"\t"+wins+"\t"+draws+"\t"+loses+"\t"+totalScored+":"+totalScoredAgaints+"\t"+points;
    }
}

