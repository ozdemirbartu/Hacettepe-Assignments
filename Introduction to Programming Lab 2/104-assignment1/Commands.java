import java.io.*;
import java.util.*;

public class Commands {
    private static ArrayList<Article> articles = new ArrayList<>();
    private static ArrayList<Author> authors = new ArrayList<>();
    private static Set<Integer> articleindexlist = new HashSet<Integer>();

    public static void readCommands(String commandFileName, String authorFileName) throws IOException {

        //firstly with author.txt we gonna create our author objects
        Scanner authorInput = new Scanner(new File(authorFileName)); //we create a scanner named: authorInput
        authorInput.useDelimiter("AUTHOR "); //we differentiate each element by using delimiter for each new line
        while (authorInput.hasNext()) {

            String a = authorInput.next(); // we take each line
            String[] splitted = a.trim().split(" "); // make it string array, by defining it's elements using whitespace
            String[] authorElements = new String[10]; // we create empty array(sized 10) so that when we fill up this empty array with splitted ->
            //there will be "null" for left out indexes, later we'll use it while creating our Class objects for Author
            for (int i = 0; i < splitted.length; i++) { // this for loop, fills our empty array with text file's elements
                authorElements[i] = splitted[i];
            }
            //we assign the attributes in order
            String authorid = authorElements[0];
            String name = authorElements[1];
            String university = authorElements[2];
            String department = authorElements[3];
            String email = authorElements[4];
            String article1 = authorElements[5];
            String article2 = authorElements[6];
            String article3 = authorElements[7];
            String article4 = authorElements[8];
            String article5 = authorElements[9];

            Author newAuthor = new Author(authorid, name, university, department, email, article1, article2, article3, article4, article5); //we create a new object
            //we add our newly created object to our Authors ArrayList(authors)
            authors.add(newAuthor); //we add our newly created object to arraylist of objects

        }

        //this method(readCommands()) ,after creating author objects, reads command.txt file and execute other methods according to that
        Scanner commandInput = new Scanner(new File(commandFileName)); //we create a scanner named: commandInput
        while (commandInput.hasNext()) {
            String nextCommand = commandInput.nextLine();
            if (nextCommand.startsWith("read")) {
                String articleFileName = nextCommand.substring(5);//we substring articleFileName for article.txt
                read(articleFileName);
            } else if (nextCommand.equals("list")) {
                Commands.list();
            } else if (nextCommand.equals("completeAll")) {
                Commands.completeAll();
            } else if (nextCommand.startsWith("del")) {
                String authorId = nextCommand.substring(4);
                del(authorId);
            } else if (nextCommand.equals("sortedAll")) {
                sortedAll();
            }
        }

    }

    public static void read(String articleFileName) throws FileNotFoundException { // method that reads data from article.txt(takes file name as an argument file and creates objects of class Article for each article and also author.txt

        //this method reads article.txt according to readCommands method
        Scanner articleInput = new Scanner(new File(articleFileName)); //we create a scanner named: articleInput
        articleInput.useDelimiter(" |\n"); //to differentiate each element by using space delimiter for each new line
        while (articleInput.hasNext()) {
            articleInput.next().substring(1); //to get rid of first element of each line which is "ARTICLE"
            //we assign the attributes in order
            String paperid = articleInput.next();
            String name = articleInput.next();
            String publisherName = articleInput.next();
            String publishYear = articleInput.next();
            Article newArticle = new Article(paperid, name, publisherName, publishYear); //we create a new object
            //we add our newly created object to our Articles ArrayList(articles)
            articles.add(newArticle);
        }
    }

    public static void del(String authorid) throws IOException {

        int id = 0;
        for (Author author : authors) {
            if (author.getAuthorid().equals(authorid)) {
                id = authors.indexOf(author);
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.write("*****************************************del Successful****************************************\n\n");
        writer.flush();
        writer.close();
        authors.remove(id);

    }

    public static void list() throws IOException {

        // this method fills Author's article properties by fetching data from article.txt and writes the output to output.exe
        // we are doing nested for loop to check if our Author's article present in article.txt and if so get attributes of that specific article(paperid,name,publisherName,publishYear)
        for (Author author : authors) {
            for (Article article : articles) {
                if (article.getPaperid().equals(author.getArticle1())) {
                    author.setArticle1(String.valueOf(article));
                }
                if (article.getPaperid().equals(author.getArticle2())) {
                    author.setArticle2(String.valueOf(article));
                }
                if (article.getPaperid().equals(author.getArticle3())) {
                    author.setArticle3(String.valueOf(article));
                }
                if (article.getPaperid().equals(author.getArticle4())) {
                    author.setArticle4(String.valueOf(article));
                }
                if (article.getPaperid().equals(author.getArticle5())) {
                    author.setArticle5(String.valueOf(article));
                }
            }
        }

        //now we write updated Author's info to output.exe
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.write("----------------------------------------------List---------------------------------------------\n");
        for (Author author : authors) { // we iterate over our object array of authors
            writer.write(author.toString()); //we defined Over-rided version of toString() in our Author Class so that we can write it in format we want
            writer.write("\n"); //adding new line after each author
        }
        writer.write("----------------------------------------------End----------------------------------------------\n\n");

        writer.flush();
        writer.close();

    }


    public static void sortedAll() throws IOException {

        //this method sorts all 5 article of all Author objects
        for (Author author : authors) {
            //first we create an array of articles for object author
            String[] sorted = {author.getArticle1(), author.getArticle2()
                    , author.getArticle3(), author.getArticle4()
                    , author.getArticle5()};
            //then we sort it(Comparator deals with null values we have)
            Arrays.sort(sorted, Comparator.nullsLast(Comparator.naturalOrder()));
            //we set new sorted articles to author
            author.setArticle1(sorted[0]);
            author.setArticle2(sorted[1]);
            author.setArticle3(sorted[2]);
            author.setArticle4(sorted[3]);
            author.setArticle5(sorted[4]);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.write("**********************************SortedAll Successful*********************************\n\n");
        writer.flush();
        writer.close();
    }

    public static void completeAll() throws IOException {

        // now we gonna search for missing articles in Author objects and assign their missing articles from article.txt
        for (Article article : articles) {
            for (Author author : authors) {
                // we are checking if articleid's first 3 char equals to author id and if author has null(empty spot for articles) value then insert it.
                if (author.getArticle1() == null && article.getPaperid().substring(0, 3).equals(author.getAuthorid())) {
                    author.setArticle1(String.valueOf(article));
                    continue;
                }
                if (author.getArticle2() == null && article.getPaperid().substring(0, 3).equals(author.getAuthorid())) {
                    author.setArticle2(String.valueOf(article));
                    continue;
                }
                if (author.getArticle3() == null && article.getPaperid().substring(0, 3).equals(author.getAuthorid())) {
                    author.setArticle3(String.valueOf(article));
                    continue;
                }
                if (author.getArticle4() == null && article.getPaperid().substring(0, 3).equals(author.getAuthorid())) {
                    author.setArticle4(String.valueOf(article));
                    continue;
                }
                if (author.getArticle5() == null && article.getPaperid().substring(0, 3).equals(author.getAuthorid())) {
                    author.setArticle5(String.valueOf(article));

                }

            }
        }
        articles.clear();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.write("*************************************CompleteAll Successful*************************************\n\n");
        writer.flush();
        writer.close();

    }


}