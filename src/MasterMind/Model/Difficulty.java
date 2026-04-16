package MasterMind.Model;

public class Difficulty {
    private int currenTime;
    private final int basetime = 90; 


    public void setDifficultyLevel(String level) {
        switch (level.toLowerCase()) {
            case "easy": this.currenTime = basetime; break;
            case "medium": this.currenTime = (int) (basetime * 0.75); break;
            case "hard": this.currenTime = (int) (basetime * 0.50); break;
            default: this.currenTime = this.basetime;
        }
    }
    public void tick() {
        if (currenTime > 0) {
            currenTime--;
        }
    }
    public int getCurrentTime() {
        return currenTime;
    }
    
}
