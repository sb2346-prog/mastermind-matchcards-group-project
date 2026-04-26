package MasterMind.Model;

import java.io.*;
import java.util.ArrayList;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.SQLException; 
import java.sql.ResultSet;
import java.sql.Statement;



public class ScoreSaver {
    private static final String URL = "jdbc:sqlite:player_data.db";

    // Initialize the table if it doesn't exist (Runs once at startup)
    public static void initializeDatabase() {


        String createSql = "CREATE TABLE IF NOT EXISTS stat_table ("
                         + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                         + " name TEXT NOT NULL,"
                         + " score INTEGER NOT NULL,"
                         + " mode TEXT NOT NULL"
                         + ");";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createSql);
        } catch (SQLException e) {
            System.err.println("DB Init Error: " + e.getMessage());
        }
    }

    public static void saveScore(Score newScore) {
        String sql = "INSERT INTO stat_table(name, score, mode) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newScore.getPlayerName());
            pstmt.setInt(2, newScore.getScore());
            pstmt.setString(3, newScore.getGameMode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Save Error: " + e.getMessage());
        }
    }

    public static ArrayList<Score> loadScores() {
        ArrayList<Score> scores = new ArrayList<>();
        // Note: ORDER BY score DESC puts the highest scores at the top!
        String sql = "SELECT name, score, mode FROM stat_table ORDER BY score DESC";
        
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                scores.add(new Score(rs.getString("name"), rs.getInt("score"), rs.getString("mode")));
            }
        } catch (SQLException e) {
            System.err.println("Load Error: " + e.getMessage());
        }
        return scores;
    }
}
