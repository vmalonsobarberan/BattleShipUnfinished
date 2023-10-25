public class Board {
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 8;
    private static final String WATER_SYMBOL = "O";
    private static final String SHIP_SYMBOL = "S";
    private static final String SUNK_SHIP_SYMBOL = "H";
    private static final String EMPTY_SYMBOL = "·";
    private static final int NUM_SHIPS = 2;

    private CellType[][] matrix;
    private int numShots;

    public Board() {
        numShots = 0;
        initMatrix();
        generateRandomShips();
    }

    public int getNumShots() {
        return numShots;
    }

    private void initMatrix() {
        matrix = new CellType[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = CellType.EMPTY;
            }
        }
    }

    private void generateRandomShips() {
        int numShips = 0;
        while (numShips < NUM_SHIPS) {
            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLS);
            if (matrix[row][col] == CellType.EMPTY) {
                matrix[row][col] = CellType.SHIP;
                numShips++;
            }
        }
    }

    void shoot(int row, int col) {
        switch (matrix[row][col]) {
            case EMPTY:
                matrix[row][col] = CellType.WATER;
                break;
            case WATER:
                break;
            case SHIP:
                matrix[row][col] = CellType.SUNK_SHIP;
                break;
            case SUNK_SHIP:
                break;
        }
        numShots++;
    }

    public int getSunkShips() {
        int sunkShips = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == CellType.SUNK_SHIP) {
                    sunkShips ++;
                }
            }
        }
        return sunkShips;
    }

    void printMatrix(boolean debugginng) {
        printHeader();
        char letter = 'A';
        for (int row = 0; row < matrix.length; row++) {
            System.out.print(letter + " ");
            letter++;
            printRow(debugginng, row);
            System.out.println();
        }
    }

    private void printRow(boolean debugginng, int row) {
        for (int col = 0; col < matrix[0].length; col++) {
            printSymbolForCell(debugginng, row, col);
        }
    }

    private void printSymbolForCell(boolean debugginng, int row, int col) {
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

    static void printHeader() {
        System.out.print("  ");
        for (int i = 1; i <= NUM_COLS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public boolean areAllShipsSunk() {
        return getSunkShips() == NUM_SHIPS;
    }
}
