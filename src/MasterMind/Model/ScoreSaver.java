package MasterMind.Model;

import java.io.*;
import java.util.ArrayList;


public class ScoreSaver {
    private static final String FILE_NAME = "Leaderboard.dat";


    public static void saveScore(Score newScore) {
        ArrayList<Score> scores = loadScores();
        scores.add(newScore);


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println("Error Saving Score" + e.getMessage());
        }
    }

    @SuppressWarnings ("unchecked")
    public static ArrayList<Score> loadScores() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (ArrayList<Score> ) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
