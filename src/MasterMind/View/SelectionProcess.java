package MasterMind.View;

import javax.swing.*;
import java.awt.*;

import MasterMind.Model.Model; 
import MasterMind.Controller.MastermindController; 
import MasterMind.View.UserInterface;
import MasterMind.View.MatchCards;
import MasterMind.View.Difficulty;


public class SelectionProcess extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel container = new JPanel(cardLayout);

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
        SwingUtilities.invokeLater(() -> new SelectionProcess());
    }


    private void startMemoryGame(int level) {
    memoryMatchPanel.startnewgame(level);
    cardLayout.show(container, "MemoryMatch");
    pack();
}

}
