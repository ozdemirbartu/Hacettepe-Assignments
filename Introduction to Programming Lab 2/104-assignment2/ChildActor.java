
class ChildActor extends Artist{
    String age;
    public ChildActor(long ID, String name, String surname, String country, String age) {
        super(ID, name, surname, country);
        this.age = age;
    }
}
