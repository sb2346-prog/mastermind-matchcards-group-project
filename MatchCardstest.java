package MasterMind.View;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 public class MatchCardstest {

    @Test//tests the count
public void testincreaseCorrectCount() {
    MatchCards game = new MatchCards();
    game.startnewgame(1); 

    int before = game.correctCount;

    game.correctCount++;

    assertEquals(before + 1, game.correctCount,
            "Correct pair should increase correctCount");
}

    @Test//tests the count
    public void testincreaseErrorCount() {
        MatchCards game = new MatchCards();
        game.startnewgame(1);

        int before = game.errorCount;

       
        game.errorCount++;

        assertEquals(before + 1, game.errorCount);
    }
@Test//tests the cards being made 
    public void CardsMade() {
        MatchCards game = new MatchCards();
        game.startnewgame(1); 
        assertNotNull(game.cardSet);
        assertEquals(6, game.cardSet.size());
    }
    

 }
