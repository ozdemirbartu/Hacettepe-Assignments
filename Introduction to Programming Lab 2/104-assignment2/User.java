
import java.util.LinkedHashMap;

public class User extends Person {
    public LinkedHashMap<Long, Float> userRatingMap;//FilmID'si ile rating'i mapliyor
    public User(long ID, String name, String surname, String country,LinkedHashMap<Long, Float> userRatingMap) {
        super(ID, name, surname, country);
        this.userRatingMap=userRatingMap;
    }


}
