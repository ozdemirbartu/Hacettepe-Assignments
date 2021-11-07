
import java.util.Objects;

public class Author implements java.io.Serializable {
    private String authorid;
    private String name;
    private String university;
    private String department;
    private String email;
    private String article1;
    private String article2;
    private String article3;
    private String article4;
    private String article5;

    public Author(String authorid, String name, String university, String department, String email, String article1, String article2, String article3, String article4, String article5) {
        this.authorid = authorid;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.article1 = article1;
        this.article2 = article2;
        this.article3 = article3;
        this.article4 = article4;
        this.article5 = article5;
    }

    @Override
    public String toString() {
        return String.format("Author:%s\t%s\t%s\t%s\t%s%s%s%s%s%s\n", authorid==null?"":authorid, name==null?"":name, university==null?"":university,
                department==null?"":department, email==null?"":email, article1==null?"":"\n+"+article1, article2==null?"":"\n+"+article2, article3==null?"":"\n+"+article3,
                article4==null?"":"\n+"+article4,article5==null?"":"\n+"+article5);
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArticle1() {
        return article1;
    }

    public void setArticle1(String article1) {
        this.article1 = article1;
    }

    public String getArticle2() {
        return article2;
    }

    public void setArticle2(String article2) {
        this.article2 = article2;
    }

    public String getArticle3() {
        return article3;
    }

    public void setArticle3(String article3) {
        this.article3 = article3;
    }

    public String getArticle4() {
        return article4;
    }

    public void setArticle4(String article4) {
        this.article4 = article4;
    }

    public String getArticle5() {
        return article5;
    }

    public void setArticle5(String article5) {
        this.article5 = article5;
    }

}
