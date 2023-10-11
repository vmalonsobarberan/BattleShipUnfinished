import java.util.Scanner;

public interface BattleShip {
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 8;
    public static final String WATER_SYMBOL = "O";
    public static final String SHIP_SYMBOL = "S";
    public static final String SUNK_SHIP_SYMBOL = "X";
    public static final String EMPTY_SYMBOL = "·";

    public static void main(String[] args) {
        int row, col, numShots = 0;
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        CellType[][] matrix = initMatrix();
        generateRandomShips(matrix);
        while (!gameOver) {
            printStatistics(numShots, matrix);
            printMatrix(true, matrix);
            row = readRowFromKeyboard(input);
            col = readColFromKeyboard(input);
            shoot(row, col, matrix);
            numShots++;
        }
    }

    static void shoot(int row, int col, CellType[][] matrix) {

    }

    static int readColFromKeyboard(Scanner input) {
        int col = -1;
        while (col < 0 || col >= NUM_COLS) {
            System.out.print("Enter column (Number): ");
            String s = input.nextLine();
            s = s.replaceAll("[^\\d]", "");
            if (!s.isEmpty()) {
                col = Integer.parseInt(s);
                col--;
            }
        }
        return col;
    }

    static int readRowFromKeyboard(Scanner input) {
        String s;
        int row = -1;
        while (row < 0 || row >= NUM_ROWS) {
            System.out.print("Enter row (Letter): ");
            s = input.nextLine();
            char letter = s.toUpperCase().charAt(0);
            row = letter - 65;
        }
        return row;
    }

    static void printMatrix(boolean debugginng, CellType[][] matrix) {
        printHeader();
        char letter = 'A';
        for (int row = 0; row < matrix.length; row++) {
            System.out.print(letter + " ");
            letter++;
            for (int col = 0; col < matrix[0].length; col++) {
                switch (matrix[row][col]) {
                    case EMPTY: System.out.print(EMPTY_SYMBOL + " ");
                        break;
                    case SHIP:
                        if (debugginng) {
                            System.out.print(SHIP_SYMBOL + " ");
                        } else {
                            System.out.print(EMPTY_SYMBOL + " ");
                        }
                        break;
                    case WATER: System.out.print(WATER_SYMBOL + " ");
                        break;
                    case SUNK_SHIP: System.out.print(SUNK_SHIP_SYMBOL + " ");
                        break;
                }
            }
            System.out.println();
        }
    }

    static void printHeader() {
        System.out.print("  ");
        for (int i = 1; i <= NUM_COLS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void generateRandomShips(CellType[][] matrix) {
        int numShips = 0;
        while (numShips < 10) {
            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLS);
            if (matrix[row][col] == CellType.EMPTY) {
                matrix[row][col] = CellType.SHIP;
                numShips++;
            }
        }
    }

    private static CellType[][] initMatrix() {
        CellType[][] matrix;
        matrix = new CellType[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = CellType.EMPTY;
            }
        }
        return matrix;
    }
}
