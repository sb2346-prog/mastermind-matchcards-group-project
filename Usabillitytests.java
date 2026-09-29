package MasterMind.View;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class Usabillitytests {
    @Test//UI is there 
    public void testUIexists() {
        UserInterface ui = new UserInterface();
        assertNotNull(ui);
    }
   
 @Test//  button is there and implemented
public void testButtonExists() {
    UserInterface ui = new UserInterface();
    assertNotNull(ui.getStartButton());
    assertNotNull(ui.getNewGameButton());
    assertNotNull(ui.getExitButton());
    }
}
