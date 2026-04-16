package MasterMind.View;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MatchCards extends JPanel{

    
    JButton mainmenu = new JButton("Back to Main Menu");

    // class to represent each card with its index and image
    class Card{
        String cardName;
        ImageIcon cardImageIcon;
        Card(String cardName, ImageIcon cardImageIcon){
            this.cardName = cardName;
            this.cardImageIcon = cardImageIcon;
        }

        public String toString(){
            return cardName;
        }
    }

    String[] cardList = {//actual card names
        "double",
        "darkness",
        "fairy",
        "fighting",
        "fire",
        "grass",
        "lightning",
        "metal",
        "psychic",
        "water"
    };

    int rows, columns;
    int cardWidth = 90;
    int cardHeight = 128;
    int errorCount = 0;
    int correctCount = 0;
    long startTime = 0;



    ArrayList<Card> cardSet; //create a deck of cards with cardNames and cardImageicons
    ArrayList<JButton> board;
    ImageIcon cardBackImageIcon;
    Timer hideCardTimer;
    Timer gameTimer;
    boolean gameReady = false;
    JButton card1Selected , card2Selected;
  
    JLabel textLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton restartButton = new JButton();
    JButton backButton;

    // constructor to setup the game panel
    public MatchCards() {
        restartButton.setText("Restart Game");
        restartButton.setEnabled(false);

        setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textPanel.add(textLabel);
        add(textPanel, BorderLayout.NORTH);

        add(boardPanel, BorderLayout.CENTER);

        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            // restart with the same difficulty
            gameTimer.stop();
            timeLabel.setText("Time 0.00 s");
            
            startnewgame(this.columns == 3 ? 1 : (this.columns == 4 ? 2 : 3));
        });
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        southPanel.add(restartButton);
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(mainmenu);
        add(southPanel, BorderLayout.SOUTH);
        
        backButton = new JButton("Back to Selection Screen");
        backButton.setPreferredSize(new Dimension(50,30));
        add(backButton, BorderLayout.SOUTH);
        

        // timer to flip cards back after 1.5s
        hideCardTimer = new Timer(1500, e -> hideCards());
        hideCardTimer.setRepeats(false);

        //timer to time game time
        gameTimer = new Timer(100, e->updateTime());
        timeLabel.setText("Time 0.00 s");
        southPanel.add(timeLabel);
        add(southPanel, BorderLayout.SOUTH);
    }
  
    // start a new game with a given difficulty level
    public void startnewgame(int difficultylevel) {
        // Reset everything
        errorCount = 0;
        correctCount = 0;
        card1Selected = null;
        card2Selected = null;
        gameReady = false;
        textLabel.setText("Errors: 0");

        Timer delayTimer = new Timer(1500, e->{
            startTime = System.nanoTime();
            gameTimer.start();
        });

        // Set difficulty 
        if (difficultylevel == 1) { rows = 2; columns = 3; }
        else if (difficultylevel == 2) { rows = 3; columns = 4; }
        else { rows = 4; columns = 5; }

        // Setup the board
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(rows, columns));
        board = new ArrayList<>();
        
        setupCards(rows * columns / 2);
        shuffleCards();

        // Create buttons 
        for (int i = 0; i < cardSet.size(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
            tile.setIcon(cardSet.get(i).cardImageIcon); // Show them first
            tile.setFocusable(false);
            
            final int index = i;
            tile.addActionListener(e -> handleTileClick(tile, index));
            
            board.add(tile);
            boardPanel.add(tile);
        }

        revalidate();
        repaint();

        delayTimer.setRepeats(false);

        hideCardTimer.start(); // Flip them over after 1.5s
        delayTimer.start(); //start game timer after cards are flipped.
    }
    // logic to handle when a card is clicked, and check for matches
    private void handleTileClick(JButton tile, int index) {
        if (!gameReady || tile.getIcon() != cardBackImageIcon || card2Selected != null) return;

        if (card1Selected == null) {
            card1Selected = tile;
            card1Selected.setIcon(cardSet.get(index).cardImageIcon);
        } else {
            card2Selected = tile;
            card2Selected.setIcon(cardSet.get(index).cardImageIcon);

            if (card1Selected.getIcon() != card2Selected.getIcon()) {
                errorCount++;
                textLabel.setText("Errors: " + errorCount);
                hideCardTimer.start();
            } else {
                correctCount++;
                card1Selected = null;
                card2Selected = null;
                checkWin(rows * columns / 2);
            }
        }
    }

    // setup the cardSet with the correct number of pairs based on the difficulty level
    void setupCards(int pairCount){
        cardSet = new ArrayList<Card>();

        for (int i = 0; i < pairCount; i++){
            //load each card image
            String cardName = cardList[i];
            Image cardImg = new ImageIcon(getClass().getResource("./img/" + cardName + ".jpg")).getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));

            //create card object and add to cardSet
            Card card = new Card(cardName, cardImageIcon);
            cardSet.add(card);
        }

        cardSet.addAll(cardSet);//double each card

        //load the back card image
        Image cardBackImg= new ImageIcon(getClass().getResource("./img/back.jpg")).getImage();
        cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardWidth, cardHeight, java.awt.Image.SCALE_SMOOTH));
    }
    // shuffle the card 
    void shuffleCards(){
        System.out.println(cardSet);
        //shuffle

        for (int i = 0; i < cardSet.size(); i++){
            int j = (int) (Math.random() * cardSet.size());//get random index
            //swap
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
        System.out.println(cardSet);
    }
    // hide the cards after a delay or flip them all backs if the game is reset
    void hideCards(){
        if(gameReady && card1Selected != null && card2Selected != null){ //2 cards only
            card1Selected.setIcon(cardBackImageIcon);
            card1Selected = null;
            card2Selected.setIcon(cardBackImageIcon);
            card2Selected = null;
        }
        else{//flip all cards
            for (int i = 0; i < board.size(); i++){
                board.get(i).setIcon(cardBackImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
        }
        
    }

    void updateTime(){
        double elapsed = (System.nanoTime() - startTime)/1000000000.0;
        timeLabel.setText(String.format("Time: %.2f s", elapsed));
    }

    // check if the player has won after each correct match
    void checkWin(int pairsNeeded){
        if (correctCount == pairsNeeded){
            gameReady = false;
            gameTimer.stop();
            
            double finalTime = (System.nanoTime() - startTime)/1000000000.0;
            timeLabel.setText(String.format("Final Time: %.2f s", finalTime));
            textLabel.setText("You win! Errors: " + errorCount);
        }
    }

    public JButton getmainmenuButton() {
        return mainmenu;
    }



    
}
