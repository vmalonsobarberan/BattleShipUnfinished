import java.util.Scanner;

public class BattleShip {

    public static void main(String[] args) {
        Board board = new Board();

        int row, col;
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printStatistics(board);
            board.printMatrix(true);
            row = readRowFromKeyboard(input);
            col = readColFromKeyboard(input);
            board.shoot(row, col);
            if (board.areAllShipsSunk()) {
                gameOver = true;
            }
        }
        processGameOver();
    }

    static void processGameOver() {
        System.out.println("You win !!!!");
    }

    static void printStatistics(Board board) {
        System.out.println("\nSHOTS: " + board.getNumShots());
        int sunkShips = board.getSunkShips();
        System.out.println("SUNK SHIPS: " + sunkShips);
    }

    static int readColFromKeyboard(Scanner input) {
        int col = -1;
        while (col < 0 || col >= Board.NUM_COLS) {
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
        while (row < 0 || row >= Board.NUM_ROWS) {
            System.out.print("Enter row (Letter): ");
            s = input.nextLine();
            char letter = s.toUpperCase().charAt(0);
            row = letter - 65;
        }
        return row;
    }



}
