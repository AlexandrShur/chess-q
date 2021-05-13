package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

/**
 * Class to store board data and manipulate it.
 */
public class BoardManager {

    @Getter
    private final boolean[][] board;

    @Getter
    private int countOfFiguresOnTheBoard;

    private final Set<Integer> freeRows = new HashSet<>();
    private final Set<Integer> freeColumns = new HashSet<>();

    /**
     * Constructor.
     *
     * @param dimensionOfTheBoard rows and columns number of the board.
     */
    public BoardManager(int dimensionOfTheBoard) {
        board = new boolean[dimensionOfTheBoard][dimensionOfTheBoard];
        for (int i = 0; i < dimensionOfTheBoard; i++) {
            freeRows.add(i);
            freeColumns.add(i);
        }
    }

    /**
     * Returns board value for given cell.
     *
     * @param cell entity with row and column data to check.
     * @return value of the given position.
     */
    public boolean getCellValue(Cell cell) {
        return this.getCellValue(cell.getRow(), cell.getColumn());
    }

    /**
     * Returns board value for given row and column.
     *
     * @param row horizontal position.
     * @param column vertical position.
     * @return value of the given position.
     */
    public boolean getCellValue(int row, int column) {
        return this.board[row][column];
    }

    /**
     * Sets 'true' value to given position and updates data about free cells.
     *
     * @param cell entity with row and column data.
     */
    public void fillCell(Cell cell) {
        this.fillCell(cell.getRow(), cell.getColumn());
    }

    /**
     * Sets 'true' value to given position and updates data about free cells.
     *
     * @param row horizontal position.
     * @param column vertical position.
     */
    public void fillCell(int row, int column) {
        this.board[row][column] = true;
        this.countOfFiguresOnTheBoard++;
        this.freeRows.remove(row);
        this.freeColumns.remove(column);
    }

    /**
     * Sets 'false' value to given position and updates data about free cells.
     *
     * @param cell entity with row and column data.
     */
    public void releaseCell(Cell cell) {
        this.releaseCell(cell.getRow(), cell.getColumn());
    }

    /**
     * Sets 'false' value to given position and updates data about free cells.
     *
     * @param row horizontal position.
     * @param column vertical position.
     */
    public void releaseCell(int row, int column) {
        this.board[row][column] = false;
        this.countOfFiguresOnTheBoard--;
        this.freeRows.add(row);
        this.freeColumns.add(column);
    }

    /**
     * Gives data about free cells on the board.
     *
     * @return list of list with free cells.
     */
    public List<List<Cell>> getFreeCells() {
        List<List<Cell>> rows = new ArrayList<>();

        for (Integer row : freeRows) {
            List<Cell> cols = new ArrayList<>();
            for (Integer column : freeColumns) {
                if (Utils.validateCellOnTheBoard(board, row, column)) {
                    cols.add(new Cell(row, column));
                }
            }
            if (!cols.isEmpty()) {
                rows.add(cols);
            }
        }
        Utils.sortRows(rows);
        return rows;
    }
}
