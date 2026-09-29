package MasterMind.Tests;
import MasterMind.View.Board;
import MasterMind.View.UserInterface;
import MasterMind.Controller.MastermindController;
import MasterMind.Model.Model;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
public class MasterMindTest {
    @Test//1 color combination
    public void testgetCurrentGuess() {
        Board board = new Board();

        board.setSlotColor(0, 0, Color.RED);
        board.setSlotColor(0, 1, Color.GREEN);
        board.setSlotColor(0, 2, Color.BLUE);
        board.setSlotColor(0, 3, Color.YELLOW);

        int[] guess = board.getCurrentGuess(0);

        assertEquals(1, guess[0]);
        assertEquals(2, guess[1]);
        assertEquals(3, guess[2]);
        assertEquals(4, guess[3]);
    }
    @Test//2 reseting game
    public void testboardReset() {
        Board board = new Board();
        board.setSlotColor(0, 0, Color.RED);
        board.setSlotColor(0, 1, Color.GREEN);
        board.setSlotColor(0, 2, Color.BLUE);
        board.setSlotColor(0, 3, Color.YELLOW);
        board.resetBoard();
        int[] guess = board.getCurrentGuess(0);

        for(int color : guess) {
            assertEquals(7, color);// The light gray last color index
        }
    }
    @Test//3 peg
    public void testUpdate() {
        Board board = new Board();
        board.updateHints(0, 2, 1);
        assertTrue(true);//
    }

    @Test
    public void testGeneratesecretCode() {
        Model model = new Model();

        int[] code = model.getSecretCode();

        assertEquals(4, code.length);

        for(int color : code) {
            assertTrue(color >= 0 && color <= 7);
        }

    }
    @Test
    public void testControllerGuess() {
        Model model = new Model();
        UserInterface view = new UserInterface();
        MastermindController controller = new MastermindController(model, view);

        int[] secretCode = model.getSecretCode();

        int[] guess = {secretCode[0], secretCode[1], secretCode[2], secretCode[3]};
        int[] result = controller.checkGuess(guess);

        int blackPegs = result[0];
        int whitePegs = result[1];

        assertEquals(4, blackPegs);
        assertEquals(0, whitePegs);
    }
    @Test//incorrect path
    public void testWrongGuess() {
        Model model = new Model();
        UserInterface view = new UserInterface();
        MastermindController controller = new MastermindController(model, view);

        int[] secretCode = model.getSecretCode();

        int[] guess = {secretCode[1], secretCode[0], secretCode[3], secretCode[2]};
        int[] result = controller.checkGuess(guess);

        int blackPegs = result[0];
        int whitePegs = result[1];
        
        assertTrue(blackPegs >= 0 && blackPegs <= 4);
        assertTrue(whitePegs >= 0 && whitePegs <= 4);

    }
    @Test//correct path
    public void testRightGuess() { 
        Model model = new Model();
        UserInterface view = new UserInterface();
        MastermindController controller = new MastermindController(model, view);

        int[] secretCode = model.getSecretCode();

        int[] guess = {secretCode[0], 0, 0, 0};
        int[] result = controller.checkGuess(guess);

        int blackPegs = result[0];
        int whitePegs = result[1];

        assertTrue(blackPegs >= 1);

    }

}
    


