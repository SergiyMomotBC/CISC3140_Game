package edu.ci;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sergi on 026, 9/26/2016.
 */
public class LeaderboardManager
{
    public class Entry
    {
        public Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }

        private String   name;
        private int      score;
    }

    public LeaderboardManager()
    {
        entries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));

            String line;
            while((line = reader.readLine()) != null) {
                int pos = line.indexOf(" ");
                String name = line.substring(0, pos - 1);
                int score = Integer.valueOf(line.substring(pos + 1, line.length() - pos - 1));
                entries.add(new Entry(name, score));
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Entry> getEntries()
    {
        return entries;
    }

    public boolean tryAddEntry(String name, int score)
    {
        for(int i = 0; i < entries.size(); i++)
            if(score > entries.get(i).score) {
                entries.get(i).name = name;
                entries.get(i).score = score;
                saveFile();
                return true;
            }

        return false;
    }

    private void saveFile()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt"));

            for(Entry entry : entries) {
                writer.write(entry.getName() + " " + String.valueOf(entry.getScore()));
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Entry> entries;
}
