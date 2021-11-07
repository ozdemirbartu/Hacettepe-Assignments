import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
	    String init=args[0];
        String out = args[2];
        Commands.returnOutName(out);
	    Commands.readInitial(init);
        Commands.mapViewer();
        Commands.HPViewer();
        String comm =args[1];
        Commands.readCommand(comm);
    }
}

