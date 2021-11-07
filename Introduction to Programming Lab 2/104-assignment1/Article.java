
public class Article {
    private String paperid;
    private String name;
    private String publisherName;
    private String publishYear;

    public Article(String id, String n, String pn, String py) { //constructor for Article class
        paperid = id;
        name = n;
        publisherName = pn;
        publishYear = py;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s",
                paperid, name, publisherName, publishYear);
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
}
