package components;

import processing.core.PApplet;
import java.io.*;

public class Mechanic extends Text {

    private int score;
    private int level;
    private int combo;
    private int lines;
    private int gainUp;

    public Mechanic(PApplet sketch) {
        super(sketch);
    }

    public void render(int score, int level, int combo, int lines, int gainUp) {
        this.score = score;
        this.level = level;
        this.combo = combo;
        this.lines = lines;
        this.gainUp = gainUp;

        this.sketch.fill(209, 204, 192);
        this.sketch.rect(20, 30, 130, 240, 5);
        this.sketch.fill(0);
        this.sketch.textSize(16);
        this.sketch.textAlign(PApplet.LEFT, PApplet.TOP);
        this.sketch.text("Score: " + score, 40, 60);
        this.sketch.text("Level: " + level, 40, 100);
        this.sketch.text("Combo: " + combo, 40, 140);
        this.sketch.text("Lines: " + lines, 40, 180);
        this.sketch.text("Gain Up: " + gainUp, 40, 220);
    }

    public static String[][] read() {
        String[][] output = new String[][]{{"noob","50"}};

        try {
            File file = new File("cache/leaderboard.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
                lineCount++;
            }
            br.close();

            if (lineCount > 0) {
                String[] lines = content.toString().split("\n");
                output = new String[lineCount][2];

                for (int i = 0; i < lineCount; i++) {
                    lines[i] = lines[i].trim();
                    output[i] = lines[i].split(" ");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    private static void write(String[][] data) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cache/leaderboard.txt"));
            for (String[] entry : data) {
                bw.write(entry[0] + " " + entry[1]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(int score) {
        save(score, "unknown");
    }

    public static void save(int score, String name) {

        filterTopTen();

        String[][] scores = read();
        String[] newScore = { name, Integer.toString(score) };

        String[][] newScores = new String[scores.length + 1][2];
        System.arraycopy(scores, 0, newScores, 0, scores.length);
        newScores[scores.length] = newScore;

        write(newScores);
    }

    public static void sortByScore() {
        String[][] scores = read();

        for (int i = 0; i < scores.length; i++) {
            for (int j = i + 1; j < scores.length; j++) {
                if (Integer.parseInt(scores[i][1]) < Integer.parseInt(scores[j][1])) {
                    String[] temp = scores[i];
                    scores[i] = scores[j];
                    scores[j] = temp;
                }
            }
        }

        write(scores);
    }

    public static void filterTopTen() {
        
        sortByScore();
        
        String[][] scores = read();

        if (scores.length > 10) {
            String[][] firstTen = new String[10][2];
            System.arraycopy(scores, 0, firstTen, 0, 10);
            write(firstTen);
        }
        else write(scores);

    }

    public static void updateLastPlayerName(String name) {
        String[][] scores = read();
        if (scores.length > 0) {
            scores[scores.length - 1][0] = name;
            write(scores);
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setGainUp(int gainUp) {
        this.gainUp = gainUp;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    public int getCombo() {
        return this.combo;
    }

    public int getLines() {
        return this.lines;
    }

    public int getGainUp() {
        return this.gainUp;
    }
}
