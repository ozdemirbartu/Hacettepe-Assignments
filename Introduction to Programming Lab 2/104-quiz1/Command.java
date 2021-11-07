import java.io.*;
import java.util.*;

public class Command {
    static ArrayList<Icehockey> iTeam = new ArrayList<>();
    static ArrayList<String>iNames=new ArrayList<>();
    static ArrayList<Handball> hTeam = new ArrayList<>();
    static ArrayList<String>hNames=new ArrayList<>();
    public static void readFixture(String fixtureFileName) throws IOException { // method that reads data from fixtureFileName(takes file name as an argument file and creates objects of class
        Scanner fixtureInput = new Scanner(new File(fixtureFileName)); //we create a scanner named: fixtureInput
        fixtureInput.useDelimiter("\n"); //to differentiate each element by using newline delimiter for each new line
        while (fixtureInput.hasNext()) {
            String[] a = fixtureInput.next().trim().split("\t");
            if (a[0].startsWith("I")) {
                String firstTeam = a[1];
                String secondTeam = a[2];
                String score = a[3];

                if (!iNames.contains(firstTeam)){
                    Icehockey newHockeyTeam1 = new Icehockey(firstTeam);
                    iTeam.add(newHockeyTeam1);//we add this very new object to our object arraylist*/
                    iNames.add(newHockeyTeam1.name);
                }
                if (!iNames.contains(secondTeam)){
                    Icehockey newHockeyTeam2 = new Icehockey(secondTeam);
                    iTeam.add(newHockeyTeam2);//we add this very new object to our object arraylist*/
                    iNames.add(newHockeyTeam2.name);
                }
                for (Icehockey team: iTeam){
                    if (team.name.equals(firstTeam)){
                        team.calculator1(score);}
                    if (team.name.equals(secondTeam)){
                        team.calculator2(score);}
                }
            }
            //For Handball
            if (a[0].startsWith("H")) {
                String firstTeam = a[1];
                String secondTeam = a[2];
                String score = a[3];

                if (!hNames.contains(firstTeam)){
                    Handball newHandTeam1 = new Handball(firstTeam);
                    hTeam.add(newHandTeam1);//we add this very new object to our object arraylist*/
                    hNames.add(newHandTeam1.name);
                }
                if (!hNames.contains(secondTeam)){
                    Handball newHandTeam2 = new Handball(secondTeam);
                    hTeam.add(newHandTeam2);//we add this very new object to our object arraylist*/
                    hNames.add(newHandTeam2.name);
                }
                for (Handball team: hTeam){
                    if (team.name.equals(firstTeam)){
                        team.calculator1(score);}
                    if (team.name.equals(secondTeam)){
                        team.calculator2(score);}
                }
            }
        }
        ranker();
    }
    public static void ranker() throws IOException {
        BufferedWriter iceWriter = new BufferedWriter(new FileWriter("icehockey.txt", true));
        for (Icehockey i : iTeam){
            i.pointCal();
        }
        iTeam.sort(Comparator.comparing(Icehockey::getPoints).reversed());
        String iText="";
        for (Icehockey team: iTeam){
            iText+=iTeam.indexOf(team)+1 +"."+"\t"+team+"\n";
        }
        iceWriter.write(iText.replaceAll("[\n\r]+$", ""));
        iceWriter.flush();
        iceWriter.close();


        BufferedWriter hWriter = new BufferedWriter(new FileWriter("handball.txt", true));
        for (Handball i : hTeam){
            i.pointCal();
        }
        hTeam.sort(Comparator.comparing(Handball::getPoints).reversed());
        String hText="";
        for (Handball team: hTeam){
            hText+=hTeam.indexOf(team)+1 +"."+"\t"+team+"\n";
        }
        hWriter.write(hText.replaceAll("[\n\r]+$", ""));
        hWriter.flush();
        hWriter.close();


    }
}
