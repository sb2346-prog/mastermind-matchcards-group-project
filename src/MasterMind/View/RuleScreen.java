package MasterMind.View;

import javax.swing.*;
import java.awt.*;

public class RuleScreen extends JPanel{
    private JButton backButton;

    public RuleScreen() {
        setLayout(new BorderLayout(20,20));
        setBackground(new Color(30,30,30));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel title = new JLabel("MasterMind Rules");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        JTextArea rulesText = new JTextArea();
        rulesText.setText(
                "1. The computer generates a secret code consisting of 4 colored pegs.\n"
                + "2. The player has 10 attempts to guess the secret code.\n" 
                + "3. After each guess, the player receives feedback in the form of black and white pegs:\n"
                + "  - A black peg shows a correct guess \n"
                + "  - A white peg shows a correct color in the wrong spot\n"
                + "4. The games goes until the player has guessed the code or used all of their attempts\n"
                + "5. If the player guesses the code, they win!\n" 
                + "6. If the player uses all their attempts, they lose the game and the code is revealed.\n"
                + "7. The player can choose to play again after winning or losing the game.\n"
        );

        rulesText.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesText.setForeground(Color.WHITE);
        rulesText.setBackground(new Color(30,30,30));
        rulesText.setEditable(false);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        add(rulesText, BorderLayout.CENTER);

        backButton = new JButton("Back to Start");
        backButton.setPreferredSize(new Dimension(50,30));
        add(backButton, BorderLayout.SOUTH);
    }
    public JButton getBackButton() {
        return backButton;
    }
}

