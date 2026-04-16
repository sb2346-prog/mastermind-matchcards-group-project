package MasterMind.View;

import javax.swing.*;
import java.awt.*;


public class StartScreen extends JPanel{
    private JButton startButton;
    private JButton helpButton;

  

    public StartScreen() {
        // The start screen that is displayed when the game is opened
        setLayout (new GridBagLayout());
        setBackground(new Color(30,30,30));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLable = new JLabel("Welcome to MasterMind");
        titleLable.setFont(new Font("Arial", Font.BOLD, 24));
        titleLable.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,50,0);
        add(titleLable, gbc);

        // Start button to begin the game
        startButton = new JButton("Select Difficulty");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(70,130,180));
        startButton.setFocusPainted(false);
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0,0);
        add(startButton, gbc);

        // Rule button to show the rules of the game
        helpButton = new JButton("Rules");
        helpButton.setFont(new Font("Arial", Font.BOLD, 18));
        helpButton.setBackground(new Color(70,130,180));
        helpButton.setFocusPainted(false);
        gbc.gridy = 2;
        gbc.insets = new Insets(20,0,0,0);
        add(helpButton, gbc);



    }
    
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getHelpButton() {
        return helpButton;
    }


}
