package task;

import org.junit.Assert;
import org.junit.Test;

public class BoardManagerTest {

    @Test
    public void boardManager_initializeWithDimension2_return4FreeCells() {

        // Arrange & Act.
        BoardManager boardManager = new BoardManager(2);

        // Assert.
        Assert.assertEquals(2, boardManager.getFreeCells().get(0).size());
        Assert.assertEquals(2, boardManager.getFreeCells().get(1).size());
    }

    @Test
    public void boardManager_afterReleaseCellFilledAgain_return4FreeCells() {

        // Arrange.
        BoardManager boardManager = new BoardManager(2);

        // Act.
        boardManager.fillCell(new Cell(1, 1));
        boardManager.releaseCell(new Cell(1, 1));

        // Assert.
        Assert.assertEquals(2, boardManager.getFreeCells().get(0).size());
        Assert.assertEquals(2, boardManager.getFreeCells().get(1).size());
    }

    @Test
    public void boardManager_fillCell_return3FreeCellsWithoutReleasedOne() {

        // Arrange.
        BoardManager boardManager = new BoardManager(3);

        // Act.
        boardManager.fillCell(new Cell(2,2));

        // Assert.
        Assert.assertEquals(2, boardManager.getFreeCells().size());
        Assert.assertEquals(1, boardManager.getFreeCells().get(0).size());
        Assert.assertEquals(1, boardManager.getFreeCells().get(1).size());
    }
}
