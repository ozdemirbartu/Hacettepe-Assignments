


public class Main {
    //this is the main class that executes our main command readCommands and taking console arguments
    public static void main(String[] args) throws Exception {
        String ppl = args[0];
        String film = args[1];
        String commands = args[2];
        Command.readCommands(ppl,film,commands);
    }
}

