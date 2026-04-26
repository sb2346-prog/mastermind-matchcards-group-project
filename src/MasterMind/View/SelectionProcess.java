package MasterMind.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import MasterMind.Model.Model; 
import MasterMind.Controller.MastermindController; 
import MasterMind.View.UserInterface;
import MasterMind.View.MatchCards;
import MasterMind.View.Difficulty;


public class SelectionProcess extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel container = new JPanel(cardLayout);


    JButton leaderboardBtn = new JButton("View Leaderboard");

    private Playgame selectionPanel;
    private MatchCards memoryMatchPanel;
    private UserInterface mastermindView;
    private UserInterface mastermindPanel;
    private Difficulty memoryDifficulty;

    public SelectionProcess(){

        memoryDifficulty = new Difficulty();


        setTitle("Brain Game Suite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);

        JPanel buttonWrapper = new JPanel(); 
        buttonWrapper.add(leaderboardBtn);
    
        leaderboardBtn.addActionListener(e -> showLeaderboard());

        selectionPanel = new Playgame(e-> handleSelection(e.getActionCommand()));
        memoryMatchPanel = new MatchCards();

        mastermindView = new UserInterface();
        mastermindPanel = new UserInterface();

        
        Model mmModel = new Model();
        MastermindController mmController = new MastermindController(mmModel, mastermindView);


        container.add(selectionPanel, "Menu");
        container.add(memoryMatchPanel, "MemoryMatch");
        container.add(mastermindView, "Mastermind");
        container.add(memoryDifficulty, "MemoryDifficulty");
        
        

        memoryDifficulty.getEasyBtn().addActionListener(e -> startMemoryGame(1));
        memoryDifficulty.getmediumBtn().addActionListener(e -> startMemoryGame(2));
        memoryDifficulty.getHardBtn().addActionListener(e -> startMemoryGame(3));


        memoryMatchPanel.getmainmenuButton().addActionListener(e -> cardLayout.show(container, "Menu"));
        mastermindView.getExitButton().addActionListener(e -> cardLayout.show(container, "Menu"));
        memoryDifficulty.getBackBtn().addActionListener(e -> cardLayout.show(container, "Menu"));



        selectionPanel.add(leaderboardBtn);

        cardLayout = (CardLayout) container.getLayout();

        add(container);
        cardLayout.show(container, "Menu");

        setLocationRelativeTo(null);
        setVisible(true);

        container.setPreferredSize(new Dimension(800,600));

    }
    private void handleSelection(String command){
        System.out.println("Selection made: " + command);

        if (command.equalsIgnoreCase("Memory Matching selected")){
            cardLayout.show(container, "MemoryDifficulty");
  
            pack();

            revalidate();
            repaint();
           


            setLocationRelativeTo(null);

        } else if (command.equalsIgnoreCase("Mastermind selected")){
            System.out.println("Mastermind starting...");
            cardLayout.show(container, "Mastermind");
            
        }
    }
    public static void main(String[] args){
        try {
        // 1. Force the driver to load (The Fix for the "No Suitable Driver" error)
        Class.forName("org.sqlite.JDBC");
        
        // 2. Initialize the table
        MasterMind.Model.ScoreSaver.initializeDatabase();
        
        // 3. Launch the GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SelectionProcess(); 
        });
        
    } catch (ClassNotFoundException e) {
        System.err.println("Database Driver missing! Check Referenced Libraries.");
    }
    
    }


    private void startMemoryGame(int level) {
    memoryMatchPanel.startnewgame(level);
    cardLayout.show(container, "MemoryMatch");
    pack();
    }


    private void showLeaderboard() {
        ArrayList<MasterMind.Model.Score> scores = MasterMind.Model.ScoreSaver.loadScores();

        if (scores.isEmpty()){
            JOptionPane.showMessageDialog(this, "Now Scores yet play a game first");
            return;
        }
        scores.sort((s1,s2) -> Integer.compare(s1.score, s2.score));

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-10s %-15s\n", "Player", "Score", "Game"));
        sb.append("");

        for (MasterMind.Model.Score s :scores ) {
            sb.append(String.format("%-15s %-10d %-15s\n", s.playername, s.score, s.gamemode));  
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 400));


        JOptionPane.showMessageDialog(this, scrollPane, "High Scores", JOptionPane.INFORMATION_MESSAGE);
    }
        

}
