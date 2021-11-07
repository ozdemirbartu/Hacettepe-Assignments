
import java.util.List;
class StuntPerformer extends Artist{
    List<String> rActorIDList;
    String height;
    public StuntPerformer(long ID, String name, String surname, String country,String height, List<String> rActorIDList) {
        super(ID, name, surname, country);
        this.height = height;
        this.rActorIDList = rActorIDList;
    }
}