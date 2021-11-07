import java.io.*;
import java.util.*;

public class Commands {
    static String outFileName;
    static ArrayList<Entitiy> CalList = new ArrayList<>();
    static ArrayList<Entitiy> ZorList = new ArrayList<>();
    static Entitiy[][] map; //Empty map
    //This method help us to get output file name from main.
    public static void returnOutName(String outFile){
        outFileName = outFile;
    }
    //this method reads initialfile; creates map and armies.
    public static void readInitial(String initialFileName) throws IOException, CloneNotSupportedException { // method that reads data from initialFileName(takes file name as an argument file and creates objects of class
        Scanner initialInput = new Scanner(new File(initialFileName)); //we create a scanner named: initialInput
        initialInput.useDelimiter("\n"); //to differentiate each element by using newline delimiter for each new line
        int index = 0;
        while (initialInput.hasNext()) {
            String[] a = initialInput.next().trim().split(" ");
            //MAP CREATION STEP.
            if (index == 1) {
                String[] mapD = a[0].split("x");
                int Rows = Integer.parseInt(mapD[1]);
                int Columns = Integer.parseInt(mapD[0]);
                mapMaker(Rows, Columns); //CALLS MAP CREATOR

            }
            //ARMY CREATION.
            if (a[0].equals("ELF")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Elf newElf = new Elf(name, initPos);
                CalList.add(newElf);
                mover(newElf, newElf.pos.get(0), newElf.pos.get(1));
            }
            if (a[0].equals("DWARF")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Dwarf newDwarf = new Dwarf(name, initPos);
                CalList.add(newDwarf);
                mover(newDwarf, newDwarf.pos.get(0), newDwarf.pos.get(1));
            }
            if (a[0].equals("HUMAN")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Human newHuman = new Human(name, initPos);
                CalList.add(newHuman);
                mover(newHuman, newHuman.pos.get(0), newHuman.pos.get(1));
            }
            if (a[0].equals("ORK")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Ork newOrk = new Ork(name, initPos);
                ZorList.add(newOrk);
                mover(newOrk, newOrk.pos.get(0), newOrk.pos.get(1));
            }
            if (a[0].equals("TROLL")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Troll newTroll = new Troll(name, initPos);
                ZorList.add(newTroll);
                mover(newTroll, newTroll.pos.get(0), newTroll.pos.get(1));
            }
            if (a[0].equals("GOBLIN")) {
                String name = a[1];
                ArrayList<Integer> initPos = new ArrayList<>();
                initPos.add(Integer.parseInt(a[2]));
                initPos.add(Integer.parseInt(a[3]));
                Goblin newGoblin = new Goblin(name, initPos);
                ZorList.add(newGoblin);
                mover(newGoblin, newGoblin.pos.get(0), newGoblin.pos.get(1));
            }
            index++;
        }


    }
    //this method reads commands and calls commandApplier for each line of command
    public static void readCommand(String commandFileName) throws IOException, CloneNotSupportedException {
        Scanner commandInput = new Scanner(new File(commandFileName)); //we create a scanner named: initialInput
        commandInput.useDelimiter("\n"); //to differentiate each element by using newline delimiter for each new line
        while (commandInput.hasNext()) {
            String[] a = commandInput.next().trim().split(" ");
            commandApplier(a);
            //WE SEEK FOR COMMANDS

        }
    }
    //this method will be called during readInitial and it will create the map.
    public static void mapMaker(int Rows, int Columns) {
        map = new Entitiy[Rows][Columns];
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                ArrayList<Integer> pos = new ArrayList<>();
                pos.add(i);
                pos.add(j);
                map[i][j] = new Entitiy("  ", pos);
            }
        }
    }
    //this method writes the map to outputfile
    public static void mapViewer() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName, true));
        String stars = new String(new char[map.length*2]).replace("\0", "*");
        writer.write(stars+"**"+"\n");
        for (int i = 0; i < map.length; i++) {
            writer.write("*");
            for (int j = 0; j < map[i].length; j++) {
                writer.write(map[i][j].name+"" );
            }
            writer.write("*\n");

        }
        writer.write(stars+"**"+"\n");
        writer.flush();
        writer.close();
    }
    //this method shows living armies hp
    public static void HPViewer() throws IOException {
        ArrayList<Entitiy> aliveList = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName, true));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].name != "  ") {
                    aliveList.add(map[i][j]);
                }
            }
        }
        aliveList.sort(Comparator.comparing(Entitiy::getName));
        for (Entitiy i : aliveList) {
            writer.write("\n" + i.toString());
        }
        //writer.write("\n"+aliveList.forEach();));
        writer.write("\n\n");
        writer.flush();
        writer.close();

    }
    //this method help us to move selected object around map.
    public static boolean mover(Entitiy movingObject, int x, int y) throws CloneNotSupportedException {
        int entitiyX = movingObject.pos.get(0);
        int entitiyY = movingObject.pos.get(1);
        if (map[y][x].name.equals("  ")){ //if it's an empty cell
            ArrayList<Integer> coordinates2 = new ArrayList<>();
            coordinates2.add(entitiyX);
            coordinates2.add(entitiyY);
            map[entitiyY][entitiyX] = new Entitiy("  ", coordinates2);
            map[entitiyY][entitiyX].pos = coordinates2;

            map[y][x] = movingObject;
            ArrayList<Integer> coordinates1 = new ArrayList<>();
            coordinates1.add(x);
            coordinates1.add(y);
            movingObject.pos = coordinates1;
            return true;
        }
        //if both are calliance or both are zorde return false so that we stop the move sequence
        else if ((CalList.contains(map[y][x]) && CalList.contains(movingObject)) || (ZorList.contains(map[y][x]) && ZorList.contains(movingObject))){
            return false;

        }

        //if movingobject is zorde and cell is occupied by calliance call brawl(fight to death) and return false so that we stop the move sequence.
        else if (CalList.contains(map[y][x])&&ZorList.contains(movingObject)){
            brawl(map[y][x],movingObject);
            return false;

        }
        //if movingobject is caliance and cell is occupied by zorde
        else if (ZorList.contains(map[y][x])&&CalList.contains(movingObject)){
            brawl(map[y][x],movingObject);
            return false;
        }
        return true;
    }
    //this method runs our essential commands and calls other methods in correct sequence
    public static void commandApplier(String[] a) throws IOException, CloneNotSupportedException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName, true));
        boolean condition = true;
        boolean end=winChecker();
        if (!end){
            for (Entitiy[] i : map) {
                for (Entitiy entitiy : i) {
                    //GET COORDINATES BEFORE THE MOVE
                    int x = entitiy.pos.get(0);
                    int y = entitiy.pos.get(1);
                    int xControl = x;
                    int yControl = y;

                    //FIND THE ARMY THAT COMMANDED TO MOVE. AND CHECK IF IT ATTACKS WITH EVERY MOVE OR AT FINAL STEP.
                    //FOR FINAL STEP ATTACKERS.
                    if ((entitiy.name.equals(a[0])) && (condition) && (entitiy instanceof Ork || entitiy instanceof Human || entitiy instanceof Troll)) {//CONDITION WILL BE MARKED AS FALSE ONCE WE MOVE THE ENTITY
                        String[] b = a[1].split(";");


                        //EXCEPTIONS

                        //EXCEPTIONS
                        if (b.length / 2 != entitiy.maxMove) {
                            writer.write("Error :  Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                        }

                        else{
                            //ORK'S RACIAL BEFORE THE MOVEMENT
                            ArrayList<Entitiy> zordeInRange= range(entitiy).get(0);
                            ArrayList<Entitiy> callianceInRange= range(entitiy).get(1);
                            if (entitiy instanceof Ork) {
                                //HEAL THEM (IF THEY ARE NOT FULL HP)
                                for (Entitiy ally : zordeInRange) {
                                    if (ally.HP + ((Ork) entitiy).heal <= ally.maxHP) {
                                        ally.HP = ally.HP + ((Ork) entitiy).heal;
                                    }
                                }
                            }
                            //ACTUAL MOVE
                            boolean exceptions = false;
                            for (int z = 0; z < b.length; z = z + 2) {
                                //GET THE DIRECTION OF MOVE.
                                int xM = Integer.parseInt(b[z]);
                                int yM = Integer.parseInt(b[z + 1]);
                                //ADD MOVEMENT TO THE INITIAL COORDINATES
                                x = x + xM;
                                y = y + yM;
                                if ((x < 0) || (y < 0) || (x >= map.length) || (y >= map.length)) {
                                    writer.write("Error :  Game board boundaries are exceeded. Input line ignored \n\n");
                                    exceptions=true;
                                    if (z!=0){ //if it is not the first move sequence print the map
                                        mapViewer();
                                    }
                                }
                                else if (condition && !exceptions) {
                                    condition = mover(entitiy, x, y); //RETURNS TRUE IF IT MOVES SUCCESSFULLY
                                    // OR RETURNS FALSE IF IT FAILS TO MOVE, SO THAT IT CAN BREAK THE MOVEMENT
                                }
                            }

                            if (condition){ //if movement sequence didn't encounter any colliding with other armies.
                                attacker(entitiy);
                            }
                            if (!exceptions){
                                mapViewer();
                                HPViewer();}

                            condition = false; //WE TURN CONDITION OFF TO BREAK THE LOOP
                        }
                    }
                    //FOR EVERY STEP ATTACKERS.
                    else if ((entitiy.name.equals(a[0])) && (condition) && (entitiy instanceof Goblin || entitiy instanceof Elf || entitiy instanceof Dwarf)) {//CONDITION WILL BE MARKED AS FALSE ONCE WE MOVE THE ENTITY
                        String[] b = a[1].split(";");

                        //EXCEPTIONS

                        //EXCEPTIONS
                        if (b.length / 2 != entitiy.maxMove) {
                            writer.write("Error :  Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                        }
                        else {
                            int elfCounter = 0;
                            boolean exceptions = false;
                            for (int z = 0; z < b.length; z = z + 2) {
                                //GET THE DIRECTION OF MOVE.
                                int xM = Integer.parseInt(b[z]);
                                int yM = Integer.parseInt(b[z + 1]);
                                //ADD MOVEMENT TO THE INITIAL COORDINATES
                                x = x + xM;
                                y = y + yM;
                                if ((x < 0) || (y < 0) || (x >= map.length) || (y >= map.length)) {
                                    writer.write("Error :  Game board boundaries are exceeded. Input line ignored \n\n");
                                    exceptions=true;
                                    if (z!=0){ //if it is not the first move sequence print the map
                                    mapViewer();
                                    }
                                }
                                else if (condition && !exceptions){
                                    condition =mover(entitiy, x, y);
                                    elfCounter++;
                                    //FOR ELF SPECIAL RANGED ATTACK
                                    if (entitiy instanceof Elf && elfCounter == 4 && condition) {
                                        elfRanged(entitiy);
                                    } else if (condition){
                                        attacker(entitiy);
                                    }
                                }

                            }
                            if (!exceptions){
                                mapViewer();
                                HPViewer();}
                            condition = false; //WE TURN CONDITION OFF TO BREAK THE LOOP
                        }
                    }

                }

            }
            //IF ANYONE WON, WRITE IT TO OUPUT
            if (CalList.size() == 0) {
                writer.write("\nGame Finished\n" + "Zorde Wins");
            } else if (ZorList.size() == 0) {
                writer.write("\nGame Finished\n" + "Calliance Wins");
            }
        }

        writer.flush();
        writer.close();
    }
    //this method helps us to calculate defenders' HP after the attack and removes if they died
    public static void attacker(Entitiy attacker) {
        //GET CURRENT POSITION OF THE ENTITY THAT COMMANDED TO ATTACK

        ArrayList<Entitiy> zordeInRange= range(attacker).get(0);
        ArrayList<Entitiy> callianceInRange= range(attacker).get(1);
        ArrayList<Entitiy> graveyard= new ArrayList<>();


        int dmg = attacker.AP;
        //CHECK IF THEY ARE OPPOSITE ARMIES.

        if (CalList.contains(attacker)) {
            for (Entitiy defender : zordeInRange) {
                defender.HP = defender.HP - dmg;
                //CHECK IF THEY DIED;
                if (defender.HP <= 0) {
                    //IF DIED REMOVE IT FROM EVERY LIST THAT CONTAINS IT
                    graveyard.add(defender);
                    //CHANGE IT'S PLACE WITH PLACEHOLDER ENTITY
                    int entityX = defender.pos.get(0);
                    int entityY = defender.pos.get(1);
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(entityX);
                    coordinates.add(entityY);
                    map[entityY][entityX] = new Entitiy("  ", coordinates);
                    map[entityY][entityX].pos = coordinates;
                }
            }
        }
        if (ZorList.contains(attacker)) {
            for (Entitiy defender : callianceInRange) {
                defender.HP = defender.HP - dmg;
                //CHECK IF THEY DIED;
                if (defender.HP <= 0) {
                    //IF DIED REMOVE IT FROM EVERY LIST THAT CONTAINS IT
                    graveyard.add(defender);
                    //CHANGE IT'S PLACE WITH PLACEHOLDER ENTITY
                    int entityX = defender.pos.get(0);
                    int entityY = defender.pos.get(1);
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(entityX);
                    coordinates.add(entityY);
                    map[entityY][entityX] = new Entitiy("  ", coordinates);
                }
            }
        }
        //discard the dead
        for (Entitiy dead:graveyard){
            if (CalList.contains(dead)){
                CalList.remove(dead);
                callianceInRange.remove(dead);
            }
            else if (ZorList.contains(dead)){
                ZorList.remove(dead);
                zordeInRange.remove(dead);
            }
        }
    }
    //this method calculates nearby enemies and allies
    public static ArrayList<ArrayList<Entitiy>> range(Entitiy entitiy){
        int x = entitiy.pos.get(0);
        int y = entitiy.pos.get(1);
        ArrayList<Entitiy> entitiesInRange = new ArrayList<>();
        //ADD ALL NEARBY ENTITIES TO ARRAY LIST and MAKE SURE THEY ARE IN RANGE OF THE MAP
        for (int t = -1; t < 2; t++) {
            for (int u = -1; u < 2; u++) {
                if ((t + x) < map.length && u + y < map.length && 0 <= (t + x) && 0 <= (u + y)) {
                    entitiesInRange.add(map[u + y][t + x]);
                }
            }
        }
        //SEPARATE THEM TO THEIR RESPECTIVE FACTIONS' ARRAY LIST
        ArrayList<Entitiy> zordeInRange = new ArrayList<>();
        ArrayList<Entitiy> callianceInRange = new ArrayList<>();
        for (Entitiy u : entitiesInRange) {
            if (CalList.contains(u)) {
                callianceInRange.add(u);
            }
        }
        for (Entitiy u : entitiesInRange) {
            if (ZorList.contains(u)) {
                zordeInRange.add(u);
            }
        }
        ArrayList<ArrayList<Entitiy>>listOfLists =new ArrayList<>();
        listOfLists.add(zordeInRange);
        listOfLists.add(callianceInRange);
        return listOfLists;
    }
    //this method helps elves to do their ranged attack.
    public static void elfRanged(Entitiy attacker){
        int x = attacker.pos.get(0);
        int y = attacker.pos.get(1);
        ArrayList<Entitiy> entitiesInRange = new ArrayList<>();
        //ADD ALL NEARBY ENTITIES TO ARRAY LIST and MAKE SURE THEY ARE IN RANGE OF THE MAP
        for (int t = -2; t < 3; t++) {
            for (int u = -2; u < 3; u++) {
                if ((t + x) < map.length && u + y < map.length && 0 <= (t + x) && 0 <= (u + y)) {
                    entitiesInRange.add(map[u + y][t + x]);
                }
            }
        }
        //SEPARATE THEM TO THEIR RESPECTIVE FACTIONS' ARRAY LIST
        ArrayList<Entitiy> zordeInRange = new ArrayList<>();
        for (Entitiy u : entitiesInRange) {
            if (ZorList.contains(u)) {
                zordeInRange.add(u);
            }
        }
        ArrayList<Entitiy> graveyard = new ArrayList<>();
        if (CalList.contains(attacker)) {
            for (Entitiy defender : zordeInRange) {
                defender.HP = defender.HP - ((Elf) attacker).ranged;
                //CHECK IF THEY DIED;
                if (defender.HP <= 0) {
                    //IF DIED REMOVE IT FROM EVERY LIST THAT CONTAINS IT
                    graveyard.add(defender);

                    //CHANGE IT'S PLACE WITH PLACEHOLDER ENTITY
                    int entityX = defender.pos.get(0);
                    int entityY = defender.pos.get(1);
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(entityX);
                    coordinates.add(entityY);
                    map[entityY][entityX] = new Entitiy("  ", coordinates);
                    map[entityY][entityX].pos = coordinates;
                }
            }
        }
        for (Entitiy dead:graveyard){
            if (ZorList.contains(dead)){
                ZorList.remove(dead);
                zordeInRange.remove(dead);
            }
        }
    }
    //this method chekcs if the game ended or not
    public static boolean winChecker() throws IOException {
        if (CalList.size()==0){
            return true;
        }
        else if (ZorList.size()==0){
            return true;
        }
        return false;
    }
    //this method calculates fight to death mechanic.
    public static void brawl(Entitiy defender, Entitiy attacker) throws CloneNotSupportedException {
        defender.HP= defender.HP-attacker.AP; //ATTACKER SINGLE ATTACKS
        if (defender.HP <= 0) { //IF DIED REMOVE IT
            int x= defender.pos.get(0);
            int y=defender.pos.get(1);
            remover(defender);
            mover(attacker,x,y); //move attacker to empty cell
        }
        else {
            if (attacker.HP > defender.HP) {//IF ATTACKERS HP IS MORE THAN DEFENDERS
                attacker.HP = attacker.HP - defender.HP;
                defender.HP = 0;//we kill it
                int x = defender.pos.get(0);
                int y = defender.pos.get(1);
                remover(defender); //remove it
                mover(attacker, x, y); //move attacker to empty cell
            } else if (attacker.HP < defender.HP) {//IF DEFENDERS HP IS MORE THAN ATTACKERS
                defender.HP = defender.HP - attacker.HP;
                attacker.HP = 0;//we kill it
                remover(attacker); //remove it
            } else if (attacker.HP == defender.HP) {//IF EQUAL KILL BOTH
                remover(attacker);
                remover(defender);
            }
        }

    }
    //this method helps us to remove dead armies from our faction lists and map.
    public static void remover(Entitiy defender){
        if (CalList.contains(defender)){
            CalList.remove(defender);
            //CHANGE IT'S PLACE WITH PLACEHOLDER ENTITY
            int entityX = defender.pos.get(0);
            int entityY = defender.pos.get(1);
            ArrayList<Integer> coordinates = new ArrayList<>();
            coordinates.add(entityX);
            coordinates.add(entityY);
            map[entityY][entityX] = new Entitiy("  ", coordinates);
            map[entityY][entityX].pos = coordinates;
        }
        else if (ZorList.remove(defender)){
            ZorList.remove(defender);
            //CHANGE IT'S PLACE WITH PLACEHOLDER ENTITY
            int entityX = defender.pos.get(0);
            int entityY = defender.pos.get(1);
            ArrayList<Integer> coordinates = new ArrayList<>();
            coordinates.add(entityX);
            coordinates.add(entityY);
            map[entityY][entityX] = new Entitiy("  ", coordinates);
            map[entityY][entityX].pos = coordinates;
        }
    }
}
