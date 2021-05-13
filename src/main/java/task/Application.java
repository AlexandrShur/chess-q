package task;

import java.util.Date;

/**
 * Main application class.
 */
public class Application {

    private static final String ARGUMENT_DRAW_BOARD = "draw";
    private static final String ARGUMENT_WITHOUT_DEFINED = "without";

    public static void main(String[] args) {
        int boardDimension = getDimension(args, 1000);

        System.out.println("Board dimension: " + boardDimension + "x" + boardDimension);

        BoardManager boardManager = new BoardManager(boardDimension);

        System.out.println("Initial count of the figures: " + boardManager.getCountOfFiguresOnTheBoard());
        System.out.println("Started to search queens positions: " + new Date());

        if (isArgumentsContainsValue(args, ARGUMENT_WITHOUT_DEFINED)) {
            Utils.findQueensPositions(boardManager);
        } else {
            Utils.findQueensPositionsWithPredefinedFigures(boardManager);
        }

        if (isArgumentsContainsValue(args, ARGUMENT_DRAW_BOARD)) {
            Utils.drawTheBoard(boardManager.getBoard());
        }
        System.out.println("Ended to search the queens positions: " + new Date());
        System.out.println("Count of figures registered during process: " + boardManager.getCountOfFiguresOnTheBoard());
        System.out.println("Check that figures placed according to the rules: " + Utils.validateQueensOnTheBoard(boardManager.getBoard()));
        System.out.println("Check count of the figures with side tool: " + Utils.countQueensOnTheBoard(boardManager.getBoard()));
    }

    private static boolean isArgumentsContainsValue(String[] args, String value) {
        for (String arg : args) {
            if (value.contentEquals(arg)) {
                return true;
            }
        }
        return false;
    }

    private static int getDimension(String[] args, int defaultValue) {
        for (String arg : args) {
            try {
                defaultValue = Integer.parseInt(arg);
            } catch (NumberFormatException e) {}
        }

        return defaultValue;
    }
}
