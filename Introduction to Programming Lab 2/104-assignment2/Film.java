
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;


public class Film {
    public LinkedHashMap<Long, Float> filmRatingMap;//UserID'si ile rating'i mapliyor
    String filmTitle, language, runTime, country;
    long FilmID;
    List<String> castList;
    List<String> directorList;


    public Film(long ID, String filmTitle, String language, List<String> directorList, String runTime, String country, List<String> castList,LinkedHashMap<Long, Float> filmRatingMap) {

        this.FilmID = ID;
        this.filmTitle = filmTitle;
        this.language = language;
        this.runTime = runTime;
        this.country = country;
        this.directorList = directorList;
        this.castList = castList;
        this.filmRatingMap=filmRatingMap;

    }
    public float[] ratingCalculator(){ //This method calculates and returns number of voters and avarage of ratings.
        float sum=0;
        float average=0;
        int numberOfRatings = filmRatingMap.keySet().size();
        for (float f : filmRatingMap.values()) sum += f;
        if ((sum>=0) && numberOfRatings>0){
            average = sum/numberOfRatings;
        }
        float a[] ={average,numberOfRatings};
        return a;
    }
}

class FeatureFilm extends Film {

    List<String> genreList;
    List<String> writerList;
    String releaseDate;
    String budget;

    public FeatureFilm(long ID, String filmTitle, String language, List<String> directorList, String runTime, String country, List<String> castList,LinkedHashMap<Long, Float> filmRatingMap,
                       List<String> genreList, String releaseDate, List<String> writerList, String budget) {
        super(ID, filmTitle, language, directorList, runTime, country, castList,filmRatingMap);
        this.genreList = genreList;
        this.releaseDate = releaseDate;
        this.writerList = writerList;
        this.budget = budget;
    }
    @Override
    public String toString() { //This method is override to be printed or written in correct format
        float av = ratingCalculator()[0];
        float count = ratingCalculator()[1];
        if (av==0){
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nAwaiting for votes", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""));}
        else if (String.valueOf(String.format("%,.1f",((double) av)).charAt(2)).equals("0")){
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replaceAll("0","").replaceAll("\\.",""),(int)count);}

        else {
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""), String.format("%,.1f",((double) av)).replace(".",","),(int)count);}
        }
    }

class ShortFilm extends Film {
    List<String> genreList;
    List<String> writerList;
    String releaseDate;

    public ShortFilm(long ID, String filmTitle, String language, List<String> directorList, String runTime, String country, List<String> castList,LinkedHashMap<Long, Float> filmRatingMap,
                     List<String> genreList, String releaseDate, List<String> writerList) {
        super(ID, filmTitle, language, directorList, runTime, country, castList,filmRatingMap);
        this.genreList = genreList;
        this.releaseDate = releaseDate;
        this.writerList = writerList;
    }
    @Override
    public String toString() {//This method is override to be printed or written in correct format
        float av = ratingCalculator()[0];
        float count = ratingCalculator()[1];
        if (av==0){
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nAwaiting for votes", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""));}
        else if (String.valueOf(String.format("%,.1f",((double) av)).charAt(2)).equals("0")){
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replaceAll("0","").replaceAll("\\.",""),(int)count);}


        else {
            return String.format("%s (%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate, genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replace(".",","),(int)count);}

        }
    }


class Documentary extends Film {
    String releaseDate;

    public Documentary(Long ID, String filmTitle, String language, List<String> directorList, String runTime, String country, List<String> castList,LinkedHashMap<Long, Float> filmRatingMap, String releaseDate) {
        super(ID, filmTitle, language, directorList, runTime, country, castList,filmRatingMap);
        this.releaseDate = releaseDate;
    }
    @Override
    public String toString() {//This method is override to be printed or written in correct format
        float av = ratingCalculator()[0];
        float count = ratingCalculator()[1];
        if (av==0){
            return String.format("%s (%s)\nDirectors: %s\nStars: %s\nAwaiting for votes", filmTitle,releaseDate,
                    directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""));}
        else if (String.valueOf(String.format("%,.1f",((double) av)).charAt(2)).equals("0")){
            return String.format("%s (%s)\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate,
                    directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replaceAll("0","").replaceAll("\\.",""),(int)count);}
        else {
            return String.format("%s (%s)\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,releaseDate,
                    directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replace(".",","),(int)count);}
        }
    }

class TVSeries extends Film {
    String startDate;
    String endDate;
    String seasons;
    String episodes;
    List<String> genreList;
    List<String> writerList;

    public TVSeries(Long ID, String filmTitle, String language, List<String> directorList, String runTime, String country, List<String> castList,
                    LinkedHashMap<Long, Float> filmRatingMap, List<String> genreList, List<String> writerList, String startDate, String endDate, String seasons, String episodes) {
        super(ID, filmTitle, language, directorList, runTime, country, castList, filmRatingMap);
        this.genreList = genreList;
        this.writerList = writerList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
    }
    @Override
    public String toString() {//This method is override to be printed or written in correct format
        float av = ratingCalculator()[0];
        float count = ratingCalculator()[1];
        if (av==0){
            return String.format("%s (%s-%s)\n%s seasons, %s episodes\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nAwaiting for votes", filmTitle,startDate,endDate,seasons,episodes,
                    genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""));
        }
        else if (String.valueOf(String.format("%,.1f",((double) av)).charAt(2)).equals("0")){
            return String.format("%s (%s-%s)\n%s seasons, %s episodes\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,startDate,endDate,seasons,episodes,
                    genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replaceAll("0","").replaceAll("\\.",""),(int)count);
        }
        else {
            return String.format("%s (%s-%s)\n%s seasons, %s episodes\n%s\nWriters: %s\nDirectors: %s\nStars: %s\nRatings: %s/10 rating from %s users", filmTitle,startDate,endDate,seasons,episodes,
                    genreList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    writerList.toString().replaceAll("\\[", "").replaceAll("\\]",""), directorList.toString().replaceAll("\\[", "").replaceAll("\\]",""),
                    castList.toString().replaceAll("\\[", "").replaceAll("\\]",""),String.format("%,.1f",((double) av)).replace(".",","),(int)count);
        }
    }
    }



