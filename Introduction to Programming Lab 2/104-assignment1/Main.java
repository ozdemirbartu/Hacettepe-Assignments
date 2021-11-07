
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        String authorFileName = args[0];
        String commandFileName = args[1];
        Commands.readCommands(commandFileName, authorFileName);


    }
}

