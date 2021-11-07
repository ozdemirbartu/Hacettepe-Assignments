
public class Director extends Artist {
    String agency;

    public Director(long ID, String name, String surname, String country, String agency) {
        super(ID, name, surname, country);
        this.agency = agency;
    }
}
