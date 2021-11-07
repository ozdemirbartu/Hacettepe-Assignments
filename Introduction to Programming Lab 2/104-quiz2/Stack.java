public class Stack {
    // store elements of stack
    private int arr[];
    // represent top of stack
    private int top;
    // total capacity of the stack
    private int size;

    // Creating a stack
    Stack(int size) {
        // initialize the array
        // initialize the stack variables
        arr = new int[size];
        this.size = size;
        top = -1;
        }
    // push elements to the top of stack
    public void Push(int x) {
        if (isFull()) {
            System.out.println("OverFlow");

        }

        // insert element on top of stack
        arr[++top] = x;
    }

    // pop elements from top of stack
    public String Pop() {

        if (isEmpty()) {
            System.out.println("STACK EMPTY");
        }
        // pop element from top of stack
        return String.valueOf(arr[top--]);
    }

    // return size of the stack
    public int Size() {
        return top + 1;
    }

    // check if the stack is empty
    public Boolean isEmpty() {
        return top == -1;
    }

    // check if the stack is full
    public Boolean isFull() {
        return top == size - 1;
    }

    // display elements of stack
    public void printStack() {
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}
