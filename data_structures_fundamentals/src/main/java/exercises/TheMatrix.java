package exercises;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private char[][] matrix;
    private final char borderSymbol = '#';
    private final char replacingSymbol = 'S';

    public TheMatrix() {
        this.initializeMatrix();
    }

    public TheMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    private void initializeMatrix() {
        this.matrix = new char[][]{
                {'.', '#', '#', '#', '.', '.', '.'},
                {'.', '#', '.', '#', '#', '.', '.'},
                {'.', '#', '.', '.', '#', '.', '.'},
                {'.', '#', '#', '.', '#', '#', '.'},
                {'.', '#', '.', '.', '.', '#', '.'},
                {'.', '#', '.', '.', '#', '#', '.'},
                {'.', '#', '.', '.', '#', '.', '.'},
                {'.', '#', '#', '#', '#', '.', '.'},};
    }

    private boolean isOutOfBounds(int row, int col) {
        return ((row < 0 || row > this.matrix.length - 1) || (col < 0 || col > this.matrix[row].length - 1));
    }

    public void fillRecursively(int row, int col) {
        if (this.isOutOfBounds(row, col) || this.matrix[row][col] == this.replacingSymbol || this.matrix[row][col] == this.borderSymbol)
            return;

        this.matrix[row][col] = this.replacingSymbol;

        this.fillRecursively(row + 1, col);
        this.fillRecursively(row - 1, col);
        this.fillRecursively(row, col + 1);
        this.fillRecursively(row, col - 1);

        return;
    }

    public void fillIteratively(int row, int col) {
        Deque<int[]> coordinates = new ArrayDeque<>();
        coordinates.offer(new int[]{row, col});

        while (!coordinates.isEmpty()) {
            int[] currentCoordinates = coordinates.poll();
            int currentRow = currentCoordinates[0];
            int currentCol = currentCoordinates[1];

            this.matrix[currentRow][currentCol] = this.replacingSymbol;

            if (!isOutOfBounds(currentRow + 1, currentCol)
                    && this.isNotReplacingSymbol(currentRow + 1, currentCol)
                    && this.isNotBorderSymbol(currentRow + 1, currentCol)) {
                coordinates.offer(new int[]{currentRow + 1, currentCol});
            }

            if (!isOutOfBounds(currentRow - 1, currentCol)
                    && this.isNotReplacingSymbol(currentRow - 1, currentCol)
                    && this.isNotBorderSymbol(currentRow - 1, currentCol)) {
                coordinates.offer(new int[]{currentRow - 1, currentCol});
            }

            if (!isOutOfBounds(currentRow, currentCol + 1)
                    && this.isNotReplacingSymbol(currentRow, currentCol + 1)
                    && this.isNotBorderSymbol(currentRow, currentCol + 1)) {
                coordinates.offer(new int[]{currentRow, currentCol + 1});
            }

            if (!isOutOfBounds(currentRow, currentCol - 1)
                    && this.isNotReplacingSymbol(currentRow, currentCol - 1)
                    && this.isNotBorderSymbol(currentRow, currentCol - 1)) {
                coordinates.offer(new int[]{currentRow, currentCol - 1});
            }
        }
    }

    private boolean isNotBorderSymbol(int row, int col) {
        return this.matrix[row][col] != borderSymbol;
    }

    private boolean isNotReplacingSymbol(int row, int col) {
        return this.matrix[row][col] != replacingSymbol;
    }

    public void printMatrix() {
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(this.matrix[row][col] + "  ");
            }
            System.out.println();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String matrixViewAsString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sb.append(matrix[row][col]);
                sb.append("  ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
