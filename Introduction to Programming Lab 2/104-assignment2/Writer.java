
public class Writer extends Artist {
    String style;

    public Writer(long ID, String name, String surname, String country, String style) {
        super(ID, name, surname, country);
        this.style = style;
    }
}
