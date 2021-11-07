
import java.io.*;
import java.util.*;
import static java.util.Collections.reverseOrder;
//filmratingleri hesaplanırken "." yerine "," koyman lazım string'e çevirip replaceAll(".",",") yapılabilir.
public class Command {
    public static ArrayList<Artist> artists = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Film> films = new ArrayList<>();
    //readFilm and readPerson methods are reading input files and creating objects according to them
    public static void readPerson(String peopleFileName) throws FileNotFoundException { // method that reads data from peopleFileName(takes file name as an argument file and creates objects of class
        Scanner peopleInput = new Scanner(new File(peopleFileName)); //we create a scanner named: peopleInput
        peopleInput.useDelimiter("\n"); //to differentiate each element by using newline delimiter for each new line
        while (peopleInput.hasNext()) {
            String[] a = peopleInput.next().trim().split("\t");
            if (a[0].startsWith("Actor")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                String height = a[5];
                Actor newActor = new Actor(id, name, surname, country, height);
                artists.add(newActor);//we add this very new object to our object arraylist
            }
            if (a[0].startsWith("ChildActor")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                String age = a[5];
                ChildActor newActor = new ChildActor(id, name, surname, country, age);
                artists.add(newActor);//we add this very new object to our object arraylist
            }

            if (a[0].startsWith("StuntPerformer")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                String height = a[5];
                List<String> rActorIDList = Arrays.asList(a[6].split(","));
                StuntPerformer newActor = new StuntPerformer(id, name, surname, country, height, rActorIDList);
                artists.add(newActor);//we add this very new object to our object arraylist
            }
            if (a[0].startsWith("Writer")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                String style = a[5];
                Writer newActor = new Writer(id, name, surname, country, style);
                artists.add(newActor);//we add this very new object to our object arraylist
            }
            if (a[0].startsWith("Director")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                String agency = a[5];
                Director newActor = new Director(id, name, surname, country, agency);
                artists.add(newActor);//we add this very new object to our object arraylist
            }
            if (a[0].startsWith("User")) {
                long id = Long.valueOf(a[1]); //we assign the arguments in order
                String name = a[2];
                String surname = a[3];
                String country = a[4];
                LinkedHashMap<Long, Float> userRatingMap = new LinkedHashMap<>();
                User newUser = new User(id, name, surname, country, userRatingMap);
                users.add(newUser);//we add this very new object to our object arraylist
            }
        }
    }

    public static void readFilm(String filmFileName) throws Exception { // method that reads data from filmFileName(takes file name as an argument file and creates objects of class
        Scanner filmInput = new Scanner(new File(filmFileName)); //we create a scanner named: filmInput
        filmInput.useDelimiter("\n"); //to differentiate each element by using space delimiter for each new line
        while (filmInput.hasNext()) {
            String[] a = filmInput.next().trim().split("\t");
            if (a[0].startsWith("FeatureFilm")) {
                long FilmID = Long.valueOf(a[1]); //we assign the arguments in order
                String filmTitle = a[2];
                String language = a[3];
                List<String> directorList = Arrays.asList(a[4].split(","));
                String runTime = a[5];
                String country = a[6];
                List<String> castList = Arrays.asList(a[7].split(","));
                List<String> genreList = Arrays.asList(a[8].split(","));
                String releaseDate = a[9].substring(6, 10);
                List<String> writerList = Arrays.asList(a[10].split(","));
                String budget = a[11];
                LinkedHashMap<Long, Float> filmRatingMap = new LinkedHashMap<>(); //this hashmap holds values for user ratings to this very specific film
                Film newFilm = new FeatureFilm(FilmID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap, genreList,
                        releaseDate, writerList, budget);
                films.add(newFilm);//we add this very new object to our object arraylist
            }
            if ((a[0].startsWith("ShortFilm"))) {
                if (Long.valueOf(a[5]) <= 40) {
                    long FilmID = Long.valueOf(a[1]); //we assign the arguments in order
                    String filmTitle = a[2];
                    String language = a[3];
                    List<String> directorList = Arrays.asList(a[4].split(","));
                    String runTime = a[5];
                    String country = a[6];
                    List<String> castList = Arrays.asList(a[7].split(","));
                    List<String> genreList = Arrays.asList(a[8].split(","));
                    String releaseDate = a[9].substring(6, 10);
                    List<String> writerList = Arrays.asList(a[10].split(","));
                    LinkedHashMap<Long, Float> filmRatingMap = new LinkedHashMap<>();//this hashmap holds values for user ratings to this very specific film
                    Film newFilm = new ShortFilm(FilmID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap, genreList,
                            releaseDate, writerList);
                    films.add(newFilm);//we add this very new object to our object arraylist

                } else {
                    System.out.println((a[1] + " can't be added length is too long"));
                }
            }
            if (a[0].startsWith("Documentary")) {
                long FilmID = Long.valueOf(a[1]); //we assign the arguments in order
                String filmTitle = a[2];
                String language = a[3];
                List<String> directorList = Arrays.asList(a[4].split(","));
                String runTime = a[5];
                String country = a[6];
                List<String> castList = Arrays.asList(a[7].split(","));
                String releaseDate = a[8].substring(6, 10);
                LinkedHashMap<Long, Float> filmRatingMap = new LinkedHashMap<>();//this hashmap holds values for user ratings to this very specific film
                Film newFilm = new Documentary(FilmID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap,
                        releaseDate);
                films.add(newFilm);//we add this very new object to our object arraylist
            }
            if (a[0].startsWith("TVSeries")) {
                long FilmID = Long.valueOf(a[1]); //we assign the arguments in order
                String filmTitle = a[2];
                String language = a[3];
                List<String> directorList = Arrays.asList(a[4].split(","));
                String runTime = a[5];
                String country = a[6];
                List<String> castList = Arrays.asList(a[7].split(","));
                List<String> genreList = Arrays.asList(a[8].split(","));
                List<String> writerList = Arrays.asList(a[9].split(","));
                String startDate = a[10].substring(6, 10);
                String endDate = a[11].substring(6, 10);
                String seasons = a[12];
                String episodes = a[13];// BURALARDA Bİ SORUN VAR YAW DÜZGÜN PRİNTLENMİYOOOO
                LinkedHashMap<Long, Float> filmRatingMap = new LinkedHashMap<>();
                Film newFilm = new TVSeries(FilmID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap, genreList,
                        writerList, startDate, endDate, seasons, episodes);
                films.add(newFilm);
            }
        }
    }
    //this method reads command input and calls other methods according to that data
    public static void readCommands(String pplFileName, String filmFileName, String commandsFileName) throws Exception {
        readPerson(pplFileName);
        readFilm(filmFileName);
        Scanner commandInput = new Scanner(new File(commandsFileName)); //we create a scanner named: commandInput
        commandInput.useDelimiter("\n"); //to differentiate each element by using newline delimiter for each new line
        replaceAll();

        while (commandInput.hasNext()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
            String b = commandInput.next();
            writer.write(b + "\n");
            writer.flush();
            writer.close();
            String[] a = b.split("\t");
            if (a[0].startsWith("RATE")) {
                long userID = Long.valueOf(a[1]);
                long filmID = Long.valueOf(a[2]);
                float ratingPoint = Float.valueOf(a[3]);
                rate(userID, filmID, ratingPoint);
            }
            if (a[0].startsWith("REMOVE")) {
                long userID = Long.valueOf(a[2]);
                float filmIDD = Float.valueOf(a[3]);
                long filmID = (long) filmIDD;
                removeRate(userID, filmID);
            }
            if ((a[0].startsWith("EDIT")) && a[1].startsWith("RATE")) {
                long userID = Long.valueOf(a[2]);
                long filmID = Long.valueOf(a[3]);
                float ratingPoint = Float.valueOf(a[4]);
                editRate(userID, filmID, ratingPoint);
            }
            if (a[0].startsWith("VIEWFILM")) {
                long ID = Long.valueOf(a[1].substring(0, a[1].length() - 1));
                viewFilm(ID);
            }
            if (a[0].startsWith("ADD")) {
                addFeatureFilm(a);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("USER")) {
                long userID = Long.valueOf(a[2]);
                listUser(userID);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("FILM")&&a[2].startsWith("SERIES")) {
                listSeries();
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("FILMS")&&a[3].startsWith("COUNTRY")) {
                String country = a[4].trim();
                listFilmCountry(country);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("ARTISTS")) {
                String country = a[3].trim();
                listArtistCountry(country);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("FEATUREFILMS")&&a[2].startsWith("BEFORE")) {
                String date = a[3].trim();
                listFFilmBefore(date);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("FEATUREFILMS")&&a[2].startsWith("AFTER")) {
                String date = a[3].trim();
                listFFilmAfter(date);
            }
            if ((a[0].startsWith("LIST")) && a[1].startsWith("FILMS")&&a[3].startsWith("RATE")) {
                listFilmsRate();
            }
        }

    }
    //This method allows user to rate films
    public static void rate(long userID, long filmID, float ratingScore) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = false;
        for (User user : users) {
            if (userID == user.ID) {
                z = true;
                for (Film film : films) {
                    if (filmID == film.FilmID) {
                        if (!(user.userRatingMap.containsKey(filmID))) {
                            user.userRatingMap.put(filmID, ratingScore);
                            film.filmRatingMap.put(userID, ratingScore);
                            writer.write("\nFilm rated successfully\n" + "Film type: " + film.getClass().getSimpleName() + "\n" + "Film title: " + film.filmTitle);
                            writer.write("\n");
                        } else if ((user.userRatingMap.containsKey(filmID))) {
                            writer.write("\nThis film was earlier rated" + "\n");

                        }
                    }
                }
            }
        }
        if (!z) {
            writer.write("\nCommand Failed\n" + "User ID: " + userID + "\nFilm ID: " + filmID);
            writer.write("\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");

        writer.flush();
        writer.close();
    }
    //This method allows user to remove ratings from their ratinglist and film's ratinglist
    public static void removeRate(long userID, long filmID) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = true;
        for (User user : users) {
            if (userID == user.ID) {
                for (Film film : films) {
                    if ((filmID == film.FilmID) && (user.userRatingMap.containsKey(filmID)) && (film.filmRatingMap.containsKey(userID))) {
                        z = false;
                        user.userRatingMap.remove(filmID);
                        film.filmRatingMap.remove(userID);
                        writer.write("\nYour film rating was removed successfully" + "\n" + "Film title : " + film.filmTitle + "\n");
                        break;

                    }
                }

            }
        }
        if (z) {
            writer.write("\nCommand Failed\n");
            writer.write("User ID: " + userID + "\n" + "Film ID: " + filmID + "\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method allows user to edit ratings from their ratinglist and film's ratinglist
    public static void editRate(long userID, long filmID, float ratingScore) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = true;
        for (User user : users) {
            if (userID == user.ID) {
                for (Film film : films) {
                    if ((filmID == film.FilmID) && (user.userRatingMap.containsKey(filmID)) && (film.filmRatingMap.containsKey(userID))) {
                        z = false;
                        user.userRatingMap.put(filmID, ratingScore);
                        film.filmRatingMap.remove(userID, ratingScore);
                        writer.write("\nNew ratings done successfully" + "\n" + "Film title : " + film.filmTitle + "\n" + "Your rating: " + ((int) ratingScore)+"\n");
                        break;

                    }
                }

            }
        }
        if (z) {
            writer.write("\nCommand Failed\n");
            writer.write("User ID: " + userID + "\n" + "Film ID: " + filmID + "\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method is crucial because it assigns actors to the films that they participated
    public static void replaceAll() {
        for (Film film : films) {
            for (Artist artist : artists) {
                for (int i = 0; i < film.directorList.size(); i++) {
                    if (film.directorList.get(i).equals(String.valueOf(artist.ID))) {
                        film.directorList.set(i, artist.name + " " + artist.surname);
                    }
                }
                for (int i = 0; i < film.castList.size(); i++) {
                    if (film.castList.get(i).equals(String.valueOf(artist.ID))) {
                        film.castList.set(i, artist.name + " " + artist.surname);
                    }
                }
                if (film instanceof FeatureFilm) {
                    for (int i = 0; i < ((FeatureFilm) film).writerList.size(); i++) {
                        if (((FeatureFilm) film).writerList.get(i).equals(String.valueOf(artist.ID))) {
                            ((FeatureFilm) film).writerList.set(i, artist.name + " " + artist.surname);
                        }
                    }
                }
                if (film instanceof ShortFilm) {
                    for (int i = 0; i < ((ShortFilm) film).writerList.size(); i++) {
                        if (((ShortFilm) film).writerList.get(i).substring(0, 3).equals(String.valueOf(artist.ID))) {
                            ((ShortFilm) film).writerList.set(i, artist.name + " " + artist.surname);
                        }
                    }
                }
                if (film instanceof TVSeries) {
                    for (int i = 0; i < ((TVSeries) film).writerList.size(); i++) {
                        if (((TVSeries) film).writerList.get(i).equals(String.valueOf(artist.ID))) {
                            ((TVSeries) film).writerList.set(i, artist.name + " " + artist.surname);
                        }
                    }
                }
            }
        }
    }
    //This method will call override toString methods from their classes
    public static void viewFilm(long ID) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = false;
        for (Film i : films) {
            if (i.FilmID == ID) {
                writer.write("\n");
                writer.write(i.toString());
                writer.write("\n");
                z = true;
            }
        }
        if (!z) {
            writer.write("\nCommand Failed\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method allows us to add new feature film
    public static void addFeatureFilm(String[] a) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        long FilmID = Long.valueOf(a[2]); //we assign the arguments in order
        String filmTitle = a[3];
        String language = a[4];
        List<String> directorList = Arrays.asList(a[5].split(","));
        String runTime = a[6];
        String country = a[7];
        List<String> castList = Arrays.asList(a[8].split(","));
        List<String> genreList = Arrays.asList(a[9].split(","));
        String releaseDate = a[10].substring(6, 10);
        List<String> writerList = Arrays.asList(a[11].split(","));
        String budget = a[12];
        LinkedHashMap<Long, Float> filmRatingMap = new LinkedHashMap<>();
        boolean z = true;
        if (z) {
            for (Film film : films) {
                if (FilmID == film.FilmID) {
                    writer.write("\nCommand Failed\n");
                    writer.write("Film ID: " + FilmID + "\n" + "Film title: " + filmTitle + "\n");
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            ArrayList<Long> artistIDS = new ArrayList<>();
            for (Artist artist : artists) {
                artistIDS.add(artist.ID);
            }
            for (String i : castList) {
                if (!(artistIDS.contains(Long.valueOf(i)))) {
                    writer.write("\nCommand Failed\n");
                    writer.write("Film ID: " + FilmID + "\n" + "Film title: " + filmTitle + "\n");
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            Film newFilm = new FeatureFilm(FilmID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap, genreList,
                    releaseDate, writerList, budget);
            films.add(newFilm);
            replaceAll();
            writer.write("\nFeatureFilm added successfully\n" + "Film ID: " + FilmID + "\n" + "Film title: " + filmTitle + "\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();

    }
    //This method helps us to see what films user voted and their ratings
    public static void listUser(long userID) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = true;
        for (User user:users){
            if (user.ID==userID){

                Set<Long> b = user.userRatingMap.keySet();
                if (b.size()==0){
                    writer.write("\nCommand Failed\n");
                    writer.write("User ID: "+userID);

                }
                List<Long> keyList = new ArrayList<>(b);
                z = false;
                for (int i =0;i<user.userRatingMap.keySet().size();i++){
                    for (Film film:films){
                        if (film.FilmID==keyList.get(i)){
                            writer.write("\n"+film.filmTitle+": "+
                                    String.valueOf(((double) user.userRatingMap.get(film.FilmID))).substring(0,String.valueOf(user.userRatingMap.get(film.FilmID)).length()-2));
                        }
                    }
                }
            }
        }
        if (z){
            writer.write("\nCommand Failed\n");
            writer.write("User ID: "+userID);
        }
        writer.write("\n\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();

    }
    //This method lists all TVSeries
    public static void listSeries() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = true;
        for (Film film:films){
            if (film.getClass().getSimpleName().equals("TVSeries")){
                z = false;
                writer.write("\n"+film.filmTitle+" ("+ ((TVSeries) film).startDate+"-"+((TVSeries) film).endDate+")");
                writer.write("\n");
                writer.write(((TVSeries) film).seasons+" seasons "+"and "+ ((TVSeries) film).episodes+" episodes");
                writer.write("\n");


            }
        }
        if (z){
            writer.write("\nNo result\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method lists all films according to input country
    public static void listFilmCountry(String country) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        boolean z = true;

        for (Film film:films){
            if (film.country.equals(country)){
                z = false;
                writer.write("\nFilm title: "+film.filmTitle+"\n"+film.runTime+" min\n"+"Language: "+film.language+"\n");

            }
        }
        if (z){
            writer.write("\nNo result\n");
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method lists all artists according to input country
    public static void listArtistCountry(String country) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        ArrayList<Artist> a=new ArrayList<>();
        for (Artist artist: artists){
            if (artist.country.equals(country)){
                a.add(artist);
            }
        }
        writer.write("\nDirectors:\n");
        for (Artist b:a){
            if (b.getClass().getSimpleName().trim().equals("Director")){
                writer.write(b.name +" "+b.surname+" "+ ((Director) b).agency+"\n");
            }
        }
        writer.write("\nWriters:\n");
        for (Artist b:a){
            if (b.getClass().getSimpleName().trim().equals("Writer")){
                writer.write(b.name +" "+b.surname+" "+ ((Writer) b).style+"\n");
            }
        }
        writer.write("\nActors:\n");
        for (Artist b:a){
            if (b.getClass().getSimpleName().trim().equals("Actor")){
                writer.write(b.name +" "+b.surname+" "+ ((Actor) b).height.trim()+" cm"+"\n");
            }
        }
        writer.write("\nChildActors:\n");
        for (Artist b:a){
            if (b.getClass().getSimpleName().trim().equals("ChildActor")){
                writer.write(b.name +" "+b.surname+" "+ ((ChildActor) b).age+"\n");
            }
        }
        writer.write("\nStuntPerformers:\n");
        for (Artist b:a){
            if (b.getClass().getSimpleName().trim().equals("StuntPerformer")){
                writer.write(b.name +" "+b.surname+" "+ ((StuntPerformer) b).height+" cm"+"\n");
            }
        }
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method lists all feature films before according to input date
    public static void listFFilmBefore(String date) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        int intdate = Integer.valueOf(date);
        boolean z = true;
        for (Film i : films){

            if ((i.getClass().getSimpleName().trim().equals("FeatureFilm"))&& Integer.valueOf(((FeatureFilm) i).releaseDate)<intdate){
                writer.write("\nFilm title: "+i.filmTitle+" ("+((FeatureFilm) i).releaseDate+")\n"+i.runTime+" min"+"\nLanguage: "+i.language+"\n");
                z = false;


            }
        }
        if (z){writer.write("\nNo result\n");}
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method lists all feature films after according to input date
    public static void listFFilmAfter(String date) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        int intdate = Integer.valueOf(date);
        boolean z = true;
        for (Film i : films){

            if ((i.getClass().getSimpleName().trim().equals("FeatureFilm"))&& Integer.valueOf(((FeatureFilm) i).releaseDate)>=intdate){
                writer.write("\nFilm title: "+i.filmTitle+" ("+((FeatureFilm) i).releaseDate+")\n"+i.runTime+" min"+"\nLanguage: "+i.language+"\n");
                z = false;


            }
        }
        if (z){writer.write("\nNo result\n");}
        writer.write("\n");
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
    //This method lists all films before according to their rates in descending order
    public static void listFilmsRate() throws  IOException{
        int z = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        if (z==0){
            LinkedHashMap<Long,Float> ratesHashMap = new LinkedHashMap<>();
            ArrayList<Long> idStore = new ArrayList<>();
            for (Film i:films){
                if((i.getClass().getSimpleName().trim().equals("FeatureFilm"))){
                    float av = i.ratingCalculator()[0];
                    ratesHashMap.put(i.FilmID,av);
                }
            }
            ratesHashMap.entrySet().stream()
                    .sorted(reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(entry-> idStore.add(entry.getKey()));
            writer.write("FeatureFilm:\n");
            for (Long id: idStore) {
                for (Film film : films) {
                    if (film.FilmID == id) {
                        float av = film.ratingCalculator()[0];
                        float count = film.ratingCalculator()[1];
                        if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0")) && String.valueOf(String.format("%,.1f", ((double) av)).charAt(0)).equals("0")) {
                            writer.write(film.filmTitle + " (" + ((FeatureFilm) film).releaseDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceFirst("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0"))) {
                            writer.write(film.filmTitle + " (" + ((FeatureFilm) film).releaseDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceAll("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else {
                            writer.write(film.filmTitle + " (" + ((FeatureFilm) film).releaseDate + ") Ratings: " + String.format("%,.1f",((double) av)).replace(".",",") + "/10 from " + (int) count + " users" + "\n");
                        }
                    }
                }
            }
            writer.write("\n");
        }
        z++;
        if (z==1){
            LinkedHashMap<Long,Float> ratesHashMap = new LinkedHashMap<>();
            ArrayList<Long> idStore = new ArrayList<>();
            for (Film i:films){
                if((i.getClass().getSimpleName().trim().equals("ShortFilm"))){
                    float av = i.ratingCalculator()[0];
                    ratesHashMap.put(i.FilmID,av);
                }
            }
            ratesHashMap.entrySet().stream()
                    .sorted(reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(entry-> idStore.add(entry.getKey()));
            writer.write("ShortFilm:\n");
            for (Long id: idStore) {
                for (Film film : films) {
                    if (film.FilmID == id) {
                        float av = film.ratingCalculator()[0];
                        float count = film.ratingCalculator()[1];
                        if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0")) && String.valueOf(String.format("%,.1f", ((double) av)).charAt(0)).equals("0")) {
                            writer.write(film.filmTitle + " (" + ((ShortFilm) film).releaseDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceFirst("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0"))) {
                            writer.write(film.filmTitle + " (" + ((ShortFilm) film).releaseDate  + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceAll("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else {
                            writer.write(film.filmTitle + " (" + ((ShortFilm) film).releaseDate + ") Ratings: " + String.format("%,.1f",((double) av)).replace(".",",") + "/10 from " + (int) count + " users" + "\n");
                        }
                    }
                }
            }
            writer.write("\n");
        }
        z++;
        if (z==2){
            LinkedHashMap<Long,Float> ratesHashMap = new LinkedHashMap<>();
            ArrayList<Long> idStore = new ArrayList<>();
            for (Film i:films){
                if((i.getClass().getSimpleName().trim().equals("Documentary"))){
                    float av = i.ratingCalculator()[0];
                    ratesHashMap.put(i.FilmID,av);
                }
            }
            ratesHashMap.entrySet().stream()
                    .sorted(reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(entry-> idStore.add(entry.getKey()));
            writer.write("Documentary:\n");
            for (Long id: idStore) {
                for (Film film : films) {
                    if (film.FilmID == id) {
                        float av = film.ratingCalculator()[0];
                        float count = film.ratingCalculator()[1];
                        if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0")) && String.valueOf(String.format("%,.1f", ((double) av)).charAt(0)).equals("0")) {
                            writer.write(film.filmTitle + " (" + ((Documentary) film).releaseDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceFirst("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0"))) {
                            writer.write(film.filmTitle + " (" + ((Documentary) film).releaseDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceAll("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else {
                            writer.write(film.filmTitle + " (" + ((Documentary) film).releaseDate + ") Ratings: " + String.format("%,.1f",((double) av)).replace(".",",") + "/10 from " + (int) count + " users" + "\n");
                        }
                    }
                }
            }
            writer.write("\n");
        }
        z++;
        if (z==3){
            LinkedHashMap<Long,Float> ratesHashMap = new LinkedHashMap<>();
            ArrayList<Long> idStore = new ArrayList<>();
            for (Film i:films){
                if((i.getClass().getSimpleName().trim().equals("TVSeries"))){
                    float av = i.ratingCalculator()[0];
                    ratesHashMap.put(i.FilmID,av);
                }
            }
            ratesHashMap.entrySet().stream()
                    .sorted(reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(entry-> idStore.add(entry.getKey()));
            writer.write("TVSeries:\n");
            for (Long id: idStore) {
                for (Film film : films) {
                    if (film.FilmID == id) {
                        float av = film.ratingCalculator()[0];
                        float count = film.ratingCalculator()[1];
                        if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0")) && String.valueOf(String.format("%,.1f", ((double) av)).charAt(0)).equals("0")) {
                            writer.write(film.filmTitle + " (" + ((TVSeries) film).startDate+"-"+ ((TVSeries) film).endDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceFirst("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else if ((String.valueOf(String.format("%,.1f", ((double) av)).charAt(2)).equals("0"))) {
                            writer.write(film.filmTitle + " (" + ((TVSeries) film).startDate+"-"+ ((TVSeries) film).endDate + ") Ratings: " + String.format("%,.1f", ((double) av)).replaceAll("0", "").replaceAll("\\.", "") + "/10 from " + (int) count + " users" + "\n");
                        } else {
                            writer.write(film.filmTitle + " (" + ((TVSeries) film).startDate+"-"+ ((TVSeries) film).endDate + ") Ratings: " + String.format("%,.1f",((double) av)).replace(".",",") + "/10 from " + (int) count + " users" + "\n");
                        }
                    }
                }
            }
            writer.write("\n");
        }
        writer.write("-----------------------------------------------------------------------------------------------------");
        writer.write("\n");
        writer.flush();
        writer.close();
    }
}
