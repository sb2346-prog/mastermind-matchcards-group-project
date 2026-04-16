package MasterMind.Controller;

import MasterMind.Model.Model;
import MasterMind.Model.Difficulty;
import MasterMind.View.UserInterface;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// Responsible for handling user interactios and game logic
public class MastermindController implements ActionListener {
    private Model model;
    private UserInterface view;
    private int attempts = 0;
    private Timer timer;
    private Difficulty difficultymodel;

    // Constructor to start controller and action listeners for buttons
    public MastermindController(Model model, UserInterface view) {
        this.model = model;
        this.view = view;
        this.difficultymodel = new Difficulty();


        // Action listener for the rule button to switch between start screen and rule screen
        this.view.getHelpButton().addActionListener(e-> {
            timer.stop();
            view.showRuleScreen();
        });

        // Button to exit the game early for the player
        this.view.getExitButton().addActionListener(e-> {
            timer.stop();
            attempts = 0;
            model.generateNewCode();
            view.resetBoard();
            view.showStartScreen();
            System.out.println("Exit button is working");
        });

        // Action listener for the back button to switch between rule screen and start screen
        this.view.getRuleScreenButton().addActionListener(e-> {
            view.showStartScreen();
        });


        // Action listener for the start button to switch between start screen and game panel
        this.view.getStartButton().addActionListener(e-> {
            view.showDifficultyScreen();
        });

        // Diffuclty section to start game with different time based on difficulty selected
        this.view.getDifficulty().getEasyBtn().addActionListener(e-> {
            difficultymodel.setDifficultyLevel("Easy");
            StartGame();
        });

        this.view.getDifficulty().getmediumBtn().addActionListener(e-> {
            difficultymodel.setDifficultyLevel("Medium");
            StartGame();
        });


        this.view.getDifficulty().getHardBtn().addActionListener(e-> {
            difficultymodel.setDifficultyLevel("Hard");
            StartGame();
        });

        this.view.getDifficulty().getBackBtn().addActionListener(e-> {
            view.showStartScreen();
        });


        this.view.getClickButton().addActionListener(this);
        System.out.println("This is working");

        this.view.getNewGameButton().addActionListener(this);

        this.timer = new Timer (1000, e-> handleTimerTick());

    }

    private void StartGame() {
        view.showGamePanel();
        StartNewTimer();
    }

    private void StartNewTimer() {
        int timesetting = difficultymodel.getCurrentTime();
        model.setTimeremaining(timesetting);
        view.updateTimer(model.getTimeremaining());
        timer.start();
    }

    private void handleTimerTick() {
        model.decrementTimer();
        view.updateTimer(model.getTimeremaining());

        if (model.getTimeremaining() <= 0) {
            timer.stop();
            javax.swing.JOptionPane.showMessageDialog(view, "Time's up! You have lost the game. The secret code was: " + model.getSecretCode()[0] + " " + model.getSecretCode()[1] + " " + model.getSecretCode()[2] + " " + model.getSecretCode()[3], "Game Over", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            // Go back to start screen
            view.showStartScreen();
        }
    }

    // Handle the guess when the click button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getClickButton()) {
            handleGuess();
        }
        // Reset the game when the new game button is clicked
        if (e.getSource() == view.getNewGameButton()) {
            resetGame();
        }
    }
    private void handleGuess() {
        int [] playerguess = view.getBoard().getCurrentGuess(attempts);
        int [] results = checkGuess(playerguess);

        int blackPegs = results[0];
        int whitePegs = results[1];

        view.setHints(attempts, blackPegs, whitePegs);

        System.out.println("Attempts " + attempts + ": " + blackPegs + " black pegs, " + whitePegs + " white pegs");
        // Check if the player has won condition
        if (blackPegs == 4) {
            timer.stop();
            // Player wins here then ask if they want to play again
            
            javax.swing.JOptionPane.showMessageDialog(view, "Congratulations! You guessed the code", "Game Over", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            view.getClickButton().setEnabled(false);
            // Ask the player if they want to play again
            if (javax.swing.JOptionPane.showConfirmDialog(view, "Do you want to play again?", "Play Again", javax.swing.JOptionPane.YES_NO_OPTION) == javax.swing.JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
            resetGame();
            return;
      
        } else {
            attempts++;

        }
        // Check if the player has used all their attempts
        if (attempts >= 10) {
            timer.stop();
            javax.swing.JOptionPane.showMessageDialog(view, "Game over! You have used all of your attemps. The secret code was: " + model.getSecretCode()[0] + " " + model.getSecretCode()[1] + " " + model.getSecretCode()[2] + " " + model.getSecretCode()[3], "Game Over", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            if (javax.swing.JOptionPane.showConfirmDialog(view, "Do you want to play again?", "Play Again", javax.swing.JOptionPane.YES_NO_OPTION) == javax.swing.JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }   
            
            resetGame();
            return;
        }
    }   
        public int[] checkGuess(int[] playerguess) {
        
            int blackPegs = 0;
            int whitePegs = 0;

            int[] secretcode = model.getSecretCode();

            // Track the number of black and white peg
            boolean[] secretCodeMatch = new boolean[secretcode.length];
            boolean[] playerGuessMatch = new boolean[playerguess.length];
            // Put Black Pegs (Right color, right position)
            for(int i = 0; i < playerguess.length; i++) {
                if(playerguess[i] == secretcode[i]) {
                    blackPegs++;
                    secretCodeMatch[i] = true;
                    playerGuessMatch[i] = true;
                }
            }
            // Put White Pegs (Right color, wrong position)
            for(int i = 0; i < playerguess.length; i++) {
                if(!playerGuessMatch[i]) {
                    for(int j = 0; j < secretcode.length; j++) {
                        if(!secretCodeMatch[j] && playerguess[i] == secretcode[j]) {
                            whitePegs++;
                            secretCodeMatch[j] = true;
                            break;
                        }
                    }
                }
            }
            return new int[]{blackPegs, whitePegs};
        }
        // Reset the game state for a new game
        private void resetGame() {
            attempts = 0;
            model.generateNewCode();
            view.resetBoard();
            view.getClickButton().setEnabled(true);
            StartNewTimer();
        }
    }