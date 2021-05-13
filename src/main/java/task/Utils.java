package task;

import java.util.Comparator;
import java.util.List;

/**
 * Helper class to manage and search queens on the chess board.
 */
public class Utils {

    /**
     * Add predefined queens on the board
     * and then searches queens positions based on already specified figures.
     *
     * @param boardManager manager with defined dimension.
     * @return true if all queens were found
     * and false if at least one row doesn't contain queen.
     */
    public static boolean findQueensPositionsWithPredefinedFigures(BoardManager boardManager) {
        return findQueensPositions(setAcceptableQueensOnTheBoard(boardManager));
    }

    /**
     * Searches queens positions and set it on the board.
     *
     * @param boardManager manager with defined dimension.
     * @return true if all queens were found
     * and false if at least one row doesn't contain queen.
     */
    public static boolean findQueensPositions(BoardManager boardManager) {
        List<List<Cell>> rows = boardManager.getFreeCells();
        for (Cell cell : rows.get(0)) {
            if (findNextQueenPosition(rows, cell, boardManager)) {
                return true;
            } else {
                boardManager.releaseCell(cell);
            }
        }
        return false;
    }

    /**
     * Checks that all placed on the board queens don't hit each other.
     *
     * @param board matrix with set queens.
     * @return false if at least one queen hit other one otherwise true.
     */
    public static boolean validateQueensOnTheBoard(boolean[][] board) {
        int queens = 0;
        for (int i = 0; i < board.length; i++) {
            for (int g = 0; g < board.length; g++) {
                if (board[i][g]) {
                    if (validateCellOnTheBoard(board, i, g)) {
                        queens++;
                    } else {
                        return false;
                    }
                }
            }
        }
        System.out.println("Count of the queens: " + queens);
        return true;
    }

    /**
     * Checks that given cell doesn't hit by other queens on the board.
     * Note: doesn't check given cell. If queen placed on given cell then it will ignored.
     *
     * @param board matrix with set queens where need to check given cell.
     * @param cell the entity with column and row coordinates to check.
     * @return false if given cell hit by any queen otherwise true.
     */
    public static boolean validateCellOnTheBoard(boolean[][] board, Cell cell) {
        return validateCellOnTheBoard(board, cell.getRow(), cell.getColumn());
    }

    /**
     * Checks that given cell doesn't hit by other queens on the board.
     * Note: doesn't check given cell. If queen placed on given cell then it will ignored.
     *
     * @param board matrix with set queens where need to check given cell.
     * @param row the row where cell placed.
     * @param col the column where cell placed.
     * @return false if given cell hit by any queen otherwise true.
     */
    public static boolean validateCellOnTheBoard(boolean[][] board, int row, int col) {
        return checkRow(board[row], col)
                && checkColumn(board, row, col)
                && checkTopRightDiagonal(board, row, col)
                && checkTopLeftDiagonal(board, row, col)
                && checkBottomRightDiagonal(board, row, col)
                && checkBottomLeftDiagonal(board, row, col);
    }

    /**
     * Set queens on the board to reduce number of the possible combinations.
     * Started from the second row and set queens till end of the first half of the board
     * according to the rules.
     *
     * @param boardManager manager which store board data to process.
     * @return updated manager with set predefined queens on the board.
     */
    public static BoardManager setAcceptableQueensOnTheBoard(BoardManager boardManager) {
        int rowStep = 1; // Started from the second row to leave free main diagonal (0,0 -> n,n);
        int colStep = 0;

        if ((boardManager.getBoard().length - 3) % 6 == 0) {
             rowStep = 3;
        }

        for (int i = 0; i < boardManager.getBoard().length; i++) {
            if (rowStep >= boardManager.getBoard().length
                    || colStep >= boardManager.getBoard().length) {
                break;
            }
            boardManager.fillCell(rowStep, colStep);

            rowStep = rowStep + 2;
            colStep++;
        }

        return boardManager;
    }

    /**
     * Iterate over board and count each field with `true` value.
     *
     * @param board Board which need to be checked.
     * @return the number of all queens on the board.
     */
    public static int countQueensOnTheBoard(boolean[][] board) {
        int queensCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int g = 0; g < board[i].length; g++) {
                if (board[i][g]) {
                    queensCount++;
                }
            }
        }

        return queensCount;
    }

    /**
     * Prints visual representation of the board structure in the console.
     *
     * @param board The board which need to be displayed in th e console.
     */
    public static void drawTheBoard(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int g = 0; g < board[i].length; g++) {
                if (board[i][g]) {
                    System.out.print("| * ");
                } else {
                    System.out.print("|   ");
                }
                if (g == board[i].length - 1) {
                    System.out.println("|");
                }
            }
        }
    }

    /**
     * Sorts rows from less cells lists to more.
     *
     * @param rows List with cells.
     */
    public static void sortRows(List<List<Cell>> rows) {
        rows.sort(Comparator.comparingInt(List::size));
    }

    private static boolean findNextQueenPosition(List<List<Cell>> rows, Cell targetCell, BoardManager boardManager) {
        if (validateCellOnTheBoard(boardManager.getBoard(), targetCell)) {
            boardManager.fillCell(targetCell);

            int previousRowsSize = rows.size();
            rows = boardManager.getFreeCells();

            if (previousRowsSize != 0 && rows.size() + 1 == previousRowsSize) {

                // Here can be placed debugging logic.
            } else {
                return false;
            }

            if (rows.size() == 0) {
                return true;
            }
            List<Cell> cellList = rows.get(0);
            for (int g = 0; g < cellList.size(); g++) {
                Cell cell = cellList.get(g);
                if (!boardManager.getCellValue(cell)) {
                    if (findNextQueenPosition(rows, cell, boardManager)) {
                        return true;
                    } else {
                        boardManager.releaseCell(cell);
                        if (g >= cellList.size() - 1) {
                            return false;
                        }
                    }
                } else {
                    if (g >= cellList.size() - 1) {
                        return false;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private static boolean checkRow(boolean[] row, int currentPos) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] && i != currentPos) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(boolean[][] board, int currentRowPos, int currentColPos) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][currentColPos] && i != currentRowPos) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkTopRightDiagonal(boolean[][] board, int currentRowPos, int currentColPos) {
        int colStep = currentColPos;
        int rowStep = currentRowPos;

        for (int i = 0; i < board.length; i++) {
            if (rowStep >= board.length || colStep >= board.length) {
                break;
            }
            if (board[rowStep][colStep] && colStep != currentColPos) {
                return false;
            } else {
                rowStep++;
                colStep++;
            }
        }
        return true;
    }

    private static boolean checkTopLeftDiagonal(boolean[][] board, int currentRowPos, int currentColPos) {
        int colStep = currentColPos;
        int rowStep = currentRowPos;
        for (int i = 0; i < board.length; i++) {
            if (rowStep >= board.length || colStep < 0) {
                break;
            }
            if (board[rowStep][colStep] && colStep != currentColPos) {
                return false;
            } else {
                rowStep++;
                colStep--;
            }
        }
        return true;
    }

    private static boolean checkBottomRightDiagonal(boolean[][] board, int currentRowPos, int currentColPos) {
        int colStep = currentColPos;
        int rowStep = currentRowPos;

        for (int i = 0; i < board.length; i++) {
            if (rowStep < 0 || colStep >= board.length) {
                break;
            }
            if (board[rowStep][colStep] && colStep != currentColPos) {
                return false;
            } else {
                rowStep--;
                colStep++;
            }
        }
        return true;
    }

    private static boolean checkBottomLeftDiagonal(boolean[][] board, int currentRowPos, int currentColPos) {
        int colStep = currentColPos;
        int rowStep = currentRowPos;

        for (int i = 0; i < board.length; i++) {
            if (rowStep < 0 || colStep < 0) {
                break;
            }
            if (board[rowStep][colStep] && colStep != currentColPos) {
                return false;
            } else {
                rowStep--;
                colStep--;
            }
        }
        return true;
    }
}
