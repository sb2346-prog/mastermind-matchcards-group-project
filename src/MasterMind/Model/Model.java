package MasterMind.Model;

import java.util.Random;


public class Model {
    // Where the model will generate the secret code and provide it to the controller
    private int [] secretcode;

    public Model() {
        generateNewCode();
    }
    public void generateNewCode() {
        int [] numberSelect = new int[]{0,1,2,3,4,5,6,7,};
        secretcode = new int[4];
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretcode[i] = random.nextInt(numberSelect.length);

        }
        System.out.println("Secret Code: " + secretcode[0] + " " + secretcode[1] + " " + secretcode[2] + " " + secretcode[3]);
    }
    // Provide the secret code when the controller needs it
    public int[] getSecretCode() {
        return secretcode;
    }


    // Method for the timer
    private int timeremaining;
    private final int StartTime = 20; //  20 seconds for each game


    public void resetTimer() {
        this.timeremaining = StartTime;
    }

    public int getTimeremaining() {
        return timeremaining;
    }

    public void decrementTimer() {
        timeremaining--;
    }

    public void setTimeremaining(int seconds) {
    this.timeremaining = seconds;
    }

}

