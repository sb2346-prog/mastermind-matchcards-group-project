package MasterMind.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// The panel for the player to select which game they want to play, either Mastermind or Memory Matching

public class Playgame extends JPanel{
    private JButton mastermindButton;
    private JButton memorymatchingButton;

    public Playgame(ActionListener selectionListener) {
        setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets (10,10,10,10);

        JLabel title = new JLabel ("Brain Games Selection");
        title.setFont (new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        add(title, gbc);

        mastermindButton = new JButton ("Play Mastermind");
        mastermindButton.setActionCommand("Mastermind selected");
        mastermindButton.addActionListener(selectionListener);
        gbc.gridy = 1;
        add(mastermindButton, gbc);

        memorymatchingButton = new JButton ("Play Memory Matching");
        memorymatchingButton.setActionCommand("Memory Matching selected");
        memorymatchingButton.addActionListener(selectionListener);
        gbc.gridy = 2;
        add(memorymatchingButton, gbc);
    }

}
