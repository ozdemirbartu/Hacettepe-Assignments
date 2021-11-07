
class Actor extends Artist{
    String height;
    public Actor(long ID, String name, String surname, String country, String height) {
        super(ID, name, surname, country);
        this.height = height;
    }
}