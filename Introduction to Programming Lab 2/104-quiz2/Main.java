import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> DecimalList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String input=args[0];
        Scanner initialInput = new Scanner(new File(input));
        while (initialInput.hasNext()) {
            int a = Integer.parseInt(initialInput.next().trim());

            if (1000<a && a<200000){
                DecimalList.add(a);
            }
        }
        for (int number: DecimalList){
            DecToOct(number);
        }
    }
    public static void DecToOct(int number) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("octal.txt", true));
        Stack MyStack = new Stack(DecimalList.size());
        while (number>0){
            MyStack.Push(number%8);
            number = number/8;
        }
        while (!MyStack.isEmpty()){
            writer.write(MyStack.Pop());
        }
        writer.write("\n");
        writer.flush();
        writer.close();
    }
}
