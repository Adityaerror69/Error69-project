import java.util.Scanner;

public class TicTacToe {
    private final char[][] board;
    private char currentPlayer;

    // Constructor to initialize the board and current player
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty spaces
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board
    public void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to place a mark at the specified row and column
    public boolean placeMark(int row, int col) {
        // Check if the cell is within bounds and empty
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // Method to switch the player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if the current player has won
    public boolean checkWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer));
    }

    // Method to check if the board is full (draw)
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            TicTacToe game = new TicTacToe();
            boolean gameEnded = false;

            System.out.println("Welcome to Tic Tac Toe!");

            while (!gameEnded) {
                game.printBoard();
                int row, col;

                while (true) {
                    System.out.println("Player " + game.currentPlayer + ", enter your move (row and column, 0-2): ");
                    try {
                        row = scanner.nextInt();
                        col = scanner.nextInt();

                        if (game.placeMark(row, col)) {
                            break;
                        } else {
                            System.out.println("This cell is already occupied. Try a different one.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter two integers between 0 and 2.");
                        scanner.next(); // Clear invalid input
                    }
                }

                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    gameEnded = true;
                } else if (game.isBoardFull()) {
                    game.printBoard();
                    System.out.println("The game is a draw!");
                    gameEnded = true;
                } else {
                    game.switchPlayer();
                }
            }

            System.out.println("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}