package MasterMind.Game;

import MasterMind.Controller.MastermindController;
import MasterMind.Model.Model;
import MasterMind.View.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Start the model, view and controller for the game
        Model model = new Model();
        UserInterface view = new UserInterface();
        MastermindController controller = new MastermindController(model, view);

        view.setVisible(true);

        
    }
}
