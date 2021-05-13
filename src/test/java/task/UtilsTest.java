package task;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    @Test
    public void sortRows_hasColumnsWithDifferentSize_rowsSortedInTheAscendingOrder() {

        // Arrange.
        List<List<Cell>> rows = new ArrayList<>();
        rows.add(generateCells(4));
        rows.add(generateCells(1));
        rows.add(new ArrayList<>());
        rows.add(generateCells(3));
        rows.add(generateCells(2));
        rows.add(generateCells(3));

        // Act.
        Utils.sortRows(rows);

        // Assert.
        Assert.assertEquals(0, rows.get(0).size());
        Assert.assertEquals(1, rows.get(1).size());
        Assert.assertEquals(2, rows.get(2).size());
        Assert.assertEquals(3, rows.get(3).size());
        Assert.assertEquals(3, rows.get(4).size());
        Assert.assertEquals(4, rows.get(5).size());
    }

    @Test
    public void sortRows_noRows_contentRemainsUnchanged() {

        // Arrange.
        List<List<Cell>> rows = new ArrayList<>();

        // Act.
        Utils.sortRows(rows);

        // Assert.
        Assert.assertEquals(0, rows.size());
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedToTheCellWhichIsChecked_shouldIgnoreAndReturnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(3, 3);

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_emptyBoardCheckedCenterCell_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_emptyBoardRightTopCell_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 0, 7));
    }

    @Test
    public void validateCellOnTheBoard_emptyBoardLeftTopCell_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 0, 0));
    }

    @Test
    public void validateCellOnTheBoard_emptyBoardRightBottomCell_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 7, 7));
    }

    @Test
    public void validateCellOnTheBoard_emptyBoardLeftBottomCell_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateCellOnTheBoard(board, 7, 0));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedToTheRightFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(3, 4);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedToTheLeftFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(3, 2);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheTopFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(2, 3);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheBottomFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(4, 3);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheTopRightFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(2, 4);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheTopLeftFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(2, 2);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheBottomRightFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(4, 4);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateCellOnTheBoard_figurePlacedAtTheBottomLeftFromTheCheckedCell_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8AndSetQueenThere(4, 2);

        // Act & Assert.
        Assert.assertFalse(Utils.validateCellOnTheBoard(board, 3, 3));
    }

    @Test
    public void validateQueensOnTheBoard_emptyBoard_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(board));
    }

    @Test
    public void validateQueensOnTheBoard_8QueensSetAccordingToTheRules_returnTrue() {

        // Arrange.
        boolean[][] board = getBoard8x8();
        board[0][1] = true;
        board[1][3] = true;
        board[2][5] = true;
        board[3][7] = true;
        board[5][0] = true;
        board[4][2] = true;
        board[7][4] = true;
        board[6][6] = true;

        // Act & Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(board));
    }

    @Test
    public void validateQueensOnTheBoard_2QueensSetAccordingToTheRules1QueenViolatesTheRules_returnFalse() {

        // Arrange.
        boolean[][] board = getBoard8x8();
        board[0][1] = true;
        board[1][3] = true;
        board[4][0] = true;

        // Act & Assert.
        Assert.assertFalse(Utils.validateQueensOnTheBoard(board));
    }

    @Test
    public void countQueensOnTheBoard_3QueensSetOnTheBoard_return3() {

        // Arrange.
        boolean[][] board = getBoard8x8();
        board[0][1] = true;
        board[1][3] = true;
        board[4][0] = true;

        // Act & Assert.
        Assert.assertEquals(3, Utils.countQueensOnTheBoard(board));
    }

    @Test
    public void countQueensOnTheBoard_0QueensSetOnTheBoard_return0() {

        // Arrange.
        boolean[][] board = getBoard8x8();

        // Act & Assert.
        Assert.assertEquals(0, Utils.countQueensOnTheBoard(board));
    }

    @Test
    public void setAcceptableQueensOnTheBoard_emptyBoard_firstHalfOfTheBoardIsSpecified() {

        // Arrange & Act.
        BoardManager boardManager =
                Utils.setAcceptableQueensOnTheBoard(new BoardManager(8));

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(4, boardManager.getCountOfFiguresOnTheBoard());
        Assert.assertEquals(4, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertTrue(boardManager.getBoard()[1][0]);
        Assert.assertTrue(boardManager.getBoard()[3][1]);
        Assert.assertTrue(boardManager.getBoard()[5][2]);
        Assert.assertTrue(boardManager.getBoard()[7][3]);
    }

    @Test
    public void findQueensPositions_board8x8_8QueensFound() {

        // Arrange.
        BoardManager boardManager = new BoardManager(8);

        // Act.
        Utils.findQueensPositions(boardManager);

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, boardManager.getCountOfFiguresOnTheBoard());
    }

    @Test
    public void findQueensPositionsWithPredefinedFigures_board8x8_8QueensFound() {

        // Arrange.
        BoardManager boardManager = new BoardManager(8);

        // Act.
        Utils.findQueensPositionsWithPredefinedFigures(boardManager);

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, boardManager.getCountOfFiguresOnTheBoard());
    }

    @Test
    public void findQueensPositions_board100x100_100QueensFound() {

        // Arrange.
        BoardManager boardManager = new BoardManager(100);

        // Act.
        Utils.findQueensPositions(boardManager);

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(100, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(100, boardManager.getCountOfFiguresOnTheBoard());
    }

    @Test
    public void findQueensPositionsWithPredefinedFigures_board100x100_100QueensFound() {

        // Arrange.
        BoardManager boardManager = new BoardManager(100);

        // Act.
        Utils.findQueensPositionsWithPredefinedFigures(boardManager);

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(100, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(100, boardManager.getCountOfFiguresOnTheBoard());
    }

    @Test
    public void findQueensPositions_board8x8WithPredefinedCell_8QueensFoundIncludingSpecified() {

        // Arrange.
        BoardManager boardManager = new BoardManager(8);
        boardManager.fillCell(new Cell(0, 0));

        // Act.
        Utils.findQueensPositions(boardManager);

        // Assert.
        Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, Utils.countQueensOnTheBoard(boardManager.getBoard()));
        Assert.assertEquals(8, boardManager.getCountOfFiguresOnTheBoard());
        Assert.assertTrue(boardManager.getBoard()[0][0]);
    }

    @Test
    public void findQueensPositions_boardsFrom4To80Dimensions_FoundQueensShouldMatchBoardDimension() {
        for (int i = 4; i <= 80; i++) {

            // Arrange.
            BoardManager boardManager = new BoardManager(i);

            // Act.
            Utils.findQueensPositions(boardManager);

            // Assert.
            Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
            Assert.assertEquals(i, Utils.countQueensOnTheBoard(boardManager.getBoard()));
            Assert.assertEquals(i, boardManager.getCountOfFiguresOnTheBoard());
        }
    }

    @Test
    public void findQueensPositionsWithPredefinedFigures_boardsFrom4To80Dimensions_FoundQueensShouldMatchBoardDimension() {
        for (int i = 4; i <= 80; i++) {

            // Arrange.
            BoardManager boardManager = new BoardManager(i);

            // Act.
            Utils.findQueensPositionsWithPredefinedFigures(boardManager);

            // Assert.
            Assert.assertTrue(Utils.validateQueensOnTheBoard(boardManager.getBoard()));
            Assert.assertEquals(i, Utils.countQueensOnTheBoard(boardManager.getBoard()));
            Assert.assertEquals(i, boardManager.getCountOfFiguresOnTheBoard());
        }
    }

    private boolean[][] getBoard8x8() {
        return getBoard(8);
    }

    private boolean[][] getBoard(int dimension) {
        return new boolean[dimension][dimension];
    }

    private boolean[][] getBoard8x8AndSetQueenThere(int row, int column) {
        boolean[][] board = getBoard8x8();
        board[row][column] = true;
        return board;
    }

    private List<Cell> generateCells(int numberOfCellsToGenerate) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < numberOfCellsToGenerate; i++) {
            cells.add(new Cell(0, 0));
        }
        return cells;
    }
}
