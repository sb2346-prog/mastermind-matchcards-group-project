package MasterMind.View;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    // The board is responsible for displaying the buttons, and hints for the player
    private JButton[][] slots;
    private Color [] colors = {Color.darkGray, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.LIGHT_GRAY};
    private JPanel[][] hints;
    // Constructor to create the board with buttons and hint panels
    public Board(){
        setLayout(new GridLayout(10,5));
        slots = new JButton[10][4];
        hints = new JPanel[10][4];
        // Loop that creats the buttons and hints panels for the board
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 5; c++) {
                // Create the button for the player to guess
                if (c < 4) {
                    slots[r][c] = new JButton();
                    slots[r][c].setPreferredSize(new Dimension(40,40));
                    slots[r][c].setBackground(Color.LIGHT_GRAY);
                    slots[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Clicking function for the button, to change the color of the button when clicked
                    int currentRow = r;
                    int currentCol = c;
                    slots[r][c].addActionListener(e -> cycleColor(currentRow, currentCol));
                    add(slots[r][c]);
                } else {
                    JPanel hintPanel = new JPanel(new GridLayout(2,2));
                    hintPanel.setPreferredSize(new Dimension (40,40));
                    hintPanel.setOpaque(true);
                    // Create the hint panels for the player to see how many black and white pegs they have
                    for (int i = 0; i < 4; i++) {
                        hints[r][i] = new JPanel();
                        hints[r][i].setOpaque(true);


                        hints[r][i].setBackground(Color.LIGHT_GRAY);
                        hints[r][i].setPreferredSize(new Dimension(15,15));
                        hints[r][i].setBorder(BorderFactory.createLineBorder(Color.RED));
                        hintPanel.add(hints[r][i]);
                    }
                    add(hintPanel);
                }
            }
        }
    }

    // Method to update the hints based on the player's guess
    public void updateHints(int row, int blackPegs, int whitePegs) {
        System.out.println("Row " + row + " received - Black: " + blackPegs + ", White: " + whitePegs);
        // Reset hint colors before updating
        for (int i = 0; i < 4; i++) {
            hints[row][i].setBackground(Color.LIGHT_GRAY);
            hints[row][i].repaint();
        }
        int currentPeg = 0;
        // Set the hint colors based on number of black and white pegs
        for (int i = 0; i < blackPegs; i++) {
            if (currentPeg < 4) {
              
                hints[row][currentPeg].setBackground(Color.BLACK);
                hints[row][currentPeg].repaint();
                currentPeg++;
            }
        }
        for (int i = 0; i < whitePegs; i++) {
            if (currentPeg < 4) {
                
                hints[row][currentPeg].setBackground(Color.WHITE);
                hints[row][currentPeg].repaint();
                currentPeg++;
            }
        }
        this.revalidate();
        this.repaint();
    }
    // Method to cycle through the colors when player clicks on the button
    private void cycleColor(int r, int c) {
        Color currentColor = slots[r][c].getBackground();
        int nextColorIndex = 0;

        //Show the color that is being pick by the player
        for (int i = 0; i < colors.length; i++) {
            if (currentColor.equals(colors[i]))  {
                nextColorIndex = (i + 1) % colors.length;
                break;
            }
        }
        slots[r][c].setBackground(colors[nextColorIndex]);
    }
    //Allows the main controller to get the color of the button being clicked by the player
    public int[] getCurrentGuess(int row) {
        int[] guess = new int [4];    
        for (int c = 0; c < 4; c++) {
            Color bgColor = slots[row][c].getBackground();
            for (int i = 0; i < colors.length; i++) {
                if (bgColor.equals(colors[i])) {
                    guess[c] =i;
                }
            }
        }
           return guess;
    }
    // Method to set the color of the button based on players guess
    public void setSlotColor(int row, int col, Color color) {
        slots[row][col].setBackground(color);
        slots[row][col].repaint();
    }
    // Method to reset the board
    public void resetBoard() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 4; c++) {
                slots[r][c].setBackground(Color.LIGHT_GRAY);
                slots[r][c].repaint();
            }
        }
        // Reset hints
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 4; c++) {
                hints[r][c].setBackground(Color.LIGHT_GRAY);
                hints[r][c].repaint();
            }
        }
        revalidate();
        repaint();
    }
}