package MasterMind.Model;


import java.io.Serializable;

public class Score implements java.io.Serializable {
    public String playername;
    public int score;
    public String gamemode;

    public Score (String name, int score, String mode){
        this.playername = name;
        this.score = score;
        this.gamemode = mode;
    }

    
}
