package MasterMind.View;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

// The UI responsible for displaying the game board, buttons and hints to the player
public class UserInterface extends JPanel {

    private JButton exitButton;

    private JLabel timerLabel;
    // Member for the card layout to switch between start screen and game panel.

    private Difficulty DifficultyScreen;

    private CardLayout cardLayout;

    private JPanel mainContainer;

    private StartScreen startScreen;

    private JButton newGameButton;

    private JButton ClickButton;

    private Board gameBoard;

    private RuleScreen ruleScreen;

    public UserInterface() {
   //     setTitle("MasterMind Game");
    //    setSize(800, 600);
 ///       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Start screen for the game
        startScreen = new StartScreen();
        mainContainer.add(startScreen, "StartScreen");

        // Difficulty screen for the game
        DifficultyScreen = new Difficulty();
        mainContainer.add(DifficultyScreen, "DifficultyScreen");

        // Rule screen for the game
        ruleScreen = new RuleScreen();
        mainContainer.add(ruleScreen, "RuleScreen");

        JPanel gamePanel = new JPanel(new BorderLayout());
        gameBoard = new Board();

        JPanel controlPanel = new JPanel();
        ClickButton = new JButton("Submit Guess");
        newGameButton = new JButton("New Game");
        controlPanel.add(newGameButton);
        controlPanel.add(ClickButton);

        gamePanel.add(gameBoard, BorderLayout.CENTER);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);

        mainContainer.add(gamePanel, "GamePanel");
        add(mainContainer);

        cardLayout.show(mainContainer, "StartScreen");
        
        setVisible(true);


        timerLabel = new JLabel("Time Left : 60s");
        timerLabel.setForeground(Color.RED);
        controlPanel.add(timerLabel);

        exitButton = new JButton("Exit");
        controlPanel.add(exitButton);
        
        

        mainContainer.setPreferredSize(new Dimension(800,560));
    

    }
    // Method to show the game panel when the start button is clicked
    public void showGamePanel() {
        cardLayout.show(mainContainer, "GamePanel");
    }
    // Method to get the start button from the start screen
    public JButton getStartButton() {
        return startScreen.getStartButton();
    }
    // Method to show difficutly screen when the select diffuclty button is clicked
    public void showDifficultyScreen() {
        cardLayout.show(mainContainer, "DifficultyScreen");
    }

    public Difficulty getDifficulty() {
        return DifficultyScreen;
    }

    // Method to get the submit guess button
    public JButton getClickButton() {
        return ClickButton;
    }
    // Method to get the current guess from the board
    public void updateBoardSlots(int row, int col, Color color) {
        gameBoard.setSlotColor(row, col, color);
    } 
    // Method to get the current guess from the board
    public Board getBoard() {
        return gameBoard;
    }
    // Method to set the hints for the player based on their guess
    public void setHints(int row, int blackPegs, int whitePegs) {
        gameBoard.updateHints(row, blackPegs, whitePegs);
    }  
    // Method to reset the game board for a new game
    public void resetBoard() {
        gameBoard.resetBoard();
    }
    // Method to get the new game button
    public JButton getNewGameButton() {
        return newGameButton;
    }

    public void showRuleScreen() {
        cardLayout.show(mainContainer, "RuleScreen");
    }

    public void showStartScreen() {
        cardLayout.show(mainContainer, "StartScreen");
    }

    public JButton getHelpButton() {
        return startScreen.getHelpButton();
    }

    public JButton getRuleScreenButton() {
        return ruleScreen.getBackButton();
    }

    public void updateTimer(int secondsleft){
        timerLabel.setText("Time Left : " + secondsleft + "s");
    }


    public JButton getEasyBtn() {
        return getEasyBtn();
    }
    public JButton getMediumBtn() {
        return getMediumBtn();
    }
    public JButton getHardBtn() {
        return getHardBtn();
    }

    public JButton getExitButton(){
        return exitButton;
    }

}

