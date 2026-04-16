package MasterMind.View;


import javax.swing.*;
import java.awt.*;



public class Difficulty extends JPanel{
    private JButton easyBtn;
    private JButton mediumBtn;
    private JButton hardBtn;
    private JButton backBtn;

    public Difficulty(){
        setLayout (new GridBagLayout());
        setBackground(new Color(30,30,30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;

        // Now the title for the screen
        JLabel title = new JLabel("Select Difficulty");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,50,0);
        add(title, gbc);

        //Reset button insets
        gbc.insets = new Insets(10,10,10,10);

        //Difficulty buttons
        easyBtn = createStyledButton("Easy ");
        gbc.gridy = 1;
        add(easyBtn, gbc);

        mediumBtn = createStyledButton("Medium ");
        gbc.gridy = 2;
        add(mediumBtn, gbc);

        hardBtn = createStyledButton("Hard ");
        gbc.gridy = 3;
        add(hardBtn, gbc);
        

        // Back button to return to the start screen
        backBtn = new JButton ("Back to Start");
        backBtn. setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 4;
        gbc.insets = new Insets(30,10,10,10);
        add(backBtn, gbc);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font ("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(70,130,180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200,40));
        return btn;
    }

    // Getters for the button to add action listeners in the controller
    public JButton getEasyBtn(){
        return easyBtn;
    }
    public JButton getmediumBtn(){
        return mediumBtn;
    }
    public JButton getHardBtn(){
        return hardBtn;
    }
    public JButton getBackBtn() {
        return backBtn;
    }

}
