package task;

import lombok.Getter;

/**
 * Data model to store cell position.
 */
@Getter
public class Cell {
    private final int column;
    private final int row;

    /**
     * Constructor.
     *
     * @param row the horizontal position of the cell.
     * @param column the vertical position of the cell.
     */
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }
}
