package exercises;

public class Main {
    public static void main(String[] args) {
        TheMatrix theMatrix = new TheMatrix();
        theMatrix.printMatrix();

        System.out.println();

//        theMatrix.fillRecursively(0, 1);
        theMatrix.fillIteratively(2, 2);
        theMatrix.printMatrix();
    }
}
